package com.team3.admin.nct.dao;

import java.util.List;

import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dto.AdminNctDto;

public interface AdminNctDao {
	public int nctInsert(AdminNctDto adminNctDto);
	public int nctCount();
	public List<AdminNctDto> adminNctList();
	public AdminNctDto nctSelect(int nctNumber);
	public int nctUpdateOk(AdminNctDto adminNctDto);
	public int nctDeleteOk(String checked);
	public List<AdminNctDto> getNctList();
}
