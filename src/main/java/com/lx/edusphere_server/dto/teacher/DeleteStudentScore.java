package com.lx.edusphere_server.dto.teacher;

import java.util.List;

public class DeleteStudentScore {
    //使用者的token
    private String user_token;
    //以下是被选择者
    private Long score_id;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Long getScore_id() {
        return score_id;
    }

    public void setScore_id(Long score_id) {
        this.score_id = score_id;
    }
}
