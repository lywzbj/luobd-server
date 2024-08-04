package com.luobd.server.cash.core.service;

import com.luobd.server.cash.core.entity.CashItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.common.entities.ResponseData;
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












}
