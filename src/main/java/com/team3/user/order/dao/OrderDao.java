package com.team3.user.order.dao;

import java.util.List;

import com.team3.user.order.dto.OrderDto;

public interface OrderDao {
	public int getOrderSearchCount();
	public List<OrderDto> orderSearchList(int startRow, int endRow);
}
