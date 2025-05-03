package com.lx.edusphere_server.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String msg;
    private String e_mail;
    private String new_passwd;
    private String mail_code;
} 