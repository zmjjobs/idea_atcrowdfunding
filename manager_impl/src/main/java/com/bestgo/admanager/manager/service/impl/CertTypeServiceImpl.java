package com.bestgo.admanager.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.AccountTypeCert;
import com.bestgo.admanager.bean.Cert;
import com.bestgo.admanager.manager.dao.CertTypeDao;
import com.bestgo.admanager.manager.service.CertTypeService;


/**
 * 资质类型业务实现类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月27日上午12:55:07  
 */
@Service
public class CertTypeServiceImpl implements CertTypeService {

	@Autowired
	private CertTypeDao certTypeDao;
	
	@Override
	public int saveCertType(AccountTypeCert accountTypeCert) {
		return certTypeDao.insertCertType(accountTypeCert);
	}
	@Override
	public int removeCertType(AccountTypeCert accountTypeCert) {
		return certTypeDao.deleteCertType(accountTypeCert);
	}
	@Override
	public List<AccountTypeCert> queryAllCertType() {
		return certTypeDao.queryAllCertType();
	}

}
