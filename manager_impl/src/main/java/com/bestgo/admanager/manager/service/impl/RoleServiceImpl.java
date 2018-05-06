package com.bestgo.admanager.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Role;
import com.bestgo.admanager.manager.dao.RoleDao;
import com.bestgo.admanager.manager.service.RoleService;
import com.bestgo.admanager.util.DataForParam;
import com.bestgo.admanager.util.Page;

/**  
 *
 *  用户角色的业务实现类
 *@author caisong
 *@version 
 *@datetime 2017年7月11日下午4:04:46  
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;


	public Page<Role> pageQuery(Map<String, Object> paramMap) {
		Page<Role> rolePage = new Page<Role>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		paramMap.put("startIndex", rolePage.getStartIndex());
		
		List<Role> roles = roleDao.pageQuery(paramMap);
		
		// 获取数据的总条数
		int count = roleDao.queryCount(paramMap);
		rolePage.setData(roles);
		rolePage.setTotalsize(count);
		return rolePage;
	}

	public int queryCount(Map<String, Object> paramMap) {
		return roleDao.queryCount(paramMap);
	}

	public void saveRole(Role user) {
		roleDao.insert(user);
	}

	public Role getRole(Integer id) {
		return roleDao.getRole(id);
	}

	public int updateRole(Role user) {
		return roleDao.update(user);
	}

	public int deleteRole(Integer uid) {
		return roleDao.delete(uid);
	}

	public int batchDeleteRole(Integer[] uid) {
		return roleDao.batchDelete(uid);
	}

	public int batchDeleteRole(DataForParam datas) {
		return roleDao.batchDeleteObj(datas);
	}

	public List<Role> queryAllRole() {
		return roleDao.queryAllRole();
	}

	@Override
	public int saveRolePermissionRelationship(List<Integer> ids, Integer roleId) {
		
		//保存新分配许可数据时,应该将这个角色之前分配的许可删除
		roleDao.deleteRolePermissionRelationship(roleId);
		
		return roleDao.saveRolePermissionRelationship(ids,roleId);
	}


}
