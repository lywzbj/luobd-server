package com.luobd.server.base.roles.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@ApiModel(description = "设置角色账号表单")
public class SetRoleAccountsInput {


    @ApiModelProperty(value = "角色id",required = true)
    @NotNull(message = "角色id不能为空")
    private Long roleId;


    @ApiModelProperty(value = "账号id列表",required = true)
    @NotEmpty(message = "账号id列表不能为空")
    private Set<Long> accountIds;

}
