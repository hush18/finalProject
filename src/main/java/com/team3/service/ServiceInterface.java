package com.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ServiceInterface {
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response);
	public void searchPwd(ModelAndView mav);
	public void memberLoginOK(ModelAndView mav);
	public void zipcode(ModelAndView mav);
	public void createMap(ModelAndView  mav);
	public void readMap(ModelAndView mav);
	public void updateMap(ModelAndView mav);
	public void deleteMap(ModelAndView mav);
	public void nearestList(ModelAndView mav);
	public void nearestUp(ModelAndView mav);
	public void nearestDel(ModelAndView mav);
	public void wishList(ModelAndView mav);
	public void wishListUp(ModelAndView mav);
	public void wishListDel(ModelAndView mav);
	public void createAccountOk(ModelAndView mav);
	public void myPage(ModelAndView mav);
	public void updateAccount(ModelAndView mav);
	public void updateAccountOk(ModelAndView mav);
	public void deleteAccount(ModelAndView mav);
	public void findIdOK(ModelAndView mav);
	public void searchPwdOK(ModelAndView mav);
	public void wishListInsert(ModelAndView mav);
	public void nearestInsert(ModelAndView mav);
	public void scrollBanner(ModelAndView mav);
	public void userMapRead(ModelAndView mav);
	public void diapOK(ModelAndView mav);
	public void memberManage(ModelAndView mav);
	public void adminMemberDelete(ModelAndView mav);
	public void adminMemberDeleteOK(ModelAndView mav);
	public void searchHeader(ModelAndView mav);
}
