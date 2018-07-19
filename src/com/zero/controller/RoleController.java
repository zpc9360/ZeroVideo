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
@RequestMapping(value = "role")
public class RoleController {

	@Resource
	private RoleService roleService;
	@Resource
	private AuthorityService authorityService;

	// 去角色增加页面
	@RequestMapping(value = "toRoleAdd")
	public String toAdd(Model model) {
		model.addAttribute("role", new Role());
		return getAdminJsp("add");
	}

	@RequestMapping(value = "getOne")
	public void get(HttpServletResponse response, PrintWriter out, @RequestParam long id) {
		response.setContentType("text/html;charset=utf-8");
		out.write(JSONObject.fromObject(roleService.getById(id)).toString());
	}

	// 去角色更新页面
	@RequestMapping(value = "toRoleUpdate")
	public String toUpdate(Model model, @RequestParam long id) {
		model.addAttribute("role", roleService.getById(id));
		return getAdminJsp("update");
	}

	// 角色添加
	@RequestMapping(value = "roleAdd")
	public String add(@ModelAttribute Role role) {
		roleService.save(role);
		fresh();
		return "redirect:roleManage";
	}

	// 角色更新
	@RequestMapping(value = "roleUpdate")
	public String update(@ModelAttribute Role role) {
		roleService.update(role);
		fresh();
		return "redirect:roleManage";
	}

	// 角色删除
	@RequestMapping(value = "roleDelete")
	public String add(@RequestParam int id) {
		Role role = new Role();
		role.setId(id);
		roleService.delete(role);
		fresh();
		return "redirect:roleManage";
	}

	// 角色管理主页
	@RequestMapping(value = "roleManage")
	public String home(ModelMap map, @RequestParam(defaultValue = "1") int curPage) {
		Page page = new Page();
		page.setCurPage(curPage);
		page.setDBTotalCount(roleService.getSum());
		page.setRowsPrePage(10);
		page.setActionUrl("role/roleManage");
		page.init();
		map.put("roleList", roleService.getByPage(page.getStartNum(), page.getRowsPrePage()));
		map.put("pageBar", page.getPageBar());
		map.put("role", new Role());
		return getAdminJsp("home");
	}

	private String getAdminJsp(String jsp) {
		return "/WEB-INF/jsp/admin/role/" + jsp + ".jsp";
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
