package com.lx.edusphere_server.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String msg;
    private Integer status;
    private String user_name;
    private String e_mail;
    private String passwd;
    private String token;
} 