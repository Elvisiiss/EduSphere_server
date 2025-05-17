package com.lx.edusphere_server.entity;

public class ClassTeacher {
    private Long id;
    private Long classId;
    private Long teacherId;
    private Long subjectId;
    private Boolean isHeadTeacher;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Boolean getHeadTeacher() {
        return isHeadTeacher;
    }

    public void setHeadTeacher(Boolean headTeacher) {
        isHeadTeacher = headTeacher;
    }
}
