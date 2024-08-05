package com.luobd.server.cash.core.input;

import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProjectPageInput extends PageInput {

    @ApiModelProperty(value = "项目名称")
    public String projectName;

    @ApiModelProperty(value = "项目开始日期")
    public LocalDate startProjectDate;

    @ApiModelProperty(value = "项目结束日期")
    public LocalDate endProjectDate;

}
