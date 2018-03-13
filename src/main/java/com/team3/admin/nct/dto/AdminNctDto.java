package com.team3.admin.nct.dto;

import java.util.Date;

public class AdminNctDto {
	private int notice_number;	// 고유번호
	private String title;		// 제목
	private String content;		// 내용
	private Date write_date;	// 등록일
	private int rnum;			// 가상번호
	
	public int getNotice_number() {
		return notice_number;
	}
	public void setNotice_number(int notice_number) {
		this.notice_number = notice_number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	@Override
	public String toString() {
		return "AdminNctDto [notice_number=" + notice_number + ", title=" + title + ", content=" + content
				+ ", write_date=" + write_date + ", rnum=" + rnum + "]";
	}
	
}
