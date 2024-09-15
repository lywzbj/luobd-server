package com.luobd.server.base.user.service.impl;

import cn.hutool.core.io.resource.ClassPathResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobd.server.base.user.dto.AccountUserInfoPageDTO;
import com.luobd.server.base.user.entity.CoreAccount;
import com.luobd.server.base.user.entity.CoreUserInfo;
import com.luobd.server.base.user.input.AccountUserInfoPageInput;
import com.luobd.server.base.user.input.CreateAccountInput;
import com.luobd.server.base.user.input.ResetPasswordInput;
import com.luobd.server.base.roles.input.SetAccountRolesInput;
import com.luobd.server.base.user.mapper.CoreAccountMapper;
import com.luobd.server.base.user.service.ICoreAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobd.server.base.user.service.ICoreUserInfoService;
import com.luobd.server.common.entities.ResponsePageData;
import com.luobd.server.common.utils.SnowIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.luobd.server.common.entities.ResponseData;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Luoyu
 * @since 2024-08-04
 */
@Service
@Primary
@Slf4j
public class CoreAccountServiceImpl extends ServiceImpl<CoreAccountMapper, CoreAccount> implements ICoreAccountService {


  @Resource
  private CoreAccountMapper baseMapper;







  @Resource
  private ICoreUserInfoService coreUserInfoService;


  @Resource
  private JavaMailSender javaMailSender;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> delete(Long id) {
                final boolean remove = this.removeById(id);
                if(!remove) {
                return ResponseData.error("删除失败");
                }
        return ResponseData.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> batchDelete(List<Long> ids) {
        final boolean remove = this.removeByIds(ids);
        if(!remove) {
        return ResponseData.error("删除失败");
        }
        return ResponseData.success(Boolean.TRUE);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> create(CreateAccountInput entity) {
        QueryWrapper<CoreAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accountName",entity.getAccountName());
        if(baseMapper.selectOne(queryWrapper)!=null){
            return ResponseData.error("用户名已存在");
        }
        Long accountId = SnowIdWorker.nextId();
        Long userInfoId = SnowIdWorker.nextId();
        CoreAccount coreAccount = new CoreAccount();
        coreAccount.setId(accountId);
        coreAccount.setAccountName(entity.getAccountName());
        coreAccount.setPassword(entity.getPassword());
        coreAccount.setRemark(entity.getRemark());
        coreAccount.setUserInfoId(userInfoId);

        CoreUserInfo coreUserInfo = new CoreUserInfo();
        coreUserInfo.setId(userInfoId);
        coreUserInfo.setPhoneNumber(entity.getPhoneNumber());
        coreUserInfo.setTrueName(entity.getAccountName());
        coreUserInfo.setEmail(entity.getEmail());
        coreUserInfo.setAccountId(accountId);
        // 保存数据
        this.save(coreAccount);
        this.coreUserInfoService.save(coreUserInfo);
        return ResponseData.success(Boolean.TRUE);
    }

    @Override
    public ResponseData<Boolean> resetPassword(ResetPasswordInput entity) {
        CoreAccount coreAccount = baseMapper.selectById(entity.getId());
        if(coreAccount!=null&&coreAccount.getPassword().equals(entity.getOldPassword())){
            coreAccount.setPassword(entity.getNewPassword());
            this.updateById(coreAccount);
            return ResponseData.success(Boolean.TRUE);
        }
        return ResponseData.error("密码错误");
    }

    @Override
    public ResponsePageData<AccountUserInfoPageDTO> page(AccountUserInfoPageInput input) {
        Page<AccountUserInfoPageDTO> page = baseMapper.page(new Page<>(input.getPageIndex(),input.getPageSize()),input);
        if(page!=null){
            return ResponsePageData.success(page.getRecords(),page.getTotal());
        }
        return ResponsePageData.success(Collections.emptyList(),0);
    }


}
