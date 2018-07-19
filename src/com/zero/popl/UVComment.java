package com.zero.popl;

import java.io.Serializable;

import com.zero.po.Comment;
import com.zero.po.User;

public class UVComment implements Serializable {

	private static final long serialVersionUID = -5036266823106926960L;

	// User
	private long userId;
	private String nickName;
	private String gender;
	private int roleId;
	private String headImg;

	// Comment
	private String content;
	private String createDate;

	public UVComment(User user, Comment comment) {
		this.userId = user.getId();
		this.nickName = user.getNickName();
		this.gender = user.getGender();
		this.roleId = user.getRoleId();
		this.headImg = user.getHeadImg();

		this.content = comment.getContent();
		this.createDate = comment.getCreateDate();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
