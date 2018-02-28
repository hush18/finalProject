package com.team3.user.order.dto;

import java.util.Date;

public class OrderDto {
	private String order_number;
	private String goods;
	private String order_account;
	private long total_price;
	private int order_status;
	private String id;
	private Date order_date;
	private String receive_name;
	private String receive_phone;
	private String receive_home;
	private String receive_addr;
	private String delivery_msg;
	private String isbn;
	private long member_number;
	private Date maybe_date;
	private String goods_name;
	private long goods_account;
	private String status;
	private String title;
	
	public OrderDto() {}
	
	public OrderDto(String order_number, String goods, String order_account, long total_price, int order_status,
			String id, Date order_date, String receive_name, String receive_phone, String receive_home,
			String receive_addr, String delivery_msg, String isbn, long member_number, Date maybe_date, String goods_name, long goods_account, String status, String title) {
		this.order_number = order_number;
		this.goods = goods;
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
		this.isbn = isbn;
		this.member_number = member_number;
		this.maybe_date = maybe_date;
		this.goods_name = goods_name;
		this.goods_account = goods_account;
		this.status = status;
		this.title = title;
	}

	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
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
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public long getMember_number() {
		return member_number;
	}
	public void setMember_number(long member_number) {
		this.member_number = member_number;
	}
	public Date getMaybe_date() {
		return maybe_date;
	}
	public void setMaybe_date(Date maybe_date) {
		this.maybe_date = maybe_date;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public long getGoods_account() {
		return goods_account;
	}
	public void setGoods_account(long goods_account) {
		this.goods_account = goods_account;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "OrderDto [order_number=" + order_number + ", goods=" + goods + ", order_account=" + order_account
				+ ", total_price=" + total_price + ", order_status=" + order_status + ", id=" + id + ", order_date="
				+ order_date + ", receive_name=" + receive_name + ", receive_phone=" + receive_phone + ", receive_home="
				+ receive_home + ", receive_addr=" + receive_addr + ", delivery_msg=" + delivery_msg + ", isbn=" + isbn
				+ ", member_number=" + member_number + ", maybe_date=" + maybe_date + ", goods_name=" + goods_name
				+ ", goods_account=" + goods_account + ", status=" + status + ", title=" + title + "]";
	}

}
