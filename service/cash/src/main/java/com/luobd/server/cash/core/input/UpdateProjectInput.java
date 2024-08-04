package com.luobd.server.cash.core.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "更新项目表单")
public class UpdateProjectInput extends CreateProjectInput {


    @ApiModelProperty(value = "id值",required = true)
    private Long id;
}
