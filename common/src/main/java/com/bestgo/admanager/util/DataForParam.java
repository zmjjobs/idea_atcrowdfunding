package com.bestgo.admanager.util;

import com.bestgo.admanager.bean.CertImg;
import com.bestgo.admanager.bean.User;

import java.util.ArrayList;
import java.util.List;



/**
 * 自定义的数据封装类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月12日下午7:34:17  
 */
public class DataForParam {
	/**
	 * 如果同时传多个User属性时用
	 */
	private List<User> datas = new ArrayList<>();
	
	/**
	 * 图片上传时用
	 */
	private List<CertImg> certImgs = new ArrayList<>();
	
	/**
	 * 如果只传同一个属性时用
	 */
	private List<Integer> ids;
	public List<User> getDatas() {
		return datas;
	}
	public void setDatas(List<User> datas) {
		this.datas = datas;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<CertImg> getCertImgs() {
		return certImgs;
	}
	public void setCertImgs(List<CertImg> certImgs) {
		this.certImgs = certImgs;
	}
	
}
