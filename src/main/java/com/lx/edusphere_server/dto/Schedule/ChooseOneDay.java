package com.lx.edusphere_server.dto.Schedule;

import java.time.LocalDate;

public class ChooseOneDay {
    //使用者的token
    private String user_token;
    //以下是被选择者的information
    private Boolean is_prediction;
    private LocalDate date;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Boolean getIs_prediction() {
        return is_prediction;
    }

    public void setIs_prediction(Boolean is_prediction) {
        this.is_prediction = is_prediction;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
