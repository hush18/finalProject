package com.team3.user.member.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.team3.user.member.dto.MemberDto;
import com.team3.user.member.dto.ZipcodeDto;

public interface MemberDao {
	public int insertAccount(MemberDto memberDto);
	public List<ZipcodeDto> zipcodeDto(String dong);
	public Date memberSelect(Map<String, Object> hmap);
	public MemberDto memberLoginOK(Map<String, Object> hmap);
	public int memberDiap(Map<String, Object> hmap);
}
