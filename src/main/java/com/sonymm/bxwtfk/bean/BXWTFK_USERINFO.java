package com.sonymm.bxwtfk.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 业务类型 JavaBean
 * @ClassName: BXWTFK_USERINFO
 * @author suojx
 * @date 2016年04月05日 下午23:00:00
 */
@Entity
@Table(name = "BXWTFK_USERINFO")
@NamedQuery(name = "BXWTFK_USERINFO.findAll", query = "SELECT t FROM BXWTFK_USERINFO t")
public class BXWTFK_USERINFO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USERINFOID")
	private String userinfoid;
	
	@Column(name = "USERID")
	private String userid;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DEPTNAME")
	private String deptname;
	
	@Column(name = "POSITION")
	private String position;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ISADMIN")
	private String isadmin;
	
	@Column(name = "STATU")
	private String statu;
	
	public BXWTFK_USERINFO(){
		
	}

	public String getUserinfoid() {
		return userinfoid;
	}

	public void setUserinfoid(String userinfoid) {
		this.userinfoid = userinfoid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
}
