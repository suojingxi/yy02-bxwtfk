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
	
	//通过acceptUserinfoId 删除当前登录人的所有信息 , 这里做假删除更新删除时间和删除状态
	int delSendContentByAcceptId(String acceptUserinfoId) throws Exception ;
	
	//插入多条语句
	int insertContent(List<Map<String, Object>> lmso) throws Exception ;
	
	//通过ID更新该条数据的已读未读状态，更新为已读
	int updateContent(String id) throws Exception;
	
	//获取当前登录人员的信息未读信息总数
	int getTotalWd(String userId) throws Exception;
	
	//根据发送内容ID将此条信息标记为未读
	int bjwdSendContentById(String id) throws Exception;
}
