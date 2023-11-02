package com.example.miniaibackend.service.secure;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.miniaibackend.domain.RoleToUser;
import com.example.miniaibackend.domain.Roles;
import com.example.miniaibackend.domain.User;
import com.example.miniaibackend.mapper.RoleMapper;
import com.example.miniaibackend.mapper.RoleToUserMapper;
import com.example.miniaibackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证用户是否存在
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleToUserMapper roleToUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);

        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        return getDetailUser(user);
    }

    private org.springframework.security.core.userdetails.User getDetailUser(User user){
        QueryWrapper<Roles> rolesQueryWrapper = new QueryWrapper<>();
        QueryWrapper<RoleToUser> roleToUserQueryWrapper = new QueryWrapper<>();
        roleToUserQueryWrapper.eq("userId", user.getId());
        List<RoleToUser> roleToUserList = roleToUserMapper.selectList(roleToUserQueryWrapper);
        List<Roles> roles = new ArrayList<>();
        for (RoleToUser roleToUser : roleToUserList){
            rolesQueryWrapper.eq("id", roleToUser.getRoleId());
            Roles tmp = roleMapper.selectOne(rolesQueryWrapper);
            roles.add(tmp);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles auth : roles){
            authorities.add(new SimpleGrantedAuthority(auth.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }
}
