package com.bestgo.admanager.util;
/**
 * 自定义ajax异步结果封装类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月8日下午8:29:02  
 */
public class AjaxResult {
	/**
	 * true处理成功，false处理失败
	 */
    private boolean success;
    
    /**
     * 错误信息
     */
    private String errorMessage;
    
    /**
     * page对象
     */
    private Page page;
    
    /**
     * 接收的其他任何数据
     */
    private Object data;
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	
    
}
