package com.luobd.server.base.user.service;

import com.luobd.server.common.entities.ResponseData;

public interface IAuthService {


    ResponseData<String> auth(String username,String password);








}
