package com.team3.user.cstList.dto;

import java.util.Date;

public class CstListDto {
	private int counsel_number;
	private String id;
	private String title;
	private String content;
	private String up_category;
	private String down_category;
	private Date write_date;
	private String reply_check;
	private int rnum;
	
	public int getCounsel_number() {
		return counsel_number;
	}
	public void setCounsel_number(int counsel_number) {
		this.counsel_number = counsel_number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUp_category() {
		return up_category;
	}
	public void setUp_category(String up_category) {
		this.up_category = up_category;
	}
	public String getDown_category() {
		return down_category;
	}
	public void setDown_category(String down_category) {
		this.down_category = down_category;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public String getReply_check() {
		return reply_check;
	}
	public void setReply_check(String reply_check) {
		this.reply_check = reply_check;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	@Override
	public String toString() {
		return "CstListDto [counsel_number=" + counsel_number + ", id=" + id + ", title=" + title + ", content="
				+ content + ", up_category=" + up_category + ", down_category=" + down_category + ", write_date="
				+ write_date + ", reply_check=" + reply_check + ", rnum=" + rnum + "]";
	}
	
}
