package com.luobd.server.cash.core.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateCashItemInput {


    @ApiModelProperty(value = "项目Id",required = true)
    @NotNull(message = "项目id不能为空")
    private Long projectId;

    @ApiModelProperty(value = "礼金人员",required = true)
    @NotBlank(message = "请输入礼金人员")
    private String cashUserName;

    @ApiModelProperty(value = "金额",example = "100.0",required = true)
    @NotNull(message = "请指定礼金金额")
    @DecimalMin(value = "0.0",message = "礼金金额不能为负数")
    private BigDecimal amount;

    @ApiModelProperty(value = "备注")
    private String remark;


}
