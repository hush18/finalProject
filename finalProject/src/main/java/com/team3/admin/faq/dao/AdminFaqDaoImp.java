package com.team3.admin.faq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.faq.dto.AdminFaqDto;

@Component
public class AdminFaqDaoImp implements AdminFaqDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int faqInsert(AdminFaqDto adminFaqDto) {
		return sqlSession.insert("faqInsert",adminFaqDto);
	}

	@Override
	public int faqCount(String key) {
		return sqlSession.selectOne("faqCount",key);
	}

	@Override
	public List<AdminFaqDto> adminFaqList(String key,int startRow,int endRow) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("faqList",map);
	}

	@Override
	public AdminFaqDto faqSelect(int faqNumber) {
		return sqlSession.selectOne("faqSelect",faqNumber);
	}

	@Override
	public int faqUpdateOk(AdminFaqDto adminFaqDto) {
		return sqlSession.update("faqUpdateOk",adminFaqDto);
	}

	@Override
	public int faqDeleteOk(String checked) {
		return sqlSession.delete("faqDeleteOk",checked);
	}
}
