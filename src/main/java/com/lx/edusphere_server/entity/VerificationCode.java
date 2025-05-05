package com.lx.edusphere_server.entity;

import java.time.LocalDateTime;

public class VerificationCode {

    private Long id;
    private String email;
    private String code;
    private LocalDateTime expiryDate;
    private String purpose; // 用途：REGISTER, RESET_PASSWORD, LOGIN

    public VerificationCode() {}
    public VerificationCode(Long id, String email, String code, LocalDateTime expiryDate, String purpose) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.expiryDate = expiryDate;
        this.purpose = purpose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }
} 