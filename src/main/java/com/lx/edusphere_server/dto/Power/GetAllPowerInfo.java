package com.lx.edusphere_server.dto.Power;

public class GetAllPowerInfo {
    private String msg;
    private Long power_id;
    private String power_name;

    public Long getPower_id() {
        return power_id;
    }

    public void setPower_id(Long power_id) {
        this.power_id = power_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }
}
