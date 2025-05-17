package com.lx.edusphere_server.dto.teacher;


public class RemoveStudentFromClass {
    //使用者的token
    private String user_token;
    //以下是被选择者
    private Long class_id;
    private Long student_id;

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

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
}
