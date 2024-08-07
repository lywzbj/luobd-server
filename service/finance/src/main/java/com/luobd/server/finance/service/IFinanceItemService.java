package com.luobd.server.finance.service;

import com.luobd.server.finance.entity.FinanceItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.finance.input.CreateFianceItemInput;
import com.luobd.server.finance.input.UpdateFinanceItemInput;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-07
 */
public interface IFinanceItemService extends IService<FinanceItem> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);



  ResponseData<Long> create(CreateFianceItemInput input);



  ResponseData<Boolean>  update(UpdateFinanceItemInput updateFinanceItemInput);






}
