package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.EmailCodeResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.entity.User_information;

public interface UserService {
    User_information get_my_information(OnlyToken onlyToken);

    BaseResponse set_my_information(ChooseOneUserInformation chooseOneUserInformation);


    void delete_one_user_by_user_id(Long user_id);
}
