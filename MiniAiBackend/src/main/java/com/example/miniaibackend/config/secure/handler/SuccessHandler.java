package com.example.miniaibackend.config.secure.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.mapper.UserMapper;
import com.example.miniaibackend.models.Result;
import com.example.miniaibackend.models.secure.TokenModel;
import com.example.miniaibackend.utils.TokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 成功返回
 */
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        System.out.println(username);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        String token = tokenUtils.generateToken(username, user.getId());
        Result<TokenModel> result = Result.ok(new TokenModel(token));
        String reply = JSON.toJSONString(result);
        servletOutputStream.write(reply.getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
