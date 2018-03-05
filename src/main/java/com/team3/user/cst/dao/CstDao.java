package com.team3.user.cst.dao;

import java.util.List;

import com.team3.user.cst.dto.CstDto;
import com.team3.user.cst.dto.CstOrderDto;
import com.team3.user.cst.dto.CstQuestionDto;

public interface CstDao {
	public int userInsert(CstDto cstDto);
	public List<CstQuestionDto> cstProductList(String search,int startNum,int endNum);
	public int cstProductCount(String search);
	public List<CstOrderDto> cstOrNumberList(String id);
	public CstOrderDto cstOrderList(String goods,String order_number,String date);
}
