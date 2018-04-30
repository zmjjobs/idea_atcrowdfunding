package com.bestgo.admanager.util;

/**
 * @author mengjun
 * @Date 2018-04-15 22:18
 * @Desc 用于Ajax异步，传参对象
 */
public class AjaxResult<T> {
    private boolean success;
    private String message;
    private  Page<T> page;

    public AjaxResult() {
    }

    public AjaxResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
