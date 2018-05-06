package com.bestgo.admanager.bean;
/**  
 *
 *标签类
 *@author caisong
 *@version 
 *@datetime 2017年7月7日下午6:17:22  
 
 */
public class Tag {
	/**
	 * 主键id;
	 */
	private Integer id;
	/**
	 * 标签id;
	 */
	private Integer pid;
	/**
	 * 标签名
	 */
	private String name;

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}

}
