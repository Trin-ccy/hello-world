package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/User")
//@Validated
@Api(tags = "用户CRUD接口", description = "提供用户相关的 Restful API")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/get")
    public String get(String msg){
        log.info("get方式");
        return msg;
    }

    @ApiOperation("查询所有用户接口")
    @GetMapping("/getAll")
    @ResponseBody
    public List<User> getAll(){
        List<User> list = userService.getAll();
        return list;
    }

    //增加用户
    @ApiOperation("增加用户接口")
    @PostMapping("/")
    @ResponseBody
    public User insert(User user){
        User user2 = userService.insert(user);
        return user2;
    }

    //删除用户
    @ApiOperation("删除用户接口")
    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete(@PathVariable @Valid int id){
        int delete = userService.deleteById(id);
        if (delete >= 1){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    //修改用户信息
    @ApiOperation("修改用户信息接口")
    @PutMapping("/")
    @ResponseBody
    public String update(User user){
         int update = userService.update(user);
         if (update >= 1){
             return "修改成功";
         }else{
             return "修改失败";
         }
    }

    //(测试)修改用户信息
    @ApiOperation("修改用户信息接口")
    @PutMapping("/updateById")
    @ResponseBody
    @Validated
    public String updateById(User user){
        int updateById = userService.updateById(user);
        if (updateById >= 1){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }


    @RequestMapping("/toIndex")
    public String show(){
        return "index";
    }

    @ApiOperation("用户登录接口")
    @GetMapping("/login")
    public String Login(User user, HttpServletRequest request) {
//        public String Login(User user){
        user = userService.login(user.getName(),user.getPassword());
        if (user != null){
            request.getSession().setAttribute("session_user",user);
            return "登陆成功";
        }else{
            return "登录失败";
        }
//        String name =  user.getName();
//        String password = user.getPassword();
//        User user1 = userService.login(name,password);
//        if (user1 == null){
//            System.out.println("用户名或密码输入错误");
//            return "/outUser";
//        }else{
//            request.getSession().setAttribute("session_user",user);//登陆成功后将用户放在session下，用于拦截器制作
//            return "welcome";
//        }
    }
//    @RequestMapping("/login")
//    public String Login(){
//        return userService.login();
//
//    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public User Register(User user){
        User user2 = userService.sel(user.getId());
        int su = userService.register(user);
        if (su == 0){
            System.out.println("--------");
        }
        System.out.println(user2.getId());
        return user2;
    }
//    @PostMapping("/signup")
//    public void signup(@RequestBody User user){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userService.insert(user);
//    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/outUser")
    public void outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        response.sendRedirect("/toIndex");
    }
}
