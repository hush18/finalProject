package com.team3.user.cstList.dao;

import java.util.List;

import com.team3.user.cstList.dto.CstListDto;

public interface CstListDao {
	public int cstListCount(String id);
	public List<CstListDto> cstList(String id,int startNum,int endNum,String date);
}
