package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.teacher.*;
import com.lx.edusphere_server.entity.ExamScore;
import com.lx.edusphere_server.entity.Student;
import com.lx.edusphere_server.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TeacherMapper {
    TeacherInfoResponse getTeacherInfo(OnlyToken onlyToken);

    List<TeacherClassResponse> getTeacherBasicClasses(OnlyToken onlyToken);
    List<Subject> getSubjectsForClass(String user_token, Long class_id);
    List<Subject> getSubjectsForClassForAll(Long class_id);

    List<ClassStudentResponse> getClassStudents(ClassStudentsRequest request);

    Long getTeacherIdByToken(String token);

    void submitStudentScore(ExamScore score);
    void import_student_scores(Long class_id,
                               Long student_id,
                               Long subject_id,
                               String exam_name,
                               LocalDate exam_date,
                               Double score,
                               Long grader_id,
                               String comment
    );

    void updateStudentScore(UpdateScoreRequest request);

    List<ClassScoreResponse> getClassScores(ClassScoresRequest class_scores_request);

    List<OtherSubjectScoreResponse> getOtherSubjectScores(OtherScoresRequest request);

    List<Student> get_non_class_students(GetNonClassStudents get_non_class_students);

    void add_students_to_class(Long class_id, Long student_id);

    void remove_student_from_class(RemoveStudentFromClass removeStudentFromClass);

    void delete_student_score(DeleteStudentScore deleteStudentScore);

    }
