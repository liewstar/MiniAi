package com.example.miniaibackend.config.secure.filter;

import com.example.miniaibackend.config.secure.handler.AuthenticationExceptionHandler;
import com.example.miniaibackend.service.secure.UserDetailsServiceImpl;
import com.example.miniaibackend.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;

    private String url;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationExceptionHandler authenticationExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        url = request.getRequestURI();
        String token = request.getHeader("token");
        Boolean isFilter = filterUri("/users/login", "/users/register");// 设置不需要被token拦截的URL
        if (isFilter){
            if (token!=null) {
                if (!token.equals("")) {
                    if (tokenUtils.isTokenRight(token)) {
                        String username = tokenUtils.getUserNameInToken(token);
                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            if (tokenUtils.isTokenExpire(token)) {
                                authenticationExceptionHandler.commence(request, response, new AccountExpiredException("TOKEN EXPIRED"));
                                return;
                            }
                            holdAuthentication(userDetails, request);
                        }
                    }else {
                        authenticationExceptionHandler.commence(request, response, new BadCredentialsException("TOKEN NOT RIGHT"));
                    }
                } else {
                    authenticationExceptionHandler.commence(request, response, new AuthenticationCredentialsNotFoundException("TOKEN IS NULL"));
                    return;
                }
            }else {
                authenticationExceptionHandler.commence(request, response, new AuthenticationCredentialsNotFoundException("TOKEN IS NULL"));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void holdAuthentication(UserDetails userDetails, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.getAuthorities()
                );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private Boolean filterUri(String... uri){
        Boolean reply = true;
        Boolean[] res = new Boolean[uri.length];
        for (int i = 0; i < uri.length; i++){
            if (uri[i].endsWith("*")){
                res[i] = url.contains(uri[i].substring(0, uri[i].lastIndexOf("/")));
            }else{
                res[i] = url.equals(uri[i]);
            }
        }
        for (int i = 0; i < res.length; i++){
            reply &= !res[i];
        }
        return reply;
    }
}
