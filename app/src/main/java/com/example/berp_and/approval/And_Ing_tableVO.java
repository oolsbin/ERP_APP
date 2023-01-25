package com.example.berp_and.approval;


import java.io.Serializable;
import java.sql.Date;

public class And_Ing_tableVO implements Serializable{
	private int employee_id, department_id, approver_id;
	private Date document_date,finish_date ;
	private String company_cd, file_name, file_path, document_title,

			document_content,  document_check, document_submit, ing_no,

			document_comment, emp_position_name, app_position_name, emp_position, app_position,
			check_name, company_name, emp_dpmt_name,app_dpmt_name , emp_name, app_name;


	public String getEmp_position_name() {
		return emp_position_name;

	}
	public void setEmp_position_name(String emp_position_name) {
		this.emp_position_name = emp_position_name;
	}
	public String getApp_position_name() {
		return app_position_name;
	}
	public void setApp_position_name(String app_position_name) {
		this.app_position_name = app_position_name;
	}
	public String getEmp_position() {
		return emp_position;
	}
	public void setEmp_position(String emp_position) {
		this.emp_position = emp_position;
	}
	public String getApp_position() {
		return app_position;
	}

	public void setApp_position(String app_position) {
		this.app_position = app_position;

	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
	}
	public Date getDocument_date() {
		return document_date;
	}
	public void setDocument_date(Date document_date) {
		this.document_date = document_date;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	public String getCompany_cd() {
		return company_cd;
	}
	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getDocument_title() {
		return document_title;
	}
	public void setDocument_title(String document_title) {
		this.document_title = document_title;
	}
	public String getDocument_content() {
		return document_content;
	}
	public void setDocument_content(String document_content) {
		this.document_content = document_content;
	}
	public String getDocument_check() {
		return document_check;
	}
	public void setDocument_check(String document_check) {
		this.document_check = document_check;
	}
	public String getDocument_submit() {
		return document_submit;
	}
	public void setDocument_submit(String document_submit) {
		this.document_submit = document_submit;
	}

	public String getIng_no() {
		return ing_no;
	}
	public void setIng_no(String ing_no) {
		this.ing_no = ing_no;

	}
	public String getDocument_comment() {
		return document_comment;
	}
	public void setDocument_comment(String document_comment) {
		this.document_comment = document_comment;
	}
	public String getCheck_name() {
		return check_name;
	}
	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getEmp_dpmt_name() {
		return emp_dpmt_name;
	}
	public void setEmp_dpmt_name(String emp_dpmt_name) {
		this.emp_dpmt_name = emp_dpmt_name;
	}
	public String getApp_dpmt_name() {
		return app_dpmt_name;
	}
	public void setApp_dpmt_name(String app_dpmt_name) {
		this.app_dpmt_name = app_dpmt_name;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
}

