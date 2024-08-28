package com.luobd.server.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobd.server.chat.entity.ChatConversation;
import com.luobd.server.chat.mapper.ChatConversationMapper;
import com.luobd.server.chat.input.ChatConversationInput;
import com.luobd.server.chat.service.IChatConversationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2024-08-28
 */
@Service
@Primary
public class ChatConversationServiceImpl extends ServiceImpl<ChatConversationMapper, ChatConversation> implements IChatConversationService {


  @Resource
  private ChatConversationMapper baseMapper;


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
    public ResponseData<Long> create(ChatConversationInput input) {
        ChatConversation conversation = new ChatConversation();
        conversation.setConversationName(input.getConversationName());
        conversation.setId(SnowIdWorker.nextId());
        boolean save = this.save(conversation);
        if(save) {
            return ResponseData.success(conversation.getId());
        }
        return ResponseData.error("创建失败");
    }

    @Override
    public ResponseData<List<ChatConversation>> listByUserId(Long userId) {
        QueryWrapper<ChatConversation> wrapper = new QueryWrapper<>();
        wrapper.eq("createUserId",userId);
        return ResponseData.success(list(wrapper));
    }


}
