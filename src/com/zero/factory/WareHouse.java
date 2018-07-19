package com.zero.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zero.po.Menu;

public class WareHouse{

	// 视频资源校验码
	private static Map<String, String> videoToken;
	// 权限字典
	private static Map<Integer, Set<String>> authorityMap;
	// 主菜单
	private static List<Menu> indexMenuList;
	private static Set<String> indexMenuSet;
	// 管理菜单
	private static Map<Menu, List<Menu>> adminMenuMap;

	public static boolean isPermit(String action, int roleId) {
		if (WareHouse.authorityMap.get(roleId).contains(action))
			return true;
		return false;
	}

	public static boolean isIndexMenu(String path) {
		if(WareHouse.indexMenuSet.contains(path))
			return true;
		return false;
	}
	
	public static Map<Integer, Set<String>> getAuthorityMap() {
		return authorityMap;
	}

	public static void setAuthorityMap(Map<Integer, Set<String>> authorityMap) {
		WareHouse.authorityMap = authorityMap;
	}

	public static Map<Menu, List<Menu>> getAdminMenuMap() {
		return adminMenuMap;
	}

	public static void setAdminMenuMap(Map<Menu, List<Menu>> adminMenuMap) {
		WareHouse.adminMenuMap = adminMenuMap;
	}

	public static List<Menu> getIndexMenuList() {
		return indexMenuList;
	}

	public static void setIndexMenuList(List<Menu> indexMenuList) {
		WareHouse.indexMenuList = indexMenuList;
	}

	public static Set<String> getIndexMenuSet() {
		return indexMenuSet;
	}

	public static void setIndexMenuSet(Set<String> indexMenuSet) {
		WareHouse.indexMenuSet = indexMenuSet;
	}

	public static Map<String, String> getVideoToken() {
		return videoToken;
	}

	public static void setVideoToken(Map<String, String> videoToken) {
		WareHouse.videoToken = videoToken;
	}

}
