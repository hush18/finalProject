package com.team3.user.cstList.dao;

import java.util.List;

import com.team3.user.cstList.dto.CstListDto;

public interface CstListDao {
	public List<CstListDto> cstList(String id);
}
