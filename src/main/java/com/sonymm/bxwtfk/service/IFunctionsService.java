package com.sonymm.bxwtfk.service;

import com.sonymm.bxwtfk.entity.FunctionsOfUser;

public interface IFunctionsService {
	FunctionsOfUser[] getFunctionsByUser(String user_uid) throws Exception ;
}
