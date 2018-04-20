package com.bestgo.admanager.manager.dao;

import com.bestgo.admanager.bean.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Repository
public interface UserDao {
    User queryUserForLogin(Map<String,Object> paramMap);
}
