package com.team3.user.faq.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.team3.user.faq.dto.FaqDto;

public interface FaqDao {
	public List<FaqDto> getTopTenList();
	public List<FaqDto> faqList(String upCategory,int startNum,int endNum);
	public List<FaqDto> faqDownList(String downCategory,int startNum,int endNum);
	public int faqListCount(String upCategory, String downCategory);
}
