package com.example.miniaibackend.config.secure.filter;

import com.example.miniaibackend.service.secure.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;

/**
 * 让登录可以使用JSON格式
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("METHOD NOT ALLOW");
        }

        Authentication reply = null;

        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)){
            Map<String, String> loginData = null;

            try{
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            }catch (Exception e){
            }

            String username = loginData.get("username");
            String password = loginData.get("password");

            if (username != null) {
                if (username.isEmpty()) {
                    throw new AuthenticationServiceException("用户名为空");
                }
            }else {
                throw new AuthenticationServiceException("用户名为空");
            }

            if (password != null) {
                if (password.isEmpty()) {
                    throw new AuthenticationServiceException("密码为空");
                }
            }else {
                throw new AuthenticationServiceException("密码为空");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

            setDetails(request, authenticationToken);
            reply = getAuthenticationManager().authenticate(authenticationToken);
        }else{
            reply = super.attemptAuthentication(request, response);
            throw new AuthenticationServiceException("Content Type Not Allow");
        }

        return reply;
    }
}
