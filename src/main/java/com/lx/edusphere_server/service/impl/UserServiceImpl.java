package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.entity.User;
import com.lx.edusphere_server.entity.VerificationCode;
import com.lx.edusphere_server.repository.UserRepository;
import com.lx.edusphere_server.service.EmailService;
import com.lx.edusphere_server.service.UserService;
import com.lx.edusphere_server.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VerificationCodeService verificationCodeService, EmailService emailService) {
        this.userRepository = userRepository;
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
    }

    @Override
    public EmailCodeResponse sendRegisterCode(EmailRequest request) {
        String email = request.getE_mail();
        
        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(email)) {
            return EmailCodeResponse.error("邮箱已存在", email);
        }
        
        // 生成并保存验证码
        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(email, "REGISTER");
        
        // 发送验证码
        emailService.sendVerificationCode(email, verificationCode.getCode());
        
        return EmailCodeResponse.success("成功发送验证码", email);
    }

    @Override
    public BaseResponse verifyRegisterCodeAndCreateUser(RegisterVerifyRequest request) {
        String email = request.getE_mail();
        String userName = request.getUser_name();
        String password = request.getPasswd();
        String code = request.getMail_code();
        
        // 检查用户名是否已存在
        if (userRepository.existsByUserName(userName)) {
            return BaseResponse.error("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(email)) {
            return BaseResponse.error("邮箱已存在");
        }
        
        // 验证验证码
        if (!verificationCodeService.verifyCode(email, code, "REGISTER")) {
            return BaseResponse.error("验证码错误");
        }
        
        // 创建新用户
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password); // 实际应用中应该对密码进行加密
        user.setRoleId(0); // 默认角色
        user.setCreatedAt(LocalDateTime.now().format(formatter));
        user.setUpdatedAt(LocalDateTime.now().format(formatter));
        
        userRepository.save(user);
        
        return BaseResponse.success("成功创建用户");
    }

    @Override
    public EmailCodeResponse sendResetPasswordCode(EmailRequest request) {
        String email = request.getE_mail();
        
        // 检查邮箱是否存在
        if (!userRepository.existsByEmail(email)) {
            return EmailCodeResponse.error("邮箱不存在", email);
        }
        
        // 生成并保存验证码
        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(email, "RESET_PASSWORD");
        
        // 发送验证码
        emailService.sendVerificationCode(email, verificationCode.getCode());
        
        return EmailCodeResponse.success("成功发送验证码", email);
    }

    @Override
    public BaseResponse verifyResetPasswordCodeAndUpdatePassword(ResetPasswordRequest request) {
        String email = request.getE_mail();
        String newPassword = request.getNew_passwd();
        String code = request.getMail_code();
        
        // 验证验证码
        if (!verificationCodeService.verifyCode(email, code, "RESET_PASSWORD")) {
            return BaseResponse.error("验证码错误");
        }
        
        // 查找用户
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return BaseResponse.error("用户不存在");
        }
        
        // 更新密码
        User user = optionalUser.get();
        user.setPassword(newPassword); // 实际应用中应该对密码进行加密
        user.setUpdatedAt(LocalDateTime.now().format(formatter));
        
        userRepository.save(user);
        
        return BaseResponse.success("成功重置密码");
    }

    @Override
    public EmailCodeResponse sendLoginCode(EmailRequest request) {
        String email = request.getE_mail();
        
        // 检查邮箱是否存在
        if (!userRepository.existsByEmail(email)) {
            return EmailCodeResponse.error("邮箱不存在", email);
        }
        
        // 生成并保存验证码
        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(email, "LOGIN");
        
        // 发送验证码
        emailService.sendVerificationCode(email, verificationCode.getCode());
        
        return EmailCodeResponse.success("成功发送验证码", email);
    }

    @Override
    public LoginResponse loginWithPassword(LoginRequest request) {
        Integer status = request.getStatus();
        String password = request.getPasswd();
        User user = null;
        
        // 根据登录方式查找用户
        if (status == 0) { // 用户名登录
            String userName = request.getUser_name();
            Optional<User> optionalUser = userRepository.findByUserName(userName);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        } else if (status == 1) { // 邮箱登录
            String email = request.getE_mail();
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        
        // 验证用户和密码
        if (user == null || !user.getPassword().equals(password)) { // 实际应用中应该使用加密后的密码比较
            return LoginResponse.error("账户或密码错误");
        }
        
        return LoginResponse.success(user.getEmail(), user.getUserName(), user.getRoleId());
    }

    @Override
    public LoginResponse loginWithCode(LoginCodeRequest request) {
        String email = request.getE_mail();
        String code = request.getMail_code();
        
        // 验证验证码
        if (!verificationCodeService.verifyCode(email, code, "LOGIN")) {
            return LoginResponse.error("登录验证码错误");
        }
        
        // 查找用户
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return LoginResponse.error("用户不存在");
        }
        
        User user = optionalUser.get();
        return LoginResponse.success(user.getEmail(), user.getUserName(), user.getRoleId());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }
} 