package com.team3.admin.book.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;

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
	public List<CategoryDto> adminBookCategogyList() {
		return sqlSession.selectList("com.team3.admin.book.dao.mapper.adminBookCategogyList");
	}
	
	@Override
	public List<WriterDto> getWriterList(String name) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.admin.book.dao.mapper.getWriterList", name);
	}
	
	@Override
	public String getTitle(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.getTitle", isbn);
	}
	
	@Override
	public WriterDto getWriter(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.getWriter", isbn);
	}
	
	@Override
	public int adminBookUpdateFile(BookDto bookDto) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.team3.admin.book.dao.mapper.adminBookUpdateFile", bookDto);
	}
	
	@Override
	public int adminBookUpdate(BookDto bookDto) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.team3.admin.book.dao.mapper.adminBookUpdate", bookDto);
	}
	
	@Override
	public int writerListUpdate(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		return sqlSession.update("writerListUpdate",hashMap);
	}
}
