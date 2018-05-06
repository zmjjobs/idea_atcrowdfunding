package com.bestgo.admanager.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.manager.service.PermissionService;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.StringUtil;

/**
 * 访问权限拦截器
 * 用户登录以后的权限控制
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月17日下午7:30:48  
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private PermissionService permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取请求的路径
		String servletPath = request.getServletPath();
		
		if(servletPath.contains("?")){
			servletPath = servletPath.substring(0,servletPath.indexOf("?"));
		}
		
		Set<String> allPermissionUris = (Set<String>) request.getSession().getServletContext().getAttribute(Const.SYSTEM_ALL_PERMISSION_URIS);
		if(allPermissionUris.contains(servletPath)){
			Set<String> selfAllUris = (Set<String>) request.getSession().getAttribute(Const.USER_PERMISSION_ALL_URIS);
			if(selfAllUris.contains(servletPath)){
				return true;
			}
			response.sendRedirect(request.getContextPath()+"/error.htm");
			return false;
		}
		return true;
	}
}
