package com.lx.edusphere_server.dto;

public class OnlyToken {
    private String user_token;

    public OnlyToken() {}
    public OnlyToken(String user_token) {}
    public String getUser_token() {
        return user_token;
    }
    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
