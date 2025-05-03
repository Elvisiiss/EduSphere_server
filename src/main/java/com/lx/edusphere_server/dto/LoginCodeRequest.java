package com.lx.edusphere_server.dto;

import lombok.Data;

@Data
public class LoginCodeRequest {
    private String msg;
    private String e_mail;
    private String mail_code;
} 