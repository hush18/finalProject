package com.team3.user.cst.dto;

public class CstQuestionDto {
	private String title;
	private String name;
	private String write_date;
	private int price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
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
		return "CstQuestionDto [title=" + title + ", name=" + name + ", write_date=" + write_date + ", price=" + price
				+ "]";
	}
}
