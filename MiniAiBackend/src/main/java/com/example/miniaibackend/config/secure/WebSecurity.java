package com.example.miniaibackend.config.secure;

import com.example.miniaibackend.config.secure.adapter.AuthenticationManage;
import com.example.miniaibackend.config.secure.handler.FailHandler;
import com.example.miniaibackend.config.secure.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {


    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private FailHandler failHandler;

    @Autowired
    private AuthenticationManage authenticationManage;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request) -> {
            request.anyRequest().permitAll();
        });

        http.formLogin((forms) -> {
            forms.loginProcessingUrl("/user/login");
            forms.successHandler(successHandler).failureHandler(failHandler);
        });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return authenticationManage;
    }
}
