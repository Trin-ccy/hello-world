package com.example.demo.controller;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @RequestMapping("/users/user")
    public String loginSuccess(){
        return "Login Successful";
    }

    @PostMapping("/user/roles")
    public Object checkRoles(HttpServletRequest request){
        //从token中获取用户信息
        Claims claims = request.getAttribute("claims")!= null ? (Claims)request.getAttribute("claims"):null;
        return claims.get("roles");
    }

}
