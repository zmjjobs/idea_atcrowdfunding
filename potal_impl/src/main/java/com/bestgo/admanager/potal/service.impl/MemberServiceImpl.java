package com.bestgo.admanager.potal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestgo.admanager.bean.Member;
import com.bestgo.admanager.potal.dao.MemberDao;
import com.bestgo.admanager.potal.service.MemberService;
import com.bestgo.admanager.bean.CertImg;

/**
 * 会员业务实现类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月23日下午9:23:26  
 */
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	@Override
	public Member queryMemberForLogin(Map<String, Object> paramMap) {
		return memberDao.queryMemberForLogin(paramMap);
	}
	@Override
	public int updateAccttype(Member member) {
		return memberDao.updateAccttype(member);
	}
	@Override
	public int updateBasicInfo(Member loginMember) {
		return memberDao.updateBasicInfo(loginMember);
	}
	@Override
	public void saveMemberCerts(List<CertImg> certImgs) {
		for (CertImg certImg : certImgs) {
			memberDao.insertCertImg(certImg);
		}
	}
	@Override
	public int updateEmail(Member loginMember) {
		return memberDao.updateEmail(loginMember);
	}
	@Override
	public int updateAuthstatus(Member member) {
		return memberDao.updateAuthstatus(member);
	}
	
	
	/*@Override
	public int saveMemberCert(List<CertImg> certImgs) {
		return memberDao.insertMemberCert(certImgs);
	}*/

}
