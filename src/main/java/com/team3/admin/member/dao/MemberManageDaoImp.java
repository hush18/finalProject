package com.team3.admin.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public int adminMemberDelete(int member_number, String password) {
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("member_number", member_number);
		hmap.put("password", password);
		return sqlSession.delete("adminMemberDelete", hmap);
	}
}
