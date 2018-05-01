package com.bestgo.admanager.manager.dao;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.util.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    User queryUserForLogin(Map<String,Object> paramMap);

    /**
     * 分页查询
     * @param paramMap
     * @return
     */
    List<User> queryPage(Map<String, Object> paramMap);

    /**
     * 查询分页的记录总数
     * @param paramMap
     * @return
     */
    int  queryCount(Map<String, Object> paramMap);

    int insertUser(User user);

    Integer queryUserIdByLoginacct(String loginacct);

    User queryUserById(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);


    int batchDeleteUserByUsers(List<User> users);
}
