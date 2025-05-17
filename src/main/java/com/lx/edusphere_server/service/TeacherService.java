package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.teacher.*;
import com.lx.edusphere_server.entity.Student;

import java.util.List;

public interface TeacherService {
    TeacherInfoResponse getTeacherInfo(OnlyToken onlyToken);

    List<TeacherClassResponse> getTeacherClasses(OnlyToken onlyToken);

    List<TeacherClassResponse> get_teacher_class_for_all(OnlyToken onlyToken);

    List<ClassStudentResponse> getClassStudents(ClassStudentsRequest request);

    BaseResponse submitStudentScore(SubmitScoreRequest request);
    BaseResponse import_student_scores(ImportStudentScores import_student_scores);


    BaseResponse updateStudentScore(UpdateScoreRequest request);

    List<ClassScoreResponse> getClassScores(ClassScoresRequest request);

    List<OtherSubjectScoreResponse> getOtherSubjectScores(OtherScoresRequest request);

    List<Student> get_non_class_students(GetNonClassStudents get_non_class_students);

    BaseResponse addStudentsToClass(AddStudentsToClass add_students_to_class);

    BaseResponse remove_student_from_class(RemoveStudentFromClass remove_student_from_class);


    BaseResponse delete_student_score(DeleteStudentScore delete_student_score);


}
