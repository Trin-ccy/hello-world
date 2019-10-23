package com.example.demo.jwt.filter;

import com.example.demo.utils.JwtTokenUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token的校验
 * 该类继承BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author chenchuying on 2019/10/11.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        //如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        //如果请求头中token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request,response,chain);
    }

    //这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX,"");
        String username = JwtTokenUtils.getUsername(token);
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
        }
        return null;
//        String token = request.getHeader("Authorization");
//        if (token != null){
//            String user = Jwts.parser()
//                    .setSigningKey("MyJwtSecret")
//                    .parseClaimsJws(token.replace("Bearer",""))
//                    .getBody()
//                    .getSubject();
//            if(user != null){
//                return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
//            }
//            return null;
//        }
//        return null;
    }
}
