package com.bestgo.admanager.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.manager.service.PermissionService;
import com.bestgo.admanager.util.Const;

/**
 * 服务启动时的监听器
 * 在服务器启动过程中将request.getContextPath()路径存放到application域中.
 * @author zhumengjun
 * 
 */
public class ServerStartUpListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//将request.getContextPath()路径从application域删除
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//将request.getContextPath()路径存放到application域中.
		ServletContext application = event.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		
		
		//将所有的许可路径放到set集合中，再放到application域中
		Set<String> allPermissionUris = new HashSet<>();
		WebApplicationContext ioc = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
		PermissionService permissionService = ioc.getBean(PermissionService.class);
		List<Permission> queryAllPermission = permissionService.queryAllPermission();
		for (Permission permission : queryAllPermission) {
			allPermissionUris.add("/" + permission.getUrl());
		}
		application.setAttribute(Const.SYSTEM_ALL_PERMISSION_URIS, allPermissionUris);
		
	}

}
