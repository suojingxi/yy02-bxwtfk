package com.sonymm.bxwtfk.entity;

import java.io.Serializable;

/**
 * @ClassName: ObjectsDetail
 * @Description: 
 * @author sjx
 * @date 2016年04月03日 早晨17:21:19
 */
public class ObjectsDetail implements Serializable{

	private static final long serialVersionUID = 7783661605477314266L;
	
	private String user_uid;
	private String user_name;
	private String user_passw;
	private String dept;
	private String position;
	private String phone;
	private String email;
	private String statu;
	
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
}
