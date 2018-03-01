package com.team3.user.book.dto;

public class BookDto {
	private String isbn;
	private String title;
	private long writer_number;
	private String publisher;
	private String write_date;
	private String image_path;
	private long price;
	private String contents;
	private String book_introduction;
	private String category_number;
	private String stock;
	
	//저자명 가져오기
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//리뷰평점 가져오기
	private long grade;
	public long getGrade() {
		return grade;
	}
	public void setGrade(long grade) {
		this.grade = grade;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getBook_introduction() {
		return book_introduction;
	}
	public void setBook_introduction(String book_introduction) {
		this.book_introduction = book_introduction;
	}
	public String getCategory_number() {
		return category_number;
	}
	public void setCategory_number(String category_number) {
		this.category_number = category_number;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "BookDto [isbn=" + isbn + ", title=" + title + ", writer_number=" + writer_number + ", name=" + name
				+ ", publisher=" + publisher + ", write_date=" + write_date + ", image_path=" + image_path + ", price="
				+ price + ", contents=" + contents + ", book_introduction=" + book_introduction + ", category_number="
				+ category_number + ", stock=" + stock + "]";
	}
	
}
