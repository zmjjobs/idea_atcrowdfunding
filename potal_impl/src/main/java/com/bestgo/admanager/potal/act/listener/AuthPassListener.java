package com.bestgo.admanager.potal.act.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import com.bestgo.admanager.bean.Member;
import com.bestgo.admanager.potal.service.MemberService;
import com.bestgo.admanager.potal.service.TicketService;
import com.bestgo.admanager.util.ApplicationContextUtils;

/**
 * @Description: 验证通过监听器
 * @author 朱梦君
 * @date 创建时间：2017年7月30日 下午4:27:01
 * @version v1.0
 * @since jdk1.7
 */
public class AuthPassListener implements ExecutionListener {

	//private static final long serialVersionUID = 1L;

	// 监听器对象是自己创建的,不是IOC容器创建的,所以不能直接进行自动装配

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		/*// 通过String自带的工具类获取ioc
		ApplicationContext ioc = ApplicationContextUtils.applicationContext;

		// spring容器中获取service对象
		MemberService memberService = ioc.getBean(MemberService.class);

		// 修改t_member表authstatus字段值:会员申请状态:2-已实名认证
		Integer memberid = (Integer) execution.getVariable("memberid");
		Member member = new Member();
		member.setId(memberid);
		member.setAuthstatus("2");
		memberService.updateAuthstatus(member);

		// 修改t_ticket表status字段值:1-审核结束
		TicketService ticketService = ioc.getBean(TicketService.class);
		ticketService.updateTicketStatus(memberid);*/
	}

}
