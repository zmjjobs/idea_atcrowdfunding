package com.bestgo.admanager.manager.service;

import java.util.Map;

import com.bestgo.admanager.bean.Advert;
import com.bestgo.admanager.util.DataForParam;
import com.bestgo.admanager.util.Page;

/**
 * 广告业务接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月18日下午11:25:09  
 */
public interface AdvertService {

	/**
	 * 保存广告信息
	 * @param advert
	 * @return 插入的条数
	 */
	int insertAdvert(Advert advert);

	/**
	 * 分页查询
	 * @param advertMap
	 * @return
	 */
	Page<Advert> pageQuery(Map<String, Object> advertMap);

	/**
	 * 根据ID删除广告信息
	 * @param id 广告ID
	 * @return 删除的条数
	 */
	int deleteAdvertById(Integer id);

	int batchDeleteAdvert(DataForParam ds);

	int updateAdvert(Advert advert);

	/**
	 * 根据ID查询广告信息
	 * @param id
	 * @return
	 */
	Advert queryAdvertById(Integer id);
	
}
