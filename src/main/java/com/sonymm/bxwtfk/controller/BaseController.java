package com.sonymm.bxwtfk.controller;

import javax.servlet.http.HttpSession;

import com.sonymm.bxwtfk.entity.ObjectsDetail;

public abstract class BaseController {
	public ObjectsDetail getLoginUser(HttpSession session) {
		ObjectsDetail loginUser = (ObjectsDetail) session
				.getAttribute("loginUser");
		return loginUser;
	}
}
