package com.team3.user.member.dto;

public class MemberAddressDto {
	private String id;
	private String member_zipcode;
	private String member_address;
	private String member_detail_address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "MemberAddressDto [id=" + id + ", member_zipcode=" + member_zipcode + ", member_address="
				+ member_address + ", member_detail_address=" + member_detail_address + "]";
	}
}
