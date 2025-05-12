package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.Power.GetAllPowerInfo;
import com.lx.edusphere_server.dto.Role.ChooseOneRole;
import com.lx.edusphere_server.dto.Role.GetAllRolesInfo;
import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
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
}
