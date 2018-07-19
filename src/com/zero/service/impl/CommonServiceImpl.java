package com.zero.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zero.dao.CommonDao;
import com.zero.service.CommonService;

public class CommonServiceImpl<T> implements CommonService<T> {

	@Autowired
	private CommonDao<T> commonDao;
	
	@Override
	public void save(T t) {
		commonDao.save(t);
	}

	@Override
	public void delete(T t) {
		commonDao.delete(t);
	}

	@Override
	public void update(T t) {
		commonDao.update(t);
	}

	@Override
	public long getSum(String... strings) {
		return commonDao.getSum(strings);
	}

	@Override
	public T getById(Serializable id) {
		return (T) commonDao.getById(id);
	}

	@Override
	public T get(String... strings) {
		return (T) commonDao.get(strings);
	}

	@Override
	public List<T> getAll(String... strings) {
		return commonDao.getAll(strings);
	}

	@Override
	public List<T> getByPage(int start, int perPageSum, String... strings) {
		return commonDao.getByPage(start, perPageSum, strings);
	}

}
