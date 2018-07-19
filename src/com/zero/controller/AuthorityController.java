package com.zero.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zero.factory.WareHouse;
import com.zero.po.Authority;
import com.zero.po.Role;
import com.zero.service.AuthorityService;
import com.zero.service.RoleService;
import com.zero.utils.Page;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "authority")
public class AuthorityController {

	@Resource
	private AuthorityService authorityService;
	@Resource
	private RoleService roleService;

	// 去添加权限页面
	@RequestMapping(value = "toAuthorityAdd")
	public String toAdd(Model model) {
		model.addAttribute("authority", new Authority());
		return getAdminJsp("add");
	}
	
	@RequestMapping(value = "getOne")
	public void get(HttpServletResponse response,PrintWriter out, @RequestParam long id) {
		response.setContentType("text/html;charset=utf-8");
		out.write(JSONObject.fromObject(authorityService.getById(id)).toString());
	}

	// 去权限更新页面
	@RequestMapping(value = "toAuthorityUpdate")
	public String toUpdate(Model model, @RequestParam long id) {
		model.addAttribute("authority", authorityService.getById(id));
		return getAdminJsp("update");
	}

	// 权限删除
	@RequestMapping(value = "authorityDelete")
	public String add(@RequestParam int id) {
		Authority authority = new Authority();
		authority.setId(id);
		authorityService.delete(authority);
		fresh();
		return "redirect:authorityManage";
	}

	// 添加权限
	@RequestMapping(value = "authorityAdd")
	public String add(@ModelAttribute Authority authority) {
		authorityService.save(authority);
		fresh();
		return "redirect:authorityManage";
	}

	// 权限更新
	@RequestMapping(value = "authorityUpdate")
	public String update(@ModelAttribute Authority authority) {
		authorityService.update(authority);
		fresh();
		return "redirect:authorityManage";
	}

	// 权限管理主页
	@RequestMapping(value = "authorityManage")
	public String home(ModelMap map, @RequestParam(defaultValue = "1") int curPage) {
		Page page = new Page();
		page.setCurPage(curPage);
		page.setDBTotalCount(authorityService.getSum());
		page.setRowsPrePage(10);
		page.setActionUrl("authority/authorityManage");
		page.init();
		map.put("authorityList", authorityService.getByPage(page.getStartNum(), page.getRowsPrePage(),"order by id"));
		map.put("pageBar", page.getPageBar());
		Map<Integer, String> roleMap = new HashMap<Integer, String>();
		for(Role role : roleService.getAll()) {
			roleMap.put(role.getRoleLevel(), role.getRoleName());
		}
		map.put("roleMap", roleMap);
		map.addAttribute("authority", new Authority());
		return getAdminJsp("home");
	}

	private String getAdminJsp(String jsp) {
		return "/WEB-INF/jsp/admin/authority/" + jsp + ".jsp";
	}

	private void fresh() {
		Map<Integer, Set<String>> authorityMap = new HashMap<Integer, Set<String>>();
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
