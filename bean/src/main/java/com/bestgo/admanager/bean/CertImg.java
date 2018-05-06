package com.bestgo.admanager.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传资质图片的封装类
 * 对应数据库的t_member_cert表
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月27日下午11:21:36  
 */
public class CertImg {
	/**
	 * 会员ID
	 */
	private Integer memberid;
	
	/**
	 * 资质ID
	 */
	private Integer certid;
	
	/**
	 * 上传文件
	 */
	private MultipartFile multipartFile;
	
	/**
	 * 图片路径
	 */
	private String iconpath;

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public Integer getCertid() {
		return certid;
	}

	public void setCertid(Integer certid) {
		this.certid = certid;
	}

	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getIconpath() {
		return iconpath;
	}

	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
	
	
	
}
