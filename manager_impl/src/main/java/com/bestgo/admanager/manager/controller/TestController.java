package com.bestgo.admanager.manager.controller;

import java.io.IOException;

import com.bestgo.admanager.util.DesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月22日下午12:49:06  
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/act")
	public Object act(String p) throws IOException, Exception{
		String val =  DesUtil.decrypt(p, "abcdefghijklmnopquvwxyz");
		System.out.println("val===================>"+val);
		return val;
	}
}
