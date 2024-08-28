package com.luobd.server.chat.input;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "会话")
public class ChatConversationInput {


    @ApiModelProperty(value = "会话ID")
    private Long id;


    @ApiModelProperty(value = "会话名称")
    private String conversationName;



}
