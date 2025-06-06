package com.lx.edusphere_server.dto.User;

import java.io.File;

public class ChooseOneImage {
    //使用者的token
    private String User_token;
    //以下是被选择者的information
    private File file;
    private Long belong_user;
    private String file_name;
    private String file_url;

    public String getUser_token() {
        return User_token;
    }

    public void setUser_token(String user_token) {
        User_token = user_token;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Long getBelong_user() {
        return belong_user;
    }

    public void setBelong_user(Long belong_user) {
        this.belong_user = belong_user;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
