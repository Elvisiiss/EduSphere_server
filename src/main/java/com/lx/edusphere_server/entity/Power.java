package com.lx.edusphere_server.entity;

public class Power {
    private Long power_id;
    private String power_name;

    public Power() {}
    public Power(Long power_id, String power_name) {
        this.power_id = power_id;
        this.power_name = power_name;
    }

    public Long getPower_id() {
        return power_id;
    }

    public void setPower_id(Long power_id) {
        this.power_id = power_id;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }
}
