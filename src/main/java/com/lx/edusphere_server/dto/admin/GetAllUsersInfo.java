package com.lx.edusphere_server.dto.admin;

import java.util.Set;

/**
 * 用处：
 * 1、返回  给管理员想要看到所有人的信息
 */
public class GetAllUsersInfo {
    private String msg;
    private Long user_id;
    private String user_name;
    private String user_email;
    private String user_token;
    private Set<Integer> user_roles_id;
    private String user_number;

    public GetAllUsersInfo() {}

    public GetAllUsersInfo(String msg, Long user_id, String user_name, String user_email, String user_token, Set<Integer> user_roles_id, String user_number) {
        this.msg = msg;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_token = user_token;
        this.user_roles_id = user_roles_id;
        this.user_number = user_number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public Set<Integer> getUser_roles_id() {
        return user_roles_id;
    }

    public void setUser_roles_id(Set<Integer> user_roles_id) {
        this.user_roles_id = user_roles_id;
    }
}
