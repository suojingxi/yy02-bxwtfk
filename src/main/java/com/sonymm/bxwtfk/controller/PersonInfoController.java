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
	
	/*@RequestMapping(value = "/myInfo/personInfo", method = RequestMethod.GET)
    public @ResponseBody String personInfo(
            @RequestParam(value = "test1") String test1,
            ServletRequest request, HttpSession session) throws Exception {
//		JSONObject obj = JSONObject.fromObject(test1);
//		return obj.toString();
		return test1;
	}*/
	@RequestMapping(value = "/myInfo/personInfo", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> personInfo(
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BXWTFK_SENDCONTENT> lsc = new ArrayList<BXWTFK_SENDCONTENT>();
		BXWTFK_SENDCONTENT sendContent = new BXWTFK_SENDCONTENT();
		sendContent.setContent("这是第一条内容。");
		sendContent.setContent_themes("内容1");
		sendContent.setId("111");
		sendContent.setSend_time("2016-04-01 11:11:00");
		sendContent.setSend_userinfo_id("11");
		sendContent.setState("1");
		sendContent.setUserinfo_id("12");
		lsc.add(sendContent);
		sendContent = new BXWTFK_SENDCONTENT();
		sendContent.setContent("这是第二条内容。");
		sendContent.setContent_themes("内容2");
		sendContent.setId("222");
		sendContent.setSend_time("2016-04-01 11:11:11");
		sendContent.setSend_userinfo_id("11");
		sendContent.setState("0");
		sendContent.setUserinfo_id("13");
		lsc.add(sendContent);
		map.put("SENDCONTENT", lsc);
		return map;
	}

}
