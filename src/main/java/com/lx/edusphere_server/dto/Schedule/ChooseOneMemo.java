package com.lx.edusphere_server.dto.Schedule;

import java.time.LocalDateTime;

public class ChooseOneMemo {
    //使用者的token
    private String user_token;
    private Long user_id;
    //以下是被选择者的information
    private String memo_id;
    private String content;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getMemo_id() {
        return memo_id;
    }

    public void setMemo_id(String memo_id) {
        this.memo_id = memo_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }
}
