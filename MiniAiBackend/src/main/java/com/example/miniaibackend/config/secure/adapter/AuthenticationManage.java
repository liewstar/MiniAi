package com.example.miniaibackend.config.secure.adapter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.mapper.UserMapper;
import com.example.miniaibackend.models.secure.AuthUserDetail;
import com.example.miniaibackend.service.secure.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManage implements AuthenticationManager {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 判断密码是否正确
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getCredentials();
        String password = (String) authentication.getPrincipal();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        String localPassword = user.getPassword();
        AuthUserDetail userDetail = (AuthUserDetail) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, localPassword)){
            throw new BadCredentialsException("密码不正确");
        }
        return new UsernamePasswordAuthenticationToken(userDetail, password, userDetail.getAuthorities());
    }
}
