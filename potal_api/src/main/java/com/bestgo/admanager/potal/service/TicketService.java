package com.bestgo.admanager.potal.service;

import com.bestgo.admanager.bean.Ticket;

/**
 * 流程单业务接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月25日下午11:23:29  
 */
public interface TicketService {

	/**
	 * 根据会员ID查询流程单
	 * @param memberid 会员ID
	 * @return 流程单
	 */
	Ticket queryTicketByMemberid(Integer memberid);

	/**
	 * 保存新的流程单
	 * @param ticket 流程单
	 * @return 保存的条数
	 */
	int saveTicket(Ticket ticket);

	/**
	 * 更新流程单的步骤
	 * @param ticket
	 * @return 
	 */
	int updateTicketProcessStep(Ticket ticket);

	/**
	 * 更新流程单
	 * @param ticket
	 * @return 更新的条数
	 */
	int updateTicketInfo(Ticket ticket);
	
}
