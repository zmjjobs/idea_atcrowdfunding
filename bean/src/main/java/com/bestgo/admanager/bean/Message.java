package com.bestgo.admanager.bean;
/**
 * 消息类
 *@author wusiyuan
 *@version 
 *@datetime 2017年7月7日下午6:35:00  
 *
 */
public class Message {
	
	private Integer id;
	
	/**
	 * 接收消息会员Id
	 */
	private Integer memberId;
	
	/**
	 * 内容
	 */
	private String context;
	
	/**
	 * 发送时间
	 */
	private String sendDate;

	public Message() {
		
	}

	public Message(Integer id, Integer memberId, String context, String sendDate) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.context = context;
		this.sendDate = sendDate;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", memberId=" + memberId + ", context="
				+ context + ", sendDate=" + sendDate + "]";
	}
	
	

}
