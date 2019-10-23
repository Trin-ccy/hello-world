package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	private UserMapper userMapper;
	@Test
	public void contextLoads() {
		System.out.println(("----------selectAll method test----"));
//		List<User> userList = userMapper.selectList(null);
//		Assert.assertEquals(6, userList.size());
//		userList.forEach(System.out::println);
//		User user = userMapper.selectOne()
	}

}
