package com.bestgo.admanager.manager.dao;

import com.bestgo.admanager.bean.Cert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 资质数据访问接口
 *@author lizhentao
 *@version 
 *@datetime 2017年7月12日下午11:55:30  
 */
@Repository
public interface CertDao {

	/**
	 * 根据条件分页查询资质
	 * @param paramMap 查询条件
	 * @return 满足条件的Cert集合
	 */
	List<Cert> queryPage(Map<String, Object> paramMap);

	/**
	 * 根据条件查询符合条件的记录数
	 * @param paramMap
	 * @return 记录数
	 */
	int queryCount(Map<String, Object> paramMap);

	/**
	 * 插入资质信息
	 * @param Cert
	 * @return
	 */
	int insertCert(Cert cert);

	/**
	 * 根据ID查询资质信息
	 * @param id 用户主键ID
	 * @return Cert对象
	 */
	Cert getCertById(Integer id);

	/**
	 * 修改资质信息
	 * @param Cert 将修改的字段封装在用户对象中
	 * @return 修改的条数
	 */
	int updateCert(Cert cert);

	/**
	 * 根据ID删除资质信息
	 * @param id
	 * @return
	 */
	int deleteCert(Integer id);

	/**
	 * 批量删除资质
	 * @param id 主键数组
	 * @return 删除的数量
	 */
	int deleteBatch(Integer[] id);

	/**
	 * 查询所有的资质
	 * @return
	 */
	List<Cert> queryAllCert();
	
	/**
	 * 根据会员账户类型查询其对应的资质文件
	 * @param accttype
	 * @return
	 */
	List<Cert> queryCertsByAccttype(String accttype);
}
