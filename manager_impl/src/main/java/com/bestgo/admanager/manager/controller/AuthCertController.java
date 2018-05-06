package com.bestgo.admanager.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description: 审核资质控制器
 * @author 朱梦君
 * @date 2017年7月30日 下午10:20:17
 * @version v1.0
 * @since jdk1.7
 */
@Controller
@RequestMapping("/auth_cert")
public class AuthCertController {
	
	/**
	 * 去往审核资质首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		return "auth_cert/index";
	}
}
