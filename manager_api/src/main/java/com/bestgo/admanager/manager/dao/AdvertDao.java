package com.bestgo.admanager.manager.dao;

import com.bestgo.admanager.bean.Advert;
import com.bestgo.admanager.util.DataForParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



/**
 *  广告数据访问接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月18日下午9:38:10  
 */
@Repository
public interface AdvertDao {
	/**
	 * 根据传参查询广告
	 * @param advertMap 传入map参数
	 * @return 广告对象
	 */
	Advert queryAdvert(Map<String, Object> advertMap);

	/**
	 * 分页查询广告
	 * @param advertMap
	 * @return
	 */
	List<Advert> pageQuery(Map<String, Object> advertMap);

	int queryCount(Map<String, Object> advertMap);

	/**
	 * 保存广告信息
	 * @param advert
	 * @return 插入的条数
	 */
	int insertAdvert(Advert advert);

	

	int updateAdvert(Advert advert);

	int deleteAdvertById(Integer id);

	int batchDelete(DataForParam dataForParam);

	int batchDeleteAdvert(DataForParam ds);

	/**
	 * 根据ID查询广告信息
	 * @param id
	 * @return
	 */
	Advert queryAdvertById(Integer id);

}
