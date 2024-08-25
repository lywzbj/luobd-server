package com.luobd.server.base.roles.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateRoleInput {

    @ApiModelProperty(value = "角色标识",required = true)
    @NotNull(message = "请指定角色标识")
    private String roleKey;

    @ApiModelProperty(value = "角色名称",required = true)
    @NotNull(message = "请指定角色名称")
    private String roleName;


}
