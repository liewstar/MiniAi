package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.models.UserDTO;
import com.example.miniaibackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
/*
* 用户登录
* 用户注册
* 用户修改密码
* */
public class UserController {
    @Resource
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // TODO: 这里的URL不需要存在，如果需要将登录的时候的请求变成JSON形式这边也没有必要存在
//    @PostMapping("/login")
//    public ResponseEntity<?> getUser(String username, String password) {
//        User user = userService.checkUser(username, password);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password or username error");
//        }
//    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(String username, String oldPassword, String newPassword) {
        int i = userService.changePassword(username, oldPassword, newPassword);
        if (i > 0) {
            return ResponseEntity.ok("change success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password error");
        }
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        boolean i = userService.addUser(user1);
        if (i) {
            return Result.ok("注册成功");
        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("args error");
            return Result.err(HttpStatus.BAD_REQUEST.value(), "注册失败");
        }
    }
}
