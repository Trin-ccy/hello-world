package com.example.demo.jwt.filter;

import com.example.demo.entity.User;
import com.example.demo.jwt.JwtUser;
import com.example.demo.utils.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)throws AuthenticationException{

        //从输入流中获取到登录的信息
        try{
            User loginUser = new ObjectMapper().readValue(request.getInputStream(),User.class);
//            rememberMe.set(loginUser.get)
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getName(),loginUser.getPassword(),new ArrayList<>())
            );
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    //成功验证后调用的方法
    //如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)throws IOException, ServletException{
        JwtUser jwtUser = (JwtUser)authResult.getPrincipal();
        System.out.println("JwtUser:"+jwtUser.toString());
        boolean isRemember = rememberMe.get() == 1;
//
//        String role = "";
//        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
//        for (GrantedAuthority authority:authorities){
//            role = authority.getAuthority();
//        }

        String token  = JwtTokenUtils.createToken(jwtUser.getUsername(),isRemember);

        //返回创建成功的token
        //按照jwt的规定，最后请求的时候应该是‘bearer token’
        response.setHeader("token",JwtTokenUtils.TOKEN_PREFIX + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,HttpServletResponse response,AuthenticationException failed)throws IOException,ServletException{
        response.getWriter().write("anthentication failed,reason:" + failed.getMessage());
    }
}
