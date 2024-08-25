package com.luobd.server.base.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobd.server.base.roles.service.ICoreUserRoleService;
import com.luobd.server.base.user.entity.CoreAccount;
import com.luobd.server.base.user.entity.CoreUserInfo;
import com.luobd.server.base.user.service.IAuthService;
import com.luobd.server.base.user.service.ICoreAccountService;
import com.luobd.server.base.user.service.ICoreUserInfoService;
import com.luobd.server.common.entities.CurrentUserInfo;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.Role;
import com.luobd.server.common.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Primary
public class AuthServiceImpl implements IAuthService {


    @Resource
    private JWTUtil jwtUtil;


    @Resource
    private ICoreAccountService coreAccountService;


    @Resource
    private ICoreUserInfoService coreUserInfoService;



    @Resource
    private ICoreUserRoleService coreUserRoleService;





    @Override
    public ResponseData<String> auth(String username, String password) {

        QueryWrapper<CoreAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accountName",username);
        List<CoreAccount> list = coreAccountService.list(queryWrapper);
        if(list == null || list.isEmpty()) {
            return ResponseData.error("用户不存在或者密码不正确");
        }
        if(list.size() > 1) {
            return ResponseData.error("数据异常,请联系管理员");
        }


        CoreAccount account = list.get(0);

        CoreUserInfo info = coreUserInfoService.getById(account.getUserInfoId());
        List<Role> roles = coreUserRoleService.getRolesByUserId(info.getId());


        CurrentUserInfo userInfo = new CurrentUserInfo();
        userInfo.setUsername(username);
        userInfo.setTrueName(info.getTrueName());
        userInfo.setRoles(roles);
        userInfo.setUserInfoId(account.getUserInfoId());
        userInfo.setAccountId(account.getId());
        String token = jwtUtil.createToken(userInfo);
        return ResponseData.success(token);
    }
}
