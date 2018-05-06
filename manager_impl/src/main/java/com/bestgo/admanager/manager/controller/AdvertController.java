package com.bestgo.admanager.manager.controller;

import com.bestgo.admanager.bean.Advert;
import com.bestgo.admanager.bean.User;
import com.bestgo.admanager.manager.service.AdvertService;
import com.bestgo.admanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 广告请求处理器
 * @author 朱梦君
 * @date 创建时间：2017年7月30日 下午10:20:17
 * @version v1.0
 * @since jdk1.7
 */
@Controller
@RequestMapping("/advert")
public class AdvertController {

	@Autowired
	private AdvertService advertService;

	/**
	 * 修改更新广告信息
	 * @param advert
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update(Advert advert){
		AjaxResult result = new AjaxResult();
		try {
			int count = advertService.updateAdvert(advert);
			result.setSuccess(count== 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	//批量删除
	@ResponseBody
	@RequestMapping("/batchDelete")
	public Object batchDelete( DataForParam ds ) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count = advertService.batchDeleteAdvert(ds);
			if ( count == ds.getDatas().size() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int count = advertService.deleteAdvertById(id);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 去往广告主页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "advert/index";
	}

	/**
	 * 去往广告添加页面
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "advert/add";
	}
	
	/**
	 * 去往广告修改页面
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(Integer id,Model model) {
		Advert advert = advertService.queryAdvertById(id);
		model.addAttribute("advert", advert);
		return "advert/edit";
	}

	/**
	 * 添加广告
	 * 异步
	 * @param request
	 * @param advertisement
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doAdd")
	public Object doAdd(HttpServletRequest request, Advert advert,HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile mulFile = mulRequest.getFile("advpic");

			// 获取原始名称 
			String oriName = mulFile.getOriginalFilename();

			// 获取后缀名 
			String sufName = oriName.substring(oriName.lastIndexOf("."));

			// 图片路径 
			String iconpath = UUID.randomUUID().toString()+sufName;

			String realPath = session.getServletContext().getRealPath("pic");
			realPath = realPath + "\\adv\\iconpath";

			// 将文件另存为真实路径 
			mulFile.transferTo(new File(realPath));

			User user = (User) session.getAttribute(Const.LOGIN_USER);
			advert.setUserid(user.getId());
			advert.setStatus("1");
			advert.setIconpath(iconpath);
			int count = advertService.insertAdvert(advert);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	// 同步请求处理.
	/*@RequestMapping("/doAdd")
	public Object doAdd(HttpServletRequest request, Advert advert,
			HttpSession session) {

		try {
			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;

			MultipartFile mfile = mreq.getFile("advpic");

			String name = mfile.getOriginalFilename();
			String extname = name.substring(name.lastIndexOf("."));

			String iconpath = UUID.randomUUID().toString() + extname;

			ServletContext servletContext = session.getServletContext();
			String realpath = servletContext.getRealPath("/pic");

			String path = realpath + "\\adv\\" + iconpath;

			mfile.transferTo(new File(path));

			User user = (User) session.getAttribute(Const.LOGIN_USER);
			advert.setUserid(user.getId());
			advert.setStatus("1");
			advert.setIconpath(iconpath);

			advertService.insertAdvert(advert);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "redirect:/advert/index.htm";
	}*/

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
			// 查询资质数据
			Map<String, Object> advertMap = new HashMap<String, Object>();
			advertMap.put("pageno", pageno);
			advertMap.put("pagesize", pagesize);
			if (StringUtil.isNotEmpty(pagetext)) {
				pagetext = pagetext.replaceAll("%", "\\\\%");
			}
			advertMap.put("pagetext", pagetext);

			// 分页查询
			Page<Advert> page = advertService.pageQuery(advertMap);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}