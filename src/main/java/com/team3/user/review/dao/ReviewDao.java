package com.team3.user.review.dao;

import java.util.List;

import com.team3.user.review.dto.ReviewDto;

public interface ReviewDao {
	public int reviewInsert(ReviewDto reviewDto);
	public List<ReviewDto> getReviewList(String isbn);
}
