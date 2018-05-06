package com.bestgo.admanager.bean;

import java.math.BigDecimal;

/**  
 *
 *  回报类
 *@author caisong
 *@version 
 *@datetime 2017年7月7日下午6:29:27  
 *
 */
public class Return {
	
	private Integer id;
	
	private Integer projectId;
	
	private String type;
	
	private BigDecimal supportMoney;
	
	private String content;
	/**
	 * 回报数量
	 */
	private Integer count;
	/**
	 * 单笔限购数量
	 */
	private Integer signalPurchase;
	/**
	 * 限购数量
	 */
	private Integer purchase ;
	/**
	 * 运费
	 */
	private BigDecimal freight;
	/**
	 * 发票
	 */
	private String  invoice;
	/**
	 * 回报时间（天数）
	 */
	private Integer rtnDate;
	public Return() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Return(Integer id, Integer projectId, String type,
			BigDecimal supportMoney, String content, Integer count,
			Integer signalPurchase, Integer purchase, BigDecimal freight,
			String invoice, Integer rtnDate) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.supportMoney = supportMoney;
		this.content = content;
		this.count = count;
		this.signalPurchase = signalPurchase;
		this.purchase = purchase;
		this.freight = freight;
		this.invoice = invoice;
		this.rtnDate = rtnDate;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getSupportMoney() {
		return supportMoney;
	}
	public void setSupportMoney(BigDecimal supportMoney) {
		this.supportMoney = supportMoney;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getSignalPurchase() {
		return signalPurchase;
	}
	public void setSignalPurchase(Integer signalPurchase) {
		this.signalPurchase = signalPurchase;
	}
	public Integer getPurchase() {
		return purchase;
	}
	public void setPurchase(Integer purchase) {
		this.purchase = purchase;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Integer getRtnDate() {
		return rtnDate;
	}
	public void setRtnDate(Integer rtnDate) {
		this.rtnDate = rtnDate;
	}
	@Override
	public String toString() {
		return "Return [id=" + id + ", projectId=" + projectId + ", type="
				+ type + ", supportMoney=" + supportMoney + ", content="
				+ content + ", count=" + count + ", signalPurchase="
				+ signalPurchase + ", purchase=" + purchase + ", freight="
				+ freight + ", invoice=" + invoice + ", rtnDate=" + rtnDate
				+ "]";
	}
	
	
}
