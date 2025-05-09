package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.entity.User;
import com.lx.edusphere_server.mapper.AdminMapper;
import com.lx.edusphere_server.mapper.PowerMapper;
import com.lx.edusphere_server.mapper.RoleMapper;
import com.lx.edusphere_server.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final RoleMapper roleMapper;
    private final PowerMapper powerMapper;

    public AdminServiceImpl(AdminMapper adminMapper, RoleMapper roleMapper, PowerMapper powerMapper) {
        this.adminMapper = adminMapper;
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
    }

    @Override
    public List<GetAllUsersInfo> getAllUsers(OnlyToken onlyToken) {
        if(!powerMapper.ifThisTokenCanDoThis(onlyToken.getUser_token(),"查看所有用户信息")){
            return null;
        }

        List<User> users = adminMapper.selectAll();
        return users.stream().map(user -> new GetAllUsersInfo("返回用户信息",user.getUser_id(), user.getUser_name(),
                user.getUser_email(),user.getUser_token(),roleMapper.getRoleNamesByUserId(user.getUser_id()))).collect(Collectors.toList());
    }

    @Override
    public BaseResponse deleteUser(AdminChooseOneUser adminChooseOneUser) {
        try {
            adminMapper.deleteById(adminChooseOneUser.getUser_id()); // 需要先在UserMapper中添加deleteById方法
            return BaseResponse.success("用户删除成功");
        } catch (Exception e) {
            return BaseResponse.error("删除用户失败: " + e.getMessage());
        }
    }

    @Override
    public User getUserInfoByUserId(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"查看所有用户信息")){
            return null;
        }
        return adminMapper.selectById(adminChooseOneUser.getUser_id());
    }//管理员端  获取某个用户的所有信息

    @Override
    public BaseResponse updateUserInfo(AdminChooseOneUser adminChooseOneUser) {
        if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"修改用户基础信息")){
            return BaseResponse.error("修改用户失败，权限不够。");
        }
        Boolean changePassword = !adminChooseOneUser.getUser_password().isEmpty() && adminChooseOneUser.getUser_password()!=null;
        Boolean changeUserNumber = true;
        if(!adminChooseOneUser.getUser_password().isEmpty() && adminChooseOneUser.getUser_password()!=null){
            if(!powerMapper.ifThisTokenCanDoThis(adminChooseOneUser.getUser_token(),"修改用户密码")){
                return BaseResponse.error("修改用户密码失败，权限不够。");
            }
            adminMapper.updateUserPassword(adminChooseOneUser.getUser_id(),adminChooseOneUser.getUser_password());
        }



        return BaseResponse.success("用户修改成功");
    }


}