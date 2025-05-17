package com.lx.edusphere_server.controller;

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
import com.lx.edusphere_server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // 实际应用中应该限制跨域来源
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    /**
     * 用户管理
     */
    // 增加一个角色(用户管理：增)
    @PostMapping("/add_one_user")
    public BaseResponse add_one_user(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.add_one_user(adminChooseOneUser);
    }
    // 获取所有用户列表(用户管理：查)
    @PostMapping("/get_all_users_info")
    public List<GetAllUsersInfo> getAllUsers(@RequestBody OnlyToken onlyToken) {
        return adminService.getAllUsers(onlyToken);
    }
    @PostMapping("/get_user_info_by_user_id")
    public User getUserInfoByUserId(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.getUserInfoByUserId(adminChooseOneUser);
    }
    // 更新用户信息(用户管理：改)
    @PostMapping("/update_user_info")
    public BaseResponse updateUserInfo(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.updateUserInfo(adminChooseOneUser);
    }
    @PostMapping("/update_user_role")
    public BaseResponse update_user_role(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.update_user_role(adminChooseOneUser);
    }
    // 删除用户(用户管理：删)
    @PostMapping("/delete_user")
    public BaseResponse deleteUser(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.deleteUser(adminChooseOneUser);
    }


    /**
     * 角色管理
     */
    // 增加一个角色(用户管理：增)
    @PostMapping("/add_one_role")
    public BaseResponse add_one_roles(@RequestBody ChooseOneRole chooseOneRole) {
        return adminService.add_one_roles(chooseOneRole);
    }
    // 获取所有角色列表(用户管理：查)
    @PostMapping("/get_all_roles")
    public List<GetAllRolesInfo> get_all_roles(@RequestBody OnlyToken onlyToken) {
        return adminService.get_all_roles(onlyToken);
    }
    @PostMapping("/get_role_info_by_role_id")
    public ChooseOneRole get_role_info_by_role_id(@RequestBody ChooseOneRole chooseOneRole) {
        return adminService.get_role_info_by_role_id(chooseOneRole);
    }
    // 更新角色信息(角色管理：改)
    @PostMapping("/update_role_power")
    public BaseResponse update_role_power(@RequestBody ChooseOneRole chooseOneRole) {
        return adminService.update_role_power(chooseOneRole);
    }
    // 删除角色(角色管理：删)
    @PostMapping("/delete_role")
    public BaseResponse delete_role(@RequestBody ChooseOneRole chooseOneRole) {
        return adminService.delete_role(chooseOneRole);
    }


    /**
     * 权限管理
     */
    // 获取所有权限列表(用户管理：查)
    @PostMapping("/get_all_powers")
    public List<GetAllPowerInfo> get_all_powers(@RequestBody OnlyToken onlyToken) {
        return adminService.get_all_powers(onlyToken);
    }



    /**
     * 学科管理 subjects表
     */
    //增加一个学科(学科管理：增)
    @PostMapping("/add_one_subject")
    public BaseResponse add_one_subject(@RequestBody ChooseOneSubject choose_one_subject) {
        return adminService.add_one_subject(choose_one_subject);
    }

    //获取所有学科信息(学科管理:查)
    @PostMapping("/get_all_subjects")
    public List<Subject> get_all_subjects(@RequestBody OnlyToken onlyToken) {
        return adminService.get_all_subjects(onlyToken);
    }

    //修改学科(学科管理:改)
    @PostMapping("/update_subject")
    public BaseResponse update_subject(@RequestBody ChooseOneSubject choose_one_subject) {
        return adminService.update_subject(choose_one_subject);
    }


    //删除学科(学科管理:删)
    @PostMapping("/delete_subject")
    public BaseResponse delete_subject(@RequestBody ChooseOneSubject choose_one_subject) {
        return adminService.delete_subject(choose_one_subject);
    }


    /**
     * 班级管理  classes表
     * */
    //增加一个班级(班级管理：增)
    @PostMapping("/add_one_class")
    public BaseResponse add_one_class(@RequestBody ChooseOneClass choose_one_class) {
        return adminService.add_one_class(choose_one_class);
    }
    //获取所有的班级(班级管理：查)
    @PostMapping("/get_all_classes")
    public List<GetOneClass> get_all_classes(@RequestBody OnlyToken onlyToken) {
        return adminService.get_all_classes(onlyToken);
    }

    //获取所有的教师
    @PostMapping("/get_all_teacher")
    public List<GetAllTeacher> get_all_teacher(@RequestBody OnlyToken onlyToken) {
        return adminService.get_all_teacher(onlyToken);
    }

    //修改班级信息(班级管理:改)
    @PostMapping("/update_class_information")
    public BaseResponse update_class_information(@RequestBody ChooseOneClass choose_one_class) {
        return adminService.update_class_information(choose_one_class);
    }
    @PostMapping("/update_class_people")
    public BaseResponse update_class_people(@RequestBody ChooseOneClass choose_one_class) {
        return adminService.update_class_people(choose_one_class);
    }

    //删除班级(班级管理:删)
    @PostMapping("/delete_class")
    public BaseResponse delete_class(@RequestBody ChooseOneClass choose_one_class) {
        return adminService.delete_class(choose_one_class);
    }
}
