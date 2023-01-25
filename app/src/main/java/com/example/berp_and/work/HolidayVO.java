package com.example.berp_and.work;

import java.util.Date;


public class HolidayVO {

	private String employee_id, department_id, company_cd, work_code, work_status , name,dname, hire_year;

	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getHire_year() {
		return hire_year;
	}
	public void setHire_year(String hire_year) {
		this.hire_year = hire_year;
	}
	public String getWork_status() {
		return work_status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWork_status(String work_status) {
		this.work_status = work_status;
	}
	private String  holiday_date;
		private Date	work_date;
	private String  end_holiday, start_holiday;
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getCompany_cd() {
		return company_cd;
	}
	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}
	public String getWork_code() {
		return work_code;
	}
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
	public String getHoliday_date() {
		return holiday_date;
	}
	public void setHoliday_date(String holiday_date) {
		this.holiday_date = holiday_date;
	}
	public Date getWork_date() {
		return work_date;
	}
	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}
	public String getEnd_holiday() {
		return end_holiday;
	}
	public void setEnd_holiday(String end_holiday) {
		this.end_holiday = end_holiday;
	}
	public String getStart_holiday() {
		return start_holiday;
	}
	public void setStart_holiday(String start_holiday) {
		this.start_holiday = start_holiday;
	}

}
