package com.luobd.server.base.user.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "账号用户信息分页查询结果")
@Data
public class AccountUserInfoPageDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户信息表主键")
    private Long userInfoId;

    @ApiModelProperty(value = "账号名称")
    private String accountName;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "电子邮箱地址")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
