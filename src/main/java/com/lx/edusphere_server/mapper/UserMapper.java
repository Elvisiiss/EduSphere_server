package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.entity.User_information;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User_information get_my_information_by_token(String user_token);
    Boolean is_this_token_mine(String user_token, Long user_id);
    void set_my_information(ChooseOneUserInformation chooseOneUserInformation);


    void delete_one_user_roles_by_user_id(Long userId);
    void delete_one_user_information_by_user_id(Long userId);
    void delete_one_user_by_user_id(Long userId);
}
