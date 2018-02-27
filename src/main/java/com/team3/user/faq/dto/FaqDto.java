package com.team3.user.faq.dto;

public class FaqDto {
	private int faq_number;
	private int rNum;
	private String title;
	private String content;
	private String up_category;
	private String down_category;
	private int count;
	private String is_top_ten;

	public int getFaq_number() {
		return faq_number;
	}

	public void setFaq_number(int faq_number) {
		this.faq_number = faq_number;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUp_category() {
		return up_category;
	}

	public void setUp_category(String up_category) {
		this.up_category = up_category;
	}

	public String getDown_category() {
		return down_category;
	}

	public void setDown_category(String down_category) {
		this.down_category = down_category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getIs_top_ten() {
		return is_top_ten;
	}

	public void setIs_top_ten(String is_top_ten) {
		this.is_top_ten = is_top_ten;
	}

	@Override
	public String toString() {
		return "FaqDto [faq_number=" + faq_number + ", rNum=" + rNum + ", title=" + title + ", content=" + content
				+ ", up_category=" + up_category + ", down_category=" + down_category + ", count=" + count
				+ ", is_top_ten=" + is_top_ten + "]";
	}

}
