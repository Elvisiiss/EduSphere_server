package com.lx.edusphere_server.entity;

public class Class {
    private Long class_id;
    private String class_name;
    private String grade;
    private Long head_teacher_id;

    public Long getHead_teacher_id() {
        return head_teacher_id;
    }

    public void setHead_teacher_id(Long head_teacher_id) {
        this.head_teacher_id = head_teacher_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }
}
