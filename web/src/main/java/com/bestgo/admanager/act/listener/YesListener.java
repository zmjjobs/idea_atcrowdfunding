package com.bestgo.admanager.act.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 审批通过监听器
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月22日下午10:29:49  
 */
public class YesListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("审批通过！");

	}

}
