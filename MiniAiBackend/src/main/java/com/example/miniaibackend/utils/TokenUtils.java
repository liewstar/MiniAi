package com.example.miniaibackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtils {

    private long expire;

    private String secret;

    private String header;

    public TokenUtils(long expire, String secret, String header) {
        this.expire = expire;
        this.secret = secret;
        this.header = header;
    }

    /**
     * 生成Token，参数使用的是用户名
     * @param username 用户名
     * @return token验证令牌
     */
    public String generateToken(String username){
        Date expireDate = new Date(System.currentTimeMillis() + 1000*expire);

        return Jwts.builder().setExpiration(expireDate)
                .setHeaderParam("header", header)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 获取令牌
     * @param token token字符串
     * @return 验证令牌
     */
    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 获取用户名
     * @param token token字符串
     * @return 用户名
     */
    public String getUserNameInToken(String token){
        return getClaims(token).get("username", String.class);
    }

    /**
     * 判断token是否过期
     * @param token token字符串
     * @return 判断是否过期
     */
    public boolean isTokenExpire(String token){
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }


}
