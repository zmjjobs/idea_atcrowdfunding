package com.bestgo.admanager.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhumengjun
 * @version
 * @datetime 2017年7月9日下午7:06:09
 */
public class Page<T> {
	/**
	 * 总页码
	 */
	private int totalno;

	/**
	 * 当前页码
	 */
	private int pageno;

	/**
	 * 总记录数
	 */
	private int totalsize;

	/**
	 * 当前页的记录数
	 */
	private int pagesize;

	/**
	 * 分页数据
	 */
	private List<T> data = new ArrayList<T>();

	public Page(int pageno, int pagesize) {
		// 如果传入的当前页小于等于0，将当前页码设置为1
		if (pageno <= 0) {
			this.pageno = 1;
		} else {
			this.pageno = pageno;
		}

		// 如果传入的设定的每页显示的条数小于等于0，则自动更改为10条
		if (pagesize <= 0) {
			this.pagesize = 10;
		} else {
			this.pagesize = pagesize;
		}
	}
	
	/**
	 * 获取当前页的起始索引
	 * @return int类型的起始索引
	 */
	public int getStartIndex(){
		return (pageno - 1) * pagesize;
	}

	public int getTotalno() {
		//如果总页码除以设定每页的条数可以除尽，那么就是商；反之，则是商加一
		//this.totalno = (totalsize % pagesize == 0) ? (totalsize / pagesize) : (totalsize / pagesize + 1);
		return totalno;
	}

	private void setTotalno(int totalno) {
		this.totalno = totalno;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
		this.totalno = ((totalsize%pagesize)>0)? ((totalsize/pagesize)+1):(totalsize/pagesize);
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
