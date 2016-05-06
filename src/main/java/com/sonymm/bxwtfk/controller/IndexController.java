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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;
import com.sonymm.bxwtfk.common.GetAuth;
import com.sonymm.bxwtfk.entity.FunctionsOfUser;
import com.sonymm.bxwtfk.entity.ObjectsDetail;
import com.sonymm.bxwtfk.service.IFunctionsService;
import com.sonymm.bxwtfk.service.IUserinfoService;
import com.sonymm.bxwtfk.util.ConvertJson;
import com.sonymm.bxwtfk.util.DeviceUtil;
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

	//进入后台，1：判断登录的设备是PC还是移动端
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session,
			HttpServletRequest request) throws Exception {
		Logger logger = Logger.getLogger(IndexController.class);
		try {
			//判断登录端设备
			String dev = DeviceUtil.getDevice(request.getHeader("user-agent"));
			
			if(dev.equals("ios")) {
				return "indexmi";
			} else if(dev.equals("android")) {
				return "indexma";
			}else{
				//如果是pc端，就进行pc端的单点登录
				Object user = session.getAttribute("userId");
				if(user==null){
					String authCode = request.getParameter("auth_code");
					String authCodeRelRandomKey = request.getParameter("randomkey");
					if(authCode!=null&&!authCode.equals("")&&authCodeRelRandomKey!=null&&!authCodeRelRandomKey.equals("")){
						session.setAttribute("auth_code", authCode);
						session.setAttribute("randomkey", authCodeRelRandomKey);
					}
					
					List<Map<String, Object>> auth_list_map = getAllAuth(authCode, authCodeRelRandomKey);
					List<Map<String, Object>> auth_map = getAuthnow(authCode, authCodeRelRandomKey);
					Map<String, Object> save_auth_map = isOrginfo(auth_list_map, auth_map);
					//如果在企业员工列表中，判断是否在数据库表中
					if(save_auth_map!=null){
						Map<String, Object> tran_auth_map = checkInfo(auth_map, save_auth_map);
						if(tran_auth_map!=null){
							setSession(tran_auth_map, session);
							return "index";
						}else{
							return "";
						}
					}else{
						return "";
					}
				}else{
					return "index";
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		}
	}
	
	//如果是移动设备则进入这个方法，进行单点登录
	@RequestMapping(value = "dddl", method = RequestMethod.POST)
	public @ResponseBody String dddl(
			@RequestParam(value="authCode") String authCode,
			@RequestParam(value="authCodeRelRandomKey") String authCodeRelRandomKey,
			HttpServletRequest request,HttpServletResponse response, HttpSession session){
		Logger logger = Logger.getLogger(IndexController.class);
		String returns = "0";
		try{
			List<Map<String, Object>> auth_list_map = getAllAuth(authCode, authCodeRelRandomKey);
			List<Map<String, Object>> auth_map = getAuthnow(authCode, authCodeRelRandomKey);
			Map<String, Object> save_auth_map = isOrginfo(auth_list_map, auth_map);
			//如果在企业员工列表中，判断是否在数据库表中
			if(save_auth_map!=null){
				Map<String, Object> tran_auth_map = checkInfo(auth_map, save_auth_map);
				if(tran_auth_map!=null){
					setSession(tran_auth_map, session);
					returns = "1";
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.getMessage();
		}
		return returns;
	}
	
	//校验当前登录人员信息是否在本地
	private Map<String, Object> checkInfo(List<Map<String, Object>> auth_map, Map<String, Object> save_auth_map){
		//如果在数据库中（userinfo!=null）则进行下面操作，如果不在则保存到数据库中。
		Map<String, Object> tran_auth_map = null;
		try {
			//判断userId有没有在库中
			int count = iUserinfoService.checkUserinfo(auth_map.get(0).get("userId").toString());
			tran_auth_map = new HashMap<String, Object>();
			if(count>0){
				tran_auth_map = iUserinfoService.getUserinfo(auth_map.get(0).get("userId").toString());
			}else{
				tran_auth_map = iUserinfoService.addUserinfo(save_auth_map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tran_auth_map;
	}
	
	//登录成功后设置session
	private void setSession(Map<String, Object> tran_auth_map, HttpSession session){
		ObjectsDetail user = new ObjectsDetail();
		user.setDeptname(tran_auth_map.get("deptname") != null ? tran_auth_map.get("deptname").toString() : "");
		user.setEmail(tran_auth_map.get("email") != null ? tran_auth_map.get("email").toString() : "");
		user.setIsadmin(tran_auth_map.get("isadmin") != null ? tran_auth_map.get("isadmin").toString() : "");
		user.setMobile(tran_auth_map.get("mobile") != null ? tran_auth_map.get("mobile").toString() : "");
		user.setPosition(tran_auth_map.get("position") != null ? tran_auth_map.get("position").toString() : "");
		user.setStatu(tran_auth_map.get("statu") != null ? tran_auth_map.get("statu").toString() : "");
		user.setUser_name(tran_auth_map.get("name") != null ? tran_auth_map.get("name").toString() : "");
		user.setUser_passw("");
		user.setUser_uid(tran_auth_map.get("userid") != null ? tran_auth_map.get("userid").toString() : "");
		user.setUser_uuid(tran_auth_map.get("userinfoid") != null ? tran_auth_map.get("userinfoid").toString() : "");
		
		session.setAttribute("loginUser", user);
		session.setAttribute("userName", user.getUser_name());
		session.setAttribute("userId", user.getUser_uid());
	}
	
	private List<Map<String, Object>> getAllAuth(String authCode, String authCodeRelRandomKey){
		//获取所有企业员工信息
		String all_auth = getAuth.getAllAuthyd(authCode, authCodeRelRandomKey);
		Map<String, Object> lmss = convertJson.getMapByJson(all_auth);
		return convertJson.getListByJson(lmss.get("employeeInfo").toString());
	}
	
	private List<Map<String, Object>> getAuthnow(String authCode, String authCodeRelRandomKey){
		//获取当前登录工作圈的人员信息
		//这里通过调用GetAuth类中的方法，获取当前登录人的信息
		String auth = "[" + getAuth.getAuthyd(authCode, authCodeRelRandomKey) + "]";
		return convertJson.getListByJson(auth);
	}
	
	private Map<String, Object> isOrginfo(List<Map<String, Object>> auth_list_map, List<Map<String, Object>> auth_map){
		//如果不为空，这判断当前登录人员是否在当前企业员工信息列表中中
		Map<String, Object> save_auth_map = null;
		for(Map<String, Object> map : auth_list_map) {
			if(map.get("userId").equals(auth_map.get(0).get("userId"))){
				save_auth_map = new HashMap<String, Object>();
				save_auth_map = map;
				break;
			}
		}
		
		return save_auth_map;
	}
	
	
	/*
	 * @RequestMapping("/") public String login() { return "index"; }
	 */

	@RequestMapping(value = "initFunTree", method = RequestMethod.GET)
	public @ResponseBody List<FunctionVO> getFunctions(HttpSession session)
			throws Exception {
		ObjectsDetail user = (ObjectsDetail) session.getAttribute("loginUser");
		// 登录人对应角色的functions
		List<FunctionsOfUser> funs = iFuns.getFunctionsByUser(user.getUser_uuid());
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
					.getUser_uuid());
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
