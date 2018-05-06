package com.bestgo.admanager.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Type;
import com.bestgo.admanager.manager.dao.TypeDao;
import com.bestgo.admanager.manager.service.TypeService;
import com.bestgo.admanager.util.Page;

/**
 * 
 * @author zhaokexiang
 * @version
 * @datetime 2017年7月11日下午4:56:22
 */
@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeDao typeDao;
	
	
	//添加项目分类
	public int addType(Type type) {
		return typeDao.addType(type);
	}
	
	
	//分页查询
	public Page<Type> queryPage(Map<String, Object> paramMap) {
		Page<Type> page = new Page<Type>((Integer)paramMap.get("pageno"),
				(Integer)paramMap.get("pagesize"));
		paramMap.put("startIndex", page.getStartIndex());
		List<Type> projectList = typeDao.queryPage(paramMap);
		//封装数据
		page.setData(projectList);
		//计算中记录数
		int totalsize = typeDao.queryCount(paramMap);
		//封装总记录数
		page.setTotalsize(totalsize);
		
		return page;
	}


	public int deleteBatchType(Integer[] id) {
		
		int totalCount = typeDao.deleteBatchType(id);
		if (totalCount != id.length) {
			throw new RuntimeException("批量删除失败");
		}
		return totalCount;
	}


	public int deleteById(Integer id) {
		
		return typeDao.deleteById(id);
	}


	public Type getTypeById(int id) {
		return typeDao.getTypeById(id);
	}


	public int updateType(Type type) {
		
		return typeDao.updateType(type);
	}




	

}
