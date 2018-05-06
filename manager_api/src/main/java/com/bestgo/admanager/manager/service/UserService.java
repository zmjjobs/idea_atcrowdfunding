package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.util.Page;

import java.util.List;
import java.util.Map;



/**
 * 用户业务接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月7日下午11:44:52  
 */
public interface UserService {
	/**
	 * 验证登录用户是否存在
	 * @param paramMap 查询条件
	 * @return 查询出的用户对象
	 */
	User queryUserForLogin(Map<String, Object> paramMap);

	/**
	 * 根据条件分页查询
	 * @param paramMap 查询条件
	 * @return 满足条件的page对象
	 */
	Page<User> queryPage(Map<String, Object> paramMap);

	/**
	 * 保存用户
	 * @param user 根据ajax的data传入的表单数据封装成的对象
	 * @return  保存的条数
	 */
	int saveUser(User user);

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);

	/**
	 * 根据传参修改用户信息
	 * @param user 根据ajax的data传入的表单数据封装成的对象
	 * @return 修改的条数
	 */
	int updateUser(User user);

	/**
	 * 根据Id删除用户
	 * @param id
	 * @return
	 */
	int deleteUser(Integer id);

	/**
	 * 批量删除用户
	 * @param id id=xx,id=xx的数组
	 * @return 删除的条数
	 */
	int deleteBatch(Integer[] id);

	/**
	 * 根据用户id数组批量删除用户
	 * @param ids
	 * @return
	 */
	int deleteBatchByUserids(List<Integer> ids);

	/**
	 * 根据Userlist批量删除用户信息
	 * @param datas
	 * @return
	 */
	int deleteBachByUsers(List<User> datas);

	/**
	 * 根据用户ID查询对应的角色集合
	 * @param id 用户ID
	 * @return
	 */
	List<Integer> queryRoleidsByUserid(Integer id);

	/**
	 * 将用户ID和对应的角色ID一一对应放在关联表里
	 * @param userId
	 * @param ids
	 * @return 保存的条数
	 */
	int saveUserRoleRelationship(Integer userId, List<Integer> ids);

	/**
	 * 将用户ID和对应的角色ID的关联解除
	 * @param userId
	 * @param ids
	 * @return 删除的条数
	 */
	int deleteUserRoleRelationship(Integer userId, List<Integer> ids);

	/**
	 * 根据用户ID查询它所拥有的许可集合
	 * @param id 用户ID
	 * @return 许可集合
	 */
	List<Permission> queryPermissionsByUserId(Integer id);
}
