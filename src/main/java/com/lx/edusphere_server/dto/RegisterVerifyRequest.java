package com.lx.edusphere_server.dto;

import lombok.Data;

@Data
public class RegisterVerifyRequest {
    private String msg;
    private String e_mail;
    private String user_name;
    private String passwd;
    private String mail_code;
} 