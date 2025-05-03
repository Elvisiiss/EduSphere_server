package com.lx.edusphere_server.service;

public interface EmailService {
    void sendVerificationCode(String to, String code);
} 