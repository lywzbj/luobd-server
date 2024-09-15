package com.luobd.server.base.roles.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddUserRoleInput {


    @ApiModelProperty(value = "账户id",required = true)
    @NotNull(message = "请指定账户id")
    private Long accountId;

    @ApiModelProperty(value = "角色ID",required = true)
    @NotNull(message = "请指定角色ID")
    private Long roleId;


}
