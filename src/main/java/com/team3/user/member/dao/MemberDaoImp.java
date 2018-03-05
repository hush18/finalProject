package com.team3.user.member.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	public MemberDto updateAccount(HttpSession session) {
		String id = (String)session.getAttribute("mbId");
		
		return sqlSession.selectOne("getAccountInfo", id);
	}
	
	@Override
	public int updateAccountOk(MemberDto memberDto) {
		return sqlSession.update("setAccountInfo", memberDto);
	}

	@Override
	public int deleteAccount(MemberDto memberDto) {
		return sqlSession.delete("deleteAccount", memberDto);
	}

	@Override
	public int deleteDataOfCart(MemberDto memberDto) {
		return sqlSession.delete("deleteDataOfCart", memberDto);
	}

	@Override
	public int deleteDataOfDel(MemberDto memberDto) {
		return sqlSession.delete("deleteDataOfDel", memberDto);
	}

	public Date memberDate(Map<String, Object> hmap) {
		return sqlSession.selectOne("memberDate", hmap);
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
	
	@Override
	public String findId(String name, String email) {
		Map<String, String> hmap=new HashMap<String, String>();
		hmap.put("name", name);
		hmap.put("email", email);
		
		return sqlSession.selectOne("findId", hmap);
	}
	
	@Override
	public String findPwd(String id) {
		return sqlSession.selectOne("findPwd", id);
	}
	
	@Override
	public MemberDto memberSelect(String id) {
		return sqlSession.selectOne("memberSelect", id);
	}
	
	@Override
	public int diapOK(Map<String, Object> hmap) {
		return sqlSession.update("diapOK", hmap);
	}
	
	
}
