package com.sonymm.bxwtfk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sonymm.bxwtfk.service.ISendContentService;

public class SendContentServiceImpl implements ISendContentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getSendContent(String acceptUserinfoId)
			throws Exception {
		String sql = "SELECT * FROM BXWTFK_SENDCONTENT s where s.ACCEPT_USERINFO_ID = '" + acceptUserinfoId + "';";
		return jdbcTemplate.queryForList(sql);
	}

}
