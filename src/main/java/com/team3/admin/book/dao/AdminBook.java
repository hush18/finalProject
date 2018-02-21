package com.team3.admin.book.dao;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;

public interface AdminBook {
	public List<BookDto> getAdminBookSearch();
	public BookDto adminBookInfo(String isbn);
	public CategoryDto adminBookInfoCategory(String category_number);
	public String adminBookCategogy1();
	public String adminBookCategogy2(String categogy1);
}
