package com.bestgo.admanager.bean;
/**  
 *
 *  用户角色中间类
 *@author caisong
 *@version 
 *@datetime 2017年7月7日下午6:06:13  
 *
 */
public class UserRole {
	/**
	 * 用户与角色中间表id
	 */
	private Integer id;
	
	/**
	 * 用户的id
	 */
	private Integer userId;
	
	/**
	 * 角色的id
	 */
	private Integer roleId;
	
	public UserRole() {
	}

	public UserRole(Integer userId, Integer roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleId="
				+ roleId + "]";
	}

}
