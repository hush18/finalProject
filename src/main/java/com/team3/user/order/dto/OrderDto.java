package com.team3.user.order.dto;

import java.util.Date;

public class OrderDto {
	private String order_number;
	private String isbn;
	private String order_account;
	private long total_price;
	private long order_status;
	private String id;
	private Date order_date;
	private String receive_name;
	private String receive_phone;
	private String receive_home;
	private String receive_addr;
	private String delivery_msg;
	
	public OrderDto() {}
	
	public OrderDto(String order_number, String isbn, String order_account, long total_price, long order_status,
			String id, Date order_date, String receive_name, String receive_phone, String receive_home,
			String receive_addr, String delivery_msg) {
		this.order_number = order_number;
		this.isbn = isbn;
		this.order_account = order_account;
		this.total_price = total_price;
		this.order_status = order_status;
		this.id = id;
		this.order_date = order_date;
		this.receive_name = receive_name;
		this.receive_phone = receive_phone;
		this.receive_home = receive_home;
		this.receive_addr = receive_addr;
		this.delivery_msg = delivery_msg;
	}

	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getOrder_account() {
		return order_account;
	}
	public void setOrder_account(String order_account) {
		this.order_account = order_account;
	}
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
	public long getOrder_status() {
		return order_status;
	}
	public void setOrder_status(long order_status) {
		this.order_status = order_status;
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
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}
	public String getReceive_home() {
		return receive_home;
	}
	public void setReceive_home(String receive_home) {
		this.receive_home = receive_home;
	}
	public String getReceive_addr() {
		return receive_addr;
	}
	public void setReceive_addr(String receive_addr) {
		this.receive_addr = receive_addr;
	}
	public String getDelivery_msg() {
		return delivery_msg;
	}
	public void setDelivery_msg(String delivery_msg) {
		this.delivery_msg = delivery_msg;
	}

	@Override
	public String toString() {
		return "OrderDto [order_number=" + order_number + ", isbn=" + isbn + ", order_account=" + order_account
				+ ", total_price=" + total_price + ", order_status=" + order_status + ", id=" + id + ", order_date="
				+ order_date + ", receive_name=" + receive_name + ", receive_phone=" + receive_phone + ", receive_home="
				+ receive_home + ", receive_addr=" + receive_addr + ", delivery_msg=" + delivery_msg + "]";
	}
	
	
}
