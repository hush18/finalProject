package com.team3.user.book.dao;

import java.util.List;
import java.util.Map;

import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;

public interface BookDao {
	public CategoryDto getCategoryPath(String category_number);
	public int getCount(String category_number);
	public List<BookDto> getBookList(Map<String, Object> dataMap);
	public String getCategoryNumber(String category_path);
	public BookDto getBookInfo(String isbn);
	public WriterDto getWriterInfo(long writer_number);
	public List<BookDto> getMainList();
	public List<BookDto> bookListMH();
	public List<BookDto> getBookTitleList(String category);
	public List<BookDto> searchList(Map<String, String> dataMap);
	public List<BookDto> searchBook(Map<String, String> dataMap);
}
