package com.example.berp_and.work;


import java.util.Date;
import java.io.Serializable;

public class CommonCodeVO implements Serializable {

	private String code_title;
	private String code_value;
	private String code_used;
	private String code_name;
	private int code_maker;
	private String code_maker_name;
	private Date code_birth;
	private String code_comment;


	public int getCode_maker() {
		return code_maker;
	}

	public void setCode_maker(int code_maker) {
		this.code_maker = code_maker;
	}

	public String getCode_comment() {
		return code_comment;
	}

	public void setCode_comment(String code_comment) {
		this.code_comment = code_comment;
	}

	public String getCode_maker_name() {
		return code_maker_name;
	}

	public void setCode_maker_name(String code_maker_name) {
		this.code_maker_name = code_maker_name;
	}


	public String getCode_title() {
		return code_title;
	}
	public void setCode_title(String code_title) {
		this.code_title = code_title;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getCode_used() {
		return code_used;
	}
	public void setCode_used(String code_used) {
		this.code_used = code_used;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public Date getCode_birth() {
		return code_birth;
	}
	public void setCode_birth(Date code_birth) {
		this.code_birth = code_birth;
	}
	
	
	
	
}
