package com.team3.user.cst.dto;

import java.util.Date;

public class CstQuestionDto {
	private String title;
	private String writer_number;
	private Date write_date;
	private int price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter_number() {
		return writer_number;
	}
	public void setWriter_number(String writer_number) {
		this.writer_number = writer_number;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CstQuestionDto [title=" + title + ", writer_number=" + writer_number + ", write_date=" + write_date
				+ ", price=" + price + "]";
	}
	
}
