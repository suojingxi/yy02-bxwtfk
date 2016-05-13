package com.sonymm.bxwtfk.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.sonymm.bxwtfk.common.GetAuth;
import com.sonymm.bxwtfk.common.SendAuthMessage;
import com.sonymm.bxwtfk.service.ISendContentService;
import com.sonymm.bxwtfk.service.IWtdjTypeService;
import com.sonymm.bxwtfk.util.ConvertJson;
import com.sonymm.bxwtfk.util.UUIDUtil;

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
	
	@Autowired
	ISendContentService iSendContentService;
	
	@Autowired
	SendAuthMessage sendAuthMessage;
	
	@Autowired
	IWtdjTypeService iWtdjTypeService;
	
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
	
	@RequestMapping(value = "/myManager/getSerchUser", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSerchUser(
    		@RequestParam(value="serchContent") String serchContent,
            ServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> lui = new ArrayList<Map<String, Object>>();
		//获取工作圈中企业所有员工信息
		String all_auth = getAuth.getAllAuth();
		Map<String, Object> lmss = convertJson.getMapByJson(all_auth);
		lui = convertJson.getListByJson(lmss.get("employeeInfo").toString());
		String type = "";
		if((!serchContent.equals(""))&&serchContent!=null){
			String ind = serchContent.substring(0, 1);
			if(Character.isDigit(ind.charAt(0))){
				type = "mobile";
			}else{
				type = "name";
			}
			for(int i=0; i<lui.size(); i++){
				if(lui.get(i).get(type) == null || lui.get(i).get(type).equals("")){
					lui.remove(i);
					i--;
				}else if(lui.get(i).get(type).toString().indexOf(serchContent.trim())<0){
					lui.remove(i);
					i--;
				}
			}
		}
		map.put("ALLUSERINFO", lui);
		return map;
	}
	
	@RequestMapping(value = "/myManager/sendManager", method = RequestMethod.POST)
    public @ResponseBody int sendManager(
    		@RequestParam(value="ids") String ids,//传过来的是userId串
    		@RequestParam(value="problems") String problems,
    		@RequestParam(value="content") String content,
            ServletRequest request, HttpSession session) throws Exception {
		String userId = session.getAttribute("userId").toString();
		List<Map<String, Object>> lmso = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		//解析传过来的参数
		String[] userIds = ids.split(",");
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = format.format(date);
//		long longTime = date.getTime();
		long longTime = System.currentTimeMillis();
		Map<String, String> infojx = new HashMap<String, String>();
		List<String> uuids = new ArrayList<String>();
		for(String id : userIds){
			String uuid = UUIDUtil.genUUID();
			uuids.add(uuid);
			map = new HashMap<String, Object>();
			map.put("id", uuid);
			//TODO
			map.put("acceptUserinfoId", id);
			map.put("sendUserinfoId", userId);
			map.put("contentThemes", problems);
			map.put("content", content);
			map.put("sendTime", nowTime);
			map.put("deleteTime", "");
			map.put("duStatu", "0");
			map.put("statu", "0");
			infojx.put(id, uuid);
			lmso.add(map);
		}
		//保存信息
		int count = iSendContentService.insertContent(lmso);
		if(count > 0){
			//发送工作圈，如果发送失败，则重新发送，知道发送成功
			//TODO 需要传递的参数为ids，和转换过的problems
			//ids:对应工作圈的接受者用户to
			//userId对应工作圈的发送人userId
			//problems对应工作圈的摘要alert
			//
			//处理参数problems将编码转换成对应文字
			String typeNames = iWtdjTypeService.getNamesByCodes(problems);
			String returnM = sendAuthMessage.sendMessageTo(ids, userId, typeNames, longTime+"", infojx);
			//解析returnM这个json串，获取到"result":true等信息后就是返回成功，否则发送失败
			returnM = "[" + returnM + "]";
			List<Map<String, Object>> result = convertJson.getListByJson(returnM);
			boolean resultFlag = false;
			for(Map<String, Object> mso : result){
				if(mso.containsKey("result")){
					if(mso.get("result").toString().equals("true")){
						resultFlag = true;
						break;
					}
				}
			}
			if(!resultFlag){
				count = -1;
				//删除已经插入的信息
				for(String strUuid : uuids){
					iSendContentService.delSendContentById(strUuid);
				}
			}
		}
		return count;
	}
}
