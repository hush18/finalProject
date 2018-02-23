package com.team3.admin.sales.dto;

import java.util.Date;

public class SalesDto {
	private Date sales_day;
	private long sales_count;
	private long sales_total;
	private long sales_cash;
	private long sales_point;
	
	public Date getSales_day() {
		return sales_day;
	}

	public void setSales_day(Date sales_day) {
		this.sales_day = sales_day;
	}

	public long getSales_count() {
		return sales_count;
	}

	public void setSales_count(long sales_count) {
		this.sales_count = sales_count;
	}

	public long getSales_total() {
		return sales_total;
	}

	public void setSales_total(long sales_total) {
		this.sales_total = sales_total;
	}

	public long getSales_cash() {
		return sales_cash;
	}

	public void setSales_cash(long sales_cash) {
		this.sales_cash = sales_cash;
	}

	public long getSales_point() {
		return sales_point;
	}

	public void setSales_point(long sales_point) {
		this.sales_point = sales_point;
	}

	@Override
	public String toString() {
		return "SalesDto [sales_day=" + sales_day + ", sales_count=" + sales_count + ", sales_total=" + sales_total
				+ ", sales_cash=" + sales_cash + ", sales_point=" + sales_point + "]";
	}
	
}
