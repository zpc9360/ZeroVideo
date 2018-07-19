package com.zero.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

	private static final long serialVersionUID = -4226825646114658981L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gene")
	@SequenceGenerator(name = "gene", sequenceName = "authorities_id")
	private long id;
	private String authorityDetail;
	private String authorityName;
	private int roleLevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDetail() {
		return authorityDetail;
	}

	public void setAuthorityDetail(String authorityDetail) {
		this.authorityDetail = authorityDetail;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

}
