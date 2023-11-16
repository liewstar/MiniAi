package com.example.miniaibackend.controller;

import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.models.PasswordVO;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.models.UserDTO;
import com.example.miniaibackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.POST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
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


    //管理员查看用户
    @PostMapping("/selectUsers")
    public Result<?> selectAllUsers(String username) {
        return Result.ok(userService.selectUsersByUsername(username));
    }


    @PostMapping("/changePassword")
    public Result<String> changePassword(@RequestHeader("token") String token, @RequestBody PasswordVO passwordVO) {
        int i = userService.changePassword(token, passwordVO.getOldPassword(), passwordVO.getNewPassword());
        if (i > 0) {
            return Result.ok("change success");
        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password error");
            return Result.err(403, "password error");
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
            return Result.err(HttpStatus.BAD_REQUEST.value(), "注册失败");
        }
    }

    @GetMapping("/getRole")
    public Result<?> getRole(@RequestHeader("token") String token){
        List<String> roles = userService.getUserRoles(token);
        return Result.ok(roles);
    }
}
