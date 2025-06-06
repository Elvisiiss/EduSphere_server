package com.lx.edusphere_server.dto.Schedule;

import java.time.LocalDate;

public class get_schedules_by_range {
    //使用者的token
    private String user_token;
    //以下是被选择者的information
    private LocalDate start_date;
    private LocalDate end_date;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
