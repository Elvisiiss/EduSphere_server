package com.lx.edusphere_server.service;

import com.lx.edusphere_server.entity.VerificationCode;

public interface VerificationCodeService {
    String generateCode();
    VerificationCode saveVerificationCode(String email, String purpose);
    boolean verifyCode(String email, String code, String purpose);
} 