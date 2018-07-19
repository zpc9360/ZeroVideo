package com.zero.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zero.po.User;

public class IndexNav extends SimpleTagSupport {

	private JspContext jspContext;
	private JspWriter out;
	private PageContext pageContext;
	
	@Override
	public void doTag() throws JspException, IOException {

		this.jspContext = getJspContext();
		this.pageContext = (PageContext) jspContext;
		this.out = jspContext.getOut();
		init();

	}

	private void init() throws IOException {
		navBar();
	}

	private void navBar() throws IOException {
		User user = (User) pageContext.getSession().getAttribute("currenUser");
		out.print("	<div class=\"mybody\"></div>\r\n");
		out.print("	<nav class=\"navbar navbar-expand-sm navbar-light bg-light\">\r\n"
				+ "		<div class=\"blur-bg\"></div>\r\n<div class=\"nav-mask\"></div>\r\n"
				+ "		<!-- logo -->\r\n"
				+ "		<a class=\"navbar-brand nav-con py-0 my-0\" href=\"index\">"
				+ "		<img width=\"40px\" height=\"40px\" src=\"resources/imgs/zero/logo.png\" />\r\n" + "		</a>\r\n");
		out.print("		<!-- 主要导航栏 -->\r\n"
				+ "		<div class=\"collapse navbar-collapse nav-con\">\r\n");
		out.print("			<ul class=\"navbar-nav ml-5 mr-auto\">");
		// 循环我的导航栏菜单
		out.print("				<li class=\"nav-item\"><a class=\"nav-link\" href=\"index\">主页</a></li>");
		if(user != null) {
			// 显示我的空间
			// out.print("				<li class=\"nav-item\"><a class=\"nav-link\" href=\"user/myZone\">我的空间</a></li>");
			if(user.getRoleId() >= 3) {
				// 显示网站管理
				out.print("				<li class=\"nav-item\"><a class=\"nav-link\" href=\"admin/home\">网站管理</a></li>");
			}
		}
		// 循环结束
		out.print("			</ul>");
		// 搜索框
		out.print("			<!-- 搜索 -->\r\n"
				+ "			<form action=\"Search\" class=\"form-inline mr-5\">\r\n"
				+ "				<input class=\"form-control form-control-sm mr-sm-2\"\r\n"
				+ "					style=\"width: 160px;\" type=\"search\" placeholder=\"视频名 / 演员 / 作者 \"\r\n"
				+ "					aria-label=\"Search\" name=\"search\">\r\n"
				+ "				<button class=\"btn btn-outline-secondary btn-sm my-2 my-sm-0\"\r\n"
				+ "					type=\"submit\">搜索</button>\r\n" + "			</form>\r\n");
		if (user == null) {
			// 登录注册
			out.print("		<!-- 登录注册 -->\r\n"
					+ "		<ul class=\"navbar-nav mr-2\">\r\n"
					+ "			<li class=\"nav-item\"><a class=\"nav-link\" href=\"user/toLogin\">登录</a></li>\r\n"
					+ "			<li class=\"nav-item\"><a class=\"nav-link\" href=\"user/toUserRegist\">注册</a></li>\r\n"
					+ "		</ul></div></nav>");
			out.print("	<div style=\"margin-top: 130px\"></div>\r\n");
			return ;
		}
		// 用户已登陆
		out.print("			<ul class=\"navbar-nav\">\r\n" + "				<!-- 用户头像-->\r\n"
				+ "				<li class=\"nav-item dropdown mr-0\">\r\n"
				+ "					<button class=\"nav-link dropdown-toggle\" id=\"navbarDropdown\"\r\n"
				+ "						role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\"\r\n"
				+ "						aria-expanded=\"false\"\r\n"
				+ "						style=\"background-color: rgba(255, 255, 255, 0); border: 0px; outline: none; cursor: pointer;\">\r\n");
		Object _sum = (Object) pageContext.getSession().getAttribute("userMessageSum");
		String sum = "";
		if(_sum!=null) {
			if((int)_sum>0)
				sum = String.valueOf(_sum);
		}
		// 个人消息数
//		out.print("						<span class=\"badge badge-pill badge-danger\" style=\"position:absolute;\">"+sum+"</span>\r\n");
		// 用户头像
		out.print("						<img height=\"40\" width=\"40\" class=\"ml-2 rounded-circle\" src=\"" + user.getHeadImg() + "\" />"
				+ "					</button>\r\n");
		// 个人下拉菜单
		out.print("					<div class=\"dropdown-menu dropdown-menu-right\"\r\n"
				+ "						aria-labelledby=\"navbarDropdown\">\r\n"
//				+ "						<a class=\"dropdown-item\" href=\"user/toDeatil\">个人信息</a>"
//				+ "						<a class=\"dropdown-item\" href=\"user/toDeatil\">我的消息"
//				+ "						<span class=\"ml-3 badge badge-pill badge-danger\">"+sum+"</span>"
//				+ "						</a>"
//				+ "						<div class=\"dropdown-divider\"></div>\r\n"
				+ "						<a class=\"dropdown-item\" href=\"user/logout\">注销</a>\r\n"
				+ "					</div>\r\n");
		// 结尾的标签
		out.print("				</li>\r\n" + "			</ul>\r\n" + "		</div>\r\n" + "	</nav>\r\n");

	}

}
