package com.bestgo.admanager.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bestgo.admanager.bean.Role;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.RoleService;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.util.DataForParam;
import com.bestgo.admanager.util.Page;
import com.bestgo.admanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户请求处理器
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月9日下午12:47:25  
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 取消用户角色
	 * @param datas
	 * @param userId
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping(value="/unassign",method=RequestMethod.POST)
	public Object unassign(DataForParam datas, Integer userId){
		AjaxResult result = new AjaxResult();
		try {
			List<Integer> ids = datas.getIds();
			int count = userService.deleteUserRoleRelationship(userId,ids);
			result.setSuccess(count==ids.size());
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 添加用户角色
	 * @param datas
	 * @param userId
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping(value="/assign",method=RequestMethod.POST)
	public Object assign(DataForParam datas,Integer userId){
		AjaxResult result = new AjaxResult();
		try {
			List<Integer> ids = datas.getIds();
			int count = userService.saveUserRoleRelationship(userId,ids);
			result.setSuccess(count==ids.size());
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 去往分配角色的页面
	 * @param id 要分配角色的用户ID
	 * @return
	 */
	@RequestMapping("/toAssignRole")
	public String toAssignRole(Integer id,Map<String,Object> map){
		User user = userService.getUserById(id);
		map.put("user", user);
		//查询所有的角色
		List<Role> allRoles = roleService.queryAllRole();
		//已经分配的角色
		List<Role>  assignList = new ArrayList<>();
		//未分配的角色
		List<Role>  unassignList = new ArrayList<>();
		List<Integer> roleIds = userService.queryRoleidsByUserid(id);
		for (Role role : allRoles) {
			if(roleIds.contains(role.getId())){
				assignList.add(role);
			}else{
				unassignList.add(role);
			}
		}
		map.put("assignList", assignList);
		map.put("unassignList", unassignList);
		return "user/toassignrole";
	}
	
	/**
	 * 根据ids批量删除用户数据
	 * @param datas
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBatchByUsers",method=RequestMethod.POST)
	public Object deleteBatchByUsers(DataForParam datas){
		AjaxResult result = new AjaxResult();
		try {
			List<User> list = datas.getDatas();
			int count = userService.deleteBachByUsers(list);
			result.setSuccess(count==list.size());
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	/**
	 * 根据ids批量删除用户数据
	 * @param datas
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBatchByUserids",method=RequestMethod.POST)
	public Object deleteBatchByUserids(DataForParam datas){
		AjaxResult result = new AjaxResult();
		try {
			List<Integer> ids = datas.getIds();
			int count = userService.deleteBatchByUserids(ids);
			result.setSuccess(count==ids.size());
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param id 封装成数组
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doDeleteBatch",method=RequestMethod.POST)
	public Object doDeleteBatch(Integer[] id){
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.deleteBatch(id);
			result.setSuccess(count==id.length);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 根据触发传参带来的ID进行单个删除
	 * @param id 传参带来的ID
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.deleteUser(id);
			result.setSuccess(count==1);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 修改方法
	 * @param user 从表单封装成的user对象
	 * @return AjaxResult对象
	 */
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
	
	/**
	 * 去往修改页面 
	 * @param id 点击修改，传参进来的id
	 * @param map
	 * @return user/edit.jsp
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Integer id,Map<String,Object> map){
		User user = userService.getUserById(id);
		map.put("user", user);
		return "user/edit";
	}
	
	
	/**
	 * 保存用户/添加用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(User user){
		AjaxResult result = new AjaxResult();
		try {
			
			int count = userService.saveUser(user);
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("保存失败！");
			result.setSuccess(false);
		}
		return result;
	}
	
	
	/**
	 * 去往添加页面
	 * @return 去往user/add.jsp
	 */
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "user/add";
	}
	
	
	//异步方法
	/**
	 * 先直接跳转到user/index.jsp页面
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "user/index";
	}
	
	/**
	 * 查询
	 * @param pageno 当前页
	 * @param pagesize 每页显示的条数
	 * @param queryText 查询的条件
	 * @param map
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping("/doindex")
	public Object doindex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="2")  Integer pagesize,String queryText,
			Map<String,Object> map){
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>(); //VO,DTO 
			paramMap.put("pageno",pageno);
			paramMap.put("pagesize",pagesize);
			if(StringUtil.isNotEmpty(queryText) && queryText.contains("%")){
				queryText = queryText.replaceAll("%", "\\\\%");
			}
			paramMap.put("queryText",queryText);

			Page<User> page = userService.queryPage(paramMap);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("数据加载失败！");
			result.setSuccess(false);
		}
		return result;
	}
	
	
	//同步方法
	/**
	 * 通过Page来封装分页数据
	 * @param pageno
	 * @param pagesize
	 * @param map
	 * @return
	 */
	/*@RequestMapping(value="/index",method=RequestMethod.GET)
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
	}*/
	
	
	
	
	/**
	 * 从main.jsp页面中传入pageno和pagesize，进行用户的分页查询
	 * @param pageno 当前页
	 * @param pagesize 每页显示的条数
	 * @return 跳转到user/index.jsp页面
	 */
/*	@RequestMapping(value="/index")
	public String index(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,@RequestParam(value="pagesize",required=false,defaultValue="5")Integer pagesize,Map<String,Object> map){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("pageno", pageno);
		System.out.println("pageno:  "+pageno);
		System.out.println("pagesize: "+pagesize);
		paramMap.put("startIndex", (pageno-1)*pagesize);
		paramMap.put("pagesize", pagesize);
		List<User> userList = userService.queryUserByPage(paramMap);
		System.out.println("userlist:" + userList);
		map.put("userList", userList);
		return "user/index";
	}*/
}
