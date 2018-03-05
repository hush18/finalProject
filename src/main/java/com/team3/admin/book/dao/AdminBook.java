package com.team3.admin.book.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public WriterDto getWriter(long writer_number);
	public int adminBookUpdate(BookDto bookDto);
	public int adminWriterInsert(WriterDto writerDto);
	public int adminBookInsert(BookDto bookDto);
	public int updateWriter(WriterDto writerDto);
	public int adminBookDelete(String isbn);
	public int adminWriterBookListUpdate(WriterDto writerDto);
	public List<String> getWriterNameList();
}
