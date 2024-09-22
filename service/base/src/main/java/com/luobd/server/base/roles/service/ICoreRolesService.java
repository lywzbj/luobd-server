package com.luobd.server.base.roles.service;

import com.luobd.server.base.roles.dto.CoreRolePageDTO;
import com.luobd.server.base.roles.entity.CoreRoles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.base.roles.input.CreateRoleInput;
import com.luobd.server.base.roles.input.RolePageInput;
import com.luobd.server.common.entities.PageInput;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-25
 */
public interface ICoreRolesService extends IService<CoreRoles> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);



  ResponsePageData<CoreRolePageDTO> page(RolePageInput pageInput);


  ResponseData<Long> create(CreateRoleInput input);



  Long getDefaultRoleId(String key);






}
