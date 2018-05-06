package com.bestgo.admanager.bean;

import java.math.BigDecimal;

/**
 * 项目表属性
 * @author zhaokexiang
 * @version 
 * @datetime 2017年7月7日下午5:57:53
 */
public class Project {
	/**
	 * 项目编号
	 */
	private Integer id;
	
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 项目简介
	 */
	private String remak;
	
	/**
	 *筹资金额
	 */
	private BigDecimal money;
	
	/**
	 * 筹资天数
	 */
	private Integer day;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 发布日期
	 */
	private String deployDate;
	
	/**
	 * 支持金额
	 */
	private BigDecimal supportMoney;
	
	/**
	 * 支持者数量
	 */
	private Integer supporter;
	
	/**
	 * 完成度
	 */
	private Integer completion;
	
	/**
	 * 发起人ID
	 */
	private Integer memberId;
	
	/**
	 * 创建日期
	 */
	private String createDate;
	
	/**
	 * 关注者数量
	 */
	private Integer follower;
	

	public Project() {
	}

	public Project(Integer id, String name, String remak, BigDecimal money,
			Integer day, String status, String deployDate,
			BigDecimal supportMoney, Integer supporter, Integer completion,
			Integer memberId, String createDate, Integer follower) {
		super();
		this.id = id;
		this.name = name;
		this.remak = remak;
		this.money = money;
		this.day = day;
		this.status = status;
		this.deployDate = deployDate;
		this.supportMoney = supportMoney;
		this.supporter = supporter;
		this.completion = completion;
		this.memberId = memberId;
		this.createDate = createDate;
		this.follower = follower;
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

	public String getRemak() {
		return remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeployDate() {
		return deployDate;
	}

	public void setDeployDate(String deployDate) {
		this.deployDate = deployDate;
	}

	public BigDecimal getSupportMoney() {
		return supportMoney;
	}

	public void setSupportMoney(BigDecimal supportMoney) {
		this.supportMoney = supportMoney;
	}

	public Integer getSupporter() {
		return supporter;
	}

	public void setSupporter(Integer supporter) {
		this.supporter = supporter;
	}

	public Integer getCompletion() {
		return completion;
	}

	public void setCompletion(Integer completion) {
		this.completion = completion;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getFollower() {
		return follower;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", remak=" + remak
				+ ", money=" + money + ", day=" + day + ", status=" + status
				+ ", deployDate=" + deployDate + ", supportMoney="
				+ supportMoney + ", supporter=" + supporter + ", completion="
				+ completion + ", memberId=" + memberId + ", createDate="
				+ createDate + ", follower=" + follower + "]";
	}

	
}
