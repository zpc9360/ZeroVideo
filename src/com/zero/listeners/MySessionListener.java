/**
 * 
 */
package com.zero.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zero.po.User;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("session 创建");
		User user = (User) arg0.getSession().getAttribute("currenUser");
		if (user != null) {
			System.out.println("MySessionListener:" + user.getUserName());
		}

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("session 销毁");
		User user = (User) arg0.getSession().getAttribute("currenUser");
		if (user != null) {
			System.out.println("MySessionListener:" + user.getUserName());
		}
	}
}
