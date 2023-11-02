package com.example.miniaibackend.config;

import com.example.miniaibackend.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

    @Value("${jwt.expire}")
    private long expire;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.header}")
    private String header;

    /**
     * token工具bean化
     * @return com.example.miniaibackend.utils.TokenUtils
     */
    @Bean
    public TokenUtils tokenUtils(){
        return new TokenUtils(expire, secret, header);
    }
}
