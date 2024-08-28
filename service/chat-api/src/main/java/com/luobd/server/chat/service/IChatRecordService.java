package com.luobd.server.chat.service;

import com.luobd.server.chat.entity.ChatRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.common.entities.ResponseData;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-28
 */
public interface IChatRecordService extends IService<ChatRecord> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);












}
