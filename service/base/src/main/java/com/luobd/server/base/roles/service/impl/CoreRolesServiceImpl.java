package com.luobd.server.base.roles.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.roles.entity.CoreRoles;
import com.luobd.server.base.roles.input.CreateRoleInput;
import com.luobd.server.base.roles.mapper.CoreRolesMapper;
import com.luobd.server.base.roles.service.ICoreRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.common.entities.PageInput;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.utils.SnowIdWorker;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
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
public class CoreRolesServiceImpl extends ServiceImpl<CoreRolesMapper, CoreRoles> implements ICoreRolesService {


  @Resource
  private CoreRolesMapper baseMapper;


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
    public ResponsePageData<CoreRoles> page(PageInput pageInput) {
        QueryWrapper<CoreRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createTime");
        Page<CoreRoles> page = this.page(new Page<>(pageInput.getPageIndex(), pageInput.getPageSize()), queryWrapper);
        return ResponsePageData.success(page.getRecords(),page.getTotal());
    }

    @Override
    public ResponseData<Long> create(CreateRoleInput input) {
        QueryWrapper<CoreRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleKey",input.getRoleKey());
        if (this.count(queryWrapper) > 0) {
            return ResponseData.error("角色标识已存在");
        }
        CoreRoles coreRoles = new CoreRoles();
        coreRoles.setId(SnowIdWorker.nextId());
        coreRoles.setRoleKey(input.getRoleKey());
        coreRoles.setRoleName(input.getRoleName());
        if (this.save(coreRoles)) {
            return ResponseData.success(coreRoles.getId());
        }
        return ResponseData.error("创建失败");
    }


}
