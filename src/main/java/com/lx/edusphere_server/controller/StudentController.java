package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.student.*;
import com.lx.edusphere_server.entity.Teacher;
import com.lx.edusphere_server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 获取学生个人信息
    @PostMapping("/get_student_info")
    public StudentInfoResponse getStudentInfo(@RequestBody OnlyToken onlyToken) {
        return studentService.getStudentInfo(onlyToken);
    }

    // 获取学生所在班级
    @PostMapping("/get_student_classes")
    public List<StudentClassResponse> getStudentClasses(@RequestBody OnlyToken onlyToken) {
        return studentService.getStudentClasses(onlyToken);
    }

    // 获取学生成绩
    @PostMapping("/get_student_scores")
    public StudentScoresResponse getStudentScores(@RequestBody OnlyToken onlyToken) {
        return studentService.getStudentScores(onlyToken);
    }

    // 按科目筛选成绩
    @PostMapping("/get_scores_by_subject")
    public List<SubjectScoreResponse> getScoresBySubject(@RequestBody SubjectScoresRequest request) {
        return studentService.getScoresBySubject(request);
    }

    @PostMapping("/get_all_teacher_name")
    public List<Teacher> get_all_teacher_name(@RequestBody OnlyToken onlyToken) {
        return studentService.get_all_teacher_name(onlyToken);
    }
}
