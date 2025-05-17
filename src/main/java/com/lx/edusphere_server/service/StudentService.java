package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.student.*;
import com.lx.edusphere_server.entity.Teacher;

import java.util.List;

public interface StudentService {
    StudentInfoResponse getStudentInfo(OnlyToken onlyToken);

    List<StudentClassResponse> getStudentClasses(OnlyToken onlyToken);

    StudentScoresResponse getStudentScores(OnlyToken onlyToken);

    List<SubjectScoreResponse> getScoresBySubject(SubjectScoresRequest request);

    List<Teacher> get_all_teacher_name(OnlyToken onlyToken);
}
