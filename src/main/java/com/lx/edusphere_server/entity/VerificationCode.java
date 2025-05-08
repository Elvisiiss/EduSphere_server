package com.lx.edusphere_server.entity;

import java.time.LocalDateTime;

public class VerificationCode {

    private Long code_id;
    private String code_email;
    private String code_number;
    private LocalDateTime expiry_date;
    private String code_purpose; // 用途：REGISTER, RESET_PASSWORD, LOGIN

    public VerificationCode() {}

    public VerificationCode(String code_email, Long code_id, String code_number, LocalDateTime expiry_date, String code_purpose) {
        this.code_email = code_email;
        this.code_id = code_id;
        this.code_number = code_number;
        this.expiry_date = expiry_date;
        this.code_purpose = code_purpose;
    }

    public String getCode_purpose() {
        return code_purpose;
    }

    public void setCode_purpose(String code_purpose) {
        this.code_purpose = code_purpose;
    }

    public LocalDateTime getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDateTime expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getCode_number() {
        return code_number;
    }

    public void setCode_number(String code_number) {
        this.code_number = code_number;
    }

    public String getCode_email() {
        return code_email;
    }

    public void setCode_email(String code_email) {
        this.code_email = code_email;
    }

    public Long getCode_id() {
        return code_id;
    }

    public void setCode_id(Long code_id) {
        this.code_id = code_id;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiry_date);
    }
}