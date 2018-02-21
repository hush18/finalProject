package com.team3.user.member.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team3.user.member.dto.MemberDto;
import com.team3.user.member.dto.ZipcodeDto;

@Component
public class MemberDaoImp implements MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertAccount(MemberDto memberDto) {
		System.out.println(memberDto);
		return sqlSession.insert("createAccount", memberDto);
	}

	@Override
	public List<ZipcodeDto> zipcodeDto(String dong) {
		return sqlSession.selectList("memberZipcode", dong);
	}
	
	@Override
	public Date memberSelect(Map<String, Object> hmap) {
		return sqlSession.selectOne("memberSelect", hmap);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public MemberDto memberLoginOK(Map<String, Object> hmap) {
		Date last_login=new Date();
		hmap.put("last_login", last_login);
		
		sqlSession.update("lastLoginUp", hmap);
		return sqlSession.selectOne("memberLogin", hmap);
	}

	@Override
	public int memberDiap(Map<String, Object> hmap) {
		return sqlSession.update("memberDiap", hmap);
	}
}
