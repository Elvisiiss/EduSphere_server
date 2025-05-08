package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.UserResponse;
import com.lx.edusphere_server.dto.admin.DeleteUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.entity.User;
import com.lx.edusphere_server.mapper.AdminMapper;
import com.lx.edusphere_server.mapper.PowerMapper;
import com.lx.edusphere_server.mapper.RoleMapper;
import com.lx.edusphere_server.mapper.UserMapper;
import com.lx.edusphere_server.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
    public BaseResponse deleteUser(DeleteUser deleteUser) {
        try {
            adminMapper.deleteById(deleteUser.getUserId()); // 需要先在UserMapper中添加deleteById方法
            return BaseResponse.success("用户删除成功");
        } catch (Exception e) {
            return BaseResponse.error("删除用户失败: " + e.getMessage());
        }
    }


}