package com.bestgo.admanager.bean;
/**  
 *	资质表
 *  
 *@author lizhentao
 *@version 
 *@datetime 2017年7月7日下午5:00:40  
 */

public class Cert {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 资质名称
	 */
	private String name;
	public Cert() {
		super();
	}
	public Cert(Integer id, String name) {
		super();
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
		return "Cert [id=" + id + ", name=" + name + "]";
	}
	
}
