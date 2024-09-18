package com.luobd.server.common.permission.aop;


import cn.hutool.core.collection.CollUtil;
import com.luobd.server.common.constant.CommonConstant;
import com.luobd.server.common.entities.CurrentRequestHolder;
import com.luobd.server.common.entities.CurrentUserInfo;
import com.luobd.server.common.exception.NoPermissionException;
import com.luobd.server.common.exception.UnAuthenticationException;
import com.luobd.server.common.permission.AccessPermission;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PermissionAspect {


    @Before(value = "@annotation(com.luobd.server.common.permission.AccessPermission)")
    public void checkPermission(JoinPoint joinPoint) {
        AccessPermission accessPermission = null;
        Signature signature = joinPoint.getSignature();
        if(signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            accessPermission = methodSignature.getMethod().getAnnotation(AccessPermission.class);
        }
        if(accessPermission==null){
            return;
        }
        CurrentUserInfo userInfo = CurrentRequestHolder.get();
        if(userInfo==null){
            throw new UnAuthenticationException();
        }
        if (CollUtil.isEmpty(userInfo.getRoles())) {
            throw new NoPermissionException();
        }
        if (userInfo.getRoles().stream().anyMatch(r->r.getKey().equals(CommonConstant.DEFAULT_ROLE_ADMINISTRATOR))) {
            log.info("超管账户:{}访问，无需校验...", userInfo.getUsername());
            return;
        }
        String[] values = accessPermission.values();
        //获取当前用户权限
        //获取方法参数权限
        //判断权限
    }


}
