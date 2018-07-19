package com.zero.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zero.dao.UserDao;
import com.zero.po.User;

@SuppressWarnings("deprecation")
@Repository
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

	@Autowired
	private SessionFactory session;

	@SuppressWarnings("unchecked")
	@Override
	public User longin(String userName, String passWord) {
		String sql = "from User where userName=:userName and passWord=:passWord";
		Query<User> query = session.getCurrentSession().createQuery(sql);
		query.setString("userName", userName);
		query.setString("passWord", passWord);
		if (query.getResultList().size() > 0)
			return query.list().iterator().next();
		return null;
	}

}
