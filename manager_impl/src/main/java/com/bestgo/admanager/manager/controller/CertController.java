package com.bestgo.admanager.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bestgo.admanager.bean.Cert;
import com.bestgo.admanager.manager.service.CertService;
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
 * @Description: 资质请求处理器
 * @author 朱梦君
 * @date 创建时间：2017年7月30日 下午10:20:17
 * @version v1.0
 * @since jdk1.7
 */
@Controller
@RequestMapping("/cert")
public class CertController {
	@Autowired
	private CertService certService;
	
	/**
	 * 批量删除资质
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doDeleteBatch",method=RequestMethod.POST)
	public Object doDeleteBatch(Integer[] id){
		AjaxResult result = new AjaxResult();
		try {
			int count = certService.deleteBatch(id);
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
			int count = certService.deleteCert(id);
			result.setSuccess(count==1);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 修改方法
	 * @param cert 从表单封装成的cert对象
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping(value="/doEdit",method=RequestMethod.POST)
	public Object doEdit(Cert cert){
		AjaxResult result = new AjaxResult();
		try {
			int count = certService.updateCert(cert);
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
	 * @return cert/edit.jsp
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Integer id,Map<String,Object> map){
		Cert cert = certService.getCertById(id);
		map.put("cert", cert);
		return "cert/edit";
	}
	
	
	/**
	 * 保存用户/添加用户
	 * @param cert
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Cert cert){
		AjaxResult result = new AjaxResult();
		try {
			
			int count = certService.saveCert(cert);
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
	 * @return 去往cert/add.jsp
	 */
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "cert/add";
	}
	
	
	
	//异步方法
	/**
	 * 先直接跳转到cert/index.jsp页面
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "cert/index";
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
	@RequestMapping("/pageQuery")
	public Object pageQuery(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
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

			Page<Cert> page = certService.queryPage(paramMap);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("数据加载失败！");
			result.setSuccess(false);
		}
		return result;
	}
}
