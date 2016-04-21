package com.sonymm.bxwtfk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.service.IWtdjTypeService;

@Service
public class WtdjTypeServiceImpl implements IWtdjTypeService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getNameByCode(String code) throws Exception {
		String sql = "SELECT * FROM BXWTFK_WTDJTYPE w WHERE w.CODE = '"+code+"'";
		return jdbcTemplate.queryForList(sql).get(0).get("NAME").toString();
	}

	@Override
	public String getNamesByCodes(String codes) throws Exception {
		StringBuffer names = new StringBuffer();
		String[] codeArr = codes.split(",");
		for(String str : codeArr){
			names.append(getNameByCode(str.substring(0, 1))+",");
		}
		return names.substring(0,names.length()-1);
	}

}
