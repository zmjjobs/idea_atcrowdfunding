package com.bestgo.admanager.bean;
/**  
 *
 *  许可类型
 *@author caisong
 *@version 
 *@datetime 2017年7月7日下午6:22:02  
 *
 */
public class TypePermission {
	/**
	 * 主键id;
	 */
	private Integer id;
	/**
	 * 角色id;
	 */
	private Integer roleId;
	/**
	 * 许可id;
	 */
	private Integer permissionId;

	public TypePermission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypePermission(Integer id, Integer roleId, Integer permissionId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "TypePermission [id=" + id + ", roleId=" + roleId
				+ ", permissionId=" + permissionId + "]";
	}
	
	

}
