package com.zero.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.zero.po.User;
import com.zero.service.UserService;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping(value = "user")
@SessionAttributes({ "currenUser", "userMessageSum" })
public class UserController {

	@Resource
	private UserService userService;

	// 登录
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam String userName, @RequestParam String passWord,HttpSession session, Model map) {
		User user = userService.login(userName, passWord);
		if (user != null) {
			session.setAttribute("currenUser", user);
			session.setAttribute("userMessageSum", 15);
			return "redirect:/index";
		}
		return "redirect:toLogin";
	}

	// 登出
	@RequestMapping(value = "logout")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "/index";
	}

	// 注册
	@RequestMapping(value = "regist")
	public String regist(@ModelAttribute User user) {
		Random rand = new Random();
		int number = rand.nextInt(61);
		String numStr = number < 10 ? "0" + number : String.valueOf(number);
		user.setHeadImg("resources/imgs/userDefaultHead/" + numStr + ".png");
		user.setRoleId(2);
		userService.regist(user);
		return "redirect:toLogin";
	}

	// 更新
	@RequestMapping(value = "userUpdate")
	public String update(@ModelAttribute User user) {
		userService.update(user);
		return getJsp("detail");
	}

	// 去个人信息更新页
	@RequestMapping(value = "toUserUpdate")
	public String toUpdate(Model model, @SessionAttribute User user) {
		model.addAttribute("user", user);
		return getJsp("detailUpdate");
	}

	// 去注册页
	@RequestMapping(value = "toUserRegist")
	public String toAdd(Model model) {
		model.addAttribute("user", new User());
		return getJsp("regist");
	}

	// 头像上传
	@RequestMapping(value = "headImgUpload")
	public ModelAndView headImgUpload(@RequestParam String img, HttpServletRequest request, ModelMap map) {
		User user = (User) map.get("currenUser");
		String base64StrImgData = img;
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		try {
			byte[] bytes = decoder.decodeBuffer(base64StrImgData);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			String src = "\\resources\\imgs\\userHeadImg";
			String filePath = request.getServletContext().getRealPath(src);
			File storeDirectory = new File(filePath);
			if (!storeDirectory.exists()) {
				storeDirectory.mkdirs();
			}
			// 截取上传的文件名
			String fileName = user.getUserName() + ".png";
			String fullFileName = filePath + "\\" + fileName;
			request.setAttribute("head", src + fileName);
			String userHeadImg = src.replace("\\", "/").substring(1) + "/" + fileName;
			user.setHeadImg(userHeadImg);
			map.put("currenUser", user);
			userService.update(user);
			// 生成jpeg图片
			FileOutputStream o = new FileOutputStream(fullFileName);
			o.write(bytes);
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:toHead", map);
	}

	private String getJsp(String jsp) {
		return "/WEB-INF/jsp/user/" + jsp + ".jsp";
	}

}
