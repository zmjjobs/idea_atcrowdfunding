package com.bestgo.admanager.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Advert;
import com.bestgo.admanager.manager.dao.AdvertDao;
import com.bestgo.admanager.manager.service.AdvertService;
import com.bestgo.admanager.util.DataForParam;
import com.bestgo.admanager.util.Page;

/**
 * 广告业务实现类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月18日下午11:27:00  
 */
@Service
public class AdvertServiceImpl implements AdvertService {

	@Autowired
	private AdvertDao advertDao;
	
	@Override
	public int insertAdvert(Advert advert) {
		return advertDao.insertAdvert(advert);
	}

	@Override
	public Page<Advert> pageQuery(Map<String, Object> paramMap) {
		Page<Advert> advertPage = new Page<Advert>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		paramMap.put("startIndex", advertPage.getStartIndex());
		List<Advert> advertList= advertDao.pageQuery(paramMap);
		int count = advertDao.queryCount(paramMap);
		advertPage.setData(advertList);
		advertPage.setTotalsize(count);
		return advertPage;
	}

	@Override
	public int deleteAdvertById(Integer id) {
		return advertDao.deleteAdvertById(id);
	}

	@Override
	public int batchDeleteAdvert(DataForParam ds) {
		return advertDao.batchDeleteAdvert(ds);
	}

	@Override
	public int updateAdvert(Advert advert) {
		return advertDao.updateAdvert(advert);
	}

	@Override
	public Advert queryAdvertById(Integer id) {
		return advertDao.queryAdvertById(id);
	}

	

}
