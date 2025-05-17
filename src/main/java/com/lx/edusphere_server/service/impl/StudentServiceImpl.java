package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.student.*;
import com.lx.edusphere_server.entity.Teacher;
import com.lx.edusphere_server.mapper.PowerMapper;
import com.lx.edusphere_server.mapper.RoleMapper;
import com.lx.edusphere_server.mapper.StudentMapper;
import com.lx.edusphere_server.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final PowerMapper powerMapper;
    private final RoleMapper roleMapper;

    public StudentServiceImpl(StudentMapper studentMapper, PowerMapper powerMapper, RoleMapper roleMapper) {
        this.studentMapper = studentMapper;
        this.powerMapper = powerMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public StudentInfoResponse getStudentInfo(OnlyToken onlyToken) {
        if (!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(), "查看学生信息")) {
            return null;
        }
        return studentMapper.getStudentInfo(onlyToken);
    }

    @Override
    public List<StudentClassResponse> getStudentClasses(OnlyToken onlyToken) {
        if (!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(), "查看学生班级")) {
            return List.of();
        }
        return studentMapper.getStudentClasses(onlyToken);
    }

    @Override
    public StudentScoresResponse getStudentScores(OnlyToken onlyToken) {
        if (!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(), "查看学生成绩")) {
            return null;
        }

        StudentScoresResponse response = new StudentScoresResponse();
        response.setScores(studentMapper.getStudentScores(onlyToken));
        response.setSubjects(studentMapper.getStudentSubjects(onlyToken));

        return response;
    }

    @Override
    public List<SubjectScoreResponse> getScoresBySubject(SubjectScoresRequest request) {
        if (!powerMapper.ifThisTokenCanDoThis(request.getUser_token(), "查看科目成绩")) {
            return List.of();
        }
        return studentMapper.getScoresBySubject(request);
    }

    @Override
    public List<Teacher> get_all_teacher_name(OnlyToken onlyToken) {
        return roleMapper.get_all_teacher_name();
    }
}
