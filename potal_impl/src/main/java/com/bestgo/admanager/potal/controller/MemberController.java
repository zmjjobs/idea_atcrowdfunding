package com.bestgo.admanager.potal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bestgo.admanager.potal.act.listener.AuthPassListener;
import com.bestgo.admanager.potal.act.listener.AuthRefuseListener;
import com.bestgo.admanager.bean.Cert;
import com.bestgo.admanager.bean.Member;
import com.bestgo.admanager.bean.Ticket;
import com.bestgo.admanager.manager.service.CertService;
import com.bestgo.admanager.potal.service.MemberService;
import com.bestgo.admanager.potal.service.TicketService;
import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.bean.CertImg;
import com.bestgo.admanager.util.Const;
import com.bestgo.admanager.util.DataForParam;

/**
 * 会员管理控制器
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月25日上午12:25:56  
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private CertService certService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	/**
	 * 重新让服务器发送验证码给会员邮箱
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resendAuthcode")
	public Object resendAuthcode(HttpSession session){
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			int count = startProcessInstance(loginMember.getEmail(), loginMember);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/finishApply")
	public Object finishApply(String authcode,HttpSession session){
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			//根据会员Id查询它的流程单
			Ticket ticket = ticketService.queryTicketByMemberid(loginMember.getId());

			if(ticket.getAuthcode().equals(authcode)){
				//完成[审核验证码]任务
				TaskQuery taskQuery = taskService.createTaskQuery();
				Task task = taskQuery.processInstanceId(ticket.getPiid()).taskAssignee(loginMember.getLoginacct()).singleResult();
				taskService.complete(task.getId());
				
				loginMember.setAuthstatus("1");
				int count = memberService.updateAuthstatus(loginMember);
				result.setSuccess(count == 1);
			}else{
				result.setErrorMessage("您输入的验证码不正确,请重新输入!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 根据流程单跳转页面
	 * @return
	 */
	@RequestMapping("/apply")
	public String apply(HttpSession session,Map<String,Object> map){
		System.out.println("apply.............................");
		//获取流程单，判断流程单是否存在，此时的status为零，即审核中
		Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		Integer memberid = loginMember.getId();
		Ticket ticket = ticketService.queryTicketByMemberid(memberid);
		
		//如果不存在，保存流程单，跳转到账户类型页面
		if(ticket == null){
			ticket = new Ticket();
			ticket.setMemberid(memberid);
			ticketService.saveTicket(ticket);
			return "member/accttype";
		}
		
		//如果存在，根据流程单中保存的已有步骤跳转到对应的页面（记忆功能）
		String pstep = ticket.getPstep();
		if(Const.ACCTTYPE.equals(pstep)){
			return "member/basicinfo";
		}
		if(Const.BASICINFO.equals(pstep)){
			List<Cert> certList = certService.queryCertsByAccttype(loginMember.getAccttype());
			map.put("certList", certList);
			return "member/uploadfile";
		}
		if(Const.UPLOADFILE.equals(pstep)){
			return "member/checkemail";
		}
		if(Const.CHECKEMAIL.equals(pstep)){
			return "member/checkapply";
		}
		return "member/accttype";
	}
	
	/**
	 * 更新会员的基本信息
	 * @param member
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateBasicInfo")
	public Object updateBasicInfo(Member member,HttpSession session,Map<String,Object> map){
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());
			loginMember.setTel(member.getTel());
			//更新基本信息
			memberService.updateBasicInfo(loginMember);
			//根据会员Id查询它的流程单
			Ticket ticket = ticketService.queryTicketByMemberid(loginMember.getId());
			//将流程单步骤字段设置为basicinfo
			ticket.setPstep(Const.BASICINFO);
			
			ticketService.updateTicketProcessStep(ticket);
			
			List<Cert> certList = certService.queryCertsByAccttype(loginMember.getAccttype());
			map.put("certList", certList);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	
	/**
	 * 开启流程实例并更新流程审批步骤
	 * @param email
	 * @param loginMember
	 * @return
	 */
	public int startProcessInstance(String email,Member loginMember){
		//获取流程定义查询对象
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		//根据流程定义KEY找到对应的流程定义对象
		ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionKey(Const.PROCESSDEFINITIONKEY).latestVersion().singleResult();
		
		//生成验证码
		StringBuilder authcode = new StringBuilder();
		for(int i=0;i<4;i++){
			authcode.append(new Random().nextInt(10));
		}
		String authcodeStr = authcode.toString();
		//根据流程定义的流程图找到变量，封装在map中
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("toMail", email);
		variables.put("authcode", authcodeStr);
		variables.put("loginacct", loginMember.getLoginacct());
		variables.put("passListener", new AuthPassListener());
		variables.put("refuseListener", new AuthRefuseListener());
		
		//启动流程实例
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
		
		//更新流程审批的步骤
		Ticket ticket = ticketService.queryTicketByMemberid(loginMember.getId());
		ticket.setPstep(Const.CHECKEMAIL);
		ticket.setPiid(processInstance.getId());
		ticket.setAuthcode(authcodeStr);
		return ticketService.updateTicketInfo(ticket);
	}
	
	/**
	 * 进行邮箱确认
	 * @param email
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkemail(String email,HttpSession session){
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			//如果用户更改了邮箱，更新会员表的邮箱字段
			if(!loginMember.getEmail().equals(email)){
				loginMember.setEmail(email);
				memberService.updateEmail(loginMember);
			}
			
			int count = startProcessInstance(email, loginMember);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 上传资质文件
	 * @param member
	 * @param session
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping("/doUploadCertFile")
	public Object doUploadCertFile(DataForParam ds,HttpSession session){
		System.out.println("uploadfile.............................................");
		AjaxResult result = new AjaxResult();
		try {
			//获取pic包的临时部署路径
			String realPath = session.getServletContext().getRealPath("pic");
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			Integer memberid = loginMember.getId();
			
			//将上传的文件保存起来，让管理用户进行审核
			List<CertImg> certImgs = ds.getCertImgs();
			for (CertImg certImg : certImgs) {
				certImg.setMemberid(memberid);
				MultipartFile multipartFile = certImg.getMultipartFile();
				String oldFileName = multipartFile.getOriginalFilename();
				//数据库中要保存的图片路径
				String iconpath = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
				certImg.setIconpath(iconpath);
				//完整的图片部署路径
				String filePath = realPath + "/cert/" + iconpath; 
				multipartFile.transferTo(new File(filePath));
			}
			
			//保存会员上传的资质图片
			memberService.saveMemberCerts(certImgs);
			
			//更新审批流程步骤
			Ticket ticket = ticketService.queryTicketByMemberid(memberid);
			ticket.setPstep(Const.UPLOADFILE);
			int countB = ticketService.updateTicketProcessStep(ticket);
			
			result.setSuccess(countB == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 去往申请认证的提交资质文件页面
	 * @return
	 */
	@RequestMapping("/uploadfile")
	public String uploadfile(){
		return "member/uploadfile";
	}
	
	/**
	 * 去往申请认证的基本信息页面
	 * @return
	 */
	@RequestMapping("/basicinfo")
	public String basicinfo(){
		return "member/basicinfo";
	}
	
	/**
	 * 跳转到申请认证的选择账户类型页面
	 * @return
	 */
	@RequestMapping("/accttype")
	public String accttype(){
		return "member/accttype";
	}
	
	/**
	 * 会员更新账户类型
	 * @param accttype
	 * @param session
	 * @return AjaxResult对象
	 */
	@ResponseBody
	@RequestMapping("/updateAccttype")
	public Object updateAccttype(String accttype,HttpSession session){
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			//将session中的会员对象的accttype更新
			loginMember.setAccttype(accttype);
			//在数据库中对会员账户类型进行更新
			int countA = memberService.updateAccttype(loginMember);
			//根据流程单中对应的会员ID查询流程单
			Ticket ticket = ticketService.queryTicketByMemberid(loginMember.getId());
			//将此流程单的步骤字段赋值为acctttpe
			ticket.setPstep(Const.ACCTTYPE);
			//更新流程单数据
			int countB = ticketService.updateTicketProcessStep(ticket);
			result.setSuccess(countA == 1 && countB == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(true);
		}
		return result;
	}
}
