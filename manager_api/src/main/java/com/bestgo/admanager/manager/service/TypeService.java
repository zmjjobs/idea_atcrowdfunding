package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.Type;
import com.bestgo.admanager.util.Page;

import java.util.Map;


/**
 *
 *@author zhaokexiang
 *@version 
 *@datetime 2017年7月11日下午4:54:51  
 */
public interface TypeService {

	//添加用户
	int addType(Type type);
	//分页查询
	Page<Type> queryPage(Map<String, Object> paramMap);

	//批量删除用户
	int deleteBatchType(Integer[] id);
	//删除单个用户
	int deleteById(Integer id);
	//根据id查找项目分类
	Type getTypeById(int id);
	//修改所查到的项目分类
	int updateType(Type type);

}
