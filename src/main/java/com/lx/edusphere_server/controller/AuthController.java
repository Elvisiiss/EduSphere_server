package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // 实际应用中应该限制跨域来源
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 创建用户 - 发送邮箱验证码
     */
    @PostMapping("/register/send-code")
    public EmailCodeResponse sendRegisterCode(@RequestBody EmailRequest email_request) {
        return authService.sendRegisterCode(email_request);
    }

    /**
     * 创建用户 - 验证邮箱验证码并创建用户
     */
    @PostMapping("/register/verify-code")
    public BaseResponse verifyRegisterCodeAndCreateUser(@RequestBody RegisterVerifyRequest register_verify_request) {
        return authService.verifyRegisterCodeAndCreateUser(register_verify_request);
    }

    /**
     * 忘记密码 - 发送邮箱验证码
     */
    @PostMapping("/reset-password/send-code")
    public EmailCodeResponse sendResetPasswordCode(@RequestBody EmailRequest email_request) {
        return authService.sendResetPasswordCode(email_request);
    }

    /**
     * 忘记密码 - 验证邮箱验证码并重置密码
     */
    @PostMapping("/reset-password/verify-code")
    public BaseResponse verifyResetPasswordCodeAndUpdatePassword(@RequestBody ResetPasswordRequest reset_password_request) {
        return authService.verifyResetPasswordCodeAndUpdatePassword(reset_password_request);
    }

    /**
     * 登录 - 使用学号或邮箱和密码
     */
    @PostMapping("/login/password")
    public LoginResponse loginWithPassword(@RequestBody LoginRequest request) {
        return authService.loginWithPassword(request);
    }

    /**
     * 登录 - 发送邮箱验证码
     */
    @PostMapping("/login/send-code")
    public EmailCodeResponse sendLoginCode(@RequestBody EmailRequest request) {
        return authService.sendLoginCode(request);
    }

    /**
     * 登录 - 使用邮箱验证码
     */
    @PostMapping("/login/verify-code")
    public LoginResponse loginWithCode(@RequestBody LoginCodeRequest request) {
        return authService.loginWithCode(request);
    }
}
