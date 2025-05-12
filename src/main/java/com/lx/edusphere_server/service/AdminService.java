package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.Power.GetAllPowerInfo;
import com.lx.edusphere_server.dto.Role.ChooseOneRole;
import com.lx.edusphere_server.dto.Role.GetAllRolesInfo;
import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.entity.User;

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
}
