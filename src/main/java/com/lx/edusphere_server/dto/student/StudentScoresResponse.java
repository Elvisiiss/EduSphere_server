package com.lx.edusphere_server.dto.student;

import java.util.List;

public class StudentScoresResponse {
    private List<StudentScoreResponse> scores;
    private List<StudentSubjectResponse> subjects;

    public List<StudentScoreResponse> getScores() {
        return scores;
    }

    public void setScores(List<StudentScoreResponse> scores) {
        this.scores = scores;
    }

    public List<StudentSubjectResponse> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<StudentSubjectResponse> subjects) {
        this.subjects = subjects;
    }
}
