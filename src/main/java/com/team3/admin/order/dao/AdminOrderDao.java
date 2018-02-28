package com.team3.admin.order.dao;

import java.util.List;

import com.team3.user.order.dto.OrderDto;

public interface AdminOrderDao {
	public int getAdminOrderCount();
	public List<OrderDto> adminOrderList();
}
