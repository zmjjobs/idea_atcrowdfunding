package com.bestgo.admanager.bean;
/**  
 *	账户资质关系表
 *  
 *@author lizhentao
 *@version 
 *@datetime 2017年7月7日下午4:59:53  
 *			
 */
public class AccountTypeCert {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 账户类型
	 */
	private String accttype;
	/**
	 * 资质主键
	 */
	private Integer certid;
	
	public AccountTypeCert() {
		super();
	}
	public AccountTypeCert(String accttype, Integer certid) {
		this.accttype = accttype;
		this.certid = certid;
	}
	
	
	public AccountTypeCert(Integer id, String accttype, Integer certid) {
		super();
		this.id = id;
		this.accttype = accttype;
		this.certid = certid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccttype() {
		return accttype;
	}
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public Integer getCertid() {
		return certid;
	}
	public void setCertid(Integer certid) {
		this.certid = certid;
	}
	@Override
	public String toString() {
		return "AccountTypeCert [id=" + id + ", accttype=" + accttype
				+ ", certid=" + certid + "]";
	}
	
}
