package com.luobd.server.base.roles.input;

import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRolePageInput extends PageInput {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "用户姓名")
    private String trueName;

    @ApiModelProperty(value = "用户账号")
    private String accountName;

}
