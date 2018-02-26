package com.team3.user.map.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDaoImp implements PaymentDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int selectBook(String isbn) {
		return sqlSessionTemplate.selectOne("selectBookPrice",isbn);
	}
}
