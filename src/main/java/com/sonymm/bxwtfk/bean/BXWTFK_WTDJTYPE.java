package com.sonymm.bxwtfk.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 业务类型 JavaBean
 * @ClassName: BXWTFK_WTDJTYPE
 * @author suojx
 * @date 2016年04月05日 下午23:00:00
 */
@Entity
@Table(name = "BXWTFK_WTDJTYPE")
@NamedQuery(name = "BXWTFK_WTDJTYPE.findAll", query = "SELECT t FROM BXWTFK_WTDJTYPE t")
public class BXWTFK_WTDJTYPE implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;

	public BXWTFK_WTDJTYPE(){
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
