package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.entity.User;


public interface UserService {
    // 发送注册验证码
    EmailCodeResponse sendRegisterCode(EmailRequest request);
    
    // 确认注册验证码并创建用户
    BaseResponse verifyRegisterCodeAndCreateUser(RegisterVerifyRequest request);
    
    // 发送忘记密码验证码
    EmailCodeResponse sendResetPasswordCode(EmailRequest request);
    
    // 确认重置密码验证码并重置密码
    BaseResponse verifyResetPasswordCodeAndUpdatePassword(ResetPasswordRequest request);
    
    // 发送登录验证码
    EmailCodeResponse sendLoginCode(EmailRequest request);
    
    // 用户名或邮箱密码登录
    LoginResponse loginWithPassword(LoginRequest request);
    
    // 邮箱验证码登录
    LoginResponse loginWithCode(LoginCodeRequest request);
    
    // 验证用户名或邮箱是否存在
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    
    // 通过邮箱或用户名查找用户
    User findByEmail(String email);
    User findByUserName(String userName);
} 