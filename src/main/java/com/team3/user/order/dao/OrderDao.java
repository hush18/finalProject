package com.team3.user.order.dao;

import java.util.Date;
import java.util.List;

import com.team3.user.order.dto.CartDto;
import com.team3.user.order.dto.OrderDto;

public interface OrderDao {
	public int getOrderSearchCount(String id);
	public int getDeliveryCount(String id);
	public int getCancelCount(String id);
	public int getPoint(String id);
	public List<OrderDto> orderSearchList(int startRow, int endRow, int list_id, String id);
	public int statusChange(String order_number, String status, String id);
	public int getOrderingCount(String id);
	public List<OrderDto> orderingList(int startRow, int endRow, int list_id, String id);
	public List<OrderDto> deliveryList(int startRow, int endRow, int list_id, String id);
	public List<OrderDto> cancelList(int startRow, int endRow, int list_id, String id);
	public int getBuyListCount(String id);
	public List<OrderDto> buyListList(int startRow, int endRow, int list_id, String id);
	public int getCartCount(String id);
	public List<CartDto> cartList(int startRow, int endRow, int list_id, String id);
	public int insertCart(String isbn, String cart_amount, String id);
	public int cartListDelete(String isbn, String id);
	public int orderDelete(String order_number, String id);
	public List<OrderDto> getDetailOrder(String order_number, String id);
	public long getDetailPrice(String isbn);
	public Date getOrderDate(String order_number);
	public String getTitle(String isbn);
}
