package com.zero.service;

import java.io.Serializable;
import java.util.List;

public interface CommonService<T> {

	public void save(T t);

	public void delete(T t);

	public void update(T t);

	public long getSum(String... strings);

	public T getById(Serializable id);

	public T get(String... strings);

	public List<T> getAll(String... strings);

	public List<T> getByPage(int start, int perPageSum, String... strings);

	
}
