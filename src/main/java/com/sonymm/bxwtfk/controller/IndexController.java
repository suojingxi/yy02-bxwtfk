package com.sonymm.bxwtfk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonymm.bxwtfk.entity.FunctionsOfUser;
import com.sonymm.bxwtfk.entity.ObjectsDetail;
import com.sonymm.bxwtfk.service.IFunctionsService;
import com.sonymm.bxwtfk.util.FunctionURLReader;
import com.sonymm.bxwtfk.vo.FunctionVO;

/**
 * @ClassName: IndexController
 * @Description: 首页
 * @author sjx
 * @date 2015年4月03日 上午17:09:39
 *
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IFunctionsService iFuns;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session,
			HttpServletRequest request) throws Exception {
		Logger logger = Logger.getLogger(IndexController.class);
		try {
			ObjectsDetail user = new ObjectsDetail();
			user.setDept("1");
			user.setEmail("1");
			user.setPhone("1");
			user.setPosition("1");
			user.setStatu("1");
			user.setUser_name("1");
			user.setUser_passw("1");
			user.setUser_uid("1");
			
			ObjectsDetail[] users = {user};
			
			session.setAttribute("loginUser", users[0]);
			session.setAttribute("userName", users[0].getUser_name());
			session.setAttribute("userPassw", users[0].getUser_passw());

		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		}
		return "index";
	}

	/*
	 * @RequestMapping("/") public String login() { return "index"; }
	 */

	@RequestMapping(value = "initFunTree", method = RequestMethod.GET)
	public @ResponseBody List<FunctionVO> getFunctions(HttpSession session)
			throws Exception {
		ObjectsDetail user = (ObjectsDetail) session.getAttribute("loginUser");
		// 登录人对应角色的functions
		FunctionsOfUser[] funs = iFuns.getFunctionsByUser(user.getUser_uid());
		List<FunctionVO> funList = new ArrayList<FunctionVO>();
		for (FunctionsOfUser fun : funs) {
			String funCode = fun.getCode_name();
			FunctionVO funVo = new FunctionVO();
			funVo.setCode(funCode);
			funVo.setUrl(getUrlFromCode(funCode));
			funList.add(funVo);
		}
		return funList;
	}
	

	private String getUrlFromCode(String funCode) {
		FunctionURLReader urlReader = FunctionURLReader.getIns();
		String url = urlReader.getProperties(funCode);
		if (url == null || "".equals(url))
			url = "#";
		return url;
	}
	
	private Map<String, Object> replace(Map<String, Object> map, String str, Object obj1){
		if(!map.get(str).equals(obj1.toString())){
			map.put(str, obj1);
		}
		return map;
	}
	
	@RequestMapping(value = "reqAuth", method = RequestMethod.POST)
	public @ResponseBody String reqAuth(HttpServletRequest request,HttpServletResponse response){
		String authName=null;
		try {
				//对用户权限做记录
				String authNameOper=null;
		      //获取当前登录用户
		      ObjectsDetail user = (ObjectsDetail) request.getSession().getAttribute(
				"loginUser");
		      // 登录人对应角色的functions
	       	  FunctionsOfUser[] funAuths = iFuns.getFunctionsByUser(user
					.getUser_uid());
	       	  for(FunctionsOfUser userAuth:funAuths){
	       		  authName=userAuth.getCode_name();
	       		  if("BXWTFK001".equals(authName)){
	       			  	return authName;
	       		  }else if("BXWTFK002".equals(authName)){
	       			authNameOper=authName;
	       		  }
	       	  }
	       	  if(null!=authNameOper){
	       		 return authNameOper;
	       	  }else{
	       		  return "fail";
	       	  }
		} catch (Exception e) {
			 return "fail";
		}	
	}
}
