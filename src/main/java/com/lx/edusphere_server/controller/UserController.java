package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") // 实际应用中应该限制跨域来源
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 创建用户 - 发送邮箱验证码
     */
    @PostMapping("/register/send-code")
    public EmailCodeResponse sendRegisterCode(@RequestBody EmailRequest request) {
        return userService.sendRegisterCode(request);
    }

    /**
     * 创建用户 - 验证邮箱验证码并创建用户
     */
    @PostMapping("/register/verify-code")
    public BaseResponse verifyRegisterCodeAndCreateUser(@RequestBody RegisterVerifyRequest request) {
        return userService.verifyRegisterCodeAndCreateUser(request);
    }

    /**
     * 忘记密码 - 发送邮箱验证码
     */
    @PostMapping("/reset-password/send-code")
    public EmailCodeResponse sendResetPasswordCode(@RequestBody EmailRequest request) {
        return userService.sendResetPasswordCode(request);
    }

    /**
     * 忘记密码 - 验证邮箱验证码并重置密码
     */
    @PostMapping("/reset-password/verify-code")
    public BaseResponse verifyResetPasswordCodeAndUpdatePassword(@RequestBody ResetPasswordRequest request) {
        return userService.verifyResetPasswordCodeAndUpdatePassword(request);
    }

    /**
     * 登录 - 使用用户名或邮箱和密码
     */
    @PostMapping("/login/password")
    public LoginResponse loginWithPassword(@RequestBody LoginRequest request) {
        return userService.loginWithPassword(request);
    }

    /**
     * 登录 - 发送邮箱验证码
     */
    @PostMapping("/login/send-code")
    public EmailCodeResponse sendLoginCode(@RequestBody EmailRequest request) {
        return userService.sendLoginCode(request);
    }

    /**
     * 登录 - 使用邮箱验证码
     */
    @PostMapping("/login/verify-code")
    public LoginResponse loginWithCode(@RequestBody LoginCodeRequest request) {
        return userService.loginWithCode(request);
    }

    /**
     * 获取用户数据（测试用）
     */
    @PostMapping("/get-data")
    public BaseResponse getData(@RequestBody GetDataRequest request) {
        // 简单返回，实际应用中应该返回更多用户数据
        return BaseResponse.success("获取数据成功");
    }
} 