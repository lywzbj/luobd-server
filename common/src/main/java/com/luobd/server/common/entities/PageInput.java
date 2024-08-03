package com.luobd.server.common.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "分页查询表单")
public class PageInput {


    @ApiModelProperty(value = "页码",example = "1")
    private Integer pageIndex;


    @ApiModelProperty(value = "每页数量",example = "10")
    private Integer pageSize;




}
