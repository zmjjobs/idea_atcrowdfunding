package com.bestgo.admanager.manager.dao;

import java.util.List;
import java.util.Map;

import com.bestgo.admanager.bean.Role;
import com.bestgo.admanager.util.DataForParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 *
 *  角色数据访问接口
 *@author caisong
 *@version 
 *@datetime 2017年7月11日下午4:03:33  
 */
@Repository
public interface RoleDao {

	List<Role> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insert(Role user);

	Role getRole(Integer id);

	int update(Role user);

	int delete(Integer uid); 

	int batchDelete(@Param("ids") Integer[] uid);

	int batchDeleteObj(DataForParam datas);

	List<Role> queryAllRole();

	int saveRolePermissionRelationship(@Param("permissionIds") List<Integer> ids, @Param("roleId") Integer roleId);

	void deleteRolePermissionRelationship(Integer roleid);

}
