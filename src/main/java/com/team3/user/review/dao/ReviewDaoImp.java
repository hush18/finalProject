package com.team3.user.review.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.review.dto.ReviewDto;

@Component
public class ReviewDaoImp implements ReviewDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int reviewInsert(ReviewDto reviewDto) {
		return sqlSession.insert("com.team3.user.review.dao.mapper.reviewInsert", reviewDto);
	}
	
	@Override
	public List<ReviewDto> getReviewList(String isbn) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.team3.user.review.dao.mapper.getReviewList", isbn);
	}
}
