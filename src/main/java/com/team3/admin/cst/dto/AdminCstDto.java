package com.team3.admin.cst.dto;

import java.util.Date;

public class AdminCstDto {
	private int counsel_number;		// 고유번호
	private String id;				// 사용자 id
	private String title;			// 제목
	private String counsel_product;	// 문의상품명
	private String order_number;	// 주문상품명
	private String content;			// 내용
	private String email;			// email
	private String up_category;		// 상위유형
	private String down_category;	// 하위유형
	private Date write_date;		// 등록일
	private String reply_check;		// 답변여부
	private int rnum;				// 가상번호
	private String admin_content;	// 관리자 답변 내용
	private Date admin_write_date;	// 관리자 답변 등록일
	
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
	public String getCounsel_product() {
		return counsel_product;
	}
	public void setCounsel_product(String counsel_product) {
		this.counsel_product = counsel_product;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAdmin_content() {
		return admin_content;
	}
	public void setAdmin_content(String admin_content) {
		this.admin_content = admin_content;
	}
	public Date getAdmin_write_date() {
		return admin_write_date;
	}
	public void setAdmin_write_date(Date admin_write_date) {
		this.admin_write_date = admin_write_date;
	}
	
	@Override
	public String toString() {
		return "AdminCstDto [counsel_number=" + counsel_number + ", id=" + id + ", title=" + title
				+ ", counsel_product=" + counsel_product + ", order_number=" + order_number + ", content=" + content
				+ ", email=" + email + ", up_category=" + up_category + ", down_category=" + down_category
				+ ", write_date=" + write_date + ", reply_check=" + reply_check + ", rnum=" + rnum + ", admin_content="
				+ admin_content + ", admin_write_date=" + admin_write_date + "]";
	}
}
