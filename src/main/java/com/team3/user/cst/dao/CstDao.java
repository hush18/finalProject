package com.team3.user.cst.dao;

import java.util.List;

import com.team3.admin.cst.dto.AdminCstDto;

public interface CstDao {
	public int cstCount();
	public List<AdminCstDto> adminCstList();
	public int cstDeleteOk(String checked);
	public int cstInsertOk(AdminCstDto adminCstDto);
	public int cstUpdateOk(AdminCstDto adminCstDto);
}
