package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

//@ComponentScan
@Repository
public interface UserMapper extends BaseMapper<User> {

//    @Override
    List<User> getAll();

    User sel(int id);
    User login(String name,String password);
    int register(User user);
//    User insert(User user);
//    int delete(int id);

    int update(User user);



}
