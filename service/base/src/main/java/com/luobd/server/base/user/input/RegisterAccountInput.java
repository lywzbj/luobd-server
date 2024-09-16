package com.luobd.server.base.user.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "注册账号表单")
public class RegisterAccountInput {



    @ApiModelProperty(value = "账号名称",required = true)
    @NotBlank(message = "账号名称不能为空")
    private String accountName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码",required = true)
    private String checkCode;

    @ApiModelProperty(value = "电子邮箱地址")
    @NotBlank(message = "电子邮箱地址不能为空")
    private String email;


}
