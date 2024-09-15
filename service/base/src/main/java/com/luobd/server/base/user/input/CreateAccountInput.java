package com.luobd.server.base.user.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@ApiModel(value="创建账号表单")
public class CreateAccountInput {


    @ApiModelProperty(value = "账号名称",required = true)
    @NotBlank(message = "账号名称不能为空")
    private String accountName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "电子邮箱地址")
    @NotBlank(message = "电子邮箱地址不能为空")
    private String email;

    @ApiModelProperty(value = "角色id列表")
    private Set<Long> roleIds;


}
