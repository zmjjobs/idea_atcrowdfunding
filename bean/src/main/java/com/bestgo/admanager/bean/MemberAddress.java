package com.bestgo.admanager.bean;
/**
 * 用户地址
 *@author wusiyuan
 *@version 
 *@datetime 2017年7月7日下午6:01:24  
 *
 */
public class MemberAddress {
	 
	private Integer id;
	/**
	 * 会员id
	 */
	private Integer memberId;
	/**
	 * 会员地址
	 */
	private String address;
	
	public MemberAddress() {
		super();
	}
	
	public MemberAddress(Integer id, Integer memberId, String address) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.address = address;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "MemberAddress [id=" + id + ", memberId=" + memberId
				+ ", address=" + address + "]";
	}	
	
}
