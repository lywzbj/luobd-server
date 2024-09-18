package com.luobd.server.mybatis.plugin;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.luobd.server.common.constant.CommonConstant;
import com.luobd.server.common.entities.CurrentRequestHolder;
import com.luobd.server.common.entities.CurrentUserInfo;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"createTime", LocalDateTime.now());
        this.fillStrategy(metaObject,"status", CommonConstant.STATUS_ENABLED);
        CurrentUserInfo userInfo = CurrentRequestHolder.get();
        Long userInfoId = 0L;
        if(userInfo != null && userInfo.getUserInfoId() != null) {
            userInfoId = userInfo.getUserInfoId();
        }
        this.fillStrategy(metaObject,"createUserId",userInfoId);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"updateTime", LocalDateTime.now());
        CurrentUserInfo userInfo = CurrentRequestHolder.get();
        Long userInfoId = userInfo.getUserInfoId() == null ? 0L : userInfo.getUserInfoId();
        this.fillStrategy(metaObject,"updateUserId",userInfoId);
    }
}
