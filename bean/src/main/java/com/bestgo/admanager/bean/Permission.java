package com.bestgo.admanager.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 许可（权限）表属性
 *@author zhaokexiang
 *@version 
 *@datetime 2017年7月7日下午5:57:44  
 */
public class Permission {
	/**
	 * 许可ID
	 */
	private Integer id;
	
	/**
	 * 父许可ID
	 */
	private Integer pId;
	
	/**
	 * 许可名称
	 */
	private String name;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 许可的链接地址
	 */
	private String url;
	
	/**
	 * 是否展开,默认为展开
	 */
	private boolean open = true;
	
	/**
	 * 是否有复选框，默认没有
	 */
	private boolean checked = false;
	
	/**
	 * 包含的子菜单许可对象集合
	 */
	private List<Permission> children = new ArrayList<>();
	
	public Permission() {
	}

	public Permission(String name) {
		this.name = name;
	}

	public Permission(Integer id, Integer pId, String name, String icon,
			String url, boolean open, boolean checked, List<Permission> children) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.icon = icon;
		this.url = url;
		this.open = open;
		this.checked = checked;
		this.children = children;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", pId=" + pId + ", name=" + name
				+ ", icon=" + icon + ", url=" + url + ", open=" + open
				+ ", checked=" + checked + ", children=" + children + "]";
	}

	
}
