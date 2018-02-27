package com.team3.admin.faq.dao;

import java.util.List;

import com.team3.admin.faq.dto.AdminFaqDto;

public interface AdminFaqDao {
	public int faqInsert(AdminFaqDto adminFaqDto);
	public int faqCount(String key);
	public List<AdminFaqDto> adminFaqList(String key,int startRow,int endRow);
	public AdminFaqDto faqSelect(int faqNumber);
	public int faqUpdateOk(AdminFaqDto adminFaqDto);
	public int faqDeleteOk(String checked);
}
