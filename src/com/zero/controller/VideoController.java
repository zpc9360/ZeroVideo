package com.zero.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zero.po.Menu;
import com.zero.po.Video;
import com.zero.service.MenuService;
import com.zero.service.VideoPlayService;
import com.zero.service.VideoService;
import com.zero.utils.Page;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "video")
public class VideoController {

	@Resource
	private VideoService videoService;
	@Resource
	private MenuService menuService;
	@Resource
	private VideoPlayService videoPlayService;

	@RequestMapping(value = { "getVideo" })
	public String get(Model model, @RequestParam(defaultValue = "0") int id) {
		if (id == 0) {
			return "redirect:/index";
		}
		model.addAttribute("video", videoService.getById((long) id));
		model.addAttribute("videoPlayList", videoPlayService.getAll("where videoId=" + id));
		return getJsp("video");
	}

	@RequestMapping(value = "getOne")
	public void get(HttpServletResponse response, PrintWriter out, @RequestParam long id) {
		response.setContentType("text/html;charset=utf-8");
		out.write(JSONObject.fromObject(videoService.getById(id)).toString());
	}

	// 去添加视频页面
	@RequestMapping(value = "toVideoAdd")
	public String toAdd(Model model) {
		model.addAttribute("video", new Video());
		return getAdminJsp("add");
	}

	// 去视频更新页面
	@RequestMapping(value = "toVideoUpdate")
	public String toUpdate(Model model, @RequestParam long id) {
		model.addAttribute("video", videoService.getById(id));
		return getAdminJsp("update");
	}

	// 视频删除
	@RequestMapping(value = "videoDelete")
	public String add(@RequestParam int id) {
		Video video = new Video();
		video.setId(id);
		videoService.delete(video);
		return "redirect:videoManage";
	}

	// 视频添加
	@RequestMapping(value = "videoAdd")
	public String add(@ModelAttribute Video video, HttpServletRequest request, @RequestParam String isUploadFile) {
		String videoFilesrUrl = "resources/videos/" + (new Date()).getTime();
		video.setVideoFilesrUrl(videoFilesrUrl);
		System.out.println(isUploadFile);
		String filePath = request.getServletContext().getRealPath("") + video.getVideoFilesrUrl();
		File storeDirectory = new File(filePath);
		if (isUploadFile.equals("on")) {
			fileSave(request, video);
		}
		if (!storeDirectory.exists()) {
			storeDirectory.mkdirs();
		}
		videoService.save(video);
		return "redirect:videoManage";
	}

	// 视频更新
	@RequestMapping(value = "videoUpdate")
	public String update(@ModelAttribute Video video, HttpServletRequest request,
			@RequestParam(defaultValue = "0") int isUploadFile) {
		Video oldVideo = videoService.getById(video.getId());
		video.setVideoFilesrUrl(oldVideo.getVideoFilesrUrl());
		video.setVideoPlaySum(oldVideo.getVideoPlaySum());
		video.setVideoViewSum(oldVideo.getVideoViewSum());
		video.setVideoPhotoUrl(oldVideo.getVideoPhotoUrl());
		if (isUploadFile == 1) {
			fileSave(request, video);
		}
		videoService.update(video);
		return "redirect:videoManage";
	}

	// 视频管理主页
	@RequestMapping(value = "videoManage")
	public String home(ModelMap map, @RequestParam(defaultValue = "1") int curPage) {
		Page page = new Page();
		page.setCurPage(curPage);
		page.setDBTotalCount(videoService.getSum());
		page.setRowsPrePage(10);
		page.setActionUrl("video/videoManage");
		page.init();
		map.put("videoList", videoService.getByPage(page.getStartNum(), page.getRowsPrePage()));
		map.put("pageBar", page.getPageBar());
		map.put("video", new Video());
		Map<String, String> menuMap = new HashMap<String, String>();
		for (Menu menu : menuService.getAll("where fatherId=0 and menuKindId=1")) {
			menuMap.put(menu.getMenuName(), menu.getMenuDetail());
		}
		map.put("menuMap", menuMap);
		return getAdminJsp("home");
	}

	private boolean fileSave(HttpServletRequest request, Video video) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				String name = iter.next().toString();
				System.out.println(name);
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(name);
				if (file != null) {
					String filePath = request.getServletContext().getRealPath("") + video.getVideoFilesrUrl();
					File storeDirectory = new File(filePath);
					if (!storeDirectory.exists()) {
						storeDirectory.mkdirs();
					}
					String path = filePath + "\\cover.jpg";
					// 上传
					try {
						file.transferTo(new File(path));
						video.setVideoPhotoUrl(video.getVideoFilesrUrl() + "/cover.jpg");
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}

	private String getAdminJsp(String jsp) {
		return "/WEB-INF/jsp/admin/video/" + jsp + ".jsp";
	}

	private String getJsp(String jsp) {
		return "/WEB-INF/jsp/home/" + jsp + ".jsp";
	}

}
