package com.luobd.server.base.user.service;

import com.luobd.server.base.user.entity.CoreAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.common.entities.ResponseData;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-04
 */
public interface ICoreAccountService extends IService<CoreAccount> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);












}
