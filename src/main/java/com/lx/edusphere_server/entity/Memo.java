package com.lx.edusphere_server.entity;

import java.time.LocalDateTime;

public class Memo {
    private String memo_id;
    private String content;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public Memo() {}

    public Memo(String memo_id, String content, LocalDateTime create_time, LocalDateTime update_time) {
        this.memo_id = memo_id;
        this.content = content;
        this.create_time = create_time;
        this.update_time = update_time;
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
