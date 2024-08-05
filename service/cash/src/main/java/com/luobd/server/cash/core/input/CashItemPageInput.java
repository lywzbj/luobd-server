package com.luobd.server.cash.core.input;

import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class CashItemPageInput extends PageInput {


    @ApiModelProperty(value = "项目Id")
    @NotNull(message = "项目id不能为空")
    private Long projectId;

    @ApiModelProperty(value = "礼金人员")
    @NotBlank(message = "请输入礼金人员")
    private String cashUserName;

    @ApiModelProperty(value = "项目名称")
    public String projectName;

    @ApiModelProperty(value = "项目开始日期")
    public LocalDate startProjectDate;

    @ApiModelProperty(value = "项目结束日期")
    public LocalDate endProjectDate;




}
