package com.team3.admin.book.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;

public interface AdminBook {
	public List<BookDto> getAdminBookSearch();
	public BookDto adminBookInfo(String isbn);
	public List<CategoryDto> adminBookCategogyList();
	public List<WriterDto> getWriterList(String name);
	public String getTitle(String isbn);
	public WriterDto getWriter(String isbn);
	public int adminBookUpdateFile(BookDto bookDto);
	public int adminBookUpdate(BookDto bookDto);
	public int writerListUpdate(HashMap<String, Object> hashMap);
}
