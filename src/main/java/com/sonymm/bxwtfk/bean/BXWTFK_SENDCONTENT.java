package com.sonymm.bxwtfk.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 业务类型 JavaBean
 * @ClassName: BXWTFK_SENDCONTENT
 * @author suojx
 * @date 2016年04月05日 下午23:00:00
 */
@Entity
@Table(name = "BXWTFK_SENDCONTENT")
@NamedQuery(name = "BXWTFK_SENDCONTENT.findAll", query = "SELECT t FROM BXWTFK_SENDCONTENT t")
public class BXWTFK_SENDCONTENT implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "ACCEPT_USERINFO_ID")
	private String acceptUserinfoId;
	
	@Column(name = "SEND_USERINFO_ID")
	private String sendUserinfoId;
	
	@Column(name = "CONTENT_THEMES")
	private String contentThemes;
	
	@Column(name = "CONTENT")
	private String content;

	@Column(name = "SEND_TIME")
	private String sendTime;
	
	@Column(name = "DELETE_TIME")
	private String deleteTime;
	
	@Column(name = "STATU")
	private String state;
	
	public BXWTFK_SENDCONTENT(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcceptUserinfoId() {
		return acceptUserinfoId;
	}

	public void setAcceptUserinfoId(String acceptUserinfoId) {
		this.acceptUserinfoId = acceptUserinfoId;
	}

	public String getSendUserinfoId() {
		return sendUserinfoId;
	}

	public void setSendUserinfoId(String sendUserinfoId) {
		this.sendUserinfoId = sendUserinfoId;
	}

	public String getContentThemes() {
		return contentThemes;
	}

	public void setContentThemes(String contentThemes) {
		this.contentThemes = contentThemes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
