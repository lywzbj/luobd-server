package com.luobd.server.base.roles.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@ApiModel(description = "设置账号角色表单")
public class SetAccountRolesInput {

    @ApiModelProperty(value = "账号id",required = true)
    @NotNull(message = "账号id不能为空")
    private Long accountId;

    @ApiModelProperty(value = "角色id列表")
    private Set<Long> roleIds;


}
