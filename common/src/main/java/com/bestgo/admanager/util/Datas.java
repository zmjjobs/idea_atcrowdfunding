package com.bestgo.admanager.util;

import com.bestgo.admanager.bean.CertImg;
import com.bestgo.admanager.bean.User;

import java.util.ArrayList;
import java.util.List;


public class Datas {
	
	private List<User> datas = new ArrayList<User>();
	private List<Integer> ids ;
	
	private List<CertImg> certimgs;

	
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
	public List<CertImg> getCertimgs() {
		return certimgs;
	}
	public void setCertimgs(List<CertImg> certimgs) {
		this.certimgs = certimgs;
	}

	
}
