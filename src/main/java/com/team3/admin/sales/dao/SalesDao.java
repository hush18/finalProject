package com.team3.admin.sales.dao;

import java.util.List;

import com.team3.admin.sales.dto.SalesDto;

public interface SalesDao {
	public List<SalesDto> salesSelect();
}
