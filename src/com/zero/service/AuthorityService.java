package com.zero.service;

import java.util.List;

import com.zero.po.Authority;

public interface AuthorityService extends CommonService<Authority> {

	public List<Authority> getAuthorities(int roleLevel);

}
