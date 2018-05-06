package com.bestgo.admanager.manager.dao;

import com.bestgo.admanager.bean.AccountTypeCert;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 资质类型数据访问接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月27日上午12:52:53  
 */
@Repository
public interface CertTypeDao {
	
	/**
	 * 插入资质类型
	 * @param accountTypeCert
	 * @return 插入的条数
	 */
	int insertCertType(AccountTypeCert accountTypeCert);

	/**
	 * 
	 * @param accountTypeCert
	 * @return
	 */
	int deleteCertType(AccountTypeCert accountTypeCert);
	
	/**
	 * 查询所有的资质类型
	 * @return
	 */
	List<AccountTypeCert> queryAllCertType();
}
