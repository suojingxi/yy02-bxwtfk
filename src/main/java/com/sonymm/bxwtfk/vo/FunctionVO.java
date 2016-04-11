package com.sonymm.bxwtfk.vo;

public class FunctionVO {
	/*
	 * 功能名称
	 */
	private String name;
	/*
	 * 功能代码
	 */
	private String code;
	/*
	 * 功能对应的URL
	 */
	private String url;

	private String code_name;
	private String func_description;
	private String func_name;
	private String func_id;

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

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
