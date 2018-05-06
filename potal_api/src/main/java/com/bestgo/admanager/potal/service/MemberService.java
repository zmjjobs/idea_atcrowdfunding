package com.bestgo.admanager.potal.service;

import java.util.List;
import java.util.Map;

import com.bestgo.admanager.bean.CertImg;
import com.bestgo.admanager.bean.Member;
import com.bestgo.admanager.bean.Member;

/**
 * 会员业务接口
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月23日下午9:14:30  
 */
public interface MemberService {
	/**
	 * 验证登录会员是否存在
	 * @param paramMap
	 * @return 查询到的会员对象
	 */
	Member queryMemberForLogin(Map<String, Object> paramMap);

	/**
	 * 更新会员的账户类型
	 * @param member
	 * @return 更新的条数
	 */
	int updateAccttype(Member member);

	/**
	 * 更新会员的基本信息
	 * @param loginMember  登录会员
	 * @return 
	 */
	int updateBasicInfo(Member loginMember);

	/**
	 * 保存会员上传的资质图片
	 * @param certImgs
	 */
	void saveMemberCerts(List<CertImg> certImgs);

	/**
	 * 更新会员的邮箱
	 * @param loginMember
	 * @return 更新的条数
	 */
	int updateEmail(Member loginMember);

	/**
	 * 更新会员的申请状态（0-未实名认证  1-实名认证审核中  2-已实名认证）
	 * @param member
	 */
	int updateAuthstatus(Member member);

}
