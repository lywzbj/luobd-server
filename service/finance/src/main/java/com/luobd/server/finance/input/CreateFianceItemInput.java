package com.luobd.server.finance.input;

import com.luobd.server.common.utils.SnowIdWorker;
import com.luobd.server.finance.entity.FinanceItem;
import com.luobd.server.mybatis.entities.BaseInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class CreateFianceItemInput implements BaseInput<FinanceItem> {

    @ApiModelProperty(value = "分类id",required = true)
    @NotNull(message = "请指定分类")
    private Long categoryId;

    @ApiModelProperty(value = "发生时间")
    private LocalDateTime occurTime;

    @ApiModelProperty(value = "金额",required = true)
    @NotNull(message = "请指定金额")
    private BigDecimal amount;


    @ApiModelProperty(value = "备注")
    private String remark;


    @Override
    public FinanceItem toEntity() {
        FinanceItem financeItem = new FinanceItem();
        financeItem.setId(SnowIdWorker.nextId());
        financeItem.setAmount(amount);
        financeItem.setRemark(remark);
        financeItem.setOccurTime(occurTime == null ? LocalDateTime.now() : occurTime);
        return financeItem;
    }
}
