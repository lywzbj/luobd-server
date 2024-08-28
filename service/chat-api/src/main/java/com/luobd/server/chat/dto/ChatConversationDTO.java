package com.luobd.server.chat.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatConversationDTO {

    private Long id;


    private String conversationName;


    private String remark;

    private Long createUserId;

    private Long updateUserId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
