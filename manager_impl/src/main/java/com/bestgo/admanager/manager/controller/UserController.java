package com.bestgo.admanager.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestgo.admanager.bean.Role;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.util.Datas;
import com.bestgo.admanager.util.Page;
import com.bestgo.admanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController /*implements org.springframework.web.servlet.mvc.Controller*//*implements HttpRequestHandler*/{
	
	@Autowired
	private UserService userService ;
	
//	@Autowired
//	private RoleService roleService ;

	
	@ResponseBody
	@RequestMapping(value="/unassign",method=RequestMethod.POST)
	public Object unassign(Datas datas, Integer userid){
		AjaxResult result = new AjaxResult();
		
		try {
			
			int count = userService.deleteUserRoleRelationship(userid,datas.getIds());
			
			result.setSuccess(count == datas.getIds().size());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/assign",method=RequestMethod.POST)
	public Object assign(Datas datas,Integer userid){
		AjaxResult result = new AjaxResult();
		
		try {
			
			int count = userService.saveUserRoleRelationship(userid,datas.getIds());
			
			result.setSuccess(count == datas.getIds().size());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	@RequestMapping("/toAssignRole")
	public String toAssignRole(Integer id,Map map){
//		User user = userService.getUserById(id);
//		map.put("user", user);
//
//		//获取所有角色对象
//		List<Role> allRoleList = roleService.queryAllRole();
//
//		//未分配角色
//		List<Role> unassignList = new ArrayList<Role>();
//
//		//已分配角色
//		List<Role> assignList = new ArrayList<Role>();
//
//
//		//查询当前用户已经分配的角色id
//		List<Integer> ids = userService.queryRoleidsByUserid(id); //根据用户id查询角色id,到中间表查询
//
//		//判断角色是否已经被分配过;
//		for (Role role : allRoleList) {
//			if(ids.contains(role.getId())){
//				assignList.add(role);
//			}else{
//				unassignList.add(role);
//			}
//		}
//
//		map.put("assignList", assignList);
//		map.put("unassignList", unassignList);
		
		return "user/toassignrole";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/doDeleteBatch",method=RequestMethod.POST)
	public Object doDeleteBatch(Datas datas){
		AjaxResult result = new AjaxResult();
		
		try {
			//int count = userService.deleteBatchByUserids(datas.getIds());
			int count = userService.deleteBatchByUsers(datas.getDatas());
			
			result.setSuccess(count==datas.getDatas().size());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
/*	@ResponseBody
	@RequestMapping(value="/doDeleteBatch",method=RequestMethod.POST)
	public Object doDeleteBatch(Integer[] id){
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.deleteBatchUser(id);
			
			result.setSuccess(count==id.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}*/
	
	@ResponseBody
	@RequestMapping(value="/doDelete",method=RequestMethod.POST)
	public Object doDelete(Integer id){
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.deleteUser(id);
			
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Integer id,Map map){
		User user = userService.getUserById(id);
		map.put("user", user);
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping(value="/doEdit",method=RequestMethod.POST)
	public Object doEdit(User user){
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.updateUser(user);
			
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "user/add";
	}
	
	//保存用户
	@ResponseBody
	@RequestMapping(value="/doAdd")
	public Object doAdd(User user){
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.saveUser(user);
			
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("保存失败!");
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	
	
	
	//异步请求处理	: 先调转页面
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "user/index";
	}
	
	
	//带查询条件,异步分页查询
	@ResponseBody
	@RequestMapping(value="/doindex")
	public Object doindex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="2")  Integer pagesize,
			String queryText,
			Map<String,Object> map){
		
		AjaxResult result = new AjaxResult();
		
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>(); //VO,DTO 
			paramMap.put("pageno",pageno);
			paramMap.put("pagesize",pagesize);
			
			
			if(StringUtil.isNotEmpty(queryText) && queryText.contains("%")){
				queryText = queryText.replaceAll("%", "\\\\%");   //"\\%" -> "\%" -> concat('%','\%','%') =>'%\%%'
			}
			
			paramMap.put("queryText", queryText);
			
			Page<User> page = userService.queryPage(paramMap);
			
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("异步加载数据失败!");
			result.setSuccess(false);
		}		
		
		return result;
	}

/*	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}*/

	/*@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	//异步请求处理	: 页面加载数据
	//通过Page来封装分页数据,改进程序代码
	/*@ResponseBody
	@RequestMapping(value="/doindex")
	public Object doindex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="2")  Integer pagesize,
			Map<String,Object> map){
		
		AjaxResult result = new AjaxResult();
		
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>(); //VO,DTO 
			paramMap.put("pageno",pageno);
			paramMap.put("pagesize",pagesize);
			
			Page<User> page = userService.queryPage(paramMap);
			
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("异步加载数据失败!");
			result.setSuccess(false);
		}		
		
		return result;
	}
*/
	
	
	//同步请求处理
/*	//通过Page来封装分页数据,改进程序代码
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(
			@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="2")  Integer pagesize,
			Map<String,Object> map){

		Map<String,Object> paramMap = new HashMap<String,Object>(); //VO,DTO 
		paramMap.put("pageno",pageno);
		paramMap.put("pagesize",pagesize);
		
		Page<User> page = userService.queryPage(paramMap);
		map.put(Const.PAGE, page);		
		
		return "user/index";
	}
	*/
	
/*	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(
			@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="10")  Integer pagesize,
			Map<String,Object> map){

		Map<String,Object> paramMap = new HashMap<String,Object>(); //VO,DTO 
		paramMap.put("startIndex",(pageno-1)*pagesize);//空指针
		paramMap.put("pagesize",pagesize);
		
		List<User> userList = userService.queryPage(paramMap);
		map.put("userList", userList);		
		
		return "user/index";
	}*/
	
}
