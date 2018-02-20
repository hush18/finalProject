package com.team3.user.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
