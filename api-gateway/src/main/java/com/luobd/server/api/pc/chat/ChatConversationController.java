package com.luobd.server.api.pc.chat;


import com.luobd.server.chat.entity.ChatConversation;
import com.luobd.server.chat.input.ChatConversationInput;
import com.luobd.server.chat.service.IChatConversationService;
import com.luobd.server.common.entities.CurrentRequestHolder;
import com.luobd.server.common.entities.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/chat/conversation")
@Api(tags = "会话模块")
public class ChatConversationController {


    @Resource
    private IChatConversationService chatConversationService;



    @PostMapping(value = "/create")
    @ApiOperation(value = "新建会话")
    public ResponseData<Long> create(@RequestBody  ChatConversationInput input) {
       return chatConversationService.create(input);
    }



    @GetMapping(value = "/list")
    @ApiOperation(value = "获取会话列表")
    public ResponseData<List<ChatConversation>> list() {
        Long userInfoId = CurrentRequestHolder.get().getUserInfoId();
        return chatConversationService.listByUserId(userInfoId);
    }




}
