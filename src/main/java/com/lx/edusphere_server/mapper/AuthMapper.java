package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuthMapper {
    User findByEmail(String user_email);
    User findByUserNumber(String user_number);
    boolean existsByEmail(String user_email);
    boolean existsByUserName(String user_name);
    void updatePassword(User user);
    void CreateUser(User user);
    Long select_user_id_by_user_number(String user_number);
}
