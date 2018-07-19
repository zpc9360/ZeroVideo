package com.zero.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zero.dao.CommonDao;

@Transactional
public class CommonDaoImpl<T> implements CommonDao<T> {

	private Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public CommonDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public T getById(Serializable id) {
		return sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String... strings) {
		Query<T> query = sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getSimpleName() + getMySql(strings));
		return query.getSingleResult();
	}

	@Override
	public void delete(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public void update(T t) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getSum(String... strings) {
		Query<T> q = sessionFactory.getCurrentSession()
				.createQuery("select count(*) from " + clazz.getSimpleName() + getMySql(strings));
		return (long) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(String... strings) {
		Query<T> query = sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getSimpleName() + getMySql(strings));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByPage(int start, int perPageSum, String... strings) {
		Query<T> query = sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getSimpleName() + getMySql(strings));
		query.setFirstResult(start).setMaxResults(perPageSum);
		return query.getResultList();
	}

	private String getMySql(String... strings) {
		StringBuffer sql = new StringBuffer("");
		if (strings.length != 0) {
			sql.append(" ");
			for (String str : strings) {
				sql.append(str);
			}
		}
		return sql.toString();
	}

}
