package com.luobd.server.base.user.service;

import com.luobd.server.base.user.dto.AccountUserInfoPageDTO;
import com.luobd.server.base.user.entity.CoreAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobd.server.base.user.input.AccountUserInfoPageInput;
import com.luobd.server.base.user.input.CreateAccountInput;
import com.luobd.server.base.user.input.RegisterAccountInput;
import com.luobd.server.base.user.input.ResetPasswordInput;
import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.entities.ResponsePageData;

import javax.mail.MessagingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-04
 */
public interface ICoreAccountService extends IService<CoreAccount> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);



  ResponseData<Boolean> create(CreateAccountInput entity);



  ResponseData<Boolean> resetPassword(ResetPasswordInput entity);



  ResponsePageData<AccountUserInfoPageDTO> page(AccountUserInfoPageInput input);



  ResponseData<Boolean> register(RegisterAccountInput input);




}
