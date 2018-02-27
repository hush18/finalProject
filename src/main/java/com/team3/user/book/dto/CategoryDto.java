package com.team3.user.book.dto;

public class CategoryDto {
	private String category_number;
	private String category_path;
	private String low_category;
	
	public String getCategory_number() {
		return category_number;
	}
	public void setCategory_number(String category_number) {
		this.category_number = category_number;
	}
	public String getCategory_path() {
		return category_path;
	}
	public void setCategory_path(String category_path) {
		this.category_path = category_path;
	}
	public String getLow_category() {
		return low_category;
	}
	public void setLow_category(String low_category) {
		this.low_category = low_category;
	}
	
	@Override
	public String toString() {
		return "CategoryDto [category_number=" + category_number + ", category_path=" + category_path
				+ ", low_category=" + low_category + "]";
	}
}
