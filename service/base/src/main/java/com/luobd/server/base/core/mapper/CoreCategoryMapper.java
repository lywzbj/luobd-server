package com.luobd.server.base.core.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.core.dto.CoreCategoryDTO;
import com.luobd.server.base.core.entity.CoreCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.base.core.input.CategoryPageInput;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-05
 */
public interface CoreCategoryMapper extends BaseMapper<CoreCategory> {


    Page<CoreCategoryDTO> page(Page<CoreCategoryDTO> page, @Param("input") CategoryPageInput input);


}
