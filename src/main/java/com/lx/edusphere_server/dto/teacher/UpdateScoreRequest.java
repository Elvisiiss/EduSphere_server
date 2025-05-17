package com.lx.edusphere_server.dto.teacher;

public class UpdateScoreRequest {
    private String user_token;
    private Long score_id;
    private Double score;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getScore_id() {
        return score_id;
    }

    public void setScore_id(Long score_id) {
        this.score_id = score_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
