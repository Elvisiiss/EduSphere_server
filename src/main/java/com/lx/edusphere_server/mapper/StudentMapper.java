package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.student.*;
import com.lx.edusphere_server.dto.OnlyToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentInfoResponse getStudentInfo(OnlyToken onlyToken);

    List<StudentClassResponse> getStudentClasses(OnlyToken onlyToken);

    List<StudentScoreResponse> getStudentScores(OnlyToken onlyToken);

    List<StudentSubjectResponse> getStudentSubjects(OnlyToken onlyToken);

    List<SubjectScoreResponse> getScoresBySubject(SubjectScoresRequest request);
}
