package com.zero.dao;

import com.zero.po.User;

public interface UserDao extends CommonDao<User> {

	public User longin(String userName, String passWord);
	
}
