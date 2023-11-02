package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    UserService userService;

    // TODO: 这里的URL不需要存在，如果需要将登录的时候的请求变成JSON形式这边也没有必要存在
    @PostMapping("/login")
    public ResponseEntity<?> getUser(String username, String password) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password or username error");
        }
    }

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
    public ResponseEntity<?> register(User user) {
        int i = userService.addUser(user);
        if (i > 0) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("args error");
        }
    }
}
