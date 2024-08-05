package com.luobd.server.cash.core.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "更新礼金记录")
@Getter
@Setter
public class UpdateCashItemInput extends CreateCashItemInput{


    @ApiModelProperty(value = "礼金id",required = true)
    @NotNull(message = "请指定礼金记录id")
    private Long id;

}
