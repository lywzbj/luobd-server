package com.luobd.server.common.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(description = "当前用户信息")
public class CurrentUserInfo {


    @ApiModelProperty(value = "用户信息id")
    private Long userInfoId;

    @ApiModelProperty(value = "账户id")
    private Long accountId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "真名")
    private String trueName;

    @ApiModelProperty(value = "角色")
    private List<Role> roles;




}
