package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.User.ChooseOneImage;
import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.dto.User.ReSetPassword;
import com.lx.edusphere_server.entity.Image;
import com.lx.edusphere_server.entity.User_information;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User_information get_my_information(OnlyToken onlyToken);

    BaseResponse set_my_information(ChooseOneUserInformation chooseOneUserInformation);

    void delete_one_user_by_user_id(Long user_id);

    List<Image> get_all_my_img(String user_token);

    BaseResponse delete_my_img(ChooseOneImage choose_one_image);

    BaseResponse upload_img(String user_token, MultipartFile file, String file_name) throws IOException;

    BaseResponse reset_passwd(ReSetPassword reset_passwd);
}
