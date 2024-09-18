package com.luobd.server.common.permission.aop;


import cn.hutool.core.collection.CollUtil;
import com.luobd.server.common.constant.CommonConstant;
import com.luobd.server.common.entities.CurrentRequestHolder;
import com.luobd.server.common.entities.CurrentUserInfo;
import com.luobd.server.common.entities.Role;
import com.luobd.server.common.exception.NoPermissionException;
import com.luobd.server.common.exception.UnAuthenticationException;
import com.luobd.server.common.permission.AccessRoles;
import com.luobd.server.common.permission.Logic;
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
public class RoleAccessAspect {


    @Before(value = "@annotation(com.luobd.server.common.permission.AccessRoles)")
    public void checkRoles(JoinPoint joinPoint) {
        AccessRoles accessRoles = null;
        Signature signature = joinPoint.getSignature();
        if(signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            accessRoles = methodSignature.getMethod().getAnnotation(AccessRoles.class);
        }
        if(accessRoles==null){
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
        String[] values = accessRoles.values();
        if(accessRoles.logic() == Logic.AND){
            for(String value:values){
                boolean hasPermission = false;
                for(Role role:userInfo.getRoles()){
                    if(role.getKey().equals(value)){
                        hasPermission = true;
                        break;
                    }
                }
                if(!hasPermission){
                    throw new NoPermissionException();
                }
            }
        }

        if(accessRoles.logic() == Logic.OR){
            for(String value:values){
                for(Role role:userInfo.getRoles()){
                    if(role.getKey().equals(value)){
                        return;
                    }
                }
            }
            throw new NoPermissionException();
        }


    }


}
