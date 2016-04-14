package com.sonymm.bxwtfk.service;

import java.util.List;

import com.sonymm.bxwtfk.entity.FunctionsOfUser;

public interface IFunctionsService {
	//获取登录用户对应的功能权限
	List<FunctionsOfUser> getFunctionsByUser(String user_uid) throws Exception ;
}
