package com.lx.edusphere_server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailCodeResponse extends BaseResponse {
    private String e_mail;
    
    public EmailCodeResponse(String msg, String email, String code) {
        super(msg, code);
        this.e_mail = email;
    }
    
    public static EmailCodeResponse success(String msg, String email) {
        return new EmailCodeResponse(msg, email, "success");
    }
    
    public static EmailCodeResponse error(String msg, String email) {
        return new EmailCodeResponse(msg, email, "Error");
    }
} 