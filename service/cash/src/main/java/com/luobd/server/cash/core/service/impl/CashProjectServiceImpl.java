package com.luobd.server.cash.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.cash.core.entity.CashProject;
import com.luobd.server.cash.core.input.CreateProjectInput;
import com.luobd.server.cash.core.input.ProjectPageInput;
import com.luobd.server.cash.core.input.UpdateProjectInput;
import com.luobd.server.cash.core.mapper.CashProjectMapper;
import com.luobd.server.cash.core.service.ICashProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2024-08-05
 */
@Service
@Primary
public class CashProjectServiceImpl extends ServiceImpl<CashProjectMapper, CashProject> implements ICashProjectService {


  @Resource
  private CashProjectMapper baseMapper;


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
    public ResponseData<Long> create(CreateProjectInput input) {
        QueryWrapper<CashProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("projectName",input.getProjectName());
        int count = this.count(queryWrapper);
        if(count > 0) {
            return ResponseData.error("项目名称已存在");
        }
        CashProject project = new CashProject();
        project.setId(SnowIdWorker.nextId());
        project.setProjectName(input.getProjectName());
        project.setProjectDate(input.getProjectDate());
        project.setRemark(input.getRemark());
        this.save(project);
        return ResponseData.success(project.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> update(UpdateProjectInput input) {
        System.out.println(input.getId());
        CashProject project = this.getById(input.getId());
        if(project == null) {
            return ResponseData.error("项目不存在");
        }
        QueryWrapper<CashProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("projectName",input.getProjectName());
        queryWrapper.notIn("id",input.getId());
        int count = this.count(queryWrapper);
        if(count > 0 ) {
            return ResponseData.error("项目名称重复");
        }
        project.setProjectDate(input.getProjectDate());
        project.setProjectName(input.getProjectName());
        project.setRemark(input.getRemark());
        boolean update = this.updateById(project);
        if(!update) {
            return ResponseData.error("更新失败,请联系管理员");
        }
        return ResponseData.success(Boolean.TRUE);
    }

    @Override
    public ResponsePageData<CashProject> page(ProjectPageInput input) {
        Page<CashProject> page = new Page<>(input.getPageIndex(),input.getPageSize());
        Page<CashProject> projectPage = baseMapper.page(page, input);
        return ResponsePageData.success(projectPage.getRecords(),projectPage.getTotal());
    }


}
