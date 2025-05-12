package com.lx.edusphere_server.dto.Role;

import com.lx.edusphere_server.entity.Power;

import java.util.Set;

public class GetAllRolesInfo {
    private String msg;
    private Long role_id;
    private String role_name;
    private Set<Integer> powers;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Set<Integer> getPowers() {
        return powers;
    }

    public void setPowers(Set<Integer> powers) {
        this.powers = powers;
    }
}
