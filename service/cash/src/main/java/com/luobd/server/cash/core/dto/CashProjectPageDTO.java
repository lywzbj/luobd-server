package com.luobd.server.cash.core.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "礼金项目分页数据")
public class CashProjectPageDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "status")
    private Integer status;

    @ApiModelProperty(value = "createTime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "updateTime")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "deleted")
    private Boolean deleted;

    @ApiModelProperty("projectName")
    private String projectName;

    @ApiModelProperty("projectDate")
    private LocalDate projectDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("createUserId")
    private Long createUserId;

    @ApiModelProperty("updateUserId")
    private Long updateUserId;

    @ApiModelProperty(value = "totalAmount")
    private BigDecimal totalAmount = BigDecimal.ZERO;


}
