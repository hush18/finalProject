package com.team3.user.review.dto;

import java.util.Date;

public class ReviewDto {
	private long review_number;		// number(8) PRIMARY KEY,
	private String isbn;			// VARCHAR2(100) not null,
	private String id;				// VARCHAR2(200),
	private String content;			// VARCHAR2(500),
	private float grade;				// NUMBER(8),
	private Date writer_date;		// TIMESTAMP NOT NULL
	
	public long getReview_number() {
		return review_number;
	}
	public void setReview_number(long review_number) {
		this.review_number = review_number;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public Date getWriter_date() {
		return writer_date;
	}
	public void setWriter_date(Date writer_date) {
		this.writer_date = writer_date;
	}
	
	@Override
	public String toString() {
		return "ReviewDto [review_number=" + review_number + ", isbn=" + isbn + ", id=" + id
				+ ", content=" + content + ", grade=" + grade + ", writer_date=" + writer_date + "]";
	}
}
