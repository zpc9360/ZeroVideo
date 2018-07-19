package com.zero.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zero.po.User;
import com.zero.service.UserService;

public class UserTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserService userService = (UserService) context.getBean("userServiceImpl");
	User user = new User();


	@Test
	public void addUser() {
		user.setUserName("hxf");
		user.setPassWord("654321");
		user.setNickName("大佬哈哈哈");
		user.setGender("男");
		user.setRoleId(2);
		user.setHeadImg("resources/imgs/userDefaultHead/1.png");
		userService.regist(user);
		userService.regist(user);
	}

	@Test
	public void updateUser() {
		user.setId(1);
		user.setUserName("hxf");
		user.setPassWord("123456");
		user.setNickName("大佬哈哈哈");
		user.setGender("女");
		user.setRoleId(6);
		user.setHeadImg("resources/imgs/userDefaultHead/2.png");
		userService.update(user);
	}

	@Test
	public void deleteUser() {
		user.setId(2);
		userService.delete(user);
	}

	@Test
	public void getUserBySql() {
		String sql = "where passWord='123456'";
		System.out.println(userService.getUser(sql).getUserName());
	}

	@Test
	public void getUserById() {
		assertEquals("123456", userService.getUserById(1L).getPassWord());
	}

	@Test
	public void getAll() {
		assertEquals(userService.getUserSum(), (long) userService.getAllUsers().size());
	}

}
