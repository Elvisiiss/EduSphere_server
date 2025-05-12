package com.lx.edusphere_server.dto.admin;

import java.util.Set;

public class AdminChooseOneUser {
    //使用者的token
    private String user_token;
    //以下是被选择者的information
    private Long user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_number;
    private Set<Integer> user_roles_id;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
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
