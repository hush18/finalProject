package com.team3.user.cst.dto;

public class CstQuestionDto {
	private String title;		// 상품명
	private String name;		// 저자
	private String write_date;	// 상품 등록일
	private int price;			// 가격
	
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
