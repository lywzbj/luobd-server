package com.luobd.server.common.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "下拉框表单")
public class SelectDTO {



    @ApiModelProperty(value = "key")
    private String key;


    @ApiModelProperty(value = "value")
    private String value;





}
