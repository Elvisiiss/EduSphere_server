package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User findByEmail(String user_email);
    User findByUserName(String user_name);
    boolean existsByEmail(String user_email);
    boolean existsByUserName(String user_name);
    int updatePassword(User user);
    int CreateUser(User user);
}