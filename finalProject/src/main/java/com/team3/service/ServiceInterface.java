package com.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ServiceInterface {
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response);
	public void searchPwd(ModelAndView mav);
	public void memberLoginOK(ModelAndView mav);

	public void zipcode(ModelAndView mav);
	
	public void adminFaqInsertOk(ModelAndView mav);
	public void adminFaqMain(ModelAndView mav);
	public void adminFaqUpdate(ModelAndView mav);
	public void adminFaqUpdateOk(ModelAndView mav);
	public void adminFaqDeleteOk(ModelAndView mav);
}
