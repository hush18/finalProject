package com.team3.admin.map.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class MapDto {
	private String store_name;
	private String lat;
	private String lng;
	private String phone_fax;
	private String address;
	private String business_hours;
	private String directions;
	private String store_explanation;
	private String img_path;
	
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getPhone_fax() {
		return phone_fax;
	}
	public void setPhone_fax(String phone_fax) {
		this.phone_fax = phone_fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusiness_hours() {
		return business_hours;
	}
	public void setBusiness_hours(String business_hours) {
		this.business_hours = business_hours;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getStore_explanation() {
		return store_explanation;
	}
	public void setStore_explanation(String store_explanation) {
		this.store_explanation = store_explanation;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	@Override
	public String toString() {
		return "MapDto [store_name=" + store_name + ", lat=" + lat + ", lng=" + lng + ", phone_fax=" + phone_fax
				+ ", address=" + address + ", business_hours=" + business_hours + ", directions=" + directions
				+ ", store_explanation=" + store_explanation + ", img_path=" + img_path + "]";
	}
}
