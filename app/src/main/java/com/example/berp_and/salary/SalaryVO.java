package com.example.berp_and.salary;

import java.io.Serializable;
import java.sql.Date;

public class SalaryVO implements Serializable {

	private int employee_id, salary, commission_pct, department_id;
	private String company_cd, c_position, c_employee_pattern, name, department_name; 
	private String hire_date;

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getCommission_pct() {
		return commission_pct;
	}
	public void setCommission_pct(int commission_pct) {
		this.commission_pct = commission_pct;
	}
	public String getCompany_cd() {
		return company_cd;
	}
	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}
	public String getC_position() {
		return c_position;
	}
	public void setC_position(String c_position) {
		this.c_position = c_position;
	}
	public String getC_employee_pattern() {
		return c_employee_pattern;
	}
	public void setC_employee_pattern(String c_employee_pattern) {
		this.c_employee_pattern = c_employee_pattern;
	}
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
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	
	
	
	
	
	
}
