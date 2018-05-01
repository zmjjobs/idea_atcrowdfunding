package com.bestgo.admanager.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjun
 * @Date 2018-05-01 16:57
 * @Desc 多条相同对象数据从前端传递过来的时候封装为此对象的集合
 */
public class DatasObject {
    private List<User>  userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
