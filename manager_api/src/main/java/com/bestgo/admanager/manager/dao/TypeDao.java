package com.bestgo.admanager.manager.dao;

import com.bestgo.admanager.bean.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *
 *@author zhaokexiang
 *@version 
 *@datetime 2017年7月11日下午4:53:36  
 */
@Repository
public interface TypeDao {

	/**
	 * 添加项目分类
	 * @param type
	 * @return
	 */
	int addType(Type Type);
	/**
	 * 分页查询
	 * @param paramMap
	 * @return
	 */

	List<Type> queryPage(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	int deleteBatchType(Integer[] id);
	/**
	 * 单个删除
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	/**
	 * 修改项目分类
	 * @param id
	 * @return
	 */
	//第一步查找
	Type getTypeById(int id);
	//第二部修改
	int updateType(Type type);

}
