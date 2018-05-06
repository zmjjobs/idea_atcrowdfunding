package com.bestgo.admanager.bean;
/**
 * 项目分类关系表属性
 *@author zhaokexiang
 *@version 
 *@datetime 2017年7月7日下午5:58:57  
 */
public class ProjectType {
	/**
	 * 项目类编号
	 */
	private Integer	id;
	
	/**
	 * 项目编号
	 */
	private Integer projectId;
	
	/**
	 * 项目分类编号
	 */
	private Integer	typeId;
	
	public ProjectType() {
	}
	public ProjectType(Integer id, Integer projectId, Integer typeId) {
		this.id = id;
		this.projectId = projectId;
		this.typeId = typeId;
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	@Override
	public String toString() {
		return "ProjectType [id=" + id + ", projectId=" + projectId
				+ ", typeId=" + typeId + "]";
	}

}
