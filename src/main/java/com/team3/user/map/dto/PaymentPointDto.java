package com.team3.user.map.dto;
/**
 * 이름 : 김용기
 * 내용 : 결제  Dto 클래스
 */
public class PaymentPointDto {
	private String order_number;	//not null	주문번호
	private String credit_card;		//신용카드 결제시으로 데이터를 받는 필드
	private String phone_payment;	//핸드폰 결제시으로 데이터를 받는 필드
	private String realtime_account_transfer;//실시간으로 계좌이체 결제시 데이터를 받는 필드
	private String direct_deposit;	//직접입금으로 결제시 데이터를 받는 필드
	private long point_history;	//not null 포인트 사용 내역 사용하지 않으면 0 사용하면 0이상
	private long save_point;	//결제시 사용한 포인트를 제외한 금액의 10%
	
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
	public long getSave_point() {
		return save_point;
	}
	public void setSave_point(long save_point) {
		this.save_point = save_point;
	}
	@Override
	public String toString() {
		return "PaymentPointDto [order_number=" + order_number + ", credit_card=" + credit_card + ", phone_payment="
				+ phone_payment + ", realtime_account_transfer=" + realtime_account_transfer + ", direct_deposit="
				+ direct_deposit + ", point_history=" + point_history + ", save_point=" + save_point + "]";
	}
}
