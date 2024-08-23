package com.luobd.server.cash.core.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.cash.core.dto.CashProjectPageDTO;
import com.luobd.server.cash.core.entity.CashProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.cash.core.input.ProjectPageInput;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-05
 */
public interface CashProjectMapper extends BaseMapper<CashProject> {


    Page<CashProjectPageDTO> page(Page<CashProjectPageDTO> page, @Param("input") ProjectPageInput input);


}
