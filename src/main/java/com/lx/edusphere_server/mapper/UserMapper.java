package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    int insert(User user);
    int update(User user);
    User selectById(Long id);
    User findByEmail(String email);
    User findByUserName(String userName);


    default Optional<User> findByEmailOptional(String email) {
        return Optional.ofNullable(findByEmail(email));
    }
    default Optional<User> findByUserNameOptional(String userName) {
        return Optional.ofNullable(findByUserName(userName));
    }
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    default User save(User user) {
        if (user.getId() == null) {
            insert(user);
        } else {
            update(user);
        }
        return user;
    }
}