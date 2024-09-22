package com.luobd.server.base.user.input;

import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountUserInfoPageInput extends PageInput {


    @ApiModelProperty(value = "搜索信息")
    private String searchInfo;

    @ApiModelProperty(value = "账号名称")
    private String accountName;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;






}
