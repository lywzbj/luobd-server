package com.luobd.server.base.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.luobd.server.base.core.dto.CoreCategoryDTO;
import com.luobd.server.base.core.entity.CoreCategory;
import com.luobd.server.base.core.input.CategoryPageInput;
import com.luobd.server.base.core.input.CategoryTreeNode;
import com.luobd.server.base.core.input.CreateCategoryInput;
import com.luobd.server.base.core.input.UpdateCategoryInput;
import com.luobd.server.base.core.mapper.CoreCategoryMapper;
import com.luobd.server.base.core.service.ICoreCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.entities.SelectDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
    public ResponsePageData<CoreCategoryDTO> page(CategoryPageInput input) {
        Page<CoreCategoryDTO> page = baseMapper.page(new Page<>(input.getPageIndex(), input.getPageSize()), input);
        if(CollUtil.isNotEmpty(page.getRecords())) {
            List<Long> ids = page.getRecords().stream().filter(v -> v.getParentId() != null && v.getParentId() != 0L).map(CoreCategoryDTO::getParentId).distinct().collect(Collectors.toList());
            if (CollUtil.isNotEmpty(ids)){
                List<CoreCategory> parentList = this.listByIds(ids);
                if(CollUtil.isNotEmpty(parentList)) {
                    Map<Long,String> map = parentList.stream().collect(Collectors.toMap(CoreCategory::getId, CoreCategory::getCategoryName));
                    page.getRecords().forEach(v -> {
                        if(map.containsKey(v.getParentId())) {
                            v.setParentName(map.get(v.getParentId()));
                        }
                    });
                }
            }
        }
        return ResponsePageData.success(page.getRecords(),page.getTotal());
    }


    @Override
    public ResponseData<List<CategoryTreeNode>> getTree(String type) {
        QueryWrapper<CoreCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        List<CoreCategory> list = this.list(queryWrapper);
        List<CategoryTreeNode> result = Collections.emptyList();
        if(CollUtil.isNotEmpty(list)) {
            result = list.stream().filter(v -> v.getParentId() == null || v.getParentId().equals(0L)).map(v-> {
                CategoryTreeNode node = new CategoryTreeNode();
                node.setId(v.getId());
                node.setCategoryName(v.getCategoryName());
                return node;
            }).collect(Collectors.toList());
            buildTree(list,result);
        }
        return ResponseData.success(result);
    }




    public void buildTree(List<CoreCategory> list,List<CategoryTreeNode> parents) {
        for (CategoryTreeNode node : parents) {
            List<CoreCategory> collect = list.stream().filter(v -> node.getId().equals(v.getParentId())).collect(Collectors.toList());
            if(CollUtil.isNotEmpty(collect)) {
                List<CategoryTreeNode> nodes = Lists.newArrayList();
                for (CoreCategory category : collect) {
                    CategoryTreeNode item = new CategoryTreeNode();
                    item.setId(category.getId());
                    item.setCategoryName(category.getCategoryName());
                    nodes.add(item);
                }
                node.setChildren(nodes);
                buildTree(list,nodes);
            }
        }
        }

    }








