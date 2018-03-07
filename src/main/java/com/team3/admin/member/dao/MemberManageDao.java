package com.team3.admin.member.dao;

import java.util.List;

import com.team3.user.member.dto.MemberDto;

public interface MemberManageDao {
	public List<MemberDto> memberManage();
	public int adminMemberDelete(String id);
	public int memberDiapCheck();
	public int memberDiapChecking();
	public List<MemberDto> adminGetPassword();
}
