package com.zero.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.dao.AuthorityDao;
import com.zero.po.Authority;
import com.zero.service.AuthorityService;

@Service
public class AuthorityServiceImpl extends CommonServiceImpl<Authority> implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public List<Authority> getAuthorities(int roleLevel) {
		return authorityDao.getAll("where roleLevel=" + roleLevel);
	}

}
