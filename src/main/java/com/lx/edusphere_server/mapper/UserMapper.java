package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.entity.Image;
import com.lx.edusphere_server.entity.Image_in_table;
import com.lx.edusphere_server.entity.User_information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User_information get_my_information_by_token(String user_token);
    Boolean is_this_token_mine(String user_token, Long user_id);
    void set_my_information(ChooseOneUserInformation chooseOneUserInformation);


    void delete_one_user_roles_by_user_id(Long userId);
    void delete_one_user_information_by_user_id(Long userId);
    void delete_one_user_by_user_id(Long userId);

    Long get_user_id_by_user_token(String user_token);


    List<Image> get_all_my_file(Long user_id);

    void upload_img(Long belong_user, String file_url, String file_name);

    void delete_file(Long user_id, String file_url);

    Image_in_table get_one_file(String file_url);

    boolean get_user_password(Long user_id, String user_password);

    void set_new_password(Long user_id, String new_password);
}
