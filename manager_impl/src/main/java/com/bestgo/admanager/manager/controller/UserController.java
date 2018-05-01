package com.bestgo.admanager.manager.controller;

import com.bestgo.admanager.bean.DatasObject;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.Page;
import com.bestgo.admanager.util.StringUtil;
import org.activiti.bpmn.model.DataObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import com.bestgo.admanager.util.AjaxResult;

import javax.print.attribute.standard.PageRanges;
import java.util.Map;

/**
 * @author mengjun
 * @Date 2018-04-24 0:07
 * @Desc
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "user/index";
    }


    @ResponseBody
    @RequestMapping("/doIndex")
    public Object doIndex(@RequestParam(value="pageNum",required = false,defaultValue = "1") Integer pageNum,
                          @RequestParam(value="pageSize",required = false,defaultValue = "5")Integer pageSize,
                          String queryText) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            queryText = queryText == null ? "" : queryText.trim();
            if(queryText != "" && queryText.matches("[@#$%^&*]")){
                ajaxResult.setMessage("查询条件不能包含[@#$%^&*]！");
            }else{
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("pageNum",pageNum);
                paramMap.put("pageSize",pageSize);
                paramMap.put("queryText",queryText);
                Page<User> page = userService.queryPage(paramMap);
                ajaxResult.setPage(page);
                ajaxResult.setSuccess(true);
            }

        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setMessage("异步加载数据失败!");
        }
        return ajaxResult;
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/add";
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user) {
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.saveUser(user);
            result.setSuccess(count == 1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toEdit")
    public String toEdit(Integer id,Map map){
        User user = userService.queryUserById(id);
        map.put("user",user);
        return "user/edit";
    }

    @ResponseBody
    @RequestMapping("/doEdit")
    public Object doEdit(User user){
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.updateUser(user);
            if(count == -2){
                result.setMessage("此账户名已被注册，请更改账户名！");
            }else{
                result.setSuccess(count == 1);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("保存失败！");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.deleteUser(id);
            result.setSuccess(count == 1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/doBatchDeleteUsers",method = RequestMethod.POST)
    public Object doBatchDeleteUsers(DatasObject datasObject) {
        List<User> users = datasObject.getUserList();
        AjaxResult result = new AjaxResult();
        try {
            int count = userService.batchDeleteUsers(users);

            result.setSuccess(count==users.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
