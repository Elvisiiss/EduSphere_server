package com.lx.edusphere_server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends BaseResponse {
    private String e_mail;
    private String user_name;
    private Integer role_id;
    
    public LoginResponse(String msg, String code, String email, String userName, Integer roleId) {
        super(msg, code);
        this.e_mail = email;
        this.user_name = userName;
        this.role_id = roleId;
    }
    
    public static LoginResponse success(String email, String userName, Integer roleId) {
        return new LoginResponse("成功登录", "success", email, userName, roleId);
    }
    
    public static LoginResponse error(String msg) {
        return new LoginResponse(msg, "Error", null, null, null);
    }
} 