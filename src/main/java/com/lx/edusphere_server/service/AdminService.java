package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.admin.DeleteUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;

import java.util.List;

public interface AdminService {

    List<GetAllUsersInfo> getAllUsers(OnlyToken onlyToken);

    BaseResponse deleteUser(DeleteUser deleteUser);
}
