package com.bestgo.admanager.bean;

/**
 *  会员资质类
 * @author wusiyuan
 * @version
 * @datetime 2017年7月7日下午6:11:08
 * 
 */
public class MemberCert {
	private Integer id;
	
	/**
	 * 会员id
	 */
	private Integer memberId;
	
	/**
	 * 资质
	 */
	private Integer certId;
	
	/**
	 * 图片路径
	 */
	private String iconpath;

	public MemberCert() {
		super();
	}

	public MemberCert(Integer id, Integer memberId, Integer certId,
			String iconpath) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.certId = certId;
		this.iconpath = iconpath;
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

	public Integer getCertId() {
		return certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public String getIconpath() {
		return iconpath;
	}

	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}

	@Override
	public String toString() {
		return "MemberCert [id=" + id + ", memberId=" + memberId + ", certId="
				+ certId + ", iconpath=" + iconpath + "]";
	}

}