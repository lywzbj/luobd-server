package com.luobd.server.finance.input;

import com.luobd.server.common.entities.PageInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
public class FinancePageInput extends PageInput {

    @ApiModelProperty(value = "分类")
    private String categoryName;

    @ApiModelProperty(value = "发生时间 - 开始")
    private LocalDate occurStartDate;

    @ApiModelProperty(value = "发生时间 - 结束")
    private LocalDate occurEndDate;


    @ApiModelProperty(value = "发生时间 - 开始",hidden = true)
    private LocalDateTime occurStartTime;


    @ApiModelProperty(value = "发生时间 - 结束",hidden = true)
    private LocalDateTime occurEndTime;

    public void setOccurStartDate(LocalDate occurStartDate) {
        this.occurStartDate = occurStartDate;
        if (occurStartDate != null) {
            this.occurStartTime = LocalDateTime.of(occurStartDate, LocalDateTime.MIN.toLocalTime());
        }
    }


    public void setOccurEndDate(LocalDate occurEndDate) {
        this.occurEndDate = occurEndDate;
        if (occurEndDate != null) {
            this.occurEndTime = LocalDateTime.of(occurEndDate, LocalDateTime.MAX.toLocalTime());
        }
    }
}
