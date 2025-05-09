package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
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
    @PostMapping("/get_all_users_info")
    public List<GetAllUsersInfo> getAllUsers(@RequestBody OnlyToken onlyToken) {
        return adminService.getAllUsers(onlyToken);
    }

    @PostMapping("/user-delete/{id}")
    public BaseResponse deleteUser(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.deleteUser(adminChooseOneUser);
    }

    @PostMapping("/get_user_info_by_user_id")
    public User getUserInfoByUserId(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.getUserInfoByUserId(adminChooseOneUser);
    }

    @PostMapping("/update_user_info")
    public BaseResponse updateUserInfo(@RequestBody AdminChooseOneUser adminChooseOneUser) {
        return adminService.updateUserInfo(adminChooseOneUser);
    }
}