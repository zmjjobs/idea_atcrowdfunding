package com.bestgo.admanager.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bestgo.admanager.bean.Member;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.util.Const;

/**
 * 登录验证拦截器
 * 如果不登录，资源是不允许访问的
 * @author zhumengjun
 * @version
 * @datetime 2017年7月17日下午7:30:48
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//白名单
		Set<String> whiteUris = new HashSet<>();
		whiteUris.add("/index.htm");
		//whiteUris.add("/member.htm");
		whiteUris.add("/doUserLogin.do");
		whiteUris.add("/doMemberLogin.do");
		whiteUris.add("/login.htm");
		whiteUris.add("/reg.htm");
		
		//String requestURI = request.getRequestURI();//项目名/xxx.htm
		//StringBuffer requestURL = request.getRequestURL();//http://....xxx.htm
		String servletPath = request.getServletPath();///xxx.htm
		if(servletPath.contains("?")){
			servletPath = servletPath.substring(0,servletPath.indexOf("?"));
		}
		if(whiteUris.contains(servletPath)){
			return true;
		}
		HttpSession session = request.getSession(true);
		Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		User user = (User) session.getAttribute(Const.LOGIN_USER);
		if(member == null && user == null){
			response.sendRedirect(request.getContextPath()+"/login.htm");
			return false;
		}
		return true;
	}
}
