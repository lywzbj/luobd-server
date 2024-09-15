package com.luobd.server.base.user.service;

import com.luobd.server.common.entities.ResponseData;

public interface IEmailSendService {


    ResponseData<Boolean> sendCheckCodeEmail(String emailUser);


    ResponseData<Boolean> checkCheckCode(String emailUser, String code);


    String sendResetPasswordEmail(String emailUser);
}
