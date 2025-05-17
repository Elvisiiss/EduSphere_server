package com.lx.edusphere_server.dto.teacher;

import com.lx.edusphere_server.entity.Score;

import java.util.List;

public class ImportStudentScores {
    //使用者的token
    private String user_token;
    //以下是被选择者
    private Long class_id;
    private Long subject_id;
    private List<Score> scores;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
