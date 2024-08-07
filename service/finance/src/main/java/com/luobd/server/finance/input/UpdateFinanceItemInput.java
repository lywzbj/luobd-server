package com.luobd.server.finance.input;

import cn.hutool.core.util.StrUtil;
import com.luobd.server.finance.entity.FinanceItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateFinanceItemInput extends CreateFianceItemInput{


    @ApiModelProperty(value = "id",required = true)
    @NotNull(message = "请指定id")
    private Long id;


    @Override
    public void updateEntity(FinanceItem financeItem) {
        if(getOccurTime() != null) {
            financeItem.setOccurTime(getOccurTime());
        }
        if(StrUtil.isNotBlank(getRemark())) {
            financeItem.setRemark(getRemark());
        }
        financeItem.setCategoryId(getCategoryId());
        financeItem.setAmount(getAmount());
    }
}
