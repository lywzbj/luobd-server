package com.luobd.server.cash.core.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "分页查询项目表单")
public class ProjectPageInput extends PageInput {

    @ApiModelProperty(value = "项目名称")
    public String projectName;

    @ApiModelProperty(value = "项目开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate startProjectDate;

    @ApiModelProperty(value = "项目结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate endProjectDate;


}


