package com.team3.admin.cst.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.cst.dto.AdminCstDto;

@Component
public class AdminCstDaoImp implements AdminCstDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int cstCount() {
		return sqlSession.selectOne("cstCount");
	}

	@Override
	public List<AdminCstDto> adminCstList() {
		return sqlSession.selectList("cstList");
	}

	@Override
	public int cstInsertOk(AdminCstDto adminCstDto) {
		return sqlSession.update("cstInsert",adminCstDto);
	}

	@Override
	public int cstUpdateOk(AdminCstDto adminCstDto) {
		return sqlSession.update("cstUpdate",adminCstDto);
	}
	
	@Override
	public int cstDeleteOk(String checked) {
		return  sqlSession.delete("cstDeleteOk",checked);
	}
	
}
