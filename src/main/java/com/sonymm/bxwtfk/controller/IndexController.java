package com.sonymm.bxwtfk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;
import com.sonymm.bxwtfk.common.GetAuth;
import com.sonymm.bxwtfk.entity.FunctionsOfUser;
import com.sonymm.bxwtfk.entity.ObjectsDetail;
import com.sonymm.bxwtfk.service.IFunctionsService;
import com.sonymm.bxwtfk.service.IUserinfoService;
import com.sonymm.bxwtfk.util.ConvertJson;
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
	
	@Autowired
	GetAuth getAuth;
	
	@Autowired
	ConvertJson convertJson;
	
	@Autowired
	IUserinfoService iUserinfoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session,
			HttpServletRequest request) throws Exception {
		Logger logger = Logger.getLogger(IndexController.class);
		try {
			ObjectsDetail user = new ObjectsDetail();
			//通过判断当前登录人是不是管理员（这里管理员写死），如果是，则获取当前登录人所在组织的所有人员。
			//获取所有企业员工信息
			String all_auth = getAuth.getAllAuth();
			Map<String, Object> lmss = convertJson.getMapByJson(all_auth);
			List<Map<String, Object>> auth_list_map = convertJson.getListByJson(lmss.get("employeeInfo").toString());
			//获取当前登录工作圈的人员信息
			//这里通过调用GetAuth类中的方法，获取当前登录人的信息
			String auth = "[" + getAuth.getAuth() + "]";
			List<Map<String, Object>> auth_map = convertJson.getListByJson(auth);
			//判断当前登录人员信息是否为空，如果为空说明是直接访问的当前系统，不允许访问
			// TODO Auto-generated method stub
			
			//如果不为空，这判断当前登录人员是否在当前企业员工信息列表中中
			boolean flag = false;
			Map<String, Object> save_auth_map = new HashMap<String, Object>();
			for(Map<String, Object> map : auth_list_map) {
				if(map.get("userId").equals(auth_map.get(0).get("userId"))){
					flag = true;
					save_auth_map = map;
					break;
				}
			}
			
			//如果在企业员工列表中，判断是否在数据库表中
			if(flag){
				Map<String, Object> userinfo = iUserinfoService.getUserinfo(auth_map.get(0).get("userId").toString());
				//如果在数据库中（userinfo!=null）则进行下面操作，如果不在则保存到数据库中。
				Map<String, Object> tran_auth_map = new HashMap<String, Object>();
				if(userinfo == null){
					tran_auth_map = iUserinfoService.addUserinfo(save_auth_map);
				}else{
					tran_auth_map = userinfo;
				}
				user.setDeptname(tran_auth_map.get("deptname") != null ? tran_auth_map.get("deptname").toString() : "");
				user.setEmail(tran_auth_map.get("email") != null ? tran_auth_map.get("email").toString() : "");
				user.setIsadmin(tran_auth_map.get("isadmin") != null ? tran_auth_map.get("isadmin").toString() : "");
				user.setMobile(tran_auth_map.get("mobile") != null ? tran_auth_map.get("mobile").toString() : "");
				user.setPosition(tran_auth_map.get("position") != null ? tran_auth_map.get("position").toString() : "");
				user.setStatu(tran_auth_map.get("statu") != null ? tran_auth_map.get("statu").toString() : "");
				user.setUser_name(tran_auth_map.get("name") != null ? tran_auth_map.get("name").toString() : "");
				user.setUser_passw("");
				user.setUser_uid(tran_auth_map.get("userinfoid") != null ? tran_auth_map.get("userinfoid").toString() : "");
				
				ObjectsDetail[] users = {user};
				
				session.setAttribute("loginUser", users[0]);
				session.setAttribute("userName", users[0].getUser_name());
				
				return "index";
				
			}else{//如果不是企业员工默认为恶意攻击，直接跳出系统
				return "";
			}
			
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		}
	}

	/*
	 * @RequestMapping("/") public String login() { return "index"; }
	 */

	@RequestMapping(value = "initFunTree", method = RequestMethod.GET)
	public @ResponseBody List<FunctionVO> getFunctions(HttpSession session)
			throws Exception {
		ObjectsDetail user = (ObjectsDetail) session.getAttribute("loginUser");
		// 登录人对应角色的functions
		List<FunctionsOfUser> funs = iFuns.getFunctionsByUser(user.getUser_uid());
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
	       	  List<FunctionsOfUser> funAuths = iFuns.getFunctionsByUser(user
					.getUser_uid());
	       	  for(FunctionsOfUser userAuth:funAuths){
	       		  authName=userAuth.getCode_name();
	       		  if("COM_BXWTFK001".equals(authName)){
	       			  	return authName;
	       		  }else if("GL_BXWTFK002".equals(authName)){
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
