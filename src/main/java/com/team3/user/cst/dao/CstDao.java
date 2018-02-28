package com.team3.user.cst.dao;

import java.util.List;

import com.team3.user.cst.dto.CstDto;
import com.team3.user.cst.dto.CstQuestionDto;

public interface CstDao {
	public int userInsert(CstDto cstDto);
	public List<CstQuestionDto> cstQuestion(String search);
}
