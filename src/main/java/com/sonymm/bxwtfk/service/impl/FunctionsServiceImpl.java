package com.sonymm.bxwtfk.service.impl;

import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.entity.FunctionsOfUser;
import com.sonymm.bxwtfk.service.IFunctionsService;

@Service
public class FunctionsServiceImpl implements IFunctionsService {

	@Override
	public FunctionsOfUser[] getFunctionsByUser(String user_uid)
			throws Exception {
		FunctionsOfUser fu1 = new FunctionsOfUser();
		FunctionsOfUser fu2 = new FunctionsOfUser();
		fu1.setApp_id("BXWTFK001");
		fu1.setCode_name("BXWTFK001");
		fu1.setFunc_id("A1");
		fu1.setFunc_name("A1");
		FunctionsOfUser[] fus = {fu1};
		return fus;
	}

}
