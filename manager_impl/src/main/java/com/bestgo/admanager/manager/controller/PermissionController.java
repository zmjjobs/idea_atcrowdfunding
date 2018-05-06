package com.bestgo.admanager.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.manager.service.PermissionService;
import com.bestgo.admanager.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 许可请求处理器
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月15日下午5:15:27  
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 异步加载许可树，回显许可
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/asyncLoadDataAssign",method=RequestMethod.POST)
	public Object asyncLoadDataAssign(Integer roleId){
			List<Integer> permissionIds = permissionService.queryPermissionIdsByRoleId(roleId);
			//  id:permission   键值对
			Map<Integer,Permission> map = new HashMap<>();
			
			Permission root = null;
			List<Permission> allPermissions = permissionService.queryAllPermission();
			for (Permission permission : allPermissions) {
				if(permissionIds.contains(permission.getId())){
					permission.setChecked(true);
				}
				map.put(permission.getId(), permission);
			}
			for (Permission permission : allPermissions) {
				//如果没有父节点，那么证明它就是根节点
				if(permission.getpId() == null){
					root = permission;
				}else{
					//那么，通过子节点找到它的父节点ID,再通过map找到它的父节点
					Permission parent = map.get(permission.getpId());
					//父节点的子节点集合中放入这个子节点
					parent.getChildren().add(permission);
				}
			}
			//将root放在list中，这样对应页面的JSON
			List<Permission> rootPermission = new ArrayList<>();
			rootPermission.add(root);
			return rootPermission;
	}
	
	
	/**
	 * 异步加载数据，获取父子关系
	 * @return 带有根节点父子关系的许可list，到达客户端时，会自动转为数组对象
	 */
	@ResponseBody
	@RequestMapping(value="/asyncLoadData",method=RequestMethod.POST)
	public Object asyncLoadData(){
			
			//  id:permission   键值对
			Map<Integer,Permission> map = new HashMap<>();
			
			Permission root = null;
			List<Permission> allPermissions = permissionService.queryAllPermission();
			for (Permission permission : allPermissions) {
				map.put(permission.getId(), permission);
			}
			for (Permission permission : allPermissions) {
				//如果没有父节点，那么证明它就是根节点
				if(permission.getpId() == null){
					root = permission;
				}else{
					//那么，通过子节点找到它的父节点ID,再通过map找到它的父节点
					Permission parent = map.get(permission.getpId());
					//父节点的子节点集合中放入这个子节点
					parent.getChildren().add(permission);
				}
			}
			//将root放在list中，这样对应页面的JSON
			List<Permission> rootPermission = new ArrayList<>();
			rootPermission.add(root);
			return rootPermission;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/doDelete",method=RequestMethod.POST)
	public Object doDelete(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.deletePermissionById(id);
			result.setSuccess(count==1);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	/**
	 * 修改许可
	 * @param permission 传参封装成permission对象
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping(value="/doEdit",method=RequestMethod.POST)
	public Object doEdit(Permission permission){
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.updatePermission(permission);
			result.setSuccess(count==1);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 跳转到许可主页
	 * @return 去往permission/index.jsp
	 */
	@RequestMapping(value="/index")
	public String toIndex(){
		return "permission/index";
	}
	
	/**
	 * 去往许可修改页面
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(Integer id,Map<String,Object> map){
		Permission permission = permissionService.getPermissionById(id);
		map.put("permission", permission);
		return "permission/edit";
	}
	/**
	 * 去往许可添加页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "permission/add";
	}
	
	/**
	 * 添加许可到数据库
	 * @param permission 将接收的数据封装成permission对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public Object doAdd(Permission permission){
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.savePermission(permission);
			result.setSuccess(count==1);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	/**
	 * 查询所有的许可信息，并封装成json对象，通过result.data传给页面展示
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping(value="/loadData",method=RequestMethod.POST)
	public Object loadData(){
		AjaxResult result = new AjaxResult();
		try {
			
			//  id:permission   键值对
			Map<Integer,Permission> map = new HashMap<>();
			
			Permission root = null;
			List<Permission> allPermissions = permissionService.queryAllPermission();
			for (Permission permission : allPermissions) {
				map.put(permission.getId(), permission);
			}
			for (Permission permission : allPermissions) {
				if(permission.getpId() == null){
					root = permission;
				}else{
					Permission parent = map.get(permission.getpId());
					parent.getChildren().add(permission);
				}
			}
			
			result.setData(root);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/*@ResponseBody
	@RequestMapping(value="/loadData",method=RequestMethod.POST)
	public Object loadData(){
		AjaxResult result = new AjaxResult();
		try {
			Permission permission = new Permission("系统权限菜单");
			Permission permission1 = new Permission("控制面板");
			Permission permission2 = new Permission("消息管理");
			Permission permission3 = new Permission("权限管理");
			List<Permission> children = permission.getChildren();
			children.add(permission3);
			children.add(permission2);
			children.add(permission1);
			result.setData(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}*/
}
