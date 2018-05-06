package com.bestgo.admanager.bean;
/**
 * 流程单
 * @author Administrator
 *
 */
public class Ticket {
	/**
	 * 流程单ID
	 */
	private Integer id;
	
	/**
	 * 会员ID
	 */
	private Integer memberid;
	
	/**
	 *流程实例Id
	 */
	private String piid;
	/**
	 * 流程单状态，默认为审核中
	 * 0-审核中，1-审核完毕
	 */
	private String status = "0";
	
	/**
	 * 认证验证码
	 */
	private String authcode;
	/**
	 * 流程所要进行到的步骤
	 * accttype-账户类型，basicinfo-基本信息，uploadfile-资质文件上传，checkemail-邮箱确认
	 */
	private String pstep;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getPstep() {
		return pstep;
	}
	public void setPstep(String pstep) {
		this.pstep = pstep;
	}
	
}
