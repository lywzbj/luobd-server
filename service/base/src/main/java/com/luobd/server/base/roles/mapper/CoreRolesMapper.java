package com.luobd.server.base.roles.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.roles.dto.CoreRolePageDTO;
import com.luobd.server.base.roles.entity.CoreRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobd.server.base.roles.input.RolePageInput;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-25
 */
public interface CoreRolesMapper extends BaseMapper<CoreRoles> {


    Page<CoreRolePageDTO> page(Page<CoreRolePageDTO> page, @Param("input") RolePageInput input);
}
