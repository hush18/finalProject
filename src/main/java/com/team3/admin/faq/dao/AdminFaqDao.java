package com.team3.admin.faq.dao;

import java.util.List;

import com.team3.admin.faq.dto.AdminFaqDto;

public interface AdminFaqDao {
	public int faqInsert(AdminFaqDto adminFaqDto);
	public int faqCount();
	public List<AdminFaqDto> adminFaqList();
	public AdminFaqDto faqSelect(int faqNumber);
	public int faqUpdateOk(AdminFaqDto adminFaqDto);
	public int faqDeleteOk(String checked);
	public int faqTopDelete(int faqNumber);
	public int faqTopInsert(int faqNumber);
	public List<AdminFaqDto> getFaqList();
}
