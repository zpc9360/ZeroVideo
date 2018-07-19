package com.zero.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zero.factory.WareHouse;

public class MyUtil {

	public static String getToken(String key) {
		StringBuffer token = new StringBuffer("?" + key + "=");
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Map<String, String> videoToken = new HashMap<String,String>();
		videoToken.put(key, uuid);
		WareHouse.setVideoToken(videoToken);
		token.append(uuid);
		return token.toString();
	}

	public static void setBaseConfig(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		request.setAttribute("basePath", basePath);
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void cleanMyCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	public static boolean notNull(String str) {
		if (null != str && !"".equals(str)) {
			return true;
		}
		return false;
	}
}
