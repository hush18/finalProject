package com.team3.user.order.dto;

import java.util.Date;

public class CartDto {
	private String id;
	private String isbn;
	private int cart_amount;
	private Date cart_day;
	private int state;
	private String title;
	private String image_path;
	private String publisher;
	private int price;
	private String name;
	private int point;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public long getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	public Date getCart_day() {
		return cart_day;
	}
	public void setCart_day(Date cart_day) {
		this.cart_day = cart_day;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "CartDto [id=" + id + ", isbn=" + isbn + ", cart_amount=" + cart_amount + ", cart_day=" + cart_day
				+ ", state=" + state + ", title=" + title + ", image_path=" + image_path + ", publisher=" + publisher
				+ ", price=" + price + ", name=" + name + ", point=" + point + "]";
	}
	
	
}
