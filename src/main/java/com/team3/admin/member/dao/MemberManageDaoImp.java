package com.team3.admin.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team3.user.member.dto.MemberDto;

@Component
public class MemberManageDaoImp implements MemberManageDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 회원관리(관리자)
	@Override
	public List<MemberDto> memberManage() {
		return sqlSession.selectList("memberManage");
	}

	@Override
	public int memberDiapCheck() {
		return sqlSession.update("diapCheck");
	}
	
	@Override
	public List<MemberDto> adminGetPassword() {
		return sqlSession.selectList("getPassword");
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public int adminMemberDelete(String id) {
		sqlSession.delete("adminOrderDelete", id);
		sqlSession.delete("adminCartDelete", id);
		return sqlSession.delete("adminMemberDelete", id);
	}

	@Override
	public int memberDiapChecking() {
		return  sqlSession.update("diapChecking");
	}
	
	
}
