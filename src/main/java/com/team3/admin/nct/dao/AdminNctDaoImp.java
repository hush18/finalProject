package com.team3.admin.nct.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dto.AdminNctDto;

@Component
public class AdminNctDaoImp implements AdminNctDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int nctInsert(AdminNctDto adminNctDto) {
		return sqlSession.insert("nctInsert",adminNctDto);
	}

	@Override
	public int nctCount() {
		return sqlSession.selectOne("count");
	}

	@Override
	public List<AdminNctDto> adminNctList() {
		return sqlSession.selectList("nctList");
	}

	@Override
	public AdminNctDto nctSelect(int nctNumber) {
		return sqlSession.selectOne("nctSelect",nctNumber);
	}

	@Override
	public int nctUpdateOk(AdminNctDto adminNctDto) {
		return  sqlSession.update("nctUpdateOk",adminNctDto);
	}

	@Override
	public int nctDeleteOk(String checked) {
		return  sqlSession.delete("nctDeleteOk",checked);
	}

	@Override
	public List<AdminNctDto> getNctList() {
		return sqlSession.selectList("getNctList");
	}
}
