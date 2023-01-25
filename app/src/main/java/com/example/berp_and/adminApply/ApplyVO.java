package com.example.berp_and.adminApply;
import java.io.Serializable;
import java.sql.Date;

public class ApplyVO implements Serializable {
    private String recruit_num, apply_email, apply_name, file_name, file_path, apply_check, apply_phone, apply_pw
            ,company, career, salary, recruit_content, recruit_title, employee_pattern, recruit_end, recruit_start, apply_pic_name, apply_pic_path;
    private int apply_num;
    private String company_name, career_name, employee_pattern_name;
    private Date apply_date;


    public String getApply_pic_name() {
        return apply_pic_name;
    }

    public void setApply_pic_name(String apply_pic_name) {
        this.apply_pic_name = apply_pic_name;
    }

    public String getApply_pic_path() {
        return apply_pic_path;
    }

    public void setApply_pic_path(String apply_pic_path) {
        this.apply_pic_path = apply_pic_path;
    }

    public String getRecruit_num() {
        return recruit_num;
    }

    public void setRecruit_num(String recruit_num) {
        this.recruit_num = recruit_num;
    }

    public String getApply_email() {
        return apply_email;
    }

    public void setApply_email(String apply_email) {
        this.apply_email = apply_email;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
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

    public String getApply_check() {
        return apply_check;
    }

    public void setApply_check(String apply_check) {
        this.apply_check = apply_check;
    }

    public String getApply_phone() {
        return apply_phone;
    }

    public void setApply_phone(String apply_phone) {
        this.apply_phone = apply_phone;
    }

    public String getApply_pw() {
        return apply_pw;
    }

    public void setApply_pw(String apply_pw) {
        this.apply_pw = apply_pw;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRecruit_content() {
        return recruit_content;
    }

    public void setRecruit_content(String recruit_content) {
        this.recruit_content = recruit_content;
    }

    public String getRecruit_title() {
        return recruit_title;
    }

    public void setRecruit_title(String recruit_title) {
        this.recruit_title = recruit_title;
    }

    public String getEmployee_pattern() {
        return employee_pattern;
    }

    public void setEmployee_pattern(String employee_pattern) {
        this.employee_pattern = employee_pattern;
    }

    public String getRecruit_end() {
        return recruit_end;
    }

    public void setRecruit_end(String recruit_end) {
        this.recruit_end = recruit_end;
    }

    public String getRecruit_start() {
        return recruit_start;
    }

    public void setRecruit_start(String recruit_start) {
        this.recruit_start = recruit_start;
    }

    public int getApply_num() {
        return apply_num;
    }

    public void setApply_num(int apply_num) {
        this.apply_num = apply_num;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCareer_name() {
        return career_name;
    }

    public void setCareer_name(String career_name) {
        this.career_name = career_name;
    }

    public String getEmployee_pattern_name() {
        return employee_pattern_name;
    }

    public void setEmployee_pattern_name(String employee_pattern_name) {
        this.employee_pattern_name = employee_pattern_name;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }
}