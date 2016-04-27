package com.sonymm.bxwtfk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sonymm.bxwtfk.bean.BXWTFK_SENDCONTENT;
import com.sonymm.bxwtfk.service.ISendContentService;
import com.sonymm.bxwtfk.service.IWtdjTypeContentService;
import com.sonymm.bxwtfk.service.IWtdjTypeService;

/**
 * @ClassName: PersonInfoController
 * @Description: 用户信息页面
 * @author sjx
 * @date 2016年04月05日 上午10:09:39
 *
 */
@Controller
@RequestMapping(value = "/bxwtfk")
public class PersonInfoController {
	
	@Autowired
	ISendContentService iSendContentService;
	
	@Autowired
	IWtdjTypeService iWtdjTypeService;
	
	@Autowired
	IWtdjTypeContentService iWtdjTypeContentService;
	
	@RequestMapping(value = "/myInfo/personInfo", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> personInfo(
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = session.getAttribute("userId").toString();
		List<Map<String, Object>> lmso = new ArrayList<Map<String,Object>>();
		lmso = iSendContentService.getSendContent(userId);
		//修改数据:将问题类型代码转换成文字
		for(int i=0; i<lmso.size(); i++){
			StringBuffer typeCode = new StringBuffer();
			Map<String, Object> mso = lmso.get(i);
			String code = mso.get("CONTENT_THEMES").toString();
			String[] codes = code.split(",");
			for(String str : codes){
				if(typeCode.indexOf(str) < 0){
					typeCode.append(str+",");
				}
			}
			String typeCodes = typeCode.substring(0, typeCode.length()-1);
			//修改主题
			String typeNames = iWtdjTypeService.getNamesByCodes(typeCodes);
			lmso.get(i).remove("CONTENT_THEMES");
			lmso.get(i).put("CONTENT_THEMES", typeNames);
			//修改内容
			String content = lmso.get(i).get("CONTENT")==null?"":lmso.get(i).get("CONTENT").toString();
			//1.通过主题代码如：A01,B01获取到如：单据填写问题：金额有误；单据审批问题：超标末审；
			StringBuffer contentName = new StringBuffer();
			for(String str : codes){
				String typeName = iWtdjTypeService.getNameByCode(str.substring(0,1));
				String codeName = iWtdjTypeContentService.getContentNameByCode(str);
				contentName.append(typeName + ":" + codeName + ";");
			}
			contentName.append(content);
			lmso.get(i).remove("CONTENT");
			lmso.get(i).put("CONTENT", contentName);
		}
		map.put("SENDCONTENT", lmso);
		return map;
	}
	
	@RequestMapping(value = "/myInfo/personInfoById", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> personInfoById(
    		@RequestParam(value = "id") String id,
            ServletRequest request, HttpSession session) throws Exception {
		//更新数据已读未读状态
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> lmso = iSendContentService.getSendContentById(id);
		//更新数据已读未读状态
		if(lmso.get(0).get("DU_STATU").toString().equals("0")){
			iSendContentService.updateContent(id);
			lmso.get(0).remove("DU_STATU");
			lmso.get(0).put("DU_STATU", "1");
		}
		//修改数据:将问题类型代码转换成文字
		StringBuffer typeCode = new StringBuffer();
		Map<String, Object> mso = lmso.get(0);
		String code = mso.get("CONTENT_THEMES").toString();
		String[] codes = code.split(",");
		for(String str : codes){
			if(typeCode.indexOf(str) < 0){
				typeCode.append(str+",");
			}
		}
		String typeCodes = typeCode.substring(0, typeCode.length()-1);
		//修改主题
		String typeNames = iWtdjTypeService.getNamesByCodes(typeCodes);
		lmso.get(0).remove("CONTENT_THEMES");
		lmso.get(0).put("CONTENT_THEMES", typeNames);
		//修改内容
		String content = lmso.get(0).get("CONTENT")==null?"":lmso.get(0).get("CONTENT").toString();
		//1.通过主题代码如：A01,B01获取到如：单据填写问题：金额有误；单据审批问题：超标末审；
		StringBuffer contentName = new StringBuffer();
		for(String str : codes){
			String typeName = iWtdjTypeService.getNameByCode(str.substring(0,1));
			String codeName = iWtdjTypeContentService.getContentNameByCode(str);
			contentName.append(typeName + ":" + codeName + ";");
		}
		contentName.append(content);
		lmso.get(0).remove("CONTENT");
		lmso.get(0).put("CONTENT", contentName);
		map.put("SENDCONTENT", lmso.get(0));
		return map;
	}
	
	@RequestMapping(value = "/myInfo/delPersonById", method = RequestMethod.GET)
    public @ResponseBody int delPersonById(
    		@RequestParam(value = "id") String id,
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		return iSendContentService.delSendContentById(id);
	}
	
	@RequestMapping(value = "/myInfo/getPersonInfoNum", method = RequestMethod.GET)
    public @ResponseBody int getPersonInfoNum(
            ServletRequest request, HttpSession session) throws Exception {
		String userId = session.getAttribute("userId").toString();
		return iSendContentService.getTotalWd(userId);
	}
	
	@RequestMapping(value = "/myInfo/returnIndex", method = RequestMethod.GET)
    public @ResponseBody ModelAndView returnIndex(
            ServletRequest request, ServletResponse response, HttpSession session) throws Exception {
		return new ModelAndView("redirect:/");
//		return "redirect:/indexm";
//		request.getRequestDispatcher("/WEB-INF/views/indexm.ftl").forward(request, response);
	}
	
	//标记未读状态
	@RequestMapping(value = "/myInfo/bjPersonById", method = RequestMethod.GET)
    public @ResponseBody int bjPersonById(
    		@RequestParam(value = "id") String id,
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		return iSendContentService.bjwdSendContentById(id);
	}
}
