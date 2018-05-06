package com.bestgo.admanager.bean;

/**
 * 
 * 项目分类类-
 * @author caisong
 * @version
 * @datetime 2017年7月7日下午6:10:59
 * 
 */
public class Type {
	/**
	 * 项目分类的id
	 */
	private Integer id;
	/**
	 * 项目分类的名称
	 */
	private String name;
	/**
	 * 项目分类的描述
	 */
	private String remark;

	public Type(Integer id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}

	public Type() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}

}
