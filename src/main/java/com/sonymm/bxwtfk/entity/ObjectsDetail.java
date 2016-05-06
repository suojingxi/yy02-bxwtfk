package com.sonymm.bxwtfk.entity;

import java.io.Serializable;

/**
 * @ClassName: ObjectsDetail
 * @Description: 
 * @author sjx
 * @date 2016年04月03日 早晨05:21:19
 */
public class ObjectsDetail implements Serializable{

	private static final long serialVersionUID = 7783661605477314266L;

	private String user_uuid;
	private String user_uid;
	private String user_name;
	private String user_passw;
	private String deptname;
	private String position;
	private String mobile;
	private String email;
	private String isadmin;
	private String statu;
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
	public String getUser_uid() {
		return user_uid;
	}
	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_passw() {
		return user_passw;
	}
	public void setUser_passw(String user_passw) {
		this.user_passw = user_passw;
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