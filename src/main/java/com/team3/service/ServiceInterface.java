package com.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ServiceInterface {
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response);
	public void searchPwd(ModelAndView mav);
	public void memberLoginOK(ModelAndView mav);

	public void zipcode(ModelAndView mav);
	
	public void bookList(ModelAndView mav);
	public void bookInfo(ModelAndView mav);
	
	public void adminBookSearch(ModelAndView mav);
	public void adminBookInfo(ModelAndView mav);
}
