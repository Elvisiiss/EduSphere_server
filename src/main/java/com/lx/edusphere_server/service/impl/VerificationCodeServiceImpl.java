package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.entity.VerificationCode;
import com.lx.edusphere_server.repository.VerificationCodeRepository;
import com.lx.edusphere_server.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    @Autowired
    public VerificationCodeServiceImpl(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public String generateCode() {
        // 生成6位数字验证码
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @Override
    public VerificationCode saveVerificationCode(String email, String purpose) {
        // 生成验证码
        String code = generateCode();
        
        // 设置过期时间（10分钟后）
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(10);
        
        // 查找是否已存在该邮箱的验证码
        Optional<VerificationCode> existingCode = verificationCodeRepository.findByEmailAndPurpose(email, purpose);
        
        VerificationCode verificationCode;
        if (existingCode.isPresent()) {
            // 更新现有验证码
            verificationCode = existingCode.get();
            verificationCode.setCode(code);
            verificationCode.setExpiryDate(expiryDate);
        } else {
            // 创建新验证码
            verificationCode = new VerificationCode();
            verificationCode.setEmail(email);
            verificationCode.setCode(code);
            verificationCode.setExpiryDate(expiryDate);
            verificationCode.setPurpose(purpose);
        }
        
        return verificationCodeRepository.save(verificationCode);
    }

    @Override
    public boolean verifyCode(String email, String code, String purpose) {
        Optional<VerificationCode> optionalCode = verificationCodeRepository.findByEmailAndPurpose(email, purpose);
        
        if (optionalCode.isPresent()) {
            VerificationCode verificationCode = optionalCode.get();
            
            // 验证码是否过期
            if (verificationCode.isExpired()) {
                return false;
            }
            
            // 验证码是否匹配
            return verificationCode.getCode().equals(code);
        }
        
        return false;
    }
} 