package com.luobd.server.cash.core.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.cash.core.entity.CashItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.cash.core.input.CashItemPageInput;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-05
 */
public interface CashItemMapper extends BaseMapper<CashItem> {



    Page<CashItem> page(Page<CashItem> page, @Param("input") CashItemPageInput input);



}
