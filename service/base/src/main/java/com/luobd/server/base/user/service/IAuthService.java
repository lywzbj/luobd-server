package com.luobd.server.base.user.service;

import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.captcha.Base64Captcha;


public interface IAuthService {


    ResponseData<String> auth(String username,String password);


    ResponseData<Base64Captcha> getCaptchaBase64();



    boolean checkCaptcha(String id, String captcha);



}
