package com.luobd.server.base.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.core.entity.CoreCategory;
import com.luobd.server.base.core.input.CategoryPageInput;
import com.luobd.server.base.core.input.CreateCategoryInput;
import com.luobd.server.base.core.input.UpdateCategoryInput;
import com.luobd.server.base.core.mapper.CoreCategoryMapper;
import com.luobd.server.base.core.service.ICoreCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.common.entities.ResponsePageData;
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
public class CoreCategoryServiceImpl extends ServiceImpl<CoreCategoryMapper, CoreCategory> implements ICoreCategoryService {


  @Resource
  private CoreCategoryMapper baseMapper;


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
    public ResponseData<Long> create(CreateCategoryInput input) {

        int count = this.count(input.getRepeatQuery());
        if(count > 0) {
            return ResponseData.error("名称重复");
        }
        if(input.getParentId() != null) {
            QueryWrapper<CoreCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("id",input.getParentId());
            int counted = this.count(wrapper);
            if(counted <= 0) {
                return ResponseData.error("父级分类不存在");
            }
        }
        CoreCategory coreCategory = input.toEntity();
        this.save(coreCategory);
        return ResponseData.success(coreCategory.getId());
    }

    @Override
    public ResponseData<Boolean> update(UpdateCategoryInput input) {
        CoreCategory category = this.getById(input.getId());
        if(category == null) {
            return ResponseData.error("分类不存在,无法修改");
        }
        int count = this.count(input.getRepeatQuery());
        if(count > 0) {
            return ResponseData.error("名称重复");
        }
        if(input.getParentId() != null) {
            QueryWrapper<CoreCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("id",input.getParentId());
            int counted = this.count(wrapper);
            if(counted <= 0) {
                return ResponseData.error("父级分类不存在");
            }
        }
        input.updateEntity(category);
        this.updateById(category);
        return ResponseData.success(Boolean.TRUE);
    }

    @Override
    public ResponsePageData<CoreCategory> page(CategoryPageInput input) {
        Page<CoreCategory> page = baseMapper.page(new Page<>(input.getPageIndex(), input.getPageSize()), input);
        return ResponsePageData.success(page.getRecords(),page.getTotal());
    }


}
