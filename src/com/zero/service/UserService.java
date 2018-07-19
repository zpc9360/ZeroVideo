package com.zero.service;

import java.io.Serializable;
import java.util.List;

import com.zero.po.User;

public interface UserService extends CommonService<User>{

	public User login(String userName, String passWord);

	public void regist(User user);

	public void delete(User user);

	public void update(User user);

	public long getUserSum(String... strings);

	public User getUserById(Serializable id);

	public User getUser(String... strings);

	public List<User> getAllUsers(String... strings);

	public List<User> getAllUsersByPage(int start, int perPageSum, String... strings);

}
