package com.zero.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zero.factory.WareHouse;
import com.zero.po.Menu;
import com.zero.po.Video;
import com.zero.service.MenuService;
import com.zero.service.VideoService;
import com.zero.utils.Page;

@Controller
public class CommonController {

	@Resource
	private MenuService menuService;
	@Resource
	private VideoService videoService;

	@RequestMapping(value = "/admin/home")
	public String admin(ModelMap map) {
		map.put("menuMap", WareHouse.getAdminMenuMap());
		return "/WEB-INF/jsp/admin/home.jsp";
	}

	@RequestMapping(value = { "/{action}", "/" })
	public String index(@PathVariable String action, ModelMap map) {
		String target = "";
		switch (action) {
		case "index":
			target = index(map);
			break;
		default:
			target = index(map);
		}
		return target;
	}
	@RequestMapping(value = { "/Search"})
	public String search(@RequestParam(defaultValue = "1") int curPage,@RequestParam String search, ModelMap map) {
		Page page = new Page();
		page.setCurPage(curPage);
		page.setActionUrl("Search?search=" + search);
		page.setDBTotalCount(videoService.getSum("where videoName like '%"+search+"%' or videoActs like '%"+search+"%' or videoMaker like '%"+search+"%'"));
		page.setRowsPrePage(20);
		page.init();
		map.put("menuList", WareHouse.getIndexMenuList());
		map.put("pageBar", page.getPageBar());
		map.put("videoList", videoService.getByPage(page.getStartNum(), page.getRowsPrePage(), "where videoName like '%"+search+"%' or videoActs like '%"+search+"%' or videoMaker like '%"+search+"%'"));
		
		return getJsp("kind");
	}
	
	// 返回主页
	private String index(ModelMap map) {
		map.put("menuList", WareHouse.getIndexMenuList());
		map.put("hotVideoList", videoService.getByPage(0, 10, "order by videoViewSum desc"));

		Map<Menu, List<Video>> videoMap = new HashMap<Menu, List<Video>>();
		for (Menu menu : WareHouse.getIndexMenuList()) {
			videoMap.put(menu, videoService.getByPage(0, 6, "where videoMenu='" + menu.getMenuName() + "' order by videoViewSum"));
		}
		map.put("videoMap", videoMap);
		return getJsp("index");
	}

	// 请求地址转发
	@RequestMapping(value = "{path}/to{_target}")
	public String toPage(@PathVariable String _target, @PathVariable String path, Model model) {
		String target = String.valueOf(_target.charAt(0)).toLowerCase() + _target.substring(1);
		return "/WEB-INF/jsp/" + path + "/" + target + ".jsp";
	}

	private String getJsp(String jsp) {
		return "/WEB-INF/jsp/home/" + jsp + ".jsp";
	}

}
