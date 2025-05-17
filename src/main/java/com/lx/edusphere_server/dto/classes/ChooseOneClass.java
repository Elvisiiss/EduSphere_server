package com.lx.edusphere_server.dto.classes;

import com.lx.edusphere_server.entity.subject_teacher;

import java.util.List;
import java.util.Set;

public class ChooseOneClass {
    //使用者的token
    private String user_token;
    //以下是被选择者的information
    private Long class_id;
    private String class_name;
    private String class_grade;
    private Long head_teacher_id;
    private Set<Long> students_id;
    private Set<subject_teacher> subject_teacher;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

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

    public String getClass_grade() {
        return class_grade;
    }

    public void setClass_grade(String class_grade) {
        this.class_grade = class_grade;
    }

    public Long getHead_teacher_id() {
        return head_teacher_id;
    }

    public void setHead_teacher_id(Long head_teacher_id) {
        this.head_teacher_id = head_teacher_id;
    }

    public Set<Long> getStudents_id() {
        return students_id;
    }

    public void setStudents_id(Set<Long> students_id) {
        this.students_id = students_id;
    }

    public Set<com.lx.edusphere_server.entity.subject_teacher> getSubject_teacher() {
        return subject_teacher;
    }

    public void setSubject_teacher(Set<com.lx.edusphere_server.entity.subject_teacher> subject_teacher) {
        this.subject_teacher = subject_teacher;
    }
}
