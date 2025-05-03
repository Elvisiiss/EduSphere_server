package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // 设置发件人，必须和授权用户相同
        message.setTo(to);
        message.setSubject("验证码");
        message.setText("您的验证码是: " + code + "，有效期为10分钟，请勿泄露给他人。");
        mailSender.send(message);
    }
} 