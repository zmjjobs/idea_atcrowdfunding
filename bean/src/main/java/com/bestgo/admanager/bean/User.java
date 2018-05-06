package com.bestgo.admanager.bean;
/**  
 *  用户类 
 *@author caisong
 *@version 
 *@datetime 2017年7月7日下午5:52:27
 * 
 */
public class User {
	
	/**
	 * 用户的id自动生成
	 */
	private Integer id;
	/**
	 * 用户登录名
	 */
	private String loginacct;
	/**
	 * 登录密码
	 */
	private String userpswd;
	
	/**
	 * 用户昵称
	 */
	private String username;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 用户注册时间
	 */
	private String createtime;

	public User() {
	}

	public User(Integer id, String loginacct, String userpswd,
			String username, String email, String createtime) {
		this.id = id;
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.username = username;
		this.email = email;
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public String getUserpswd() {
		return userpswd;
	}

	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", loginacct=" + loginacct
				+ ", userpswd=" + userpswd + ", username=" + username
				+ ", email=" + email + ", createtime=" + createtime + "]";
	}
	
	

}
