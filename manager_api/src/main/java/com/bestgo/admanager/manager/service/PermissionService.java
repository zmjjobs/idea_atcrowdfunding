package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.Permission;

import java.util.List;


/**
 * 许可业务接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月15日下午7:03:15  
 */
public interface PermissionService {

	/**
	 * 获取所有的许可
	 * @return 许可集合
	 */
	List<Permission> queryAllPermission();

	/**
	 * 添加许可到数据库
	 * @param permission
	 * @return
	 */
	int savePermission(Permission permission);

	/**
	 * 根据ID查询许可
	 * @param id
	 * @return
	 */
	Permission getPermissionById(Integer id);

	/**
	 * 修改许可
	 * @param permission
	 * @return 修改的条数
	 */
	int updatePermission(Permission permission);

	/**
	 * 根据Id删除许可
	 * @param id
	 * @return
	 */
	int deletePermissionById(Integer id);

	/**
	 * 根据角色ID查询对应的许可集合
	 * @param roleId 角色ID
	 * @return 许可集合
	 */
	List<Integer> queryPermissionIdsByRoleId(Integer roleId);


}
