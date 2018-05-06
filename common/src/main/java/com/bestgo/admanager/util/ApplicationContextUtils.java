package com.bestgo.admanager.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 解决获取IOC容器对象问题
 * 	接口注入
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	public static ApplicationContext applicationContext ;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext ;
	}

}
