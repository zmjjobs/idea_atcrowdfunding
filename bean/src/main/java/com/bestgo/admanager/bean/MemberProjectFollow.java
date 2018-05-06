package com.bestgo.admanager.bean;
/**
 * 项目关注类
 *@author wusiyuan
 *@version 
 *@datetime 2017年7月7日下午6:25:19  
 *
 */
public class MemberProjectFollow {
	private Integer id;
	/**
	 * 项目主键
	 */
	private Integer projectId;
	/**
	 * 会员主键
	 */
	private  Integer memberId;
	
	public MemberProjectFollow() {
		
	}

	public MemberProjectFollow(Integer id, Integer projectId, Integer memberId) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.memberId = memberId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "MemberProjectFollow [id=" + id + ", projectId=" + projectId
				+ ", memberId=" + memberId + "]";
	}
	
	
	
	
}
