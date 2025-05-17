package com.lx.edusphere_server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends BaseResponse {
    private Long user_id;
    private String user_email;
    private String user_name;
    private Set<Integer> powers_id;
    private String user_token;

    public LoginResponse(String msg, String code,Long user_id, String user_email, String user_name, Set<Integer> powers_id, String user_token) {
        super(msg, code);
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_name = user_name;
        this.powers_id = powers_id;
        this.user_token = user_token;
    }

    public static LoginResponse success(Long user_id,String user_email, String user_name, Set<Integer> powers_id, String user_token) {
        return new LoginResponse("成功登录", "success",user_id, user_email, user_name, powers_id, user_token);
    }

    public static LoginResponse error(String msg) {
        return new LoginResponse(msg, "Error",null, null, null, null,null);
    }
}
