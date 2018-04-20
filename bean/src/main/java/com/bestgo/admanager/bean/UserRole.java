package com.bestgo.admanager.bean;

public class UserRole {
	private Integer id;
	private Integer userid;
	private Integer roleid;

	public UserRole() {
		super();
	}

	public UserRole(Integer userid, Integer roleid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userid=" + userid + ", roleid="
				+ roleid + "]";
	}

}
