package com.bestgo.admanager.manager.service.impl;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.dao.UserDao;
import com.bestgo.admanager.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserForLogin(Map<String, Object> paramMap) {
        return userDao.queryUserForLogin(paramMap);
    }
}
