package com.luobd.server.config;

import com.luobd.server.common.entities.CurrentRequestHolder;
import com.luobd.server.common.entities.CurrentUserInfo;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {



    @Resource
    private JWTUtil jwtUtil;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("LuobdAuth");
        if(token == null || token.isEmpty()) {
            ResponseData<Object> error = ResponseData.error("Not Authorized");
            error.setCode(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().print(error.toJson());
            return false;
        }
        try {
            CurrentUserInfo subject = jwtUtil.getTokenSubject(token);
            CurrentRequestHolder.set(subject);
        }catch (Exception e) {
            log.error("认证失败",e);
            ResponseData<Object> error = ResponseData.error("Not Authorized");
            error.setCode(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().print(error.toJson());
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentRequestHolder.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
