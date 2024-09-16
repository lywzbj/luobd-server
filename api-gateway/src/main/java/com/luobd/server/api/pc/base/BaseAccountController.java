package com.luobd.server.api.pc.base;


import com.luobd.server.base.user.dto.AccountUserInfoPageDTO;
import com.luobd.server.base.user.input.AccountUserInfoPageInput;
import com.luobd.server.base.user.input.CreateAccountInput;
import com.luobd.server.base.user.input.RegisterAccountInput;
import com.luobd.server.base.user.input.ResetPasswordInput;
import com.luobd.server.base.user.service.ICoreAccountService;
import com.luobd.server.base.user.service.IEmailSendService;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.config.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "基础数据 - 账户管理")
@RequestMapping(value = "/api/core/account")
public class BaseAccountController {

    @Resource
    private ICoreAccountService coreAccountService;


    @Resource
    private IEmailSendService emailSendService;


    @PostMapping(value = "/create")
    @ApiOperation(value = "创建账户")
    public ResponseData<Boolean> create(@RequestBody @Valid CreateAccountInput entity) {
        return coreAccountService.create(entity);
    }


    @PostMapping(value = "/register")
    @ApiOperation(value = "前台注册用户")
    @IgnoreAuth
    public ResponseData<Boolean> register(@RequestBody @Valid RegisterAccountInput input) {
        return coreAccountService.register(input);
    }



    @GetMapping(value = "/deleteById")
    @ApiOperation(value = "删除账户")
    public ResponseData<Boolean> delete(@RequestParam(value = "id") Long id) {
        return coreAccountService.delete(id);
    }


    @GetMapping(value = "/sendCheckCodeEmail")
    @ApiOperation(value = "发送验证码")
    @IgnoreAuth
    public ResponseData<Boolean> sendCheckCodeEmail(@RequestParam(value = "emailUser") String emailUser) {
        return emailSendService.sendCheckCodeEmail(emailUser);
    }

    @PostMapping(value = "/checkCheckCode")
    @ApiOperation(value = "验证验证码")
    public ResponseData<Boolean> checkCheckCode(@RequestParam(value = "emailUser") String emailUser,
                                                @RequestParam(value = "code") String code) {
        return emailSendService.checkCheckCode(emailUser, code);
    }


    @PostMapping(value = "/resetPassword")
    @ApiOperation(value = "更新密码")
    public ResponseData<Boolean> resetPassword(@RequestBody @Valid ResetPasswordInput entity) {
        return coreAccountService.resetPassword(entity);
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public ResponsePageData<AccountUserInfoPageDTO> page(@RequestBody @Valid AccountUserInfoPageInput input) {
        return coreAccountService.page(input);
    }


}
