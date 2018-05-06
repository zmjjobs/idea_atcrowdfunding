package com.bestgo.admanager.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.bean.UserRole;
import com.bestgo.admanager.manager.dao.UserDao;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.DateStringConvertUtil;
import com.bestgo.admanager.util.MD5Util;
import com.bestgo.admanager.util.Page;

/**
 * 用户业务实现类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月7日下午11:48:06  
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User queryUserForLogin(Map<String,Object> paramMap) {
		return userDao.queryUserForLogin(paramMap);
	}

	@Override
	public Page<User> queryPage(Map<String, Object> paramMap) {
		Page<User> page = new Page<User>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		paramMap.put("startIndex", page.getStartIndex());
		List<User> userList =  userDao.queryPage(paramMap);
		page.setData(userList);
		int totalsize = userDao.queryCount(paramMap);
		page.setTotalsize(totalsize);
		return page;
	}

	@Override
	public int saveUser(User user) {
		user.setUserpswd(MD5Util.digest(Const.USER_DEFAULT_PASSWORD));
		user.setCreatetime(DateStringConvertUtil.dateToString(new Date()));
		return userDao.insertUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}

	@Override
	public int deleteBatch(Integer[] id) {
		int totalCount = userDao.deleteBatch(id);
		if(totalCount != id.length){
			throw new RuntimeException("批量删除失败！");
		}
		return totalCount;
	}

	@Override
	public int deleteBatchByUserids(List<Integer> ids) {
		int totalCount = userDao.deleteBatchByUserids(ids);
		if(totalCount != ids.size()){
			throw new RuntimeException("批量删除失败！");
		}
		return totalCount;
	}

	@Override
	public int deleteBachByUsers(List<User> datas) {
		int totalCount = userDao.deleteBachByUsers(datas);
		if(totalCount != datas.size()){
			throw new RuntimeException("批量删除失败！");
		}
		return totalCount;
	}
	
	@Override
	public List<Integer> queryRoleidsByUserid(Integer id){
		return userDao.queryRoleidsByUserid(id);
		
	}

	@Override
	public int saveUserRoleRelationship(Integer userId, List<Integer> ids) {
		int count = 0;
		for (Integer roleId : ids) {
			UserRole ur = new UserRole(	userId, roleId);
			count += userDao.insertUserRole(ur);
		}
		return count;
	}
	
	@Override
	public int deleteUserRoleRelationship(Integer userId, List<Integer> ids) {
		int count = 0;
		for (Integer roleId : ids) {
			UserRole ur = new UserRole(	userId, roleId);
			count += userDao.deleteUserRole(ur);
		}
		return count;
	}

	@Override
	public List<Permission> queryPermissionsByUserId(Integer id) {
		return userDao.queryPermissionsByUserId(id);
	}

	/*@Override
	public int deleteUserRoleRelationship(Integer userId, List<Integer> ids) {
		return userDao.deleteUserRoleRelationship(userId,ids);
	}*/
	
	
}
