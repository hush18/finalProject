package com.team3.user.book.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.team3.user.book.dto.BookDto;

public class BookDaoImp implements BookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BookDto> bookListMH() {
		return sqlSession.selectList("bookListMH");
		
	}
}
