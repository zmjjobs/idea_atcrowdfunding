package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.util.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    User queryUserForLogin(Map<String,Object> paramMap);

    Page<User> queryPage(Map<String, Object> paramMap);

    int saveUser(User user);

    User queryUserById(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);
}
