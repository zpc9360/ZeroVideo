package com.zero.tags;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zero.po.Menu;

public class AdminNav extends SimpleTagSupport {

	private JspContext jspContext;
	private JspWriter out;
	private PageContext pageContext;
	
	private Map<String, List<Menu>> map;
	private String valueName;

	public void setMap(Map<String, List<Menu>> map) {
		this.map = map;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	@Override
	public void doTag() throws JspException, IOException {

		this.jspContext = getJspContext();
		this.pageContext = (PageContext) jspContext;
		this.out = jspContext.getOut();
		init();

	}

	private void init() throws IOException {
		context();
	}

	private void context() {
		
		
		
		
	}
	
}
