package com.team3.user.order.dao;

import java.util.Date;
import java.util.List;

import com.team3.user.order.dto.CartDto;
import com.team3.user.order.dto.OrderDto;

public interface OrderDao {
	public int getOrderSearchCount();
	public int getDeliveryCount();
	public int getCancelCount();
	public int getPoint();
	public List<OrderDto> orderSearchList(int startRow, int endRow, int list_id);
	public int statusChange(String order_number, String status);
	public int getOrderingCount();
	public List<OrderDto> orderingList(int startRow, int endRow, int list_id);
	public List<OrderDto> deliveryList(int startRow, int endRow, int list_id);
	public List<OrderDto> cancelList(int startRow, int endRow, int list_id);
	public int getBuyListCount();
	public List<OrderDto> buyListList(int startRow, int endRow, int list_id);
	public int getCartCount();
	public List<CartDto> cartList(int startRow, int endRow, int list_id);
	public int insertCart(String isbn, String cart_amount);
	public int cartListDelete(String isbn);
	public int orderDelete(String order_number);
	public List<OrderDto> getDetailOrder(String order_number);
	public long getDetailPrice(String isbn);
	public Date getOrderDate(String order_number);
	public String getTitle(String isbn);
}
