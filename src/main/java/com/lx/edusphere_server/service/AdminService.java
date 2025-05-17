package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AdminService {
    /**
     * 用户管理
     */
    // 增加一个角色(用户管理：增)
    BaseResponse add_one_user(AdminChooseOneUser adminChooseOneUser);

    // 获取所有用户列表(用户管理：查)
    List<GetAllUsersInfo> getAllUsers(OnlyToken onlyToken);
    User getUserInfoByUserId(AdminChooseOneUser adminChooseOneUser);

    // 更新用户信息(用户管理：改)
    BaseResponse updateUserInfo(AdminChooseOneUser adminChooseOneUser);
    BaseResponse update_user_role(AdminChooseOneUser adminChooseOneUser);

    // 删除用户(用户管理：删)
    BaseResponse deleteUser(AdminChooseOneUser adminChooseOneUser);


    /**
     * 角色管理
     */
    // 增加一个角色(用户管理：增)
    BaseResponse add_one_roles(ChooseOneRole chooseOneRole);

    // 获取所有角色列表(用户管理：查)
    List<GetAllRolesInfo> get_all_roles(OnlyToken onlyToken);
    ChooseOneRole get_role_info_by_role_id(ChooseOneRole chooseOneRole);

    // 更新角色信息(角色管理：改)
    BaseResponse update_role_power(ChooseOneRole chooseOneRole);

    // 删除角色(角色管理：删)
    BaseResponse delete_role(ChooseOneRole chooseOneRole);


    /**
     * 权限管理
     */
    // 获取所有权限列表(用户管理：查)
    List<GetAllPowerInfo> get_all_powers(OnlyToken onlyToken);




    /**
     * 学科管理 subjects表
     */
    //增加一个学科(学科管理：增)
    BaseResponse add_one_subject(ChooseOneSubject choose_one_subject);


    //获取所有学科信息(学科管理:查)
    List<Subject> get_all_subjects(OnlyToken onlyToken);


    //修改学科(学科管理:改)
    BaseResponse update_subject(ChooseOneSubject choose_one_subject);


    //删除学科(学科管理:删)
    BaseResponse delete_subject(ChooseOneSubject choose_one_subject);


    /**
     * 班级管理  classes表
     * */
    //增加一个班级(班级管理：增)
    BaseResponse add_one_class(ChooseOneClass choose_one_class);


    //获取所有的班级(班级管理：查)
    List<GetOneClass> get_all_classes(OnlyToken onlyToken);
    //获取所有的教师
    List<GetAllTeacher> get_all_teacher(OnlyToken onlyToken);

    //修改班级信息(班级管理:改)
    BaseResponse update_class_information(ChooseOneClass choose_one_class);

    BaseResponse update_class_people(ChooseOneClass choose_one_class);

    //删除班级(班级管理:删)
    BaseResponse delete_class(ChooseOneClass choose_one_class);


}
