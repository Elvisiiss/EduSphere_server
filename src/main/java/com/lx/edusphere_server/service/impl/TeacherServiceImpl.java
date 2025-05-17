package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.teacher.*;
import com.lx.edusphere_server.entity.ExamScore;
import com.lx.edusphere_server.entity.Score;
import com.lx.edusphere_server.entity.Student;
import com.lx.edusphere_server.entity.Subject;
import com.lx.edusphere_server.mapper.PowerMapper;
import com.lx.edusphere_server.mapper.TeacherMapper;
import com.lx.edusphere_server.mapper.UserMapper;
import com.lx.edusphere_server.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;
    private final PowerMapper powerMapper;
    private final UserMapper userMapper;

    public TeacherServiceImpl(TeacherMapper teacherMapper, PowerMapper powerMapper, UserMapper userMapper) {
        this.teacherMapper = teacherMapper;
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
    }

    @Override
    public TeacherInfoResponse getTeacherInfo(OnlyToken onlyToken) {
        if (!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(), "查看教师信息")) {
            return null;
        }
        return teacherMapper.getTeacherInfo(onlyToken);
    }

    @Override
    public List<TeacherClassResponse> getTeacherClasses(OnlyToken onlyToken) {
        if (!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(), "查看教师班级")) {
            return List.of();
        }

        // 1. 先查询教师的基本班级信息
        List<TeacherClassResponse> classes = teacherMapper.getTeacherBasicClasses(onlyToken);

        // 2. 为每个班级查询对应的学科
        for (TeacherClassResponse classResponse : classes) {
            List<Subject> subjects = teacherMapper.getSubjectsForClass(onlyToken.getUser_token(), classResponse.getClassId());
            classResponse.setSubjects(subjects);
        }

        return classes;
    }

    @Override
    public List<TeacherClassResponse> get_teacher_class_for_all(OnlyToken onlyToken) {
        // 1. 先查询教师的基本班级信息
        List<TeacherClassResponse> classes = teacherMapper.getTeacherBasicClasses(onlyToken);

        // 2. 为每个班级查询对应的学科
        for (TeacherClassResponse classResponse : classes) {
            List<Subject> subjects = teacherMapper.getSubjectsForClassForAll(classResponse.getClassId());
            classResponse.setSubjects(subjects);
        }

        return classes;
    }

    @Override
    public List<ClassStudentResponse> getClassStudents(ClassStudentsRequest request) {
        if (!powerMapper.ifThisTokenCanDoThis(request.getUser_token(), "查看班级学生")) {
            return List.of();
        }
        return teacherMapper.getClassStudents(request);
    }

    @Override
    public BaseResponse submitStudentScore(SubmitScoreRequest request) {
        if (!powerMapper.ifThisTokenCanDoThis(request.getUser_token(), "提交学生成绩")) {
            return BaseResponse.error("提交成绩失败，权限不足");
        }

        ExamScore score = new ExamScore();
        score.setClassId(request.getClass_id());
        score.setStudentId(request.getStudent_id());
        score.setSubjectId(request.getSubject_id());
        score.setExamName(request.getExam_name());
        score.setExamDate(request.getExam_date());
        score.setScore(request.getScore());
        score.setGraderId(teacherMapper.getTeacherIdByToken(request.getUser_token()));
        score.setComment(request.getComment());

        teacherMapper.submitStudentScore(score);
        return BaseResponse.success("成绩提交成功");
    }

    @Override
    public BaseResponse import_student_scores(ImportStudentScores import_student_scores) {
        Long teacher_id = userMapper.get_user_id_by_user_token(import_student_scores.getUser_token());
        Long class_id = import_student_scores.getClass_id();
        Long subject_id = import_student_scores.getSubject_id();
        List<Score> scores = import_student_scores.getScores();
        for (Score score : scores) {
            teacherMapper.import_student_scores(
                    class_id,
                    score.getStudent_id(),
                    subject_id,
                    score.getExam_name(),
                    score.getExam_date(),
                    score.getScore(),
                    teacher_id,
                    score.getComment()
            );
        }
        return BaseResponse.success("成绩提交成功");
    }

    @Override
    public BaseResponse updateStudentScore(UpdateScoreRequest request) {
        if (!powerMapper.ifThisTokenCanDoThis(request.getUser_token(), "更新学生成绩")) {
            return BaseResponse.error("更新成绩失败，权限不足");
        }

        teacherMapper.updateStudentScore(request);
        return BaseResponse.success("成绩更新成功");
    }

    @Override
    public List<ClassScoreResponse> getClassScores(ClassScoresRequest class_scores_request) {
        if (!powerMapper.ifThisTokenCanDoThis(class_scores_request.getUser_token(), "查看班级成绩")) {
            return List.of();
        }
        return teacherMapper.getClassScores(class_scores_request);
    }

    @Override
    public List<OtherSubjectScoreResponse> getOtherSubjectScores(OtherScoresRequest request) {
        if (!powerMapper.ifThisTokenCanDoThis(request.getUser_token(), "查看其他科目成绩")) {
            return List.of();
        }
        return teacherMapper.getOtherSubjectScores(request);
    }

    @Override
    public List<Student> get_non_class_students(GetNonClassStudents get_non_class_students) {
        get_non_class_students.setPage(get_non_class_students.getPage() - 1);
        return teacherMapper.get_non_class_students(get_non_class_students);
    }

    @Override
    public BaseResponse addStudentsToClass(AddStudentsToClass add_students_to_class) {
        for (Long student_id : add_students_to_class.getStudent_id()) {
            teacherMapper.add_students_to_class(add_students_to_class.getClass_id() ,student_id);
        }
        return BaseResponse.success("添加学生成功");
    }

    @Override
    public BaseResponse remove_student_from_class(RemoveStudentFromClass remove_student_from_class) {
        teacherMapper.remove_student_from_class(remove_student_from_class);
        return BaseResponse.success("删除学生成功");
    }

    @Override
    public BaseResponse delete_student_score(DeleteStudentScore delete_student_score) {
        teacherMapper.delete_student_score(delete_student_score);
        return BaseResponse.success("删除学生成绩成功");
    }
}
