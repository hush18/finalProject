package com.team3.user.book.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;

@Component
public class BookDaoImp implements BookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BookDto> getMainList() {
		return sqlSession.selectList("getMainList");
	}

	@Override
	public CategoryDto getCategoryPath(String category_number) {
		return sqlSession.selectOne("com.team3.user.book.dao.mapper.getCategoryPath", category_number);
	}

	@Override
	public int getCount(String category_number) {
		return sqlSession.selectOne("com.team3.user.book.dao.mapper.getCount",category_number);
	}

	@Override
	public List<BookDto> getBookList(Map<String, Object> dataMap) {
		return sqlSession.selectList("com.team3.user.book.dao.mapper.getBookList", dataMap);
	}

	@Override
	public String getCategoryNumber(String category_path) {
		return sqlSession.selectOne("com.team3.user.book.dao.mapper.getCategoryNumber", category_path);
	}
	
	@Override
	public BookDto getBookInfo(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.user.book.dao.mapper.getBookInfo", isbn);
	}
	
	@Override
	public WriterDto getWriterInfo(long writer_number) {
		return sqlSession.selectOne("com.team3.user.book.dao.mapper.getWriterInfo", writer_number);
	}
	
	@Override
	public List<BookDto> bookListMH() {
		return sqlSession.selectList("bookListMH");
	}
	
	@Override
	public List<BookDto> getBookTitleList(String category) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.user.book.dao.mapper.getBookTitleList", category);
	}
	
	@Override
	public List<BookDto> searchList(Map<String, String> dataMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.user.book.dao.mapper.searchList", dataMap);
	}
	
	@Override
	public List<BookDto> searchBook(Map<String, String> dataMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.user.book.dao.mapper.searchBook", dataMap);
	}
	
}
