package com.luobd.server.base.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Maps;
import com.luobd.server.base.user.service.IEmailSendService;
import com.luobd.server.common.entities.ResponseData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@Primary
public class EmailSenderServiceImpl implements IEmailSendService {


    @Value("${spring.mail.username}")
    private String formEmailUser;


    @Resource
    private JavaMailSender javaMailSender;


    private static final Map<String, EmailCheckCodeCache> emailCheckCodeCacheMap = Maps.newConcurrentMap();

    @PostConstruct
    public void init() {
        log.info("邮件发送服务初始化,当前邮件发送用户:{}",formEmailUser);
    }


    @Override
    public ResponseData<Boolean> sendCheckCodeEmail(String emailUser) {
        if(emailCheckCodeCacheMap.containsKey(emailUser)) {
            EmailCheckCodeCache cache = emailCheckCodeCacheMap.get(emailUser);
            if(cache.isNextSendTime()) {
                emailCheckCodeCacheMap.remove(emailUser);
            }else {
                return ResponseData.error("请勿频繁发送验证码");
            }
        }
        String generate = RandomUtil.randomNumbers(6);
        try {
            String content = buildCheckCodeContent(generate);
            sendEmail(emailUser, content, "验证码");
            log.info("发送验证码:{},验证码有效期30分钟",generate);
            emailCheckCodeCacheMap.put(emailUser, EmailCheckCodeCache.of(generate));
            return ResponseData.success(true);
        } catch (MessagingException e) {
            log.error("发送邮件失败",e);
            return ResponseData.error("发送邮件失败");
        }
    }

    @Override
    public ResponseData<Boolean> checkCheckCode(String emailUser, String code) {
        if(emailCheckCodeCacheMap.containsKey(emailUser)) {
            EmailCheckCodeCache cache = emailCheckCodeCacheMap.get(emailUser);
            if(cache.isValid(code)) {
                emailCheckCodeCacheMap.remove(emailUser);
                return ResponseData.success(true);
            }else{
                return ResponseData.error("验证码错误或已过期");
            }
        }
        return ResponseData.error("验证码不存在");
    }

    @Override
    public String sendResetPasswordEmail(String emailUser) {
        return null;
    }


    /**
     * 发送模板文本,基于html格式
     * @param emailUser
     * @param content
     * @param title
     * @return
     * @throws MessagingException
     */
    private String sendEmail(String emailUser, String content, String title) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper simpleMailMessage = new MimeMessageHelper(message,true);
        simpleMailMessage.setFrom(formEmailUser);
        simpleMailMessage.setTo(emailUser);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content,true);
        javaMailSender.send(message);
        return "success";
    }


    /**
     * 发送普通文本
     * @param emailUser
     * @param content
     * @throws MessagingException
     */
    private void sendEmailText(String emailUser, String content,String title) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formEmailUser);
        simpleMailMessage.setTo(emailUser);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }




    public String buildCheckCodeContent(String title) {
        //加载邮件html模板
        StringBuilder buffer = new StringBuilder();
        String line = "";
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("emailTemplate.ftl");
             BufferedReader fileReader =new BufferedReader(new InputStreamReader(inputStream));
        ){
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("发送邮件读取模板失败", e);
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), title);
    }



    @Data
    private static class EmailCheckCodeCache {

        private String code;


        private LocalDateTime nextSendTime = LocalDateTime.now().plusMinutes(1);


        private LocalDateTime expireTime = LocalDateTime.now().plusMinutes(30) ;

        private EmailCheckCodeCache(String code) {
            this.code = code;
        }

        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expireTime);
        }

        public boolean isNextSendTime() {
            return LocalDateTime.now().isAfter(nextSendTime);
        }


        public boolean isValid(String code) {
            return this.code.equals(code) && !isExpired();
        }

        public static EmailCheckCodeCache of(String code) {
            return new EmailCheckCodeCache(code);
        }


    }



}
