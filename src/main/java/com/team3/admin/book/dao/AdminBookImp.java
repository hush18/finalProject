package com.team3.admin.book.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;

@Component
public class AdminBookImp implements AdminBook{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BookDto> getAdminBookSearch() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.admin.book.dao.mapper.getAdminBookSearch");
	}
	
	@Override
	public BookDto adminBookInfo(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.adminBookInfo", isbn);
	}
	
	@Override
	public CategoryDto adminBookInfoCategory(String category_number) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.adminBookInfoCategory", category_number);
	}
	
	@Override
	public String adminBookCategogy1() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.adminBookCategogy1");
	}
	@Override
	public String adminBookCategogy2(String categogy1) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.adminBookCategogy2", categogy1);
	}
}
