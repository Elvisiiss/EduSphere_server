package com.lx.edusphere_server.entity;

import java.time.LocalDate;

public class User_information {
    private Long user_id;
    private String user_nick_name;
    private String user_gender;
    private LocalDate user_birthday;
    private String user_personalized_signature;
    private String user_profile_picture;

    public User_information() {}

    public User_information(Long user_id, String user_nick_name, String user_gender, LocalDate user_birthday, String user_personalized_signature, String user_profile_picture) {
        this.user_id = user_id;
        this.user_nick_name = user_nick_name;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_personalized_signature = user_personalized_signature;
        this.user_profile_picture = user_profile_picture;
    }

    public String getUser_personalized_signature() {
        return user_personalized_signature;
    }

    public void setUser_personalized_signature(String user_personalized_signature) {
        this.user_personalized_signature = user_personalized_signature;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_nick_name() {
        return user_nick_name;
    }

    public void setUser_nick_name(String user_nick_name) {
        this.user_nick_name = user_nick_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public LocalDate getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(LocalDate user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_profile_picture() {
        return user_profile_picture;
    }

    public void setUser_profile_picture(String user_profile_picture) {
        this.user_profile_picture = user_profile_picture;
    }
}
