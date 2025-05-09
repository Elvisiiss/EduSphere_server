package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.entity.User;

import java.util.List;

public interface AdminService {

    List<GetAllUsersInfo> getAllUsers(OnlyToken onlyToken);

    BaseResponse deleteUser(AdminChooseOneUser adminChooseOneUser);

    User getUserInfoByUserId(AdminChooseOneUser adminChooseOneUser);

    BaseResponse updateUserInfo(AdminChooseOneUser adminChooseOneUser);
}
