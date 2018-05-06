package com.bestgo.admanager.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Cert;
import com.bestgo.admanager.manager.dao.CertDao;
import com.bestgo.admanager.manager.service.CertService;
import com.bestgo.admanager.util.Page;

/**
 * 资质业务实现类
 *@author lizhentao
 *@version 
 *@datetime 2017年7月12日下午11:48:06  
 */
@Service("CertService")
public class CertServiceImpl implements CertService{

	@Autowired
	private CertDao certDao;

	@Override
	public Page<Cert> queryPage(Map<String, Object> paramMap) {
		Page<Cert> page = new Page<Cert>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		paramMap.put("startIndex", page.getStartIndex());
		List<Cert>CertList = certDao.queryPage(paramMap);
		page.setData(CertList);
		int totalsize =certDao.queryCount(paramMap);
		page.setTotalsize(totalsize);
		return page;
	}

	@Override
	public int saveCert(Cert cert) {
		return certDao.insertCert(cert);
	}

	@Override
	public Cert getCertById(Integer id) {
		return certDao.getCertById(id);
	}

	@Override
	public int updateCert(Cert cert) {
		return certDao.updateCert(cert);
	}

	@Override
	public int deleteCert(Integer id) {
		return certDao.deleteCert(id);
	}

	@Override
	public int deleteBatch(Integer[] id) {
		int totalCount =certDao.deleteBatch(id);
		if(totalCount != id.length){
			throw new RuntimeException("批量删除失败！");
		}
		return totalCount;
	}

	@Override
	public int queryCount(Map<String, Object> paramMap) {
		return certDao.queryCount(paramMap);
	}

	@Override
	public List<Cert> queryAllCert() {
		return certDao.queryAllCert();
	}

	@Override
	public List<Cert> queryCertsByAccttype(String accttype) {
		return certDao.queryCertsByAccttype(accttype);
	}

}
