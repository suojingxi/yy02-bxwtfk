package com.sonymm.bxwtfk.service;

import com.sonymm.bxwtfk.entity.ObjectsDetail;

public interface IInformationService {
	
	/**
	 * 根据的登陆的账号+认证方式得到用户的信息    例："hpuser1"+"&"+"forms"
	 * @param usercode
	 * @return
	 * @throws Exception
	 */
	public abstract ObjectsDetail[] getUserinfos(String usercode)
			throws Exception;

}
