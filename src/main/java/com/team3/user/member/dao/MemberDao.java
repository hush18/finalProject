package com.team3.user.member.dao;

import java.util.List;

import com.team3.user.member.dto.MemberDto;
import com.team3.user.member.dto.ZipcodeDto;

public interface MemberDao {
	public int insertAccount(MemberDto memberDto);
	public List<ZipcodeDto> zipcodeDto(String dong);
}
