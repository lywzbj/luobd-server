package com.luobd.server.finance.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.finance.entity.FinanceItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.finance.input.FinancePageInput;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-07
 */
public interface FinanceItemMapper extends BaseMapper<FinanceItem> {

    Page<FinanceItem> page(Page<FinanceItem> page, @Param("input") FinancePageInput input);
}
