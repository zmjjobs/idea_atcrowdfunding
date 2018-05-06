package com.bestgo.admanager.act.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 审批拒绝监听器
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月22日下午10:29:49  
 */
public class NoListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("审批拒绝！");

	}

}
