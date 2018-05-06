package com.bestgo.admanager.manager.exception;
/**
 * 自定义用户异常
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月8日上午12:24:12  
 */
public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserException() {
	}

	public UserException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}
	
}
