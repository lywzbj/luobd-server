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
import java.io.IOException;


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {



    @Resource
    private JWTUtil jwtUtil;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty()) {
            unauthorized(response, "Not Authorized");
            return false;
        }
        token = token.replace("Bearer ","");
        if(token.isEmpty()) {
            unauthorized(response, "Not Authorized");
            return false;
        }
        try {
            CurrentUserInfo subject = jwtUtil.getTokenSubject(token);
            CurrentRequestHolder.set(subject);
        }catch (Exception e) {
            log.error("认证失败",e);
            unauthorized(response, e.getMessage());
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentRequestHolder.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    private void unauthorized(HttpServletResponse response,String msg) throws IOException {
        ResponseData<Object> error = ResponseData.error(msg);
        error.setCode(HttpStatus.UNAUTHORIZED.value());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().print(error.toJson());
    }



}
