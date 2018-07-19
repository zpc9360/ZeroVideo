package com.zero.tags;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class VideoItem extends SimpleTagSupport {

	private JspContext jspContext;
	private JspWriter out;
	//private PageContext pageContext;

	private List<?> list;
	private String detail;
	private String url;

	@Override
	public void doTag() throws JspException, IOException {

		this.jspContext = getJspContext();
		//this.pageContext = (PageContext) jspContext;
		this.out = jspContext.getOut();
		init();

	}

	private void init() throws IOException {
		menuBar();
	}

	private void menuBar() throws IOException {
		out.print("		<div class=\"row p-1 rounded text-center\" style=\"background-color: #CCCCCC;\">\r\n");
		for (Object obj : list) {
			try {
				Method methodDetail = obj.getClass().getMethod("get" + detail.toUpperCase().charAt(0) + detail.substring(1));
				Method methodUrl = obj.getClass().getMethod("get" + url.toUpperCase().charAt(0) + url.substring(1));
				out.print("			<div class=\"col\"><a class=\"px-5 btn btn-sm btn-block btn-noborder text-dark rounded\" href=\"" + methodUrl.invoke(obj).toString() + "\">" + methodDetail.invoke(obj).toString() + "</a></div>");
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		out.print("		</div>\r\n");
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
