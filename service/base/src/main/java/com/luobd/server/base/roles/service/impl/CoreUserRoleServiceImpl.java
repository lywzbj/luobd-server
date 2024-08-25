package com.luobd.server.base.roles.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.roles.dto.UserRolePageDTO;
import com.luobd.server.base.roles.entity.CoreUserRole;
import com.luobd.server.base.roles.input.AddUserRoleInput;
import com.luobd.server.base.roles.input.UserRolePageInput;
import com.luobd.server.base.roles.mapper.CoreUserRoleMapper;
import com.luobd.server.base.roles.service.ICoreUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.entities.Role;
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
        queryWrapper.eq("userInfoId",input.getUserInfoId());
        queryWrapper.eq("roleId",input.getRoleId());
        if(this.count(queryWrapper) > 0) {
            return ResponseData.error("该用户已拥有该角色");
        }
        CoreUserRole coreUserRole = new CoreUserRole();
        coreUserRole.setId(SnowIdWorker.nextId());
        coreUserRole.setUserInfoId(input.getUserInfoId());
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
    public List<Role> getRolesByUserId(Long userId) {
        return baseMapper.getRolesByUserInfoId(userId);
    }


}
