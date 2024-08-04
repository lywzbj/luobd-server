package com.luobd.server.base.user.service.impl;

import com.luobd.server.base.user.entity.CoreUserInfo;
import com.luobd.server.base.user.mapper.CoreUserInfoMapper;
import com.luobd.server.base.user.service.ICoreUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2024-08-04
 */
@Service
@Primary
public class CoreUserInfoServiceImpl extends ServiceImpl<CoreUserInfoMapper, CoreUserInfo> implements ICoreUserInfoService {


  @Resource
  private CoreUserInfoMapper baseMapper;


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






}
