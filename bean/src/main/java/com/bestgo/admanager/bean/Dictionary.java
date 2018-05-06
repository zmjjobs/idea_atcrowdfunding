package com.bestgo.admanager.bean;
/**  
 *	数据字典
 *  
 *@author lizhentao
 *@version 
 *@datetime 2017年7月7日下午5:00:59  
 */

public class Dictionary {
	/**
	 * 主键
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
	 * 分类代码
	 */
	private String subcode;
	/**
	 * 值
	 */
	private String val;
	public Dictionary() {
		super();
	}
	public Dictionary(Integer id, String name, String code, String subcode,
			String val) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.subcode = subcode;
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
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", name=" + name + ", code=" + code
				+ ", subcode=" + subcode + ", val=" + val + "]";
	}
	
}
