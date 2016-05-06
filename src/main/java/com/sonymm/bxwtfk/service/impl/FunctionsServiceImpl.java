package com.sonymm.bxwtfk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.entity.FunctionsOfUser;
import com.sonymm.bxwtfk.service.IFunctionsService;
import com.sonymm.bxwtfk.service.IUserinfoService;

@Service
public class FunctionsServiceImpl implements IFunctionsService {
	
	@Autowired
	private IUserinfoService iUserinfoService;

	@Override
	public List<FunctionsOfUser> getFunctionsByUser(String user_uuid)
			throws Exception {
		//这里获取登录人员的功能列表
		//1.判断登录人的是不是管理员,如果不是管理员则获取功能url中公共功能信息，如果是管理员则获取所有
		List<FunctionsOfUser> fus = new ArrayList<FunctionsOfUser>();
		Map<String, Object> map = iUserinfoService.getUserinfoById(user_uuid);
		FunctionsOfUser fu = new FunctionsOfUser();
		if(map.get("isadmin").toString().equals("0")){//登录用户是管理员
			fu.setApp_id("COM_BXWTFK001");
			fu.setCode_name("COM_BXWTFK001");
			fu.setFunc_id("A1");
			fu.setFunc_name("A1");
			fus.add(fu);
			fu = new FunctionsOfUser();
			fu.setApp_id("GL_BXWTFK002");
			fu.setCode_name("GL_BXWTFK002");
			fu.setFunc_id("A2");
			fu.setFunc_name("A2");
			fus.add(fu);
		}else{//登录用户不是管理员
			fu.setApp_id("COM_BXWTFK001");
			fu.setCode_name("COM_BXWTFK001");
			fu.setFunc_id("A1");
			fu.setFunc_name("A1");
			fus.add(fu);
		}
		return fus;
	}

}
