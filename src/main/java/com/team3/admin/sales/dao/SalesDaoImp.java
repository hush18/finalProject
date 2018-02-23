package com.team3.admin.sales.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.sales.dto.SalesDto;

@Component
public class SalesDaoImp implements SalesDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<SalesDto> salesSelect() {
		
		return sqlSession.selectList("selectSales");
	}
	
	
	
}
