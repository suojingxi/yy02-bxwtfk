package com.sonymm.bxwtfk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.service.IWtdjTypeContentService;

@Service
public class WtdjTypeContentServiceImpl implements IWtdjTypeContentService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public String getContentNameByCode(String code) throws Exception {
		String sql = "SELECT * FROM BXWTFK_WTDJTYPECONTENT w WHERE w.WTDJTYPE_CODE = '" + code.substring(0, 1) + "' and w.CODE = '" + code.substring(1, code.length()) + "'";
		return jdbcTemplate.queryForList(sql).get(0).get("NAME").toString();
	}

}
