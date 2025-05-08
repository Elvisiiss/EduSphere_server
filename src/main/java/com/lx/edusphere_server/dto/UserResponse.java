package com.lx.edusphere_server.dto;

public class UserResponse {
    private Long id;
    private String user_name;
    private String e_mail;

    // 构造器、getter和setter
    public UserResponse(Long id, String user_name, String e_mail) {
        this.id = id;
        this.user_name = user_name;
        this.e_mail = e_mail;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

}