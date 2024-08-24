package com.luobd.server.common.permission;


import java.lang.annotation.*;

@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface AccessPermission {
    String[] values() default {};

    Logic logic() default Logic.OR;



}
