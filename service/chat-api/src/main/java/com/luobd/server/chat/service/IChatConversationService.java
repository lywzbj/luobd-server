package com.luobd.server.chat.service;

import com.luobd.server.chat.entity.ChatConversation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.chat.input.ChatConversationInput;
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
public interface IChatConversationService extends IService<ChatConversation> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);


  ResponseData<Long> create(ChatConversationInput input);



  ResponseData<List<ChatConversation>> listByUserId(Long userId);












}
