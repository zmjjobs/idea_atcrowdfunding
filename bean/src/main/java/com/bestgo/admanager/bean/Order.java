package com.bestgo.admanager.bean;

import java.math.BigDecimal;

/**
 *  订单类
 * @author wusiyuan
 * @version
 * @datetime 2017年7月7日下午6:43:42
 * 
 */
public class Order {
	
	private Integer id;
	/**
	 * 会员Id
	 */
	private Integer memberId;
	/**
	 * 项目Id
	 */
	private Integer projectId;
	/**
	 * 回报Id
	 */
	private Integer returnId;
	/**
	 * 订单编号
	 */
	private String orderNum;
	/**
	 * 创建时间
	 */
	private String createDate;
	/**
	 * 支持金额
	 */
	private BigDecimal money;
	/**
	 * 回报数量
	 */
	private BigDecimal rtnCount;
	/**
	 * 交易状态
	 */
	private String status;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 发票
	 */
	private String invoice;
	/**
	 * 发票抬头
	 */
	private String invoiceTitle;
	/**
	 * 备注
	 */
	private String reamrk;

	public Order() {

	}

	public Order(Integer id, Integer memberId, Integer projectId,
			Integer returnId, String orderNum, String createDate,
			BigDecimal money, BigDecimal rtnCount, String status,
			String address, String invoice, String invoiceTitle, String reamrk) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.projectId = projectId;
		this.returnId = returnId;
		this.orderNum = orderNum;
		this.createDate = createDate;
		this.money = money;
		this.rtnCount = rtnCount;
		this.status = status;
		this.address = address;
		this.invoice = invoice;
		this.invoiceTitle = invoiceTitle;
		this.reamrk = reamrk;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getReturnId() {
		return returnId;
	}

	public void setReturnId(Integer returnId) {
		this.returnId = returnId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getRtnCount() {
		return rtnCount;
	}

	public void setRtnCount(BigDecimal rtnCount) {
		this.rtnCount = rtnCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getReamrk() {
		return reamrk;
	}

	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", memberId=" + memberId + ", projectId="
				+ projectId + ", returnId=" + returnId + ", orderNum="
				+ orderNum + ", createDate=" + createDate + ", money=" + money
				+ ", rtnCount=" + rtnCount + ", status=" + status
				+ ", address=" + address + ", invoice=" + invoice
				+ ", invoiceTitle=" + invoiceTitle + ", reamrk=" + reamrk + "]";
	}

}
