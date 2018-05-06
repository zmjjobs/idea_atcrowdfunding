package com.bestgo.admanager.manager.controller;

import java.util.List;
import java.util.Map;

import com.bestgo.admanager.bean.AccountTypeCert;
import com.bestgo.admanager.bean.Cert;
import com.bestgo.admanager.manager.service.CertService;
import com.bestgo.admanager.manager.service.CertTypeService;
import com.bestgo.admanager.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 资质分类控制器
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月26日下午11:56:38  
 */
@Controller
@RequestMapping("/cert_type")
public class CertTypeController {
	@Autowired
	private CertTypeService certTypeService;
	
	@Autowired
	private CertService certService;
	
	@ResponseBody
	@RequestMapping("/saveCertType")
	public Object saveCertType(AccountTypeCert accountTypeCert){
		AjaxResult result = new AjaxResult();
		try {
			int count = certTypeService.saveCertType(accountTypeCert);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteCertType")
	public Object deleteCertType(AccountTypeCert accountTypeCert){
		AjaxResult result = new AjaxResult();
		try {
			int count = certTypeService.removeCertType(accountTypeCert);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 跳转到分类管理页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Map<String,Object> map){
		List<Cert> certList  = certService.queryAllCert();
		map.put("certList", certList);
		List<AccountTypeCert> certAccttypeList  = certTypeService.queryAllCertType();
		System.out.println("certacctyeplsit===="+certAccttypeList);
		map.put("certAccttypeList", certAccttypeList);
		return "cert_type/index";
	}
}
