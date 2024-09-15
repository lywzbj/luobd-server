package com.luobd.server.base.roles.service;

import com.luobd.server.base.roles.dto.UserRolePageDTO;
import com.luobd.server.base.roles.entity.CoreUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.base.roles.input.AddUserRoleInput;
import com.luobd.server.base.roles.input.UserRolePageInput;
import com.luobd.server.base.roles.input.SetAccountRolesInput;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.entities.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-25
 */
public interface ICoreUserRoleService extends IService<CoreUserRole> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);


  ResponseData<Boolean> add(AddUserRoleInput input);


  ResponsePageData<UserRolePageDTO> page(UserRolePageInput input);



  List<Role> getRolesByAccountId(Long userId);


  ResponseData<Boolean> setAccountRoles(SetAccountRolesInput entity);







}
