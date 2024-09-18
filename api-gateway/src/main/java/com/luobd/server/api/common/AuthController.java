package com.luobd.server.api.common;


import com.luobd.server.base.user.service.IAuthService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.config.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api/auth")
@Api(tags = "认证模块")
public class AuthController {



    @Resource
    private IAuthService authService;




    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口")
    @IgnoreAuth
    public ResponseData<String> login(@RequestBody @Valid LoginInput input) {
//        if(!authService.checkCaptcha(input.getCaptchaId(),input.getCaptcha())){
//            return ResponseData.error("验证码错误");
//        }
        return authService.auth(input.getUsername(),input.getPassword());
    }



}

@ApiModel(description = "登录表单")
@Setter
@Getter
class LoginInput {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "请输入用户名")
    private String username;


    @ApiModelProperty(value = "密码  MD5加密字符串",required = true)
    @NotBlank(message = "请输入密码")
    private String password;


    @ApiModelProperty(value = "验证码",required = true)
    @NotBlank(message = "请输入验证码")
    private String captcha;

    @ApiModelProperty(value = "验证码id",required = true)
    @NotBlank(message = "请输入验证码id")
    private String captchaId;


}



