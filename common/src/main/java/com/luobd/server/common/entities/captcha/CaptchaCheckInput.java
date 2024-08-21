package com.luobd.server.common.entities.captcha;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "验证码校验")
@Data
public class CaptchaCheckInput {


    @ApiModelProperty(value = "验证码id",required = true)
    @NotBlank(message = "请输入验证码id")
    private String id;

    @ApiModelProperty(value = "验证码",required = true)
    @NotBlank(message = "请输入验证码")
    private String code;

}
