package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.User.ChooseOneImage;
import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.dto.User.ReSetPassword;
import com.lx.edusphere_server.entity.Image;
import com.lx.edusphere_server.entity.User_information;
import com.lx.edusphere_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") // 实际应用中应该限制跨域来源
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/get_my_information")
    public User_information sendRegisterCode(@RequestBody OnlyToken onlyToken) {
        return userService.get_my_information(onlyToken);
    }
    @PostMapping("/set_my_information")
    public BaseResponse sendRegisterCode(@RequestBody ChooseOneUserInformation chooseOneUserInformation) {
        return userService.set_my_information(chooseOneUserInformation);
    }
    /**
     * 图片/文件管理
     **/
    @PostMapping("/upload_img")
    public BaseResponse upload_img(
            @RequestParam("user_token") String user_token,
            @RequestParam("file") MultipartFile file,
            @RequestParam("file_name") String file_name) throws IOException {

        // 处理文件上传逻辑
        return userService.upload_img(user_token, file, file_name);
    }
    @PostMapping("/get_all_my_img")
    public List<Image> get_all_my_img(@RequestBody OnlyToken onlyToken) {
        return userService.get_all_my_img(onlyToken.getUser_token());
    }
    @PostMapping("/delete_my_img")
    public BaseResponse delete_my_img(@RequestBody ChooseOneImage choose_one_image) {
        return userService.delete_my_img(choose_one_image);
    }

    @PostMapping("/reset_passwd")
    public BaseResponse reset_passwd(@RequestBody ReSetPassword reset_passwd) {
        return userService.reset_passwd(reset_passwd);
    }

}
