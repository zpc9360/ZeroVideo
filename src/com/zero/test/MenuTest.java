package com.zero.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zero.po.Menu;
import com.zero.service.MenuService;

public class MenuTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	MenuService menuService = (MenuService) context.getBean("menuServiceImpl");

	String[] indexMenuName = { "movie", "teleplay", "anime", "original", "crazy", "music" };
	String[] indexMenuDetail = { "电影", "电视剧", "动漫", "原创", "鬼畜", "音乐" };
	int size = indexMenuName.length;

	String[] adminMenuName = { "menuManage", "roleManage", "authorityManage", "menuManage", "roleManage", "authorityManage"};
	String[] adminMenuDetail = { "菜单管理", "角色管理", "权限管理","菜单首页", "角色首页", "权限首页" };
	String[] adminMenuUrl = { "menuManage", "roleManage", "authorityManage", "menu/menuManage", "role/roleManage", "authority/authorityManage"};
	int[] fatherId = { 0, 0, 0, size + 1, size + 2, size + 3 };
	
	@Test
	public void save() {

		addIndexMenu();
		
		addAdminMenu();

	}

	private void addIndexMenu() {
		Menu menu;
		for (int i = 0; i < size; i++) {
			menu = new Menu();
			menu.setMenuName(indexMenuName[i]);
			menu.setMenuDetail(indexMenuDetail[i]);
			menu.setFatherId(0);
			menu.setMenuKindId(1);
			menu.setMenuUrl("home/" + indexMenuName[i]);
			menuService.save(menu);
		}
	}

	private void addAdminMenu() {
		Menu menu ;
		for (int i = 0; i < size; i++) {
			menu = new Menu();
			menu.setMenuName(adminMenuName[i]);
			menu.setMenuDetail(adminMenuDetail[i]);
			menu.setFatherId(fatherId[i]);
			menu.setMenuKindId(2);
			menu.setMenuUrl(adminMenuUrl[i]);
			menuService.save(menu);
		}
	}

}
