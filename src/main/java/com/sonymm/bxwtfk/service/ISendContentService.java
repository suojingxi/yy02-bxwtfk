package com.sonymm.bxwtfk.service;

import java.util.List;
import java.util.Map;

public interface ISendContentService {

	//通过acceptUserinfoId查询所有记录
	List<Map<String, Object>> getSendContent(String acceptUserinfoId) throws Exception ;
	
	//通过信息记录ID查询一条信息
	List<Map<String, Object>> getSendContentById(String id) throws Exception ;
	
	//通过信息记录ID删除一条信息
	int delSendContentById(String id) throws Exception ;
	
	//通过acceptUserinfoId 删除当前登录人的所有信息
	int delSendContentByAcceptId(String acceptUserinfoId) throws Exception ;
	
	//插入多条语句
	int insertContent(List<Map<String, Object>> lmso) throws Exception ;
}
