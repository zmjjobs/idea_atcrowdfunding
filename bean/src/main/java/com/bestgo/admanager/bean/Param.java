package com.bestgo.admanager.bean;
/**
 * 参数表属性
 *@author zhaokexiang
 *@version 
 *@datetime 2017年7月7日下午5:57:25  
 */
public class Param {
	/**
	 * 参数编号
	 */
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 代码
	 */
	private String code;
	
	/**
	 * 值
	 */
	private String val;
	
	public Param() {
	}
	
	public Param(Integer id, String name, String code, String val) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.val = val;
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
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getVal() {
		return val;
	}
	
	public void setVal(String val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "Param [id=" + id + ", name=" + name + ", code=" + code
				+ ", val=" + val + "]";
	}
	
	
}
