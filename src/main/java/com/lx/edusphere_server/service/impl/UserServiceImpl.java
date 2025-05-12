package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.EmailCodeResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.entity.User_information;
import com.lx.edusphere_server.mapper.UserMapper;
import com.lx.edusphere_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper
    ) {
        this.userMapper = userMapper;
    }

    @Override
    public User_information get_my_information(OnlyToken onlyToken) {
        return userMapper.get_my_information_by_token(onlyToken.getUser_token());
    }

    @Override
    public BaseResponse set_my_information(ChooseOneUserInformation chooseOneUserInformation) {
        if(!userMapper.is_this_token_mine(chooseOneUserInformation.getUser_token(), chooseOneUserInformation.getUser_id())){
            return new BaseResponse("token不符","400");
        }
        userMapper.set_my_information(chooseOneUserInformation);
        return new BaseResponse("保存成功","500");
    }

    /**
     * 工具方法。，不只是控制器，都可以用。
     */
    @Override
    public void delete_one_user_by_user_id(Long user_id) {
        userMapper.delete_one_user_roles_by_user_id(user_id);
        userMapper.delete_one_user_information_by_user_id(user_id);
        userMapper.delete_one_user_by_user_id(user_id);
    }
}
