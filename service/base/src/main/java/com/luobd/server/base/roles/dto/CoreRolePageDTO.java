package com.luobd.server.base.roles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "角色信息")
public class CoreRolePageDTO {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色key")
    private String roleKey;

    @ApiModelProperty(value = "是否默认")
    private Boolean defaulted;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户数量")
    private Integer userCount;


}
