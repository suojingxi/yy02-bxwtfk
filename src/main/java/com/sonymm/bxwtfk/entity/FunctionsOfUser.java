package com.sonymm.bxwtfk.entity;

import java.io.Serializable;

public class FunctionsOfUser implements Serializable {

	private static final long serialVersionUID = 6539204348232217364L;

	private String func_id;
	private String app_id;
	private String parent_id;
	private String parent_code;
	private String func_name;
	private String code_name;
	private String func_description;
	private String sort_id;
	private String resource_level;
	private String classify;
	private String object_type;
	private String hash_role;
	private String create_time;
	private String modify_time;
	
	public String getFunc_id() {
		return func_id;
	}
	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getFunc_description() {
		return func_description;
	}
	public void setFunc_description(String func_description) {
		this.func_description = func_description;
	}
	public String getSort_id() {
		return sort_id;
	}
	public void setSort_id(String sort_id) {
		this.sort_id = sort_id;
	}
	public String getResource_level() {
		return resource_level;
	}
	public void setResource_level(String resource_level) {
		this.resource_level = resource_level;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getHash_role() {
		return hash_role;
	}
	public void setHash_role(String hash_role) {
		this.hash_role = hash_role;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
}
