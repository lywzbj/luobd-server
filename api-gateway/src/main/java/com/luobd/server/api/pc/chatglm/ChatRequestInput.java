package com.luobd.server.api.pc.chatglm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "ChatGLM请求参数")
public class ChatRequestInput {


    @ApiModelProperty(value = "请求内容",required = true)
    @NotBlank(message = "请求内容不能为空")
    private String prompt;


    @ApiModelProperty(value = "模型名称",required = true)
    @NotBlank(message = "模型名称不能为空")
    private String model;


}
