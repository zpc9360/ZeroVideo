package com.zero.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zero.factory.WareHouse;
import com.zero.po.User;
import com.zero.utils.MyUtil;

@WebFilter("/*")
public class CommonFilter implements Filter {

	public CommonFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 添加UTF-8编码
		MyUtil.setBaseConfig(req, resp);
		
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		
		int roleId = 1;
		User user = (User) req.getSession().getAttribute("currenUser");
//		if(user != null)
//			roleId = user.getRoleId();
//		if(action.contains(".css")||action.contains(".js")||action.contains(".jpg")||action.contains(".png")||action.contains(".mp4")||action.contains(".mp3")) {
//			chain.doFilter(req, resp);
//			return;
//		}
//		if(!WareHouse.isPermit(action, roleId)){
//			req.getRequestDispatcher("/WEB-INF/jsp/home/error.jsp").forward(request, response);
//			return ; 
//		}
		if (action.equals("")) {
			resp.sendRedirect("index");
			return ;
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
