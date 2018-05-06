package com.bestgo.admanager.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.manager.dao.PermissionDao;
import com.bestgo.admanager.manager.service.PermissionService;

/**
 * 许可业务实现类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月15日下午7:03:51  
 */
@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> queryAllPermission() {
		return permissionDao.queryAllPermission();
	}

	@Override
	public int savePermission(Permission permission) {
		return permissionDao.savePermission(permission);
	}

	@Override
	public Permission getPermissionById(Integer id) {
		return permissionDao.getPermissionById(id);
	}

	@Override
	public int updatePermission(Permission permission) {
		return permissionDao.updatePermission(permission);
	}
	
	@Override
	public int deletePermissionById(Integer id) {
		return permissionDao.deletePermissionById(id);
	}

	@Override
	public List<Integer> queryPermissionIdsByRoleId(Integer roleId) {
		return permissionDao.queryPermissionIdsByRoleId(roleId);
	}


}
