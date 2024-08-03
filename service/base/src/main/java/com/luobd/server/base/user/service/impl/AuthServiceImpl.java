package com.luobd.server.base.user.service.impl;

import com.luobd.server.base.user.service.IAuthService;
import com.luobd.server.common.entities.CurrentUserInfo;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {


    @Resource
    private JWTUtil jwtUtil;


    @Override
    public ResponseData<String> auth(String username, String password) {
        CurrentUserInfo userInfo = new CurrentUserInfo();
        userInfo.setUsername(username);
        userInfo.setUserInfoId(1L);
        userInfo.setTrueName("张三");
        userInfo.setAccountId(1L);
        String token = jwtUtil.createToken(userInfo);
        return ResponseData.success(token);
    }
}
