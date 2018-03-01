package com.team3.user.cst.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.cst.dto.CstDto;
import com.team3.user.cst.dto.CstQuestionDto;

@Component
public class CstDaoImp implements CstDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int userInsert(CstDto cstDto) {
		return sqlSession.insert("userInsert",cstDto);
	}

	@Override
	public List<CstQuestionDto> cstProductList(String search,int startNum,int endNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("cstProductList",map);
	}

	@Override
	public int cstProductCount(String search) {
		return sqlSession.selectOne("cstProductCount",search);
	}
}
