package com.zero.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zero.dao.UserDao;
import com.zero.po.User;
import com.zero.service.UserService;

@Service
public class UserServiceImpl extends CommonServiceImpl<User> implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public void regist(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public long getUserSum(String... strings) {
		return userDao.getSum(strings);
	}

	@Override
	public User getUserById(Serializable id) {
		return userDao.getById(id);
	}

	@Override
	public User getUser(String... strings) {
		return userDao.get(strings);
	}

	@Override
	public List<User> getAllUsers(String... strings) {
		return userDao.getAll(strings);
	}

	@Override
	public List<User> getAllUsersByPage(int start, int perPageSum, String... strings) {
		return userDao.getByPage(start, perPageSum, strings);
	}

	@Override
	public User login(String userName, String passWord) {
		return userDao.longin(userName, passWord);
	}

}
