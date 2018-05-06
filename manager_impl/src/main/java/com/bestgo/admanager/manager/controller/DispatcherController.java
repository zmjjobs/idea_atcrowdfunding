package com.bestgo.admanager.manager.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bestgo.admanager.bean.Member;
import com.bestgo.admanager.bean.Permission;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.UserService;
import com.bestgo.admanager.potal.service.MemberService;
import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 自定义前端核心控制器
 * @author zhumengjun
 * @version
 * @datetime 2017年7月6日上午9:40:21
 */
@Controller
public class DispatcherController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;

	/**
	 * 权限分配
	 * @param session
	 * @param loginUser 登录的管理员用户
	 */
	public void permissionAssign(HttpSession session,User loginUser){
		Set<String> selfAllUris = new HashSet<>();
		Map<Integer,Permission> permissionMap = new HashMap<>();
		//根据用户所分配的角色，以及角色所分配的许可，来查询用户所拥有的许可集合
		List<Permission> permissionsForUser = userService.queryPermissionsByUserId(loginUser.getId());
		for (Permission permission : permissionsForUser) {
			permissionMap.put(permission.getId(), permission);
			selfAllUris.add("/"+permission.getUrl());
		}
		Permission root = null;
		for (Permission permission : permissionsForUser) {
			if(permission.getpId() == null){
				root = permission;
			}else{
				Permission parent = permissionMap.get(permission.getpId());
				parent.getChildren().add(permission);
			}
		}
		session.setAttribute(Const.ROOTPERMISSION, root);
		session.setAttribute(Const.USER_PERMISSION_ALL_URIS, selfAllUris);
	}
	
	/**
	 * 注销
	 * @param session
	 * @return 返回到首页
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		/*System.out.println("logout>>>>>>>>>>>>>>>>>>");
		session.removeAttribute(Const.LOGIN_MEMBER);
		session.removeAttribute(Const.LOGIN_USER);*/
		session.invalidate();
		return "redirect:/index.htm";
	}
	
	/**
	 * @return 跳转到网站首页
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	/**
	 * @return 跳转到网站首页
	 */
	@RequestMapping(value = "/member")
	public String member() {
		return "member/index";
	}
	
	@RequestMapping(value = "/error")
	public String error() {
		return "error/erroranth";
	}

	/**
	 * @return 跳转到登录页
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session){	
		
		//判断是否需要自动登录
		//如果之前登录过，cookie中存放了用户信息，需要获取cookie中的信息，并进行数据库的验证
		
		boolean needLogin = true;
		String logintype = null ;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){ //如果客户端禁用了Cookie，那么无法获取Cookie信息
			
			for (Cookie cookie : cookies) {
				if("logincode".equals(cookie.getName())){
					String logincode = cookie.getValue();
					//loginacct=admin&userpwd=21232f297a57a5a743894a0e4a801fc3&logintype=member
					System.out.println("获取到Cookie中的键值对"+cookie.getName()+"===== " + logincode);
					String[] split = logincode.split("&");
					if(split.length == 3){
						String loginacct = split[0].split("=")[1];
						String userpswd = split[1].split("=")[1];
						logintype = split[2].split("=")[1];
						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put("loginacct", loginacct);
						paramMap.put("userpswd", userpswd);
						if("user".equals(logintype)){
							User loginUser = userService.queryUserForLogin(paramMap);
							if(loginUser!=null){
								session.setAttribute(Const.LOGIN_USER, loginUser);
								permissionAssign(session,loginUser);
								needLogin = false ;
							}
						}else if("member".equals(logintype)){
							Member dbLogin = memberService.queryMemberForLogin(paramMap);
							
							if(dbLogin!=null){
								session.setAttribute(Const.LOGIN_MEMBER, dbLogin);
								needLogin = false ;
							}
						}
						
					}
				}
			}
		}
		
		if(needLogin){
			return "login";
		}else{
			if("user".equals(logintype)){
				return "redirect:/main.htm";
			}else if("member".equals(logintype)){
				return "redirect:/member.htm";
			}
		}

		return "login";
	}

	/**
	 * 跳转到注册页
	 */
	@RequestMapping(value = "/reg")
	public String reg(){
		return "reg";
	}
	
	/**
	 * @return 跳转到后台主页面
	 */
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	/**
	 * 
	 * 会员开始登录
	 * @param loginacct 登录帐号
	 * @param userpswd 登录密码
	 * @param usertype 登录的用户类型
	 * @param session 获取的session对象
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping("/doMemberLogin")
	public Object doMemberLogin(HttpServletResponse response,String flag,String loginacct,String userpswd ,HttpSession session){	
		AjaxResult result = new AjaxResult();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		Member loginMember = memberService.queryMemberForLogin(paramMap);
		if(loginMember==null){
			result.setErrorMessage("用户名称或密码不正确,请重新登录!");
			result.setSuccess(false);
		}else{
			if("1".equals(flag)){//选中记住我
				String logincode = "\"loginacct="+loginMember.getLoginacct() + "&userpswd="+loginMember.getUserpswd()+"&logintype=member\"";
				Cookie c = new Cookie("logincode", logincode);
				c.setMaxAge(60*60*24*7);//此Cookie信息一周后过期
				c.setPath("/");//任何请求路径都可以访问此Cookie
				response.addCookie(c);
			}
			session.setAttribute(Const.LOGIN_MEMBER, loginMember);
			result.setSuccess(true);
		}
		return result;
	}
	
	
	//异步方法
	/**
	 * 
	 * 管理员用户开始登录
	 * @param loginacct 登录帐号
	 * @param userpswd 登录密码
	 * @param usertype 登录的用户类型
	 * @param session 获取的session对象
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping("/doUserLogin")
	public Object doUserLogin(HttpServletResponse response,String flag,String loginacct,String userpswd ,HttpSession session){	
		AjaxResult result = new AjaxResult();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		User loginUser = userService.queryUserForLogin(paramMap);
		if(loginUser==null){
			result.setErrorMessage("用户名称或密码不正确,请重新登录!");
			result.setSuccess(false);
		}else{
			if("1".equals(flag)){//选中记住我
				String logincode = "\"loginacct="+loginUser.getLoginacct() + "&userpswd="+loginUser.getUserpswd()+"&logintype=user\"";
				Cookie c = new Cookie("logincode", logincode);
				c.setMaxAge(60*60*24*7);//此Cookie信息一周后过期
				c.setPath("/");//任何请求路径都可以访问此Cookie
				response.addCookie(c);
			}
			
			permissionAssign(session,loginUser);
			session.setAttribute(Const.LOGIN_USER, loginUser);
			result.setSuccess(true);
		}
		return result;
	}
	
	
	//同步方法
	/*@RequestMapping("/dologin")
	public String dologin(String loginacct , String userpswd , String usertype , Map<String,Object> map){	
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", userpswd);
		
		User loginuser = userService.queryUserForLogin(paramMap);
		
		if(loginuser==null){
			
			map.put("errorMessage", "用户名称或密码不正确,请重新登录!");
			//return "redirect:/login.htm";
			return "login";
		}else{
			
			if("member".equals(usertype)){				
				
				return "redirect:/";
			}else if("user".equals(usertype)){
				
				return "redirect:/main.htm";
			} else{
				throw new RuntimeException("登录失败!");
			}			
		}

	}*/
}
