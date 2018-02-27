package com.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ServiceInterface {
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response);
	public void searchPwd(ModelAndView mav);
	public void memberLoginOK(ModelAndView mav);

	public void zipcode(ModelAndView mav);
	
	public void orderSearch(ModelAndView mav);
	public void statusChange(ModelAndView mav);
	public void orderDelete(ModelAndView mav);
	public void ordering(ModelAndView mav);
	public void delivery(ModelAndView mav);
	public void cancel(ModelAndView mav);
	public void buyList(ModelAndView mav);
	public void cart(ModelAndView mav);
	public void cartListDelete(ModelAndView mav);
	public void detailOrder(ModelAndView mav);
}
