package com.team3.user.cstList.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.cst.dto.AdminCstDto;
import com.team3.aop.LogAspect;
import com.team3.user.cst.dto.CstDto;

@Component
public class CstListDaoImp implements CstListDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int userInsert(CstDto cstDto) {
		return sqlSession.insert("userInsert",cstDto);
	}
}
