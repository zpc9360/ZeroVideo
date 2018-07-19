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

import com.zero.factory.WareHouse;

@WebFilter("/*")
public class Mp4Filter implements Filter {

	public Mp4Filter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (uri.contains(".mp4")) {
			String videoSid = req.getParameter("videoSid");
			if (videoSid == null || WareHouse.getVideoToken() == null) {
				req.getRequestDispatcher("/WEB-INF/jsp/home/error.jsp").forward(request, response);
				return;
			}
			if (!videoSid.equals(WareHouse.getVideoToken().get("videoSid"))) {
				req.getRequestDispatcher("/WEB-INF/jsp/home/error.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
