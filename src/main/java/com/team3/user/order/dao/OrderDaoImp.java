package com.team3.user.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.order.dto.OrderDto;

@Component
public class OrderDaoImp implements OrderDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int getOrderSearchCount() {
		return sqlSession.selectOne("orderSearchCount");
	}

	@Override
	public List<OrderDto> orderSearchList(int startRow, int endRow) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		return sqlSession.selectList("orderSearchList", map);
	}

	
}
