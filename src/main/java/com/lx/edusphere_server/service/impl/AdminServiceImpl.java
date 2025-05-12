package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.Power.GetAllPowerInfo;
import com.lx.edusphere_server.dto.Role.ChooseOneRole;
import com.lx.edusphere_server.dto.Role.GetAllRolesInfo;
import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.entity.User;
import com.lx.edusphere_server.mapper.AdminMapper;
import com.lx.edusphere_server.mapper.PowerMapper;
import com.lx.edusphere_server.mapper.RoleMapper;
import com.lx.edusphere_server.service.AdminService;
import com.lx.edusphere_server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final RoleMapper roleMapper;
    private final PowerMapper powerMapper;

    private final UserService userService;

    public AdminServiceImpl(
            //mapper
            AdminMapper adminMapper,
            RoleMapper roleMapper,
            PowerMapper powerMapper,
            //服务
            UserService userService
    ) {
        //mapper
        this.adminMapper = adminMapper;
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
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
        adminMapper.add_one_user_users(adminChooseOneUser);
        adminChooseOneUser.setUser_id(adminMapper.select_user_id_by_user_number(adminChooseOneUser.getUser_number()));
        adminMapper.add_one_user_user_information(adminChooseOneUser);
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
        if(roleMapper.is_there_such_a_name(chooseOneRole.getRole_name())){
            return BaseResponse.error("更新角色信息失败，名称已存在。");
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

}
