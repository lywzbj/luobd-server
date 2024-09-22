package com.luobd.server.base.roles.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.roles.dto.UserRolePageDTO;
import com.luobd.server.base.roles.entity.CoreUserRole;
import com.luobd.server.base.roles.input.AddUserRoleInput;
import com.luobd.server.base.roles.input.SetAccountRolesInput;
import com.luobd.server.base.roles.input.SetRoleAccountsInput;
import com.luobd.server.base.roles.input.UserRolePageInput;
import com.luobd.server.base.roles.mapper.CoreUserRoleMapper;
import com.luobd.server.base.roles.service.ICoreUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.entities.Role;
import com.luobd.server.common.utils.SnowIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import com.luobd.server.common.entities.ResponseData;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-25
 */
@Service
@Primary
@Slf4j
public class CoreUserRoleServiceImpl extends ServiceImpl<CoreUserRoleMapper, CoreUserRole> implements ICoreUserRoleService {


  @Resource
  private CoreUserRoleMapper baseMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> delete(Long id) {
        final boolean remove = this.removeById(id);
            if(!remove) {
            return ResponseData.error("删除失败");
            }
            return ResponseData.success(Boolean.TRUE);
        }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> batchDelete(List<Long> ids) {
            final boolean remove = this.removeByIds(ids);
            if(!remove) {
            return ResponseData.error("删除失败");
            }
            return ResponseData.success(Boolean.TRUE);
            }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> add(AddUserRoleInput input) {
        QueryWrapper<CoreUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accountId",input.getAccountId());
        queryWrapper.eq("roleId",input.getRoleId());
        if(this.count(queryWrapper) > 0) {
            log.info("账户:{}已存在角色:{},直接返回",input.getAccountId(),input.getRoleId());
            return ResponseData.success(Boolean.TRUE);
        }
        CoreUserRole coreUserRole = new CoreUserRole();
        coreUserRole.setId(SnowIdWorker.nextId());
        coreUserRole.setAccountId(input.getAccountId());
        coreUserRole.setRoleId(input.getRoleId());
        if(this.save(coreUserRole)) {
            return ResponseData.success(Boolean.TRUE);
        }
        return ResponseData.error("添加失败");
    }

    @Override
    public ResponsePageData<UserRolePageDTO> page(UserRolePageInput input) {
        Page<UserRolePageDTO> page = baseMapper.page(new Page<>(input.getPageIndex(), input.getPageSize()), input);
        return ResponsePageData.success(page.getRecords(),page.getTotal());
    }

    @Override
    public List<Role> getRolesByAccountId(Long accountId) {
        return baseMapper.getRolesByAccountId(accountId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> setAccountRoles(SetAccountRolesInput entity) {
        this.remove(new QueryWrapper<CoreUserRole>().eq("accountId",entity.getAccountId()));
        List<CoreUserRole> coreUserRoles = entity.getRoleIds().stream().map(roleId -> {
            CoreUserRole coreUserRole = new CoreUserRole();
            coreUserRole.setId(SnowIdWorker.nextId());
            coreUserRole.setId(SnowIdWorker.nextId());
            coreUserRole.setAccountId(entity.getAccountId());
            coreUserRole.setRoleId(roleId);
            return coreUserRole;
        }).collect(Collectors.toList());
        this.saveBatch(coreUserRoles);
        return ResponseData.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> setRoleAccounts(SetRoleAccountsInput entity) {
        QueryWrapper<CoreUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleId",entity.getRoleId());
        queryWrapper.in("accountId",entity.getAccountIds());
        int count = this.count(queryWrapper);
        if(count > 0) {
            log.error("角色:{}已存在账户:{},直接返回",entity.getRoleId(),entity.getAccountIds());
            return ResponseData.error("重复添加");
        }
        List<CoreUserRole> coreUserRoles = entity.getAccountIds().stream().map(accountId -> {
            CoreUserRole coreUserRole = new CoreUserRole();
            coreUserRole.setId(SnowIdWorker.nextId());
            coreUserRole.setAccountId(accountId);
            coreUserRole.setRoleId(entity.getRoleId());
            return coreUserRole;
        }).collect(Collectors.toList());
        this.saveBatch(coreUserRoles);
        return ResponseData.success(Boolean.TRUE);
    }


}
