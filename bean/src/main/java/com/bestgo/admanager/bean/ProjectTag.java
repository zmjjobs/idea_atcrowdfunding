package com.bestgo.admanager.bean;
/**
 * 项目标签关系表属性
 *@author zhaokexiang
 *@version 
 *@datetime 2017年7月7日下午5:58:10  
 */
public class ProjectTag {
	/**
	 * 项目标签关系编号
	 */
	private Integer id; 
	/**
	 * 项目编号
	 */
	private Integer	projectId;
	
	/**
	 * 标签编号
	 */
	private Integer tagId;
	
	public ProjectTag() {
	}
	
	public ProjectTag(Integer id, Integer projectId, Integer tagId) {
		this.id = id;
		this.projectId = projectId;
		this.tagId = tagId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	
	public String toString() {
		return "ProjectTag [id=" + id + ", projectId=" + projectId + ", tagId="
				+ tagId + "]";
	}
	
	
}
