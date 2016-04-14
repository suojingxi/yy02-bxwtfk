package com.sonymm.bxwtfk.service;

import java.util.List;
import java.util.Map;

public interface ISendContentService {

	//通过acceptUserinfoId查询所有记录
	List<Map<String, Object>> getSendContent(String acceptUserinfoId) throws Exception ;
}
