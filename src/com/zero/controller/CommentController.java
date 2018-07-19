package com.zero.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zero.po.Comment;
import com.zero.popl.UVComment;
import com.zero.service.CommentService;
import com.zero.service.UserService;
import com.zero.utils.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "comment")
public class CommentController {

	@Resource
	private CommentService commentService;
	@Resource
	private UserService userService;

	@RequestMapping(value = "addComment")
	public void addComments(HttpServletResponse response, PrintWriter out, @RequestParam String content,
			@RequestParam(defaultValue = "0") long userId, @RequestParam(defaultValue = "0") long videoId,
			@RequestParam(defaultValue = "0") long commentId) {
		if (userId == 0 || videoId == 0)
			return;
		Comment comment = new Comment();
		comment.setVideoId(videoId);
		comment.setUserId(userId);
		comment.setContent(content.replaceAll("[<>]", ""));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setCreateDate(df.format(new Date()));
		commentService.save(comment);
		getComments(response, out, videoId, commentId, 0);

	}

	@RequestMapping(value = "getComments")
	public void getComments(HttpServletResponse response, PrintWriter out,
			@RequestParam(defaultValue = "0") long videoId, @RequestParam(defaultValue = "0") long commentId,
			@RequestParam(defaultValue = "0") int curPage) {
		if (videoId == 0)
			return;
		response.setContentType("text/html;charset=utf-8");
		Page page = new Page();
		page.setCurPage(curPage);
		page.setDBTotalCount(commentService.getSum("where videoId=" + videoId));
		page.setRowsPrePage(15);
		page.setActionUrl("comment/getComments?videoId="+videoId);
		List<UVComment> list = new ArrayList<UVComment>();
		for(Comment comment : commentService.getByPage(page.getStartNum(), page.getRowsPrePage(), "where videoId=" + videoId + " order by createDate desc")) {
			list.add(new UVComment(userService.getById(comment.getUserId()), comment));
		}
		JSONArray json = JSONArray.fromObject(list);
		out.write(json.toString());
	}

}
