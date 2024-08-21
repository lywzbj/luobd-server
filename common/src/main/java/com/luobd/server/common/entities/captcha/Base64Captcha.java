package com.luobd.server.common.entities.captcha;

import cn.hutool.captcha.LineCaptcha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luobd.server.common.utils.SnowIdWorker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "验证码 - base64字符串")
public class Base64Captcha {


    @ApiModelProperty(value = "验证码id")
    private String id;


    @ApiModelProperty(value = "验证码")
    @JsonIgnore
    private String code;

    @ApiModelProperty(value = "验证码base64字符串")
    private String base64;

    private Base64Captcha(){}

    public static Base64Captcha of(String id){
        Base64Captcha captcha = new Base64Captcha();
        captcha.setId(id);
        LineCaptcha lineCaptcha = new LineCaptcha(200, 100, 4, 20);
        lineCaptcha.createCode();
        captcha.setCode(lineCaptcha.getCode());
        String imageBase64 = lineCaptcha.getImageBase64();
        captcha.setBase64("data:image/jpeg;base64," + imageBase64);
        return captcha;
    }

    public static Base64Captcha of() {
        return of(String.valueOf(SnowIdWorker.nextId()));
    }









}
