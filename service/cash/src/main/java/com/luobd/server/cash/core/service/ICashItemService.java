package com.luobd.server.cash.core.service;

import com.luobd.server.cash.core.entity.CashItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.cash.core.input.CashItemPageInput;
import com.luobd.server.cash.core.input.CreateCashItemInput;
import com.luobd.server.cash.core.input.UpdateCashItemInput;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-05
 */
public interface ICashItemService extends IService<CashItem> {


       ResponseData<Boolean> delete(Long id);



      ResponseData<Boolean> batchDelete(List<Long> ids);



      ResponseData<Long> create(CreateCashItemInput input);


      ResponseData<Boolean> update(UpdateCashItemInput input);



      ResponsePageData<CashItem> page(CashItemPageInput input);






}
