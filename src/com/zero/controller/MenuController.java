package com.zero.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zero.factory.WareHouse;
import com.zero.po.Menu;
import com.zero.service.MenuService;
import com.zero.utils.Page;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

	@Resource
	private MenuService menuService;

	// 去菜单添加页面
	@RequestMapping(value = "toMenuAdd")
	public String toAdd(Model model) {
		model.addAttribute("menu", new Menu());
		return getAdminJsp("add");
	}

	// fetch请求的返回
	@RequestMapping(value = "getOne")
	public void get(HttpServletResponse response, PrintWriter out, @RequestParam long id) {
		response.setContentType("text/html;charset=utf-8");
		out.write(JSONObject.fromObject(menuService.getById(id)).toString());
	}

	// 去菜单更新页面
	@RequestMapping(value = "toMenuUpdate")
	public String toUpdate(Model model, @RequestParam long id) {
		model.addAttribute("menu", menuService.getById(id));
		return getAdminJsp("update");
	}

	// 菜单更新
	@RequestMapping(value = "menuUpdate")
	public String update(Model model, @ModelAttribute Menu menu) {
		menuService.update(menu);
		fresh();
		return "redirect:menuManage";
	}

	// 菜单添加
	@RequestMapping(value = "menuAdd")
	public String add(@ModelAttribute Menu menu) {
		menuService.save(menu);
		fresh();
		return "redirect:menuManage";
	}

	// 菜单删除
	@RequestMapping(value = "menuDelete")
	public String add(@RequestParam int id) {
		Menu menu = new Menu();
		menu.setId(id);
		menuService.delete(menu);
		fresh();
		return "redirect:menuManage";
	}

	// 菜单管理主页
	@RequestMapping(value = "menuManage")
	public String home(ModelMap map, @RequestParam(defaultValue = "1") int curPage) {
		Page page = new Page();
		page.setCurPage(curPage);
		page.setDBTotalCount(menuService.getSum());
		page.setRowsPrePage(10);
		page.setActionUrl("menu/menuManage");
		page.init();
		map.put("menuList", menuService.getByPage(page.getStartNum(), page.getRowsPrePage()));
		map.put("pageBar", page.getPageBar());
		map.put("menu", new Menu());
		Map<Integer, String> menuKinds = new HashMap<Integer, String>();
		String[] menuKindsN = { "网站主页菜单", "管理页面菜单" };
		Map<Long, String> fatherMenu = new HashMap<Long, String>();
		for (int i = 1; i < 3; i++) {
			menuKinds.put(i, menuKindsN[i - 1]);
		}
		for (Menu menu : menuService.getAll("where fatherId=0 and menuKindId=2")) {
			fatherMenu.put(menu.getId(), menu.getMenuDetail());
		}
		fatherMenu.put(0L, "父菜单,没有所属");
		map.put("menuKinds", menuKinds);
		map.put("fatherMenu", fatherMenu);
		return getAdminJsp("home");
	}

	private String getAdminJsp(String jsp) {
		return "/WEB-INF/jsp/admin/menu/" + jsp + ".jsp";
	}

	private void fresh() {

		List<Menu> indexMenu = new LinkedList<Menu>();
		Set<String> indexSet = new HashSet<String>();
		for (Menu menu : menuService.getAll("where menuKindId=1")) {
			indexMenu.add(menu);
			indexSet.add(menu.getMenuName());
		}
		WareHouse.setIndexMenuSet(indexSet);
		WareHouse.setIndexMenuList(indexMenu);

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
}
