package com.zero.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zero.po.User;
import com.zero.po.Video;
import com.zero.po.VideoPlay;
import com.zero.service.VideoPlayService;
import com.zero.service.VideoService;
import com.zero.utils.MyUtil;
import com.zero.utils.Page;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "videoPlay")
@SessionAttributes(names = { "currenUser" })
public class VideoPlayController {

	@Resource
	private VideoPlayService vpService;
	@Resource
	private VideoService vService;

	@RequestMapping(value = { "play" })
	public String get(Model model, @RequestParam(defaultValue = "0") long id,
			@SessionAttribute(required = false) User currenUser) {
		if (id == 0) {
			return "redirect:/index";
		}
		VideoPlay videoPlay = vpService.getById(id);
		Video video = vService.getById(videoPlay.getVideoId());
		if (videoPlay.getIsCharge() == 1) {
			if (currenUser == null) {
				return "redirect:/user/toLogin";
			}
		}
		video.setVideoViewSum(video.getVideoViewSum() + 1);
		vService.update(video);
		model.addAttribute("videoToken", MyUtil.getToken("videoSid"));
		model.addAttribute("play", videoPlay);
		return getJsp("play");
	}

	@RequestMapping(value = "getOne")
	public void get(HttpServletResponse response, PrintWriter out, @RequestParam long id) {
		response.setContentType("text/html;charset=utf-8");
		out.write(JSONObject.fromObject(vpService.getById(id)).toString());
	}

	// 去添加视频页面
	@RequestMapping(value = "toVideoPlayAdd")
	public String toAdd(Model model) {
		model.addAttribute("videoPlay", new VideoPlay());
		return getAdminJsp("add");
	}

	// 去视频更新页面
	@RequestMapping(value = "toVideoPlayUpdate")
	public String toUpdate(Model model, @RequestParam long id) {
		model.addAttribute("videoPlay", vpService.getById(id));
		return getAdminJsp("update");
	}

	// 视频删除
	@RequestMapping(value = "videoPlayDelete")
	public String add(@RequestParam long id) {
		VideoPlay videoPlay = vpService.getById(id);
		Video video = vService.getById(videoPlay.getVideoId());
		video.setVideoPlaySum(video.getVideoPlaySum() - 1);
		vpService.delete(videoPlay);
		return "redirect:videoPlayManage?id=" + videoPlay.getVideoId();
	}

	// 视频添加
	@RequestMapping(value = "videoPlayAdd")
	public String add(@ModelAttribute VideoPlay videoPlay, HttpServletRequest request,
			@RequestParam(defaultValue = "0") int isUploadFile) {
		Video video = vService.getById(videoPlay.getVideoId());
		video.setVideoPlaySum(video.getVideoPlaySum() + 1);
		if (isUploadFile == 1) {
			fileSave(request, video, videoPlay);
		}
		vpService.save(videoPlay);
		vService.update(video);
		return "redirect:videoPlayManage?id=" + videoPlay.getVideoId();
	}

	// 视频更新
	@RequestMapping(value = "videoPlayUpdate")
	public String update(@ModelAttribute VideoPlay videoPlay, HttpServletRequest request,
			@RequestParam(defaultValue = "0") int isUploadFile) {
		Video video = vService.getById(videoPlay.getVideoId());
		if (isUploadFile == 1) {
			fileSave(request, video, videoPlay);
		}
		vpService.update(videoPlay);
		return "redirect:videoPlayManage?id=" + videoPlay.getVideoId();
	}

	// 视频管理主页
	@RequestMapping(value = "videoPlayManage")
	public String home(ModelMap map, @RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "0") long id) {
		if (id == 0)
			return "redirect:/video/videoManage";
		Page page = new Page();
		page.setCurPage(curPage);
		page.setDBTotalCount(vpService.getSum("where videoId=" + id));
		page.setRowsPrePage(10);
		page.setActionUrl("videoPlay/videoPlayManage?id=" + id);
		page.init();
		map.put("playList", vpService.getByPage(page.getStartNum(), page.getRowsPrePage(), "where videoId=" + id));
		map.put("video", vService.getById(id));
		map.put("pageBar", page.getPageBar());
		map.put("play", new VideoPlay());
		return getAdminJsp("home");
	}

	private boolean fileSave(HttpServletRequest request, Video video, VideoPlay videoPlay) {
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
				// System.out.println(name);
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(name);
				if (file != null) {
					String filePath = request.getServletContext().getRealPath("") + video.getVideoFilesrUrl();
					File storeDirectory = new File(filePath);
					if (!storeDirectory.exists()) {
						storeDirectory.mkdirs();
					}
					String fileName = (new Date()).getTime()
							+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					String fullFileName = filePath + "\\" + fileName;
					videoPlay.setVideoUrl(video.getVideoFilesrUrl() + "/" + fileName);
					// 上传
					try {
						file.transferTo(new File(fullFileName));
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
		return "/WEB-INF/jsp/admin/videoPlay/" + jsp + ".jsp";
	}

	private String getJsp(String jsp) {
		return "/WEB-INF/jsp/home/" + jsp + ".jsp";
	}

}
