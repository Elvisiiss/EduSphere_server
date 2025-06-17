package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.Power.GetAllPowerInfo;
import com.lx.edusphere_server.dto.Role.ChooseOneRole;
import com.lx.edusphere_server.dto.Role.GetAllRolesInfo;
import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.dto.classes.ChooseOneClass;
import com.lx.edusphere_server.dto.classes.GetOneClass;
import com.lx.edusphere_server.dto.subject.ChooseOneSubject;
import com.lx.edusphere_server.dto.teacher.GetAllTeacher;
import com.lx.edusphere_server.entity.Subject;
import com.lx.edusphere_server.entity.User;
import com.lx.edusphere_server.entity.subject_teacher;
import com.lx.edusphere_server.mapper.*;
import com.lx.edusphere_server.service.AdminService;
import com.lx.edusphere_server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final RoleMapper roleMapper;
    private final PowerMapper powerMapper;
    private final SubjectMapper subjectMapper;
    private final ClassMapper classMapper;

    private final UserService userService;

    public AdminServiceImpl(
            //mapper
            AdminMapper adminMapper,
            RoleMapper roleMapper,
            PowerMapper powerMapper,
            SubjectMapper subjectMapper,
            ClassMapper classMapper,
            //服务
            UserService userService
    ) {
        //mapper
        this.adminMapper = adminMapper;
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
        this.subjectMapper = subjectMapper;
        this.classMapper = classMapper;
        //服务
        this.userService = userService;
    }






    /**
     * 用户管理
     */
    // 增加一个角色(用户管理：增)
    @Override
    public BaseResponse add_one_user(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"增加角色")){
            return BaseResponse.error("增加角色失败，权限不够。");
        }
        if(adminChooseOneUser.getUser_number().isEmpty()){
            return BaseResponse.error("增加角色失败，学号不能为空。");
        }
        if(adminChooseOneUser.getUser_name().isEmpty()){
            return BaseResponse.error("增加角色失败，用户名不能为空。");
        }
        if(adminChooseOneUser.getUser_email().isEmpty()){
            return BaseResponse.error("增加角色失败，邮箱不能为空。");
        }
        adminMapper.add_one_user_users(adminChooseOneUser);
        adminChooseOneUser.setUser_id(adminMapper.select_user_id_by_user_number(adminChooseOneUser.getUser_number()));
        adminMapper.add_one_user_user_information(adminChooseOneUser);
        roleMapper.create_user_from_admin_page(adminChooseOneUser.getUser_email());
        return BaseResponse.success("增加角色成功");
    }
    // 获取所有用户列表(用户管理：查)
    @Override
    public List<GetAllUsersInfo> getAllUsers(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"查看所有用户信息")){
            return null;
        }
        return adminMapper.get_all_users();
    }

    @Override
    public User getUserInfoByUserId(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"查看所有用户信息")){
            return null;
        }
        return adminMapper.selectById(adminChooseOneUser.getUser_id());
    }//管理员端  获取某个用户的所有信息


    // 更新用户信息(用户管理：改)
    @Override
    public BaseResponse updateUserInfo(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"修改用户基础信息")){
            return BaseResponse.error("修改用户失败，权限不够。");
        }
        if(!adminChooseOneUser.getUser_password().isEmpty() && adminChooseOneUser.getUser_password()!=null){
            if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"修改用户密码")){
                return BaseResponse.error("修改用户密码失败，权限不够。");
            }
            adminMapper.updateUserPassword(adminChooseOneUser);
        }
        adminMapper.updateUserInformation(adminChooseOneUser);
        return BaseResponse.success("用户修改成功");
    }

    @Override
    public BaseResponse update_user_role(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"修改用户角色")){
            return BaseResponse.error("修改用户角色失败，权限不够。");
        }
        adminMapper.remove_user_role(adminChooseOneUser);
        try{
            adminMapper.renew_user_role(adminChooseOneUser);
        } catch (Exception e) {
            return BaseResponse.success("修改用户角色成功，空权限。");
        }
        return BaseResponse.success("修改用户角色成功");
    }



    // 删除用户(用户管理：删)
    @Override
    public BaseResponse deleteUser(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"删除用户")){
            return BaseResponse.error("删除用户失败，权限不够。");
        }
        userService.delete_one_user_by_user_id(adminChooseOneUser.getUser_id());
        return BaseResponse.success("删除用户成功");
    }



    /**
     * 角色管理
     */
    // 增加一个角色(用户管理：增)
    @Override
    public BaseResponse add_one_roles(ChooseOneRole chooseOneRole) {
        if(!powerMapper.ifThisTokenCanDoThis(chooseOneRole.getUser_token(),"增加角色")){
            return BaseResponse.error("增加角色失败，权限不够。");
        }
        if(chooseOneRole.getRole_name().isEmpty()){
            return BaseResponse.error("增加角色失败，名称不能为空。");
        }
        if(roleMapper.is_there_such_a_name(chooseOneRole.getRole_name())){
            return BaseResponse.error("增加角色失败，名称已存在。");
        }
        roleMapper.add_one_roles(chooseOneRole);
        chooseOneRole.setRole_id(roleMapper.get_role_id_by_role_name(chooseOneRole.getRole_name()));
        roleMapper.renew_role_power(chooseOneRole);
        return BaseResponse.success("增加角色成功");
    }


    // 获取所有角色列表(用户管理：查)
    @Override
    public List<GetAllRolesInfo> get_all_roles(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"查看所有角色信息")){
            return List.of();
        }
        return roleMapper.get_all_roles();
    }

    @Override
    public ChooseOneRole get_role_info_by_role_id(ChooseOneRole chooseOneRole) {
        if(!powerMapper.ifThisTokenCanDoThis(chooseOneRole.getUser_token(),"查看所有角色信息")){
            return null;
        }
        return roleMapper.get_role_info_by_role_id();
    }


    // 更新角色信息(角色管理：改)
    @Override
    public BaseResponse update_role_power(ChooseOneRole chooseOneRole) {
        if(!powerMapper.ifThisTokenCanDoThis(chooseOneRole.getUser_token(),"更新角色信息")){
            return BaseResponse.error("更新角色信息失败，权限不足");
        }
        roleMapper.remove_role_power(chooseOneRole);
        roleMapper.renew_role_name(chooseOneRole);
        try{
            roleMapper.renew_role_power(chooseOneRole);
        } catch (Exception e) {
            return BaseResponse.success("更新角色信息成功，空权限。");
        }
        return BaseResponse.success("更新角色信息成功");
    }


    // 删除角色(角色管理：删)
    @Override
    public BaseResponse delete_role(ChooseOneRole chooseOneRole) {
        if(!powerMapper.ifThisTokenCanDoThis(chooseOneRole.getUser_token(),"删除角色")){
            return BaseResponse.error("删除角色失败，权限不足");
        }
        roleMapper.remove_role_power(chooseOneRole);
        roleMapper.remove_role_user(chooseOneRole.getRole_id());
        roleMapper.delete_role(chooseOneRole);
        return BaseResponse.success("删除角色成功");
    }



    /**
     * 权限管理
     */
    // 获取所有权限列表(用户管理：查)
    @Override
    public List<GetAllPowerInfo> get_all_powers(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"查看所有权限列表")){
            return null;
        }
        return powerMapper.get_all_powers();
    }






    /**
     * 学科管理 subjects表
     */
    //增加一个学科(学科管理：增)
    @Override
    public BaseResponse add_one_subject(ChooseOneSubject choose_one_subject) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_subject.getUser_token(),"增加学科")){
            return BaseResponse.error("增加学科失败，权限不够。");
        }
        if(choose_one_subject.getSubject_name().isEmpty()){
            return BaseResponse.error("增加学科失败，名称不能为空。");
        }
        subjectMapper.add_one_subject(choose_one_subject);
        return BaseResponse.success("增加学科成功");
    }

    //获取所有学科信息(学科管理:查)
    @Override
    public List<Subject> get_all_subjects(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"查看所有学科")){
            return List.of();
        }
        return subjectMapper.get_all_subjects();
    }

    //修改学科(学科管理:改)
    @Override
    public BaseResponse update_subject(ChooseOneSubject choose_one_subject) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_subject.getUser_token(),"修改学科")){
            return BaseResponse.error("修改学科失败，权限不够。");
        }
        if(choose_one_subject.getSubject_name().isEmpty()){
            return BaseResponse.error("修改学科失败，名称不能为空。");
        }
        subjectMapper.update_subject(choose_one_subject);
        return BaseResponse.success("修改学科成功");
    }


    //删除学科(学科管理:删)
    @Override
    public BaseResponse delete_subject(ChooseOneSubject choose_one_subject) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_subject.getUser_token(),"删除学科")){
            return BaseResponse.error("删除学科失败，权限不够。");
        }
        if(subjectMapper.is_there_a_class_that_has_this_course(choose_one_subject.getSubject_id())){
            return BaseResponse.error("删除学科失败，有班级正在学习这门科。");
        }
        subjectMapper.delete_subject(choose_one_subject.getSubject_id());
        return BaseResponse.success("删除学科成功");
    }


    /**
     * 班级管理  classes表
     * */
    //增加一个班级(班级管理：增)
    @Override
    public BaseResponse add_one_class(ChooseOneClass choose_one_class) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_class.getUser_token(),"增加班级")){
            return BaseResponse.error("增加班级失败，权限不够。");
        }
        if(classMapper.get_class_id_by_class_name(choose_one_class.getClass_name())!=null){
            return BaseResponse.error("班级名已存在");
        }
        classMapper.add_one_class(choose_one_class);
        choose_one_class.setClass_id(classMapper.get_class_id_by_class_name(choose_one_class.getClass_name()));
        for (subject_teacher subject_teacher : choose_one_class.getSubject_teacher()) {
            classMapper.for_class_add_subject_teacher(choose_one_class.getClass_id(),subject_teacher.getTeacher_id(),subject_teacher.getSubject_id());
        }
        return BaseResponse.success("增加班级成功");
    }

    //获取所有的班级(班级管理：查)
    @Override
    public List<GetOneClass> get_all_classes(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"查看班级信息")){
            return List.of();
        }

        // 获取所有班级的基本信息
        List<GetOneClass> classes = classMapper.selectAllClassesBasicInfo();

        // 为每个班级填充详细信息
        for (GetOneClass cls : classes) {
            // 获取学生ID集合
            Set<Integer> studentIds = classMapper.selectStudentIdsByClassId(cls.getClass_id());
            cls.setStudents_id(studentIds);


            // 获取学科教师关联信息
            Set<subject_teacher> subjectTeachers = classMapper.selectSubjectTeachersByClassId(cls.getClass_id());
            cls.setSubject_teacher(subjectTeachers);
        }

        return classes;
    }

    //获取所有的教师
    @Override
    public List<GetAllTeacher> get_all_teacher(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"获取所有的教师")){
            return List.of();
        }
        return adminMapper.get_all_teacher();
    }


    //修改班级信息(班级管理:改)
    @Override
    public BaseResponse update_class_information(ChooseOneClass choose_one_class) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_class.getUser_token(),"获取所有的教师")){
            return BaseResponse.error("获取教师失败，权限不够。");
        }
        classMapper.update_class(choose_one_class);
        classMapper.delete_class_in_teacher(choose_one_class.getClass_id());
        for (subject_teacher subject_teacher : choose_one_class.getSubject_teacher()) {
            classMapper.for_class_add_subject_teacher(choose_one_class.getClass_id(),subject_teacher.getTeacher_id(),subject_teacher.getSubject_id());

        }
        return BaseResponse.success("修改班级信息成功");
    }

    @Override
    public BaseResponse update_class_people(ChooseOneClass choose_one_class) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_class.getUser_token(),"修改班级人员")){
            return BaseResponse.error("修改班级人员失败，权限不够。");
        }
        return BaseResponse.success("修改班级人员成功");
    }

    //删除班级(班级管理:删)
    @Override
    public BaseResponse delete_class(ChooseOneClass choose_one_class) {
        if(!powerMapper.ifThisTokenCanDoThis(choose_one_class.getUser_token(),"删除班级")){
            return BaseResponse.error("删除班级失败，权限不够。");
        }
        Long class_id = choose_one_class.getClass_id();
        classMapper.delete_class_exam_scores(class_id);
        classMapper.delete_class_in_students(class_id);
        classMapper.delete_class_in_teacher(class_id);
        classMapper.delete_class(class_id);
        return BaseResponse.success("删除班级成功");
    }
}
