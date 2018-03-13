package com.team3.user.cst.dto;

import java.util.Date;

public class CstOrderDto {
	private String goods;			// 주문 상품 번호
	private String order_account;	// 주문 상품 개수
	private long price;				// 가격
	private String id;				// 사용자 id
	private Date order_date;		// 구입한 날짜
	private String title;			// 상품명
	private String order_number;	// 고유번호
	
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getOrder_account() {
		return order_account;
	}
	public void setOrder_account(String order_account) {
		this.order_account = order_account;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	
	@Override
	public String toString() {
		return "CstOrderDto [goods=" + goods + ", order_account=" + order_account + ", price=" + price + ", id=" + id
				+ ", order_date=" + order_date + ", title=" + title + ", order_number=" + order_number + "]";
	}
}
