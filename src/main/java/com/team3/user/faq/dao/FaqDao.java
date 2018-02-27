package com.team3.user.faq.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.team3.user.faq.dto.FaqDto;

public interface FaqDao {
	public List<FaqDto> getTopTenList();
}
