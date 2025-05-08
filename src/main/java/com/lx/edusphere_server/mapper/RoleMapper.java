package com.lx.edusphere_server.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {
    List<Integer> getRoleIdsByUserId(String user_id);
    Set<String> getRoleNamesByUserId(Long user_id);

}
