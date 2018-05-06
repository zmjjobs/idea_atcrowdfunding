package com.bestgo.admanager.manager.dao;

import java.util.List;
import java.util.Map;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.bean.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 用户数据访问接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月7日下午11:55:30  
 */
@Repository
public interface UserDao {
	/**
	 * 验证登录用户是否存在
	 * @param paramMap 查询条件
	 * @return 查询出的用户对象
	 */
	User queryUserForLogin(Map<String, Object> paramMap);

	/**
	 * 根据条件分页查询用户
	 * @param paramMap 查询条件
	 * @return 满足条件的user集合
	 */
	List<User> queryPage(Map<String, Object> paramMap);

	/**
	 * 根据条件查询符合条件的记录数
	 * @param paramMap
	 * @return 记录数
	 */
	int queryCount(Map<String, Object> paramMap);

	/**
	 * 插入用户
	 * @param user
	 * @return
	 */
	int insertUser(User user);

	/**
	 * 根据ID查询用户信息
	 * @param id 用户主键ID
	 * @return user对象
	 */
	User getUserById(Integer id);

	/**
	 * 修改用户信息
	 * @param user 将修改的字段封装在用户对象中
	 * @return 修改的条数
	 */
	int updateUser(User user);

	/**
	 * 根据ID删除用户信息
	 * @param id
	 * @return
	 */
	int deleteUser(Integer id);

	/**
	 * 批量删除用户
	 * @param id 主键数组
	 * @return 删除的数量
	 */
	int deleteBatch(Integer[] id);

	/**
	 * 根据用户IDS批量删除用户
	 * @param ids 将传参封装成list
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
	 * 将数据插入到用户和角色的中间表中
	 * @param ur 用户角色类
	 * @return
	 */
	int insertUserRole(UserRole ur);
	
	/**
	 * 将数据从用户和角色的中间表中删除
	 * @param ur 用户角色类
	 * @return
	 */
	int deleteUserRole(UserRole ur);

	/**
	 * 将数据从用户和角色的中间表中删除
	 * @param userId
	 * @param ids
	 * @return 删除的条数
	 */
	int deleteUserRoleRelationship(@Param("userId") Integer userId, @Param("ids") List<Integer> ids);
	
	
	/**
	 * 根据登录用户ID查询它所拥有的许可集合
	 * @param id 用户ID
	 * @return 许可集合
	 */
	List<Permission> queryPermissionsByUserId(Integer id);
}
