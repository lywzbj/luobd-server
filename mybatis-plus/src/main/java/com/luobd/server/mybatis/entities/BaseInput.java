package com.luobd.server.mybatis.entities;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface BaseInput<T> {


    default QueryWrapper<T> getRepeatQuery(){return null;};

    default T toEntity() {return null;};


    default void updateEntity(T t){};



}
