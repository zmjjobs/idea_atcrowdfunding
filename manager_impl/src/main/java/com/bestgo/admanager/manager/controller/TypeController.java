package com.bestgo.admanager.manager.controller;

import java.util.HashMap;
import java.util.Map;

import com.bestgo.admanager.bean.Type;
import com.bestgo.admanager.manager.service.TypeService;
import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.util.Page;
import com.bestgo.admanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author zhaokexiang
 * @version
 * @datetime 2017年7月11日下午4:46:18
 */
@Controller
@RequestMapping("/type")
public class TypeController {
	@Autowired
	private TypeService typeService;
	
	
	// 修改用户
	// 第一步根据id获取用户信息
	@RequestMapping("/edit")
	public String editGetUserById(int id, Map map) {

		Type type = typeService.getTypeById(id);

		map.put("type", type);

		return "type/edit";
	}

	// 修改用户
	@ResponseBody
	@RequestMapping(value = "doedit", method = RequestMethod.POST)
	public Object doedit(Type type) {
		AjaxResult result = new AjaxResult();

		try {
			int count = typeService.updateType(type);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("添加用户失败");
			result.setSuccess(false);
		}

		return result;
	}

	//批量删除用户
	
		@ResponseBody
		@RequestMapping(value = "dodeleteBatch", method = RequestMethod.POST)
		public Object dodeleteBatch(Integer[] id){
			AjaxResult result = new AjaxResult();
			try {
				int count = typeService.deleteBatchType(id);
				System.out.println(count);
				System.out.println(id.length);
				result.setSuccess(count == id.length);
				
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
			return result;
		}
		
		
		
		//删除用户
		@ResponseBody
		@RequestMapping(value = "dodelete", method = RequestMethod.POST)
		public Object deleteById(Integer id){
			AjaxResult result = new AjaxResult();		
			try {
				int count = typeService.deleteById(id);
				result.setSuccess(count == 1);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
			return result;
		}
	
	
	//负责异步跳转
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String type(){
		return "type/index";
		
	}
	
	//异步查询
		@ResponseBody
		@RequestMapping(value = "doindex", method = RequestMethod.POST)
		public Object type(
				@RequestParam(value = "pageno", required = false, defaultValue = "1") int pageno,
				@RequestParam(value = "pagesize", required = false, defaultValue = "2") int pagesize,
				Map<String, Object> map,String queryText) {
			AjaxResult result = new AjaxResult();
			try {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("pageno", pageno);
				paramMap.put("pagesize", pagesize);
				if (StringUtil.isNotEmpty(queryText) && queryText.contains("%")) {
					queryText = queryText.replace("%", "////%");
				}
				paramMap.put("queryText", queryText);
				Page<Type> page = typeService.queryPage(paramMap);
				
				result.setPage(page);
				System.out.println(result.getPage());
				result.setSuccess(true);
				
			} catch (Exception e) {
				result.setErrorMessage("异步查询失败");
				result.setSuccess(false);
			}

			return result;

		}

	// 添加用户  仅用于跳转
	@RequestMapping("/add")
	public String add() {
		return "type/add";
	}
	// 添加用户
	@ResponseBody
	@RequestMapping("/doadd")
	public Object doadd(Type type) {
		AjaxResult result = new AjaxResult();

		try {
			int count = typeService.addType(type);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("添加用户失败");
			result.setSuccess(false);
		}
		return result;
	}
}
