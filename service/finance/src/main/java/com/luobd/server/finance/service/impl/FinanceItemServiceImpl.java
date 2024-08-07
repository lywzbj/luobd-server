package com.luobd.server.finance.service.impl;

import com.luobd.server.base.core.entity.CoreCategory;
import com.luobd.server.base.core.service.ICoreCategoryService;
import com.luobd.server.finance.entity.FinanceItem;
import com.luobd.server.finance.input.CreateFianceItemInput;
import com.luobd.server.finance.input.UpdateFinanceItemInput;
import com.luobd.server.finance.mapper.FinanceItemMapper;
import com.luobd.server.finance.service.IFinanceItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.luobd.server.common.entities.ResponseData;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-07
 */
@Service
@Primary
public class FinanceItemServiceImpl extends ServiceImpl<FinanceItemMapper, FinanceItem> implements IFinanceItemService {


  @Resource
  private FinanceItemMapper baseMapper;


  @Resource
  private ICoreCategoryService coreCategoryService;



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
    public ResponseData<Long> create(CreateFianceItemInput input) {
        if(BigDecimal.ZERO.compareTo(input.getAmount()) == 0) {
            return ResponseData.error("金额不能为0");
        }
        CoreCategory category = coreCategoryService.getById(input.getCategoryId());
        if(category == null) {
            return ResponseData.error("分类不存在");
        }
        FinanceItem entity = input.toEntity();
        entity.setCategoryName(category.getCategoryName());
        this.save(entity);
        return ResponseData.success(entity.getId());
    }

    @Override
    public ResponseData<Boolean> update(UpdateFinanceItemInput updateFinanceItemInput) {
        FinanceItem item = this.getById(updateFinanceItemInput.getId());
        if(item == null) {
            return ResponseData.error("数据不存在");
        }
        CoreCategory category = this.coreCategoryService.getById(updateFinanceItemInput.getCategoryId());
        if(category == null) {
            return ResponseData.error("分类不存在");
        }
        updateFinanceItemInput.updateEntity(item);
        item.setCategoryName(category.getCategoryName());
        this.updateById(item);
        return ResponseData.success(Boolean.TRUE);
    }
}
