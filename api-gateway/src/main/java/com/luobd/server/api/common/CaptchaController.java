package com.luobd.server.api.common;

import com.google.common.collect.Maps;
import com.luobd.server.base.user.service.IAuthService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.captcha.Base64Captcha;
import com.luobd.server.common.entities.captcha.CaptchaCheckInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RequestMapping(value = "/api/captcha")
@Api(tags = "验证码模块")
@RestController
public class CaptchaController {


    private static Map<String,String> captchaMap = Maps.newConcurrentMap();


    @Resource
    private IAuthService authService;


    @GetMapping(value = "/getCaptchaBase64")
    @ApiOperation(value = "获取验证码")
    public ResponseData<Base64Captcha> getCaptchaBase64() {
        return authService.getCaptchaBase64();
    }


    @PostMapping(value = "/checkCaptcha")
    @ApiOperation(value = "验证验证码")
    public ResponseData<Boolean> checkCaptcha(@RequestBody @Valid CaptchaCheckInput input){
        String id = input.getId();
        if(captchaMap.containsKey(id)){
            String code = captchaMap.get(id);
            captchaMap.remove(id);
            if(code.equals(input.getCode())){
                return ResponseData.success(true);
            }
        }
        return ResponseData.success(false);
    }






}






