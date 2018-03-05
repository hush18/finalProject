package com.team3.admin.order.dao;

import java.util.List;

import com.team3.user.order.dto.OrderDto;

public interface AdminOrderDao {
	public int getAdminOrderCount();
	public List<OrderDto> adminOrderList();
	public int getAdminChangeCount();
	public List<OrderDto> adminChangeList();
	public int getAdminDeliveryCount();
	public List<OrderDto> adminDeliveryList();
	public String getOrder_id(String order_number);
	public String getOrder_name(String order_id);
	public OrderDto getAdminDetail(String order_number);
	public String getPublisher(String isbn);
	public String getAuthor(String isbn);
}
