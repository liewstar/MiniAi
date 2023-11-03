package com.example.miniaibackend.config.secure;

import com.example.miniaibackend.config.secure.adapter.AuthenticationManage;
import com.example.miniaibackend.config.secure.filter.LoginFilter;
import com.example.miniaibackend.config.secure.filter.TokenFilter;
import com.example.miniaibackend.config.secure.handler.FailHandler;
import com.example.miniaibackend.config.secure.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Configuration
@EnableWebSecurity
public class WebSecurity {


    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private FailHandler failHandler;

    @Autowired
    private AuthenticationManage authenticationManage;

    @Autowired
    private TokenFilter tokenFilter;

    /**
     * 核心代码，创建验证信息
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request) -> {
            request.anyRequest().permitAll();
            //需要验证的URL
            // TODO: 需要完善URL的过滤
        });

        http.csrf((configurer) -> {
            configurer.disable();
        });// 关闭跨域
        http.cors((config) -> {
            config.disable();
        });// 关闭验证跨域

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);//token filter
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);// 使其完成JSON形式的登录

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return authenticationManage;
    }

    @Bean
    public LoginFilter loginFilter(){
        LoginFilter loginFilter = new LoginFilter();

        try{
            loginFilter.setFilterProcessesUrl("/users/login");
            loginFilter.setAuthenticationManager(authenticationManager());
            loginFilter.setAuthenticationSuccessHandler(successHandler);
            loginFilter.setAuthenticationFailureHandler(failHandler);
        }catch (Exception e){

        }
        return loginFilter;
    }
}
