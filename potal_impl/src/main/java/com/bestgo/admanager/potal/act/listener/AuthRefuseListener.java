package com.bestgo.admanager.potal.act.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @Description: 验证拒绝监听器
 * @author 朱梦君
 * @date 创建时间：2017年7月30日 下午4:27:12
 * @version v1.0
 * @since jdk1.7
 */
public class AuthRefuseListener implements ExecutionListener {
	//监听器对象是自己创建的,不是IOC容器创建的,所以不能直接进行自动装配
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {

	}

}
