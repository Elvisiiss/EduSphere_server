package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.admin.AdminChooseOneUser;
import com.lx.edusphere_server.dto.admin.GetAllUsersInfo;
import com.lx.edusphere_server.dto.teacher.GetAllTeacher;
import com.lx.edusphere_server.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    void add_one_user_users(AdminChooseOneUser adminChooseOneUser);
    void add_one_user_user_information(AdminChooseOneUser adminChooseOneUser);

    List<GetAllUsersInfo> get_all_users();
    Long select_user_id_by_user_number(String user_number);
    User selectById(Long user_id);
    void updateUserPassword(AdminChooseOneUser adminChooseOneUser);
    void updateUserInformation(AdminChooseOneUser adminChooseOneUser);

    void remove_user_role(AdminChooseOneUser adminChooseOneUser);
    void renew_user_role(AdminChooseOneUser adminChooseOneUser);

    List<GetAllTeacher> get_all_teacher();
}
