package com.bestgo.admanager.manager.service.impl;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.dao.UserDao;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.DateStringConvertUtil;
import com.bestgo.admanager.util.MD5Util;
import com.bestgo.admanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserForLogin(Map<String, Object> paramMap) {
        return userDao.queryUserForLogin(paramMap);
    }

    @Override
    public Page<User> queryPage(Map<String, Object> paramMap) {
        Page<User> page = new Page<>((Integer) paramMap.get("pageNum"), (Integer) paramMap.get("pageSize"));
        paramMap.put("startIndex",page.getStartIndex());
        List<User> userList = userDao.queryPage(paramMap);
        page.setData(userList);
        int totalSize = userDao.queryCount(paramMap);
        page.setTotalSize(totalSize);
        return page;
    }

    @Override
    public int saveUser(User user) {
        Integer id = userDao.queryUserIdByLoginacct(user.getLoginacct());
        if(id != null){
            return -2;
        }
        user.setUserpswd(MD5Util.digest(Const.DEFAULT_PASSWORD));
        user.setCreatetime(DateStringConvertUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return userDao.insertUser(user);
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }


}
