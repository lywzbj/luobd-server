package com.luobd.server.api.pc.chatglm;


import cn.bugstack.chatglm.model.*;
import cn.bugstack.chatglm.session.OpenAiSession;
import com.alibaba.fastjson.JSON;
import com.luobd.server.config.IgnoreAuth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/chatglm")
@Api(tags = "ChatGLM")
@CrossOrigin(value = "*")
@Slf4j
public class ChatController {


    @Resource
    private OpenAiSession openAiSession;

    @PostMapping("/conversation")
    @IgnoreAuth
    public ResponseBodyEmitter handleRbe(HttpServletResponse response,@RequestBody @Valid ChatRequestInput input) throws Exception {
        String model = input.getModel();
        List<MessageEntity> messages = new ArrayList<>();
        messages.add(MessageEntity.builder()
                .role("user")
                .content(input.getPrompt())
                .build());
        log.info("流式问答请求开始，使用模型：{} 请求信息：{}", model, JSON.toJSONString(messages));
        try {
            // 1. 基础配置；流式输出、编码、禁用缓存
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");

            // 2. 构建异步响应对象【对 Token 过期拦截】
            ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);

            // 3. 获取 OpenID
            String openid = "1ed747a5e448e91fa29e717c187a982f";
            log.info("流式问答请求处理，openid:{} 请求模型:{}", openid, model);

            // 4. 构建参数
            ChatProcessAggregate chatProcessAggregate = ChatProcessAggregate.builder()
                    .openid(openid)
                    .model(model)
                    .messages(messages.stream()
                            .map(entity -> MessageEntity.builder()
                                    .role(entity.getRole())
                                    .content(entity.getContent())
                                    .name(entity.getName())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();

            // 5. 请求结果&返回
            return completions(emitter, chatProcessAggregate);
        } catch (Exception e) {
            log.error("流式应答，请求模型：{} 发生异常", model, e);
            throw new RuntimeException(e.getMessage());
        }
    }


    public ResponseBodyEmitter completions(ResponseBodyEmitter emitter, ChatProcessAggregate chatProcess) {
        try {
            // 1. 请求应答
            emitter.onCompletion(() -> {
                log.info("流式问答请求完成，使用模型：{}", chatProcess.getModel());
            });
            emitter.onError(throwable -> log.error("流式问答请求异常，使用模型：{}", chatProcess.getModel(), throwable));

            // 4. 应答处理 【ChatGPT、ChatGLM 策略模式】
            doMessageResponse(chatProcess, emitter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 5. 返回结果
        return emitter;
    }

    public void doMessageResponse(ChatProcessAggregate chatProcess, ResponseBodyEmitter emitter) throws Exception {
        if (null == openAiSession) {
            emitter.send("ChatGLM 通道，模型调用未开启，可以选择其他模型对话！");
            return;
        }

        // 1. 请求消息
        List<ChatCompletionRequest.Prompt> prompts = new ArrayList<>();

        List<MessageEntity> messages = chatProcess.getMessages();
        MessageEntity messageEntity = messages.remove(messages.size() - 1);

        for (MessageEntity message : messages) {
            String role = message.getRole();
            if (Objects.equals(role, Role.system.getCode())) {
                prompts.add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.system.getCode())
                        .content(message.getContent())
                        .build());

                prompts.add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("Okay")
                        .build());
            } else {
                prompts.add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content(message.getContent())
                        .build());

                prompts.add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("Okay")
                        .build());
            }
        }

        prompts.add(ChatCompletionRequest.Prompt.builder()
                .role(messageEntity.getRole())
                .content(messageEntity.getContent())
                .build());

        // 2. 封装参数
        ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel(Model.valueOf(ChatGLMModel.get(chatProcess.getModel()).name())); // chatGLM_6b_SSE、chatglm_lite、chatglm_lite_32k、chatglm_std、chatglm_pro
        request.setPrompt(prompts);

        openAiSession.completions(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);

                // 发送信息
                if (EventType.add.getCode().equals(type)) {
                    try {
                        log.info("[输出信息] {}", response.getData());
                        emitter.send(response.getData());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                // type 消息类型，add 增量，finish 结束，error 错误，interrupted 中断
                if (EventType.finish.getCode().equals(type)) {
                    ChatCompletionResponse.Meta meta = JSON.parseObject(response.getMeta(), ChatCompletionResponse.Meta.class);
                    log.info("[输出结束] Tokens {}", JSON.toJSONString(meta));
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                emitter.complete();
            }
        });
    }


    private void end(ResponseBodyEmitter emitter) {
        emitter.complete();
    }


}
