package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.Role;
import com.bestgo.admanager.util.DataForParam;
import com.bestgo.admanager.util.Page;

import java.util.List;
import java.util.Map;


/**  
 *
 *  角色业务接口
 *@author caisong
 *@version 
 *@datetime 2017年7月11日下午4:03:53  
 */
public interface RoleService {

	public Page<Role> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void saveRole(Role user);

	public Role getRole(Integer id);

	public int updateRole(Role user);

	public int deleteRole(Integer uid);

	public int batchDeleteRole(Integer[] uid);
	
	public int batchDeleteRole(DataForParam datas);

	public List<Role> queryAllRole();

	public int saveRolePermissionRelationship(List<Integer> ids, Integer roleId);


}

