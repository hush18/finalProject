package com.team3.user.member.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.team3.user.member.dto.MemberDto;
import com.team3.user.member.dto.ZipcodeDto;

public interface MemberDao {
	public int insertAccount(MemberDto memberDto);
	public List<ZipcodeDto> zipcodeDto(String dong);
	public MemberDto updateAccount(HttpSession session);
	public int updateAccountOk(MemberDto memberDto);
	public int deleteAccount(MemberDto memberDto);
	public Date memberDate(Map<String, Object> hmap);
	public MemberDto memberLoginOK(Map<String, Object> hmap);
	public int memberDiap(Map<String, Object> hmap);
	public String findId(String name, String email);
	public String findPwd(String id);
	public MemberDto memberSelect(String id);
}
