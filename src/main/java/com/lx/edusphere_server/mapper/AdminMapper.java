package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<User> selectAll();
    int deleteById(Long user_id);
}
