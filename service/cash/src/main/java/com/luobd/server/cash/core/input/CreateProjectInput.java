package com.luobd.server.cash.core.input;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@ApiModel(description = "创建项目表单")
public class CreateProjectInput {


    @ApiModelProperty(value = "项目名称",required = true)
    private String projectName;


    @ApiModelProperty(value = "项目日期",required = true)
    private LocalDate projectDate;


    @ApiModelProperty(value = "备注")
    private String remark;

}
