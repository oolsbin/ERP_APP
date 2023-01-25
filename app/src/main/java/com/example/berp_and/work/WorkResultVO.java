package com.example.berp_and.work;

import java.io.Serializable;
import java.util.Date;

public class WorkResultVO implements Serializable {
	private String company_cd, excess_date, work_code ,work_status, department_name, name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	private int employee_id, department_id;
	private String start_work, end_work;

	private String work_date;

	public String getCompany_cd() {
		return company_cd;
	}
	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}
	public String getExcess_date() {
		return excess_date;
	}
	public void setExcess_date(String excess_date) {
		this.excess_date = excess_date;
	}
	public String getWork_code() {
		return work_code;
	}
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
	public String getWork_status() {
		return work_status;
	}
	public void setWork_status(String work_status) {
		this.work_status = work_status;
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
	public String getStart_work() {
		return start_work;
	}
	public void setStart_work(String start_work) {
		this.start_work = start_work;
	}
	public String getEnd_work() {
		return end_work;
	}
	public void setEnd_work(String end_work) {
		this.end_work = end_work;
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	
	
	
	}
