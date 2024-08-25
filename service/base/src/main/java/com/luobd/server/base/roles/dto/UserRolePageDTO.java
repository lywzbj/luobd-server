package com.luobd.server.base.roles.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRolePageDTO {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String trueName;

    @ApiModelProperty(value = "用户账号")
    private String userName;


    @ApiModelProperty(value = "角色ID")
    private Long roleId;


    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @ApiModelProperty(value = "角色标识")
    private String roleKey;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
