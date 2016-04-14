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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonymm.bxwtfk.bean.BXWTFK_SENDCONTENT;
import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;
import com.sonymm.bxwtfk.common.GetAuth;
import com.sonymm.bxwtfk.util.ConvertJson;

/**
 * @ClassName: PersonInfoController
 * @Description: 用户信息页面
 * @author sjx
 * @date 2016年04月05日 上午10:09:39
 *
 */
@Controller
@RequestMapping(value = "/bxwtfk")
public class SendManagerController {
	
	@Autowired
	GetAuth getAuth;
	
	@Autowired
	ConvertJson convertJson;
	
	@RequestMapping(value = "/myManager/sendManager", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> sendManager(
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BXWTFK_USERINFO> lui = new ArrayList<BXWTFK_USERINFO>();
		BXWTFK_USERINFO userInfo = new BXWTFK_USERINFO();
		
		userInfo.setDeptname("dept1");
		userInfo.setEmail("email1");
		userInfo.setIsadmin("1");
		userInfo.setMobile("mobile1");
		userInfo.setName("name1");
		userInfo.setPosition("position1");
		userInfo.setStatu("0");
		userInfo.setUserid("1");
		lui.add(userInfo);
		
		userInfo = new BXWTFK_USERINFO();
		userInfo.setDeptname("dept2");
		userInfo.setEmail("email2");
		userInfo.setIsadmin("1");
		userInfo.setMobile("mobile2");
		userInfo.setName("name2");
		userInfo.setPosition("position2");
		userInfo.setStatu("0");
		userInfo.setUserid("2");
		lui.add(userInfo);
		
		userInfo = new BXWTFK_USERINFO();
		userInfo.setDeptname("dept3");
		userInfo.setEmail("email3");
		userInfo.setIsadmin("1");
		userInfo.setMobile("mobile3");
		userInfo.setName("name3");
		userInfo.setPosition("position3");
		userInfo.setStatu("0");
		userInfo.setUserid("3");
		lui.add(userInfo);
		
		map.put("USERINFO", lui);
		return map;
	}
	
	@RequestMapping(value = "/myManager/getAllUser", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getAllUser(
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> lui = new ArrayList<Map<String, Object>>();
		//获取工作圈中企业所有员工信息
		String all_auth = getAuth.getAllAuth();
		Map<String, Object> lmss = convertJson.getMapByJson(all_auth);
		lui = convertJson.getListByJson(lmss.get("employeeInfo").toString());
		map.put("ALLUSERINFO", lui);
		return map;
	}
}
