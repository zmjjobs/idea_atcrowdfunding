package com.bestgo.admanager.bean;
/**  
 *
 * 用户角色类 
 *@author caisong
 *@version 
 *@datetime 2017年7月7日下午6:25:49  
 
 */
public class Role {
	/**
	 * 角色主键id;
	 */
	private Integer id;
	/**
	 * 角色名
	 */
	private String name;
	public Role() {
	}
	public Role(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	

}
