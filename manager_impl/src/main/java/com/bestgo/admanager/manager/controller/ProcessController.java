package com.bestgo.admanager.manager.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bestgo.admanager.util.AjaxResult;
import com.bestgo.admanager.util.Page;
import com.bestgo.admanager.util.StringUtil;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * 流程定义请求处理器
 * 
 * @author zhumengjun
 * @version
 * @datetime 2017年7月18日下午9:37:09
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private RepositoryService repositoryService;
	
	/**
	 * 查询并展示流程图
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/queryProcDefImg")
	public void queryProcDefImg(String id,HttpServletResponse response) throws IOException{
		//根据流程定义的ID查询流程定义的对象
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
		//通过流程对象获取部署DEPLOYMENT_ID和DGRM_RESOURCE_NAME
		String deploymentId = processDefinition.getDeploymentId();
		String resourceName = processDefinition.getDiagramResourceName();
		
		//将流程定义图片以流的形式返回给客户端,相当于文件下载
		InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
		ServletOutputStream outputStream = response.getOutputStream();
		IOUtils.copy(inputStream, outputStream);
	}
	
	/**
	 * 去往展示页面，展示详情
	 * @return
	 */
	@RequestMapping("/toShowImg")
	public String toShowImg(){
		return "process/show";
	}
	
	/**
	 * 删除流程定义
	 * @param id 流程定义的ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doDelete")
	public Object doDelete(String id) {
		AjaxResult result = new AjaxResult();
		
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
			//true表示级联删除
			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 去往流程主页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "process/index";
	}

	

	/**
	 * 添加上传文件，进行部署
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deploy")
	public Object deploy(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		
		try {
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile mulFile = mulRequest.getFile("procDefFile");

			// 获取上传文件的原始名称 
			String resourceName= mulFile.getOriginalFilename();
			//将文件转为字节流
			InputStream inputStream = mulFile.getInputStream();
			//进行流程部署
			repositoryService.createDeployment().addInputStream(resourceName, inputStream).deploy();

			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	

	/**
	 * 分页查询
	 * @param pagetext
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, @RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno, @RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize) {
		AjaxResult result = new AjaxResult();
		
		try {
			//创建流程定义查询
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			if (StringUtil.isNotEmpty(pagetext)) {
				pagetext = pagetext.replaceAll("%", "\\\\%").trim();
				//根据ID查询
				//processDefinitionQuery = processDefinitionQuery.processDefinitionId(pagetext);
				
				//根据名字查询
				processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike(pagetext);
			}
			//按照版本进行降序排列
			processDefinitionQuery = processDefinitionQuery.orderByProcessDefinitionVersion().desc();
			//用于存放listPage中每个对象的所有属性封装成的pbMap
			List<Map<String,Object>> pbMaps = new ArrayList<>();
			//分页查询流程定义数据
			List<ProcessDefinition> listPage = processDefinitionQuery.listPage((pageno-1)*pagesize, pagesize);
			for (ProcessDefinition pd : listPage) {
				Map<String,Object> pbMap = new HashMap<>();
				pbMap.put("id", pd.getId());
				pbMap.put("name", pd.getName());
				pbMap.put("key", pd.getKey());
				pbMap.put("version", pd.getVersion());
				pbMap.put("pagetext", pagetext);
				pbMaps.add(pbMap);
			}
			Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageno, pagesize);
			Long totalsize = processDefinitionQuery.count();
			//activiti传入数据时，Data属性对应的值必须是List<Map<String,Object>>,否则会报错
			page.setData(pbMaps);
			page.setTotalsize(totalsize.intValue());
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}