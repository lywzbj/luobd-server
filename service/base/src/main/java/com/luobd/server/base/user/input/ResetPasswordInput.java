package com.luobd.server.base.user.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "重置密码表单")
public class ResetPasswordInput {

    @ApiModelProperty(value = "账号id",required = true)
    private Long id;

    @ApiModelProperty(value = "新密码",required = true)
    private String newPassword;

    @ApiModelProperty(value = "旧密码",required = true)
    private String oldPassword;

}
