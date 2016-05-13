package com.sonymm.bxwtfk.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.dao.SendContentDao;
import com.sonymm.bxwtfk.service.ISendContentService;
import com.sonymm.bxwtfk.service.pagination.IPaginationService;

@Service
public class SendContentServiceImpl implements ISendContentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IPaginationService ipaginationservice;
	
	@Override
	public List<Map<String, Object>> getSendContent(String acceptUserinfoId)
			throws Exception {
		String sql = "SELECT * FROM BXWTFK_SENDCONTENT s where 1 = 1 and s.STATU = '0' and s.ACCEPT_USERINFO_ID = '" + acceptUserinfoId + "' ORDER BY s.SEND_TIME DESC";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getSendContentById(String id)
			throws Exception {
		String sql = "SELECT * FROM BXWTFK_SENDCONTENT s where 1 = 1 and s.STATU = '0' and s.ID = '" + id + "'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int delSendContentById(String id) throws Exception {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return jdbcTemplate.update("UPDATE BXWTFK_SENDCONTENT s SET s.DELETE_TIME = ?, s.STATU = '1' WHERE s.ID = ?", new Object[]{format.format(date), id});
//		return jdbcTemplate.update("DELETE FROM BXWTFK_SENDCONTENT s where 1 = 1 and s.ID = ?",new Object[]{ id });
	}
	
	@Override
	public int bjwdSendContentById(String id) throws Exception {
		return jdbcTemplate.update("UPDATE BXWTFK_SENDCONTENT s SET s.DU_STATU = '0' WHERE s.ID = ?", new Object[]{id});
	}

	@Override
	public int delSendContentByAcceptId(String acceptUserinfoId)
			throws Exception {
		return jdbcTemplate.update("DELETE FROM BXWTFK_SENDCONTENT s where 1 = 1 and s.ACCEPT_USERINFO_ID = ?",new Object[]{ acceptUserinfoId });
	}

	@Override
	public int insertContent(List<Map<String, Object>> lmso) throws Exception {
		final List<Map<String, Object>> lm = lmso;
		String sql = "INSERT INTO BXWTFK_SENDCONTENT (ID,ACCEPT_USERINFO_ID,SEND_USERINFO_ID,CONTENT_THEMES,CONTENT,SEND_TIME,DELETE_TIME,DU_STATU,STATU) VALUES (?,?,?,?,?,?,?,?,?)";
		int[] count = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, lm.get(i).get("id")!=null?lm.get(i).get("id").toString():"");
				ps.setString(2, lm.get(i).get("acceptUserinfoId")!=null?lm.get(i).get("acceptUserinfoId").toString():"");
				ps.setString(3, lm.get(i).get("sendUserinfoId")!=null?lm.get(i).get("sendUserinfoId").toString():"");
				ps.setString(4, lm.get(i).get("contentThemes")!=null?lm.get(i).get("contentThemes").toString():"");
				ps.setString(5, lm.get(i).get("content")!=null?lm.get(i).get("content").toString():"");
				ps.setString(6, lm.get(i).get("sendTime")!=null?lm.get(i).get("sendTime").toString():"");
				ps.setString(7, lm.get(i).get("deleteTime")!=null?lm.get(i).get("deleteTime").toString():"");
				ps.setString(8, lm.get(i).get("duStatu")!=null?lm.get(i).get("duStatu").toString():"");
				ps.setString(9, lm.get(i).get("statu")!=null?lm.get(i).get("statu").toString():"");
			}
			
			@Override
			public int getBatchSize() {
				return lm.size();
			}
		});
		return count.length;
	}

	@Override
	public int updateContent(String id) throws Exception {
		String sql = "update bxwtfk_sendcontent w set w.du_statu='1' where w.id='"+id+"'";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getTotalWd(String userId) throws Exception {
		String sql = "select * from bxwtfk_sendcontent t where 1=1 and t.du_statu = '0' and t.statu = '0' and t.accept_userinfo_id = '"+userId+"'";
		return ipaginationservice.getDataCount(sql);
	}

}
