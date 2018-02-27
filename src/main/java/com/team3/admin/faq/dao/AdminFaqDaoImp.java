package com.team3.admin.faq.dao;

import java.util.List;

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
	public int faqCount() {
		return sqlSession.selectOne("faqCount");
	}

	@Override
	public List<AdminFaqDto> adminFaqList() {
		return sqlSession.selectList("faqList");
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

	@Override
	public int faqTopDelete(int faqNumber) {
		return sqlSession.update("faqTopDelete",faqNumber);
	}

	@Override
	public int faqTopInsert(int faqNumber) {
		return sqlSession.update("faqTopInsert",faqNumber);
	}
}
