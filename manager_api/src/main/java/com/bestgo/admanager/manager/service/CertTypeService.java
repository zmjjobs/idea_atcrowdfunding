package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.AccountTypeCert;

import java.util.List;


/**
 * 资质类型业务接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月27日上午12:52:53  
 */
public interface CertTypeService {
	

	/**
	 * 保存资质类型
	 * @param accountTypeCert
	 * @return 保存的条数
	 */
	int saveCertType(AccountTypeCert accountTypeCert);

	/**
	 * 移除资质类型与会员的关联
	 * @param accountTypeCert
	 * @return 移除的条数
	 */
	int removeCertType(AccountTypeCert accountTypeCert);

	/**
	 * 查询所有的资质类型
	 * @return
	 */
	List<AccountTypeCert> queryAllCertType();

}
