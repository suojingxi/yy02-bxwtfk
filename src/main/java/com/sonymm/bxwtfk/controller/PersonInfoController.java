package com.sonymm.bxwtfk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonymm.bxwtfk.bean.BXWTFK_SENDCONTENT;
import com.sonymm.bxwtfk.service.ISendContentService;

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
	
	@RequestMapping(value = "/myInfo/personInfo", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> personInfo(
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = session.getAttribute("userId").toString();
		List<Map<String, Object>> lmso = new ArrayList<Map<String,Object>>();
		lmso = iSendContentService.getSendContent(userId);
		map.put("SENDCONTENT", lmso);
		return map;
	}
	
	@RequestMapping(value = "/myInfo/personInfoById", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> personInfoById(
    		@RequestParam(value = "id") String id,
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> lmso = iSendContentService.getSendContentById(id);
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

}
