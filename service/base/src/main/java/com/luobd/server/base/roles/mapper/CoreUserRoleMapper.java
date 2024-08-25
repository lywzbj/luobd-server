package com.luobd.server.base.roles.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.roles.dto.UserRolePageDTO;
import com.luobd.server.base.roles.entity.CoreUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.base.roles.input.UserRolePageInput;
import com.luobd.server.common.entities.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-25
 */
public interface CoreUserRoleMapper extends BaseMapper<CoreUserRole> {


    Page<UserRolePageDTO> page(Page<UserRolePageDTO> page, @Param("input") UserRolePageInput input);


    List<Role> getRolesByUserInfoId(@Param("userInfoId") Long userInfoId);


}
