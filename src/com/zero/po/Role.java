 package com.zero.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role  implements Serializable {

	private static final long serialVersionUID = 206599284451428214L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gene")
	@SequenceGenerator(name = "gene", sequenceName = "roles_id")
	private long id;
	private String roleName;
	private int roleLevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}


}
