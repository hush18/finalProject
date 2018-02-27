package com.team3.user.cst.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.cst.dto.AdminCstDto;

@Component
public class CstDaoImp implements CstDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
}
