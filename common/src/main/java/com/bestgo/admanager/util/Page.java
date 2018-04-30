package com.bestgo.admanager.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class Page<T> {
	private List<T> data = new ArrayList<T>(); // 分页数据

	private int totalPage; // 总页码
	private int pageNum; // 当前页码
	private int totalSize; // 总记录数
	private int pageSize; // 当前页码数据条数
	
	public Page(int pageNum,int pageSize){
		if(pageNum <= 0){
			this.pageNum = 1;
		}else {
			this.pageNum = pageNum;
		}
		if(pageSize <= 0){
			this.pageSize = 10;
		}else {
			this.pageSize = pageSize;
		}
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		this.totalPage = (totalSize % pageSize > 0) ? (totalSize / pageSize + 1) : (totalSize / pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	//计算开始索引
	public int getStartIndex(){
		return (pageNum - 1) * pageSize;
	}

}
