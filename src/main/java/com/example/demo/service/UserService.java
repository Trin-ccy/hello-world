package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User sel(int id){
        return userMapper.sel(id);
    }

    public User login(String name,String password){
        User user = userMapper.login(name, password);
        if (user != null && user.getPassword().equals(password)){
            System.out.println("登录成功");
            return user;
        }else{
            System.out.println("用户名或密码输入错误！");
            return null;
        }
//        return userMapper.login(name,password);
    }

    public int register(User user){
        user.setName("chen");
        user.setPassword("123456");
        return userMapper.register(user);
    }
    //添加用户
    public User insert(User user){
        userMapper.insert(user);
        return user;
    }
    //删除用户
    public int deleteById(int id){
        return userMapper.deleteById(id);
    }
    //更改用户信息
    public int update(User user){
        return userMapper.update(user);
    }

    //(测试)更改用户信息
    public int updateById(User user){
        return userMapper.updateById(user);
    }

    public List<User> getAll(){
        return userMapper.getAll();
    }


}
