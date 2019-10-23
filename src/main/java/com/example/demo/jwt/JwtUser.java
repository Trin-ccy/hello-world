package com.example.demo.jwt;

import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private Integer id;

    private String name;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(){
    }

    //写一个能直接使用user创建jwtUser的构造器
    public JwtUser(User user){
        id = user.getId();
        name = user.getName();
        password = user.getPassword();
//        authorities = Collections.singleton(new SimpleGrantedAuthority(user.))
    }

    //获取权限信息，目前博主只会拿来存角色。。
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }
    @Override
    public String getUsername(){
        return name;
    }

    @Override
    public String getPassword(){
        return password;
    }

    //账号是否未过期，默认是false
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    //账号凭证是否未过期，默认是false
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    //账号凭证是否未过期，默认是false
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    //默认值为false
    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
