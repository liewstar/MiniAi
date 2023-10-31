package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.service.UserService;
import com.example.miniaibackend.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
* @author 29354
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-31 21:40:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username)
                    .eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    public int changePassword(String username, String oldPassword, String newPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", oldPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            //newpassword user
            user.setPassword(newPassword);
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", username);
            return userMapper.update(user, updateWrapper);
        } else {
            //args error
            return 0;
        }


    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }
}




