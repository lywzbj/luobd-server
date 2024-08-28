package com.luobd.server.api.pc.chatglm;


import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/api/chatglm")
@Api(tags = "ChatGLM")
@CrossOrigin(value = "*")
public class ChatController {
    private ExecutorService executor
            = Executors.newCachedThreadPool();
    @GetMapping("/conversation")
    public SseEmitter handleRbe(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        SseEmitter emitter = new SseEmitter();
        AtomicInteger count = new AtomicInteger(0);
        executor.execute(() -> {
            try {
                while (count.get() < 10) {
                    Thread.sleep(100);
                    emitter.send(UUID.randomUUID().toString());
                    System.out.println("send");
                    count.incrementAndGet();
                }
            }catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
