package com.luobd.server.cash.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.cash.core.entity.CashItem;
import com.luobd.server.cash.core.entity.CashProject;
import com.luobd.server.cash.core.input.CashItemPageInput;
import com.luobd.server.cash.core.input.CreateCashItemInput;
import com.luobd.server.cash.core.input.UpdateCashItemInput;
import com.luobd.server.cash.core.mapper.CashItemMapper;
import com.luobd.server.cash.core.service.ICashItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.cash.core.service.ICashProjectService;
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
public class CashItemServiceImpl extends ServiceImpl<CashItemMapper, CashItem> implements ICashItemService {


  @Resource
  private CashItemMapper baseMapper;


  @Resource
  private ICashProjectService cashProjectService;



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
    public ResponseData<Long> create(CreateCashItemInput input) {
        CashProject project = cashProjectService.getById(input.getProjectId());
        if(project == null) {
            return ResponseData.error("礼金项目不存在");
        }

        QueryWrapper<CashItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("projectId",input.getProjectId());
        queryWrapper.eq("cashUserName",input.getCashUserName());
        int count = this.count(queryWrapper);
        if(count > 0) {
            return ResponseData.error("您已送礼金,感谢您的到来，若需修改礼金信息可联系管理员");
        }
        CashItem item = new CashItem();
        item.setId(SnowIdWorker.nextId());
        item.setAmount(input.getAmount());
        item.setCashUserName(input.getCashUserName());
        item.setProjectId(input.getProjectId());
        item.setRemark(input.getRemark());
        item.setProjectDate(project.getProjectDate());
        item.setProjectName(project.getProjectName());
        this.save(item);
        return ResponseData.success(item.getId());
    }

    @Override
    public ResponseData<Boolean> update(UpdateCashItemInput input) {
        CashItem cashItem = this.getById(input.getId());
        if(cashItem == null) {
            return ResponseData.error("礼金记录不存在,无法修改");
        }
        CashProject cashProject = cashProjectService.getById(input.getProjectId());
        if(cashProject == null) {
            return ResponseData.error("礼金项目不存在,无法修改");
        }
        QueryWrapper<CashItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("projectId",input.getProjectId());
        queryWrapper.eq("cashUserName",input.getCashUserName());
        queryWrapper.notIn("id",cashItem.getId());
        int count = this.count(queryWrapper);
        if(count > 0) {
            return ResponseData.error("礼金已送,感谢您的到来");
        }
        cashItem.setAmount(input.getAmount());
        cashItem.setCashUserName(input.getCashUserName());
        cashItem.setProjectId(input.getProjectId());
        cashItem.setRemark(input.getRemark());
        cashItem.setProjectDate(cashProject.getProjectDate());
        cashItem.setProjectName(cashProject.getProjectName());
        boolean update = this.updateById(cashItem);
        if(!update) {
            return ResponseData.error("更新失败,请联系管理员");
        }
        return ResponseData.success(Boolean.TRUE);
    }

    @Override
    public ResponsePageData<CashItem> page(CashItemPageInput input) {
        Page<CashItem> page = new Page<>(input.getPageIndex(),input.getPageSize());
        Page<CashItem> itemPage = baseMapper.page(page, input);
        return ResponsePageData.success(itemPage.getRecords(),itemPage.getTotal());
    }


}
