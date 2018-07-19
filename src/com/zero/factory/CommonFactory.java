package com.zero.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zero.po.Authority;
import com.zero.po.Menu;
import com.zero.po.Role;
import com.zero.service.AuthorityService;
import com.zero.service.MenuService;
import com.zero.service.RoleService;

@Component
public class CommonFactory implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MenuService menuService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private RoleService roleService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initIndexMenu();
		initAdminMenu();
		initAuthority();
		System.out.println("资源初始化完成");
	}

	// 初始化主菜单
	private void initIndexMenu() {
		List<Menu> indexMenu = new LinkedList<Menu>();
		Set<String> indexSet = new HashSet<String>();
		for (Menu menu : menuService.getAll("where menuKindId=1")) {
			indexMenu.add(menu);
			indexSet.add(menu.getMenuName());
		}
		WareHouse.setIndexMenuSet(indexSet);
		WareHouse.setIndexMenuList(indexMenu);
	}

	// 初始化管理菜单
	private void initAdminMenu() {
		Map<Menu, List<Menu>> menuMap = new HashMap<Menu, List<Menu>>();
		for (Menu menuf : menuService.getAll("where menuKindId=2 and fatherId=0")) {
			List<Menu> list = new ArrayList<Menu>();
			for (Menu menuc : menuService.getAll("where menuKindId=2 and fatherId=" + menuf.getId())) {
				list.add(menuc);
			}
			menuMap.put(menuf, list);
		}
		WareHouse.setAdminMenuMap(menuMap);
	}

	// 初始化权限字典
	private void initAuthority() {
		Map<Integer, Set<String>> authorityMap = new TreeMap<Integer, Set<String>>();
		for (Role role : roleService.getAll()) {
			Set<String> authoritiesSet = new TreeSet<String>();
			for (Authority authority : authorityService.getAuthorities(role.getRoleLevel())) {
				authoritiesSet.add(authority.getAuthorityName());
			}
			authorityMap.put(role.getRoleLevel(), authoritiesSet);
		}
		WareHouse.setAuthorityMap(authorityMap);
	}

}
