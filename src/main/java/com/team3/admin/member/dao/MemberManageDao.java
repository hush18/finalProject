package com.team3.admin.member.dao;

import java.util.List;

import com.team3.user.member.dto.MemberDto;

public interface MemberManageDao {
	public List<MemberDto> memberManage();
	public int adminMemberDelete(int member_number, String password);
	public int memberDiapCheck();
}
