package com.example.berp_and.notice;

import java.io.Serializable;
import java.util.Date;

public class NoticeListVO implements Serializable {

    private int notice_num, notice_readCnt;
    private String notice_title,notice_writer,notice_content;
    private Date notice_date;

    public int getNotice_num() {
        return notice_num;
    }

    public void setNotice_num(int notice_num) {
        this.notice_num = notice_num;
    }

    public int getNotice_readCnt() {
        return notice_readCnt;
    }

    public void setNotice_readCnt(int notice_readCnt) {
        this.notice_readCnt = notice_readCnt;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_writer() {
        return notice_writer;
    }

    public void setNotice_writer(String notice_writer) {
        this.notice_writer = notice_writer;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public Date getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(Date notice_date) {
        this.notice_date = notice_date;
    }
}
