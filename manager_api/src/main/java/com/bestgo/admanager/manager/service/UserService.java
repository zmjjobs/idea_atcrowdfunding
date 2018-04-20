package com.bestgo.admanager.manager.service;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
    User queryUserForLogin(Map<String,Object> paramMap);

}
