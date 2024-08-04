package com.luobd.server.mybatis.plugin;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.luobd.server.common.constant.CommonConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"createTime", LocalDateTime.now());
        this.fillStrategy(metaObject,"status", CommonConstant.STATUS_ENABLED);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"updateTime", LocalDateTime.now());
    }
}
