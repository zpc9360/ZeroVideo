package com.zero.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zero.po.Role;
import com.zero.service.RoleService;

public class RoleTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RoleService roleService = (RoleService) context.getBean("roleServiceImpl");

	@Test
	public void save() {
		int[] roleLevel = {	1, 2, 3, 6 };
		String[] roleName = { "游客", "普通用户", "Vip用户", "超级管理员" };
		int size = roleLevel.length;
		Role role;
		for (int i = 0; i < size; i++) {
			role = new Role();
			role.setRoleLevel(roleLevel[i]);
			role.setRoleName(roleName[i]);
			roleService.save(role);
		}
	}

	@Test
	public void getAll() {
		System.out.println(roleService.getAll().size());
	}

}
