package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.EmailCodeResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.User.ChooseOneImage;
import com.lx.edusphere_server.dto.User.ChooseOneUserInformation;
import com.lx.edusphere_server.dto.User.ReSetPassword;
import com.lx.edusphere_server.entity.Image;
import com.lx.edusphere_server.entity.Image_in_table;
import com.lx.edusphere_server.entity.User_information;
import com.lx.edusphere_server.mapper.UserMapper;
import com.lx.edusphere_server.service.UserService;
import com.lx.edusphere_server.tools.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.file.where}")
    private String Base_url;

    @Value("${spring.file.give_all}")
    private String Base_url_give_all;

    @Value("${spring.file.deleted}")
    private String Base_url_deleted;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper
    ) {
        this.userMapper = userMapper;
    }

    @Override
    public User_information get_my_information(OnlyToken onlyToken) {
        return userMapper.get_my_information_by_token(onlyToken.getUser_token());
    }

    @Override
    public BaseResponse set_my_information(ChooseOneUserInformation chooseOneUserInformation) {
        if(!userMapper.is_this_token_mine(chooseOneUserInformation.getUser_token(), chooseOneUserInformation.getUser_id())){
            return new BaseResponse("token不符","400");
        }
        userMapper.set_my_information(chooseOneUserInformation);
        return new BaseResponse("保存成功","500");
    }

    /**
     * 工具方法。，不只是控制器，都可以用。
     */
    @Override
    public void delete_one_user_by_user_id(Long user_id) {
        userMapper.delete_one_user_roles_by_user_id(user_id);
        userMapper.delete_one_user_information_by_user_id(user_id);
        userMapper.delete_one_user_by_user_id(user_id);
    }

    @Override
    public List<Image> get_all_my_img(String user_token) {
        Long user_id = userMapper.get_user_id_by_user_token(user_token);
        return userMapper.get_all_my_file(user_id);
    }

    @Override
    public BaseResponse delete_my_img(ChooseOneImage choose_one_image) {
        Long user_id = userMapper.get_user_id_by_user_token(choose_one_image.getUser_token());
        if(user_id == null){
            return new BaseResponse("token不符","400");
        }
        Image_in_table image_in_table = userMapper.get_one_file(choose_one_image.getFile_url());
        if(image_in_table == null){
            return new BaseResponse("文件不存在","400");
        }
        if(!image_in_table.getBelong_user().equals(user_id)){
            return new BaseResponse("文件不属于该用户","400");
        }
        String ip_and_name = image_in_table.getFile_url();
        int firstIndex = ip_and_name.indexOf("\\");
        // 2. 从第一个反斜杠后开始查找第二个反斜杠
        int secondIndex = ip_and_name.indexOf("\\", firstIndex + 1);
        // 3. 截取第二个反斜杠后的子字符串
        String result = ip_and_name.substring(secondIndex + 1);

        String from = Base_url + image_in_table.getBelong_user().toString() + "\\" + result;
        String to = Base_url_deleted + image_in_table.getBelong_user().toString() + "\\" + result + "." + image_in_table.getFile_name();

        Path source = Paths.get(from);
        Path target = Paths.get(to);
        try {
            // 确保目标目录存在
            Files.createDirectories(target.getParent());

            // 移动并重命名文件（覆盖已存在的目标文件）
            Files.move(
                    source,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            System.err.println("操作失败: " + e.getMessage());
            e.printStackTrace();
        }



        userMapper.delete_file(user_id,choose_one_image.getFile_url());
        return new BaseResponse("删除成功","500");
    }

    @Override
    public BaseResponse upload_img(String user_token, MultipartFile file, String file_name) throws IOException {
        Long user_id = userMapper.get_user_id_by_user_token(user_token);
        if(user_id == null){
            return new BaseResponse("token不符","400");
        }

        String file_id = TokenGenerator.generateToken();
        String file_url_windows = Base_url + (user_id).toString() + "\\" + file_id;
        String file_url = Base_url_give_all + (user_id).toString() + "\\" + file_id;
        userMapper.upload_img(user_id,file_url,file_name);

        // 确保输出目录存在
        Path path = Paths.get(file_url_windows);
        File directory = path.getParent().toFile();
        if (!directory.exists()) {
            directory.mkdirs(); // 创建多级目录
        }
        // 将MultipartFile内容写入目标文件
        Files.write(path, file.getBytes());

        return new BaseResponse("上传成功","500");
    }

    @Override
    public BaseResponse reset_passwd(ReSetPassword reset_passwd) {
        reset_passwd.setUser_id(userMapper.get_user_id_by_user_token(reset_passwd.getUser_token()));
        if(reset_passwd.getUser_id() == null){
            return BaseResponse.error("用户唯一象征物无效");
        }
        if(userMapper.get_user_password(reset_passwd.getUser_id(),reset_passwd.getUser_password())){
            return BaseResponse.error("原密码错误");
        }
        userMapper.set_new_password(reset_passwd.getUser_id(),reset_passwd.getNew_password());
        return BaseResponse.success("成功重置密码");
    }


}
