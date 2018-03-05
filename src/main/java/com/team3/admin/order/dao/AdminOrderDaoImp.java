package com.team3.admin.order.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.order.dto.OrderDto;

@Component
public class AdminOrderDaoImp implements AdminOrderDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int getAdminOrderCount() {
		return sqlSession.selectOne("getAdminOrderCount");
	}

	@Override
	public List<OrderDto> adminOrderList() {
		return sqlSession.selectList("adminOrderList");
	}

	@Override
	public int getAdminChangeCount() {
		return sqlSession.selectOne("getAdminChangeCount");
	}
	
	@Override
	public List<OrderDto> adminChangeList() {
		return sqlSession.selectList("adminChangeList");
	}
	
	@Override
	public int getAdminDeliveryCount() {
		return sqlSession.selectOne("getAdminDeliveryCount");
	}
	
	@Override
	public List<OrderDto> adminDeliveryList() {
		return sqlSession.selectList("adminDeliveryList");
	}
	
	@Override
	public String getOrder_id(String order_number) {
		return sqlSession.selectOne("getAdminOrder_id", order_number);
	}

	@Override
	public String getOrder_name(String order_id) {
		return sqlSession.selectOne("getAdminOrder_name", order_id);
	}
	
	@Override
	public OrderDto getAdminDetail(String order_number) {
		return sqlSession.selectOne("getAdminDetail", order_number);
	}
	
	@Override
	public String getPublisher(String isbn) {
		return sqlSession.selectOne("getPublisher", isbn);
	}
	
	@Override
	public String getAuthor(String isbn) {
		return sqlSession.selectOne("getAuthor", isbn);
	}
}
