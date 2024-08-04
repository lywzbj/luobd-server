package com.luobd.server.common.utils;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

public class SnowIdWorker {

    private static final SnowflakeGenerator generator;


    static {
        generator = new SnowflakeGenerator();
    }



    public synchronized static long nextId() {
        return generator.next();
    }

}
