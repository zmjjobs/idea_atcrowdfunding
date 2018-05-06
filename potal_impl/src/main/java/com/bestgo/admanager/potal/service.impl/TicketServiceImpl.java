package com.bestgo.admanager.potal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Ticket;
import com.bestgo.admanager.potal.dao.TicketDao;
import com.bestgo.admanager.potal.service.TicketService;

/**
 * 流程单业务实现类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月25日下午11:24:25  
 */
@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketDao ticketDao;

	@Override
	public Ticket queryTicketByMemberid(Integer memberid) {
		return ticketDao.queryTicketByMemberid(memberid);
	}

	@Override
	public int saveTicket(Ticket ticket) {
		return ticketDao.saveTicket(ticket);
	}

	@Override
	public int updateTicketProcessStep(Ticket ticket) {
		return ticketDao.updateTicketProcessStep(ticket);
	}

	@Override
	public int updateTicketInfo(Ticket ticket) {
		return ticketDao.updateTicketInfo(ticket);
	}
}
