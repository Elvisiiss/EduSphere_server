package com.lx.edusphere_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TokenMapper {
    int updateToken(@Param("user_name") String user_name, @Param("user_token") String user_token);
}
