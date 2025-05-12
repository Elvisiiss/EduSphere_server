package com.lx.edusphere_server.dto.Role;

import com.lx.edusphere_server.entity.Power;

import java.util.Set;

public class ChooseOneRole {
    //使用者的token
    private String user_token;
    //以下是被选择者的information
    private Long role_id;
    private String role_name;
    private Set<Integer> role_powers;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Set<Integer> getRole_powers() {
        return role_powers;
    }

    public void setRole_powers(Set<Integer> role_powers) {
        this.role_powers = role_powers;
    }
}
