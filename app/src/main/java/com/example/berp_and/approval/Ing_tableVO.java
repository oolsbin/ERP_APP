package com.example.berp_and.approval;


import java.io.Serializable;
import java.sql.Date;

public class Ing_tableVO implements Serializable {
	
	private int no, ing_no, employee_id, department_id, approver_id;
	private String company_cd, file_name, file_path, document_title, 
					document_content, document_check, document_status, document_submit,
					email, approver, drafter, c_status, c_position, document_comment, name, employee_name;
	private Date document_date, finish_date;



	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getDocument_comment() {
		return document_comment;
	}
	public void setDocument_comment(String document_comment) {
		this.document_comment = document_comment;
	}
	public String getC_status() {
		return c_status;
	}
	public void setC_status(String c_status) {
		this.c_status = c_status;
	}
	public String getC_position() {
		return c_position;
	}
	public void setC_position(String c_position) {
		this.c_position = c_position;
	}
	public String getDocument_status() {
		return document_status;
	}
	public void setDocument_status(String document_status) {
		this.document_status = document_status;
	}
	public String getDrafter() {
		return drafter;
	}
	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	public int getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}



	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIng_no() {
		return ing_no;
	}
	public void setIng_no(int ing_no) {
		this.ing_no = ing_no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}