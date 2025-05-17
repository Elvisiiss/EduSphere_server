package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.entity.User;
import com.lx.edusphere_server.entity.VerificationCode;
import com.lx.edusphere_server.mapper.*;
import com.lx.edusphere_server.service.EmailService;
import com.lx.edusphere_server.service.AuthService;
import com.lx.edusphere_server.service.VerificationCodeService;
import com.lx.edusphere_server.tools.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;
    private final PowerMapper powerMapper;
    private final TokenMapper tokenMapper;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RoleMapper roleMapper;

    @Autowired
    public AuthServiceImpl(AuthMapper authMapper,
                           VerificationCodeService verificationCodeService,
                           EmailService emailService,
                           PowerMapper powerMapper,
                           TokenMapper tokenMapper,
                           RoleMapper roleMapper) {
        this.authMapper = authMapper;
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
        this.powerMapper = powerMapper;
        this.tokenMapper = tokenMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public EmailCodeResponse sendRegisterCode(EmailRequest request) {
        String email = request.getE_mail();

        // 检查邮箱是否已存在
        if (authMapper.existsByEmail(email)) {
            return EmailCodeResponse.error("邮箱已存在", email);
        }

        // 生成并保存验证码
        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(email, "REGISTER");

        // 发送验证码
        emailService.sendVerificationCode(email, verificationCode.getCode_number());

        return EmailCodeResponse.success("成功发送验证码", email);
    }

    @Override
    public BaseResponse verifyRegisterCodeAndCreateUser(RegisterVerifyRequest request) {
        String email = request.getE_mail();
        String userName = request.getUser_name();
        String password = request.getUser_password();
        String code = request.getMail_code();

        // 检查邮箱是否已存在
        if (authMapper.existsByEmail(email)) {
            return BaseResponse.error("邮箱已存在");
        }

        // 验证验证码
        if (!verificationCodeService.verifyCode(email, code, "REGISTER")) {
            return BaseResponse.error("验证码错误");
        }

        // 创建新用户
        User user = new User();
        user.setUser_name(userName);
        user.setUser_email(email);
        user.setUser_password(password); // 实际应用中应该对密码进行加密

        authMapper.CreateUser(user);
        roleMapper.create_user_from_login_page(user.getUser_email());
        return BaseResponse.success("成功创建用户");
    }

    @Override
    public EmailCodeResponse sendResetPasswordCode(EmailRequest request) {
        String email = request.getE_mail();

        // 检查邮箱是否存在
        if (!authMapper.existsByEmail(email)) {
            return EmailCodeResponse.error("邮箱不存在", email);
        }

        // 生成并保存验证码
        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(email, "RESET_PASSWORD");

        // 发送验证码
        emailService.sendVerificationCode(email, verificationCode.getCode_number());

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
        Optional<User> optionalUser = Optional.ofNullable(authMapper.findByEmail(email));
        if (optionalUser.isEmpty()) {
            return BaseResponse.error("用户不存在");
        }

        // 更新密码
        User user = optionalUser.get();
        user.setUser_password(newPassword); // 实际应用中应该对密码进行加密

        authMapper.updatePassword(user);

        return BaseResponse.success("成功重置密码");
    }

    @Override
    public EmailCodeResponse sendLoginCode(EmailRequest request) {
        String email = request.getE_mail();

        // 检查邮箱是否存在
        if (!authMapper.existsByEmail(email)) {
            return EmailCodeResponse.error("邮箱不存在", email);
        }

        // 生成并保存验证码
        VerificationCode verificationCode = verificationCodeService.saveVerificationCode(email, "LOGIN");

        // 发送验证码
        emailService.sendVerificationCode(email, verificationCode.getCode_number());

        return EmailCodeResponse.success("成功发送验证码", email);
    }

    @Override
    public LoginResponse loginWithPassword(LoginRequest request) {
        Integer status = request.getStatus();
        String password = request.getPasswd();
        User user = null;

        // 根据登录方式查找用户
        if (status == 0) { // 用户名登录
            String userNumber = request.getUser_number();
            Optional<User> optionalUser = Optional.ofNullable(authMapper.findByUserNumber(userNumber));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        } else if (status == 1) { // 邮箱登录
            String email = request.getE_mail();
            Optional<User> optionalUser = Optional.ofNullable(authMapper.findByEmail(email));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }

        // 验证用户和密码
        if (user == null || !user.getUser_password().equals(password)) { // 实际应用中应该使用加密后的密码比较
            return LoginResponse.error("账户或密码错误");
        }
        String user_token = TokenGenerator.generateToken();
        tokenMapper.updateToken(user.getUser_name(),user_token);
        user.setUser_token(user_token);
        return LoginResponse.success(user.getUser_id(),user.getUser_email(), user.getUser_name(), powerMapper.getPowerIdsByUserName(user.getUser_name()),user_token);
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
        Optional<User> optionalUser = Optional.ofNullable(authMapper.findByEmail(email));
        if (optionalUser.isEmpty()) {
            return LoginResponse.error("用户不存在");
        }

        User user = optionalUser.get();
        String user_token = TokenGenerator.generateToken();
        tokenMapper.updateToken(user.getUser_name(), user_token);
        user.setUser_token(user_token);
        return LoginResponse.success(user.getUser_id(), user.getUser_email(), user.getUser_name(),powerMapper.getPowerIdsByUserName(user.getUser_name()),user_token);
    }

    @Override
    public boolean existsByEmail(String email) {
        return authMapper.existsByEmail(email);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return authMapper.existsByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return authMapper.findByEmail(email);
    }
}
