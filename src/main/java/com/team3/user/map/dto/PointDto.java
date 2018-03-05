package com.team3.user.map.dto;

import java.sql.Timestamp;
import java.util.Date;

public class PointDto {
	private String id;
	private String order_number;
	private String title;
	private long total_price;
	private Timestamp order_date;
	private long point_history;
	private long save_point;
	private long point;
	private String str_order_date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
	public long getPoint_history() {
		return point_history;
	}
	public void setPoint_history(long point_history) {
		this.point_history = point_history;
	}
	public long getSave_point() {
		return save_point;
	}
	public void setSave_point(long save_point) {
		this.save_point = save_point;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public String getStr_order_date() {
		return str_order_date;
	}
	public void setStr_order_date(String str_order_date) {
		this.str_order_date = str_order_date;
	}
	@Override
	public String toString() {
		return "PointDto [id=" + id + ", order_number=" + order_number + ", title=" + title + ", total_price="
				+ total_price + ", order_date=" + order_date + ", point_history=" + point_history + ", save_point="
				+ save_point + ", point=" + point + "]";
	}
	
}
