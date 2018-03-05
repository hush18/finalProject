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

}
