package com.team3.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.team3.user.book.dto.BookDto;

public interface ServiceInterface {
	public void getMainList(ModelAndView mav);
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
	public void cstOk(ModelAndView mav);
	public void adminFaqInsertOk(ModelAndView mav);
	public void adminFaqMain(ModelAndView mav);
	public void adminFaqUpdate(ModelAndView mav);
	public void adminFaqUpdateOk(ModelAndView mav);
	public void adminFaqDeleteOk(ModelAndView mav);
	public void adminFaqTopDelete(ModelAndView mav);
	public void adminFaqTopInsert(ModelAndView mav);
	public void adminNctInsertOk(ModelAndView mav);
	public void adminNctMain(ModelAndView mav);
	public void adminNctDeleteOk(ModelAndView mav);
	public void adminNctUpdate(ModelAndView mav);
	public void adminNctUpdateOk(ModelAndView mav);
	public void adminCstMain(ModelAndView mav);
	public void adminCstInsertOk(ModelAndView mav);
	public void adminCstUpdateOk(ModelAndView mav);
	public void adminCstDeleteOk(ModelAndView mav);
	public void bookList(ModelAndView mav);
	public void bookInfo(ModelAndView mav);
	public void adminBookSearch(ModelAndView mav);
	public void adminBookInfo(ModelAndView mav);
	public void adminWriterSearch(ModelAndView mav);
	public void adminBookUpdate(ModelAndView mav);
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
	public void adminSales(ModelAndView mav);
	public void userMapRead(ModelAndView mav);
	public void diapOK(ModelAndView mav);
	public void memberManage(ModelAndView mav);
	public void adminMemberDelete(ModelAndView mav);
	public void adminMemberDeleteOK(ModelAndView mav);
	public void searchHeader(ModelAndView mav);
	public void getTopTen(ModelAndView mav);
	public void getFaq(ModelAndView mav);
	public void loginMember(ModelAndView mav);
	public void naverCreateAccount(ModelAndView mav) throws Throwable;
	public void facebookCreateAccount(ModelAndView mav) throws Throwable;
	public void adminOrderSearch(ModelAndView mav);
}

