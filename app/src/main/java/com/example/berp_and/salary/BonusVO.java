package com.example.berp_and.salary;

import java.sql.Date;


public class BonusVO {

	private int employee_id, bonus, department_id;
	private Date bonus_date;

	private String bonus_comment, name, c_position, c_employee_pattern, department_name;


	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public Date getBonus_date() {
		return bonus_date;
	}
	public void setBonus_date(Date bonus_date) {
		this.bonus_date = bonus_date;
	}
	public String getBonus_comment() {
		return bonus_comment;
	}
	public void setBonus_comment(String bonus_comment) {
		this.bonus_comment = bonus_comment;
	}


}
