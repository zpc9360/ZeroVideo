package com.zero.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zero.po.Authority;
import com.zero.service.AuthorityService;

public class AuthorityTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	AuthorityService authorityService = (AuthorityService) context.getBean("authorityServiceImpl");

	// 基础权限
	String[] baseAuthorityName = { "toUserRegist", "regist", "toLogin", "login", "index", "danmu", "kind", "pay", "play", "video", "home"};
	String[] baseAuthorityDetail = { "去注页面", "注册", "去登录页面", "登录", "去主页", "弹幕", "分类", "支付", "播放", "详情", "主页" };
	// 用户权限
	String[] userAuthorityName = { "logout", "toUserDetail", "userUpdate", "headImgUpload" };
	String[] userAuthorityDetail = { "用户登出", "去用户个人信息空间", "用户信息更新", "用户头像上传" };
	// 角色管理权限
	String[] roleAuthorityName = { "roleManage", "toRoleAdd", "roleAdd", "roleDelete", "toRoleUpdate", "roleUpdate" };
	String[] roleAuthorityDetail = { "去角色管理页面", "去角色添加页面", "角色添加", "角色删除", "去角色更新页面", "角色修改" };
	// 菜单管理权限
	String[] menuAuthorityName = { "menuManage", "toMenuAdd", "menuAdd", "menuDelete", "toMenuUpdate", "menuUpdate" };
	String[] menuAuthorityDetail = { "去菜单管理页面", "去菜单添加页面", "菜单添加", "菜单删除", "去菜单更新页面", "菜单修改" };

	int baseAuthority = baseAuthorityName.length;
	int userAuthority = userAuthorityName.length;
	int roleAuthority = roleAuthorityName.length;
	int menuAuthority = menuAuthorityName.length;

	@Test
	public void save() {
		//
		addBaseAuthorities(1);
		addUserAuthorities(2);
		addMenuAuthorities(6);
		addRoleAuthorities(6);
	}

	private void addBaseAuthorities(int level) {
		Authority authority;
		for (int i = 0; i < baseAuthority; i++) {
			authority = new Authority();
			authority.setRoleLevel(level);
			authority.setAuthorityName(baseAuthorityName[i]);
			authority.setAuthorityDetail(baseAuthorityDetail[i]);
			authorityService.save(authority);
		}
	}
	private void addUserAuthorities(int level) {
		Authority authority;
		for (int i = 0; i < userAuthority; i++) {
			authority = new Authority();
			authority.setRoleLevel(level);
			authority.setAuthorityName(userAuthorityName[i]);
			authority.setAuthorityDetail(userAuthorityDetail[i]);
			authorityService.save(authority);
		}
	}
	private void addMenuAuthorities(int level) {
		Authority authority;
		for (int i = 0; i < menuAuthority; i++) {
			authority = new Authority();
			authority.setRoleLevel(level);
			authority.setAuthorityName(menuAuthorityName[i]);
			authority.setAuthorityDetail(menuAuthorityDetail[i]);
			authorityService.save(authority);
		}
	}
	private void addRoleAuthorities(int level) {
		Authority authority;
		for (int i = 0; i < roleAuthority; i++) {
			authority = new Authority();
			authority.setRoleLevel(level);
			authority.setAuthorityName(roleAuthorityName[i]);
			authority.setAuthorityDetail(roleAuthorityDetail[i]);
			authorityService.save(authority);
		}
	}

}
