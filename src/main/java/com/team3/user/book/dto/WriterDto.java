package com.team3.user.book.dto;

public class WriterDto {
	private long writer_number;
	private String name;
	private String nationality;
	private String debut_year;
	private String writer_introduction;
	private String writer_bookList;
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getWriter_number() {
		return writer_number;
	}
	public void setWriter_number(long writer_number) {
		this.writer_number = writer_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getDebut_year() {
		return debut_year;
	}
	public void setDebut_year(String debut_year) {
		this.debut_year = debut_year;
	}
	public String getWriter_introduction() {
		return writer_introduction;
	}
	public void setWriter_introduction(String writer_introduction) {
		this.writer_introduction = writer_introduction;
	}
	public String getWriter_bookList() {
		return writer_bookList;
	}
	public void setWriter_bookList(String writer_bookList) {
		this.writer_bookList = writer_bookList;
	}
	@Override
	public String toString() {
		return "WriterDto [writer_number=" + writer_number + ", name=" + name + ", nationality=" + nationality
				+ ", debut_year=" + debut_year + ", writer_introduction=" + writer_introduction + ", writer_bookList="
				+ writer_bookList + ", title=" + title + "]";
	}
	
}
