package com.bestgo.admanager.manager.controller;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }


    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String loginacct, String userpswd, HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("loginacct",loginacct);

        //密码通过MD5加密
        paramMap.put("userpswd", MD5Util.digest(userpswd));
        User loginUser = userService.queryUserForLogin(paramMap);
        if(loginUser == null){
            ajaxResult.setMessage("用户名或密码不正确");
            ajaxResult.setSuccess(false);
        }else{
            session.setAttribute(Const.LOGIN_USER,loginUser);
            ajaxResult.setSuccess(true);
        }
        return ajaxResult;
    }

    @RequestMapping("/toRegister")
    public String toRedister(){
        return "register";
    }
}
