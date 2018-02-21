package com.team3.user.interest.dto;

import java.util.Date;

public class InterestDto {
	private String id;
	private String isbn;
	private Date cart_day;
	private int state;
//	-------------------------
	private String name;
//	-------------------------
	private String title;
	private String publisher;
	private String image_path;
	
	
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "InterestDto [id=" + id + ", isbn=" + isbn + ", cart_day=" + cart_day + ", state=" + state + ", name="
				+ name + ", title=" + title + ", publisher=" + publisher + ", image_path=" + image_path + "]";
	}
	
	
	
	
}
