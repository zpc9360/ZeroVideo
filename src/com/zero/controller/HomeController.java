package com.zero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zero.factory.WareHouse;
import com.zero.service.VideoService;
import com.zero.utils.Page;

@Controller
@RequestMapping(value = "home")
public class HomeController {

	@Autowired
	private VideoService videoService;

	
	@RequestMapping(value = "{path}")
	public String disMenu(@RequestParam(defaultValue = "1") int curPage, @PathVariable String path, ModelMap map) {
		if(!WareHouse.isIndexMenu(path))
			return "redirect:/index";
		Page page = new Page();
		page.setCurPage(curPage);
		page.setActionUrl("home/" + path);
		page.setDBTotalCount(videoService.getSum("where videoMenu='" + path + "'"));
		page.setRowsPrePage(20);
		page.init();
		map.put("menuList", WareHouse.getIndexMenuList());
		map.put("pageBar", page.getPageBar());
		map.put("videoList", videoService.getByPage(page.getStartNum(), page.getRowsPrePage(), "where videoMenu='" + path + "' order by videoViewSum"));
		return getVideoJsp("kind");
	}

	private String getVideoJsp(String jsp) {
		return "/WEB-INF/jsp/home/" + jsp + ".jsp";
	}

}
