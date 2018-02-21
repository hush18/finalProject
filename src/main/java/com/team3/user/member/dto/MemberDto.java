package com.team3.user.member.dto;

import java.util.Date;

public class MemberDto {
	private String member_number;
	private String id;
	private String password;
	private String name;
	private String email;
	private String diap;
	private int order_count;
	private int point;
	private int block_count;
	private Date last_login;
	private String member_zipcode;
	private String member_address;
	private String member_detail_address;

	public String getMember_number() {
		return member_number;
	}

	public void setMember_number(String member_number) {
		this.member_number = member_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiap() {
		return diap;
	}

	public void setDiap(String diap) {
		this.diap = diap;
	}

	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getBlock_count() {
		return block_count;
	}

	public void setBlock_count(int block_count) {
		this.block_count = block_count;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getMember_zipcode() {
		return member_zipcode;
	}

	public void setMember_zipcode(String member_zipcode) {
		this.member_zipcode = member_zipcode;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getMember_detail_address() {
		return member_detail_address;
	}

	public void setMember_detail_address(String member_detail_address) {
		this.member_detail_address = member_detail_address;
	}

	@Override
	public String toString() {
		return "MemberDto [member_number=" + member_number + ", id=" + id + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", diap=" + diap + ", order_count=" + order_count + ", point=" + point
				+ ", block_count=" + block_count + ", last_login=" + last_login + ", member_zipcode=" + member_zipcode
				+ ", member_address=" + member_address + ", member_detail_address=" + member_detail_address + "]";
	}

}
