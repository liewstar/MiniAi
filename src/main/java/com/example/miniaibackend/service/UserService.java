package com.example.miniaibackend.service;

import com.example.miniaibackend.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 29354
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-31 21:40:38
*/
public interface UserService extends IService<User> {
    User checkUser(String username, String password);

    int changePassword(String username, String oldPassword, String newPassword);

}
