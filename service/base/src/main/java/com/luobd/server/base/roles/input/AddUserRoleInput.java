package com.luobd.server.base.roles.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddUserRoleInput {


    @ApiModelProperty(value = "用户ID",required = true)
    @NotNull(message = "请指定用户ID")
    private Long userInfoId;

    @ApiModelProperty(value = "角色ID",required = true)
    @NotNull(message = "请指定角色ID")
    private Long roleId;


}
