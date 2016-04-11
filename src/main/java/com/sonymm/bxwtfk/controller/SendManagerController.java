package com.sonymm.bxwtfk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonymm.bxwtfk.bean.BXWTFK_SENDCONTENT;
import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;

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
	@RequestMapping(value = "/myManager/sendManager", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> sendManager(
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BXWTFK_USERINFO> lui = new ArrayList<BXWTFK_USERINFO>();
		BXWTFK_USERINFO userInfo = new BXWTFK_USERINFO();
		
		userInfo.setDept("dept1");
		userInfo.setEmail("email1");
		userInfo.setId("id1");
		userInfo.setPhone("phone1");
		userInfo.setPosition("position1");
		userInfo.setState("state1");
		userInfo.setTel("tel1");
		userInfo.setUsername("name1");
		lui.add(userInfo);
		
		userInfo = new BXWTFK_USERINFO();
		userInfo.setDept("dept2");
		userInfo.setEmail("email2");
		userInfo.setId("id2");
		userInfo.setPhone("phone2");
		userInfo.setPosition("position2");
		userInfo.setState("state2");
		userInfo.setTel("tel2");
		userInfo.setUsername("name2");
		lui.add(userInfo);
		
		userInfo = new BXWTFK_USERINFO();
		userInfo.setDept("dept3");
		userInfo.setEmail("email3");
		userInfo.setId("id3");
		userInfo.setPhone("phone3");
		userInfo.setPosition("position3");
		userInfo.setState("state3");
		userInfo.setTel("tel3");
		userInfo.setUsername("name3");
		lui.add(userInfo);
		
		map.put("USERINFO", lui);
		return map;
	}
}
