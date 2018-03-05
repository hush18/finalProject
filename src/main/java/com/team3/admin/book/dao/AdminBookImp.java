package com.team3.admin.book.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public int adminBookInsert(BookDto bookDto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.team3.admin.book.dao.mapper.adminBookInsert", bookDto);
	}
	
	@Override
	public int updateWriter(WriterDto writerDto) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.team3.admin.book.dao.mapper.updateWriter", writerDto);
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
	public WriterDto getWriter(long writer_number) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.team3.admin.book.dao.mapper.getWriter", writer_number);
	}
	
	@Override
	public int adminBookUpdate(BookDto bookDto) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.team3.admin.book.dao.mapper.adminBookUpdate", bookDto);
	}
	
	@Override
	public int adminWriterInsert(WriterDto writerDto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.team3.admin.book.dao.mapper.adminWriterInsert", writerDto);
	}
	
	@Override
	public int adminBookDelete(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.delete("com.team3.admin.book.dao.mapper.adminBookDelete", isbn);
	}
	
	@Override
	public int adminWriterBookListUpdate(WriterDto writerDto) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.team3.admin.book.dao.mapper.adminWriterBookListUpdate", writerDto);
	}
	
	@Override
	public List<String> getWriterNameList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.admin.book.dao.mapper.getWriterNameList");
	}
}
