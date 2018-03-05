package com.team3.user.map.dto;

public class PaymentDto {
	private String order_number;	//not null	
	private String credit_card;
	private String phone_payment;
	private String realtime_account_transfer;
	private String direct_deposit;
	private long point_history;	//not null
	
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getCredit_card() {
		return credit_card;
	}
	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}
	public String getPhone_payment() {
		return phone_payment;
	}
	public void setPhone_payment(String phone_payment) {
		this.phone_payment = phone_payment;
	}
	public String getRealtime_account_transfer() {
		return realtime_account_transfer;
	}
	public void setRealtime_account_transfer(String realtime_account_transfer) {
		this.realtime_account_transfer = realtime_account_transfer;
	}
	public String getDirect_deposit() {
		return direct_deposit;
	}
	public void setDirect_deposit(String direct_deposit) {
		this.direct_deposit = direct_deposit;
	}
	public long getPoint_history() {
		return point_history;
	}
	public void setPoint_history(long point_history) {
		this.point_history = point_history;
	}
	@Override
	public String toString() {
		return "PaymentDto [order_number=" + order_number + ", credit_card=" + credit_card + ", phone_payment="
				+ phone_payment + ", realtime_account_transfer=" + realtime_account_transfer + ", direct_deposit="
				+ direct_deposit + ", point_history=" + point_history + "]";
	}
}
