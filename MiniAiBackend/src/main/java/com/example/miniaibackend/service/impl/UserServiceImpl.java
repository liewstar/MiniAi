package com.example.miniaibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.miniaibackend.domain.RoleToUser;
import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.mapper.RoleToUserMapper;
import com.example.miniaibackend.service.UserService;
import com.example.miniaibackend.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author 29354
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-31 21:40:38
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    UserMapper userMapper;

    @Resource
    private RoleToUserMapper roleToUserMapper;

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

    public boolean addUser(User user) {

        boolean reply = false;

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        User user1 = userMapper.selectOne(userQueryWrapper);

        if (user1 == null){
            Integer res2 = userMapper.insert(user);
            Integer userId = getUserId(user);
            user.setId(userId);
            Integer res = addRole(user);

            if (res2 != 0 && res != 0){
                reply = true;
            }

        }
        return reply;
    }

    @Override
    public List<User> selectUsersByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        return userMapper.selectList(queryWrapper);
    }

    private int addRole(User user){
        Integer userId = user.getId();
        return roleToUserMapper.insert(new RoleToUser().setUserId(userId).setRoleId(1));
    }

    private Integer getUserId(User user){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        return userMapper.selectOne(userQueryWrapper).getId();
    }
}




