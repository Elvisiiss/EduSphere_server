package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.teacher.*;
import com.lx.edusphere_server.entity.ExamScore;
import com.lx.edusphere_server.entity.Student;
import com.lx.edusphere_server.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // 获取教师个人信息
    @PostMapping("/get_teacher_info")
    public TeacherInfoResponse getTeacherInfo(@RequestBody OnlyToken onlyToken) {
        return teacherService.getTeacherInfo(onlyToken);
    }

    // 获取教师所教班级列表
    @PostMapping("/get_teacher_classes")
    public List<TeacherClassResponse> getTeacherClasses(@RequestBody OnlyToken onlyToken) {
        return teacherService.getTeacherClasses(onlyToken);
    }

    // 获取教师所教班级列表
    @PostMapping("/get_teacher_class_for_all")
    public List<TeacherClassResponse> get_teacher_class_for_all(@RequestBody OnlyToken onlyToken) {
        return teacherService.get_teacher_class_for_all(onlyToken);
    }

    // 获取班级学生列表
    @PostMapping("/get_class_students")
    public List<ClassStudentResponse> getClassStudents(@RequestBody ClassStudentsRequest request) {
        return teacherService.getClassStudents(request);
    }

    // 提交学生成绩
    @PostMapping("/submit_student_score")
    public BaseResponse submitStudentScore(@RequestBody SubmitScoreRequest request) {
        return teacherService.submitStudentScore(request);
    }
    // 提交学生成绩
    @PostMapping("/import_student_scores")
    public BaseResponse import_student_scores(@RequestBody ImportStudentScores import_student_scores) {
        return teacherService.import_student_scores(import_student_scores);
    }

    // 更新学生成绩
    @PostMapping("/update_student_score")
    public BaseResponse updateStudentScore(@RequestBody UpdateScoreRequest request) {
        return teacherService.updateStudentScore(request);
    }

    // 获取班级成绩(自己教授的科目)
    @PostMapping("/get_class_scores")
    public List<ClassScoreResponse> getClassScores(@RequestBody ClassScoresRequest request) {
        return teacherService.getClassScores(request);
    }

    // 获取班级其他科目成绩(只读)
    @PostMapping("/get_other_subject_scores")
    public List<OtherSubjectScoreResponse> getOtherSubjectScores(@RequestBody OtherScoresRequest request) {
        return teacherService.getOtherSubjectScores(request);
    }

    // 获取所有非本班学生用户（分页）
    @PostMapping("/get_non_class_students")
    public List<Student> get_non_class_students(@RequestBody GetNonClassStudents get_non_class_students) {
        return teacherService.get_non_class_students(get_non_class_students);
    }

    // 添加学生到班级上
    @PostMapping("/add_students_to_class")
    public BaseResponse add_students_to_class(@RequestBody AddStudentsToClass add_students_to_class) {
        return teacherService.addStudentsToClass(add_students_to_class);
    }

    // 删除班级上的某个学生
    @PostMapping("/remove_student_from_class")
    public BaseResponse remove_student_from_class(@RequestBody RemoveStudentFromClass remove_student_from_class) {
        return teacherService.remove_student_from_class(remove_student_from_class);
    }

    // 删除某个学生成绩
    @PostMapping("/delete_student_score")
    public BaseResponse delete_student_score(@RequestBody DeleteStudentScore delete_student_score) {
        return teacherService.delete_student_score(delete_student_score);
    }





}
