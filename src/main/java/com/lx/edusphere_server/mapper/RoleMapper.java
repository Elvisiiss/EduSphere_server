package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.Role.ChooseOneRole;
import com.lx.edusphere_server.dto.Role.GetAllRolesInfo;
import com.lx.edusphere_server.entity.Teacher;
import com.lx.edusphere_server.entity.subject_teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {
    Boolean is_there_such_a_name(String name);
    List<Integer> getRoleIdsByUserId(String user_id);
    Set<String> getRoleNamesByUserId(Long user_id);

    void add_one_roles(ChooseOneRole chooseOneRole);

    List<GetAllRolesInfo> get_all_roles();

    ChooseOneRole get_role_info_by_role_id();

    void remove_role_power(ChooseOneRole chooseOneRole);
    void renew_role_power(ChooseOneRole chooseOneRole);
    void renew_role_name(ChooseOneRole chooseOneRole);
    Long get_role_id_by_role_name(String role_name);
    void remove_role_user(Long roleId);
    void delete_role(ChooseOneRole chooseOneRole);
    void create_user_from_login_page(String user_email);
    void create_user_from_admin_page(String user_email);

    List<Teacher> get_all_teacher_name();

}
