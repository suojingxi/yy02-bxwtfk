package com.sonymm.bxwtfk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;
import com.sonymm.bxwtfk.dao.UserinfoDao;
import com.sonymm.bxwtfk.service.IUserinfoService;
import com.sonymm.bxwtfk.util.Convert;
import com.sonymm.bxwtfk.util.UUIDUtil;

@Service
public class UserinfoServiceImpl implements IUserinfoService {

	@Autowired
	private UserinfoDao userinfoDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	RowMapper<BXWTFK_USERINFO> rw =  ParameterizedBeanPropertyRowMapper.newInstance(BXWTFK_USERINFO.class);
	
	@Override
	public List<Map<String, Object>> getAllUserinfo() throws Exception {
		List<Map<String, Object>> lmso = new ArrayList<Map<String,Object>>();
		List<BXWTFK_USERINFO> lbu = (List<BXWTFK_USERINFO>)userinfoDao.findAll();
		for(BXWTFK_USERINFO user : lbu){
			lmso.add(Convert.bean2map(user));
		}
		return lmso;
	}

	@Override
	public Map<String, Object> getUserinfo(String userid) throws Exception {
		String sql = "SELECT * FROM BXWTFK_USERINFO u where u.USERID = ?";
		BXWTFK_USERINFO user = jdbcTemplate.queryForObject(sql, new Object[]{userid}, rw);
		Map<String, Object> map = new HashMap<String, Object>();
		map = Convert.bean2map(user);
		return map;
	}

	@Override
	public int checkUserinfo(String userid) throws Exception {
		String sql = "SELECT * FROM BXWTFK_USERINFO u where u.USERID = '"+userid+"'";
		try{
			int count = jdbcTemplate.queryForList(sql).size();
			return count;
		}catch (EmptyResultDataAccessException e) {   
            return 0;   
        }  
	}
	
	@Override
	public Map<String, Object> getUserinfoById(String userinfoid) throws Exception {
		String sql = "SELECT * FROM BXWTFK_USERINFO u where u.USERINFOID = ?";
		BXWTFK_USERINFO user = jdbcTemplate.queryForObject(sql, new Object[]{userinfoid}, rw);
		Map<String, Object> map = new HashMap<String, Object>();
		map = Convert.bean2map(user);
		return map;
	}

	@Override
	public Map<String, Object> addUserinfo(Map<String, Object> save_auth_map)
			throws Exception {
		String sql = "INSERT INTO BXWTFK_USERINFO (USERINFOID,USERID,NAME,DEPTNAME,POSITION,MOBILE,EMAIL,ISADMIN,STATU) VALUES (?,?,?,?,?,?,?,?,?)";
		String uuid = UUIDUtil.genUUID();
		Map<String, Object> tran_auth_map = new HashMap<String, Object>();
		int flag = jdbcTemplate.update(sql, new Object[]{uuid,
				save_auth_map.get("userId") != null ? save_auth_map.get("userId").toString() : "",
				save_auth_map.get("name") != null ? save_auth_map.get("name").toString() : "",
				save_auth_map.get("deptName") != null ? save_auth_map.get("deptName").toString() : "",
				save_auth_map.get("position") != null ? save_auth_map.get("position").toString() : "",
				save_auth_map.get("mobile") != null ? save_auth_map.get("mobile").toString() : "",
				save_auth_map.get("email") != null ? save_auth_map.get("email").toString() : "",
				"1","0"
		});
		if(flag > 0){
			tran_auth_map.put("userinfoid", uuid);
			tran_auth_map.put("userid", save_auth_map.get("userId") != null ? save_auth_map.get("userId").toString() : "");
			tran_auth_map.put("name", save_auth_map.get("name") != null ? save_auth_map.get("name").toString() : "");
			tran_auth_map.put("deptname", save_auth_map.get("deptName") != null ? save_auth_map.get("deptName").toString() : "");
			tran_auth_map.put("position", save_auth_map.get("position") != null ? save_auth_map.get("position").toString() : "");
			tran_auth_map.put("mobile", save_auth_map.get("mobile") != null ? save_auth_map.get("mobile").toString() : "");
			tran_auth_map.put("email", save_auth_map.get("email") != null ? save_auth_map.get("email").toString() : "");
			tran_auth_map.put("isadmin", "1");
			tran_auth_map.put("statu", "0");
			return tran_auth_map;
		}else{
			return null;
		}
	}
}
