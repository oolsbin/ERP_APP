package com.example.berp_and.home;

import java.sql.Date;

public class EmpVO {

    private int employee_id, department_id;
    private String company_cd, name, email, phone, admin, job_id,position
            , position_name, employee_pattern, employee_pattern_name, department_name, company_name, pw;
    private Date hire_date;


    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmployee_pattern_name() {
        return employee_pattern_name;
    }
    public void setEmployee_pattern_name(String employee_pattern_name) {
        this.employee_pattern_name = employee_pattern_name;
    }
    public String getDepartment_name() {
        return department_name;
    }
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    public String getJob_id() {
        return job_id;
    }
    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getPosition_name() {
        return position_name;
    }
    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
    public String getEmployee_pattern() {
        return employee_pattern;
    }
    public void setEmployee_pattern(String employee_pattern) {
        this.employee_pattern = employee_pattern;
    }
    public Date getHire_date() {
        return hire_date;
    }
    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }



}

