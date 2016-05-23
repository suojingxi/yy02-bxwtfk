package com.sonymm.bxwtfk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.service.IQueryStaSendContentService;
import com.sonymm.bxwtfk.service.pagination.IPaginationService;

@Service
public class QueryStaSendContentServiceImpl implements IQueryStaSendContentService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IPaginationService ipaginationservice;
	
	@Override
	public List<Map<String, String>> staSendContent(
			Map<String, Object> waitParams) throws Exception {
		//定义返回值类型
		List<Map<String, String>> returnMap = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		//获取所有问题类型组合代码
		List<String> ls = getAllWtTypeCode();
		//解析传过来的参数
		String userId = waitParams.get("userId")==null?"":waitParams.get("userId").toString();
		String proType = waitParams.get("proType")==null?"":waitParams.get("proType").toString();
		//拼sql查询所有符合条件的数据
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM BXWTFK_SENDCONTENT s where 1 = 1");
		if(!userId.equals("")){
			sb.append("and s.ACCEPT_USERINFO_ID = '"+userId+"'");
		}
		if(!proType.equals("")){
			sb.append("and s.CONTENT_THEMES like '%"+proType+"%'");
		}
		
		//解析查询出来的数据进行统计
		List<Map<String, Object>> lmso = jdbcTemplate.queryForList(sb.toString());
		for(int i=0; i<lmso.size(); i++){
			map = setReturn(ls);
			String themes = lmso.get(i).get("CONTENT_THEMES").toString();
			String[] strs = themes.split(",");
			for(String str : strs){
				String val = map.get(str);
				map.put(str, (Integer.parseInt(val)+1)+"");
			}
			returnMap.add(map);
		}
		return returnMap;
	}
	
	//获取所有问题类型组合代码
	private List<String> getAllWtTypeCode(){
		List<String> ls = new ArrayList<String>();
		String sql = "SELECT * FROM BXWTFK_WTDJTYPECONTENT w where 1=1";
		List<Map<String, Object>> lmso = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map : lmso){
			ls.add(map.get("WTDJTYPE_CODE").toString()+map.get("CODE").toString());
		}
		return ls;
	}
	
	//定义返回值类型
	private Map<String, String> setReturn(List<String> ls){
		//定义返回值类型
		Map<String, String> returnMap = new HashMap<String, String>();
		for(String str : ls){
			returnMap.put(str, "0");
		}
		return returnMap;
	}

}
