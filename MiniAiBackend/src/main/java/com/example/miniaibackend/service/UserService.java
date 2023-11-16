package com.example.miniaibackend.service;

import com.example.miniaibackend.domain.Roles;
import com.example.miniaibackend.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 29354
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-31 21:40:38
*/
public interface UserService extends IService<User> {
    User checkUser(String username, String password);

    int changePassword(String token, String oldPassword, String newPassword);

    boolean addUser(User user);

    List<User> selectUsersByUsername(String username);

    List<String> getUserRoles(String token);

}
