package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.entity.User_information;
import com.lx.edusphere_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

}
