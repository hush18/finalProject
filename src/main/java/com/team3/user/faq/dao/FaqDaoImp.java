package com.team3.user.faq.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.faq.dto.FaqDto;

@Component
public class FaqDaoImp implements FaqDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<FaqDto> getTopTenList() {
		return sqlSession.selectList("getTopTenList");
	}

}
