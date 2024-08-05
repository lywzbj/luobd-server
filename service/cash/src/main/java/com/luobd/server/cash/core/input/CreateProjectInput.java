package com.luobd.server.cash.core.input;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@ApiModel(description = "创建项目表单")
public class CreateProjectInput {


    @ApiModelProperty(value = "项目名称",required = true)
    @NotBlank(message = "请输入项目名称")
    private String projectName;


    @ApiModelProperty(value = "项目日期",required = true)
    @NotNull(message = "请指定项目日期")
    private LocalDate projectDate;


    @ApiModelProperty(value = "备注")
    @Length(max = 200,message = "备注信息最多输入200个字符")
    private String remark;

}
