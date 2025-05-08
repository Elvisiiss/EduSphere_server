package com.lx.edusphere_server.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PowerMapper {
    Set<Integer> getPowerIdsByUserName(String user_name);
    Set<Integer> getPowerIdsByUserToken(String user_token);
    Boolean ifThisTokenCanDoThis(String user_token, String power_name);

}
