package com.sonymm.bxwtfk.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ConvertJson {
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMapByJson(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);   
		Map<String, Object> map = JSONObject.fromObject(jsonObject);
		return map;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Map<String, Object>> getListByJson(String json){
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<Map<String, Object>> list= JSONArray.toList(jsonArray, Map.class);
		return list;
	}

}
