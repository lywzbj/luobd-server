package com.luobd.server.base.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.user.dto.AccountUserInfoPageDTO;
import com.luobd.server.base.user.entity.CoreAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.base.user.input.AccountUserInfoPageInput;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-04
 */
public interface CoreAccountMapper extends BaseMapper<CoreAccount> {


    Page<AccountUserInfoPageDTO> page(Page<AccountUserInfoPageDTO> page, @Param("input") AccountUserInfoPageInput input);

}
