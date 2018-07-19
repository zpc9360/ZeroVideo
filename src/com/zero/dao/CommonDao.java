package com.zero.dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDao<T> {

	// 保存
	public void save(T t);

	// 删除
	public void delete(T t);

	// 更新
	public void update(T t);

	// 获取数量（可以添加条件或者排序等）
	public long getSum(String... strings);

	// 根据ID获取数据
	public T getById(Serializable id);

	// 根据条件获得单条数据
	public T get(String... strings);

	// 获得多条数据（可以添加条件或者排序等）
	public List<T> getAll(String... strings);

	// 获得多条数据并分页（可以添加条件或者排序等）
	public List<T> getByPage(int start, int perPageSum, String... strings);

}
