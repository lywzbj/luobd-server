package com.luobd.server.base.roles.input;

import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePageInput extends PageInput {

    @ApiModelProperty(value = "角色名/角色key搜索")
    private String searchInfo;

    @ApiModelProperty(value = "是否默认")
    private Boolean defaulted;


}
