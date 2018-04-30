package com.bestgo.admanager.manager.controller;

import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.Page;
import com.bestgo.admanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    @RequestMapping("/index")
//    public String index(@RequestParam(value="pageNum",required = false,defaultValue = "1") Integer pageNum,
//                        @RequestParam(value="pageSize",required = false,defaultValue = "5")Integer pageSize, Map<String,Object> map){
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("pageNum",pageNum);
//        paramMap.put("pageSize",pageSize);
//        Page<User> page = userService.queryPage(paramMap);
//        map.put(Const.PAGE,page);
//        return "user/index";
//    }

//        @RequestMapping("/index")
//        public String index(@RequestParam(value="pageNum",required = false,defaultValue = "1") Integer pageNum,
//                            @RequestParam(value="pageSize",required = false,defaultValue = "10")Integer pageSize, Map<String,Object> map){
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("startIndex",(pageNum - 1) * pageSize);
//            paramMap.put("pageSize",pageSize);
//            List<User> userList = userService.queryPage(paramMap);
//            map.put("userList",userList);
//            return "user/index";
//        }
}
