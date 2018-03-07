package com.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ServiceInterface {
	
	//사용자~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// 사용자 도서리스트 및 상세 - 심제민
	public void bookList(ModelAndView mav);
	public void bookInfo(ModelAndView mav);
	public void searchList(ModelAndView mav);
	
	// 사용자 도서검색 및 저자 검색 - 심제민
	public void searchTitle(ModelAndView mav);
	
	// 사용자 리뷰리스트 및 등록 - 심제민
	public void reviewInsert(ModelAndView mav);
	public void reviewList(ModelAndView mav);
	
	// 사용자 고객센터 - 최은지
	public void getTopTen(ModelAndView mav);
	public void getFaq(ModelAndView mav);
	public void cstProduct(ModelAndView mav);
	public void cstOrder(ModelAndView mav);
	public void cstConsulting(ModelAndView mav);
	public void cstOk(ModelAndView mav);
	public void cstList(ModelAndView mav);
	
	//사용자 주문조회 - 신호용
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
	
	// 사용자 로그인/휴면계정 - 김미화
	public void searchPwd(ModelAndView mav);
	public void memberLoginOK(ModelAndView mav);
	public void findIdOK(ModelAndView mav);
	public void diapOK(ModelAndView mav);
	public void loginMember(ModelAndView mav);
	
	//~~
	public void getMainList(ModelAndView mav);
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response);
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
	public int createAccountOk(ModelAndView mav);
	public void myPage(ModelAndView mav);
	public void updateAccount(ModelAndView mav);
	public void updateAccountOk(ModelAndView mav);
	public void deleteAccount(ModelAndView mav);
	public void searchPwdOK(ModelAndView mav);
	public void wishListInsert(ModelAndView mav);
	public void nearestInsert(ModelAndView mav);
	public void scrollBanner(ModelAndView mav);
	public void userMapRead(ModelAndView mav);
	public void payment(ModelAndView mav);
	public void addressList(ModelAndView mav);
	public void addAddress(ModelAndView mav);
	public void deleteAddress(ModelAndView mav);
	public void paymentOk(ModelAndView mav);
	public void searchHeader(ModelAndView mav);
	public void naverCreateAccount(ModelAndView mav) throws Throwable;
	public void facebookCreateAccount(ModelAndView mav) throws Throwable;
	public void userPoint(ModelAndView mav);
	public void recommend(ModelAndView mav);
	
	//관리자~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// 관리자 도서관리 - 심제민
	public void adminBookInsert(ModelAndView mav);
	public void adminBookInsertOk(ModelAndView mav);
	public void adminBookSearch(ModelAndView mav);
	public void adminBookInfo(ModelAndView mav);
	public void adminBookUpdate(ModelAndView mav);
	public void adminBookDelete(ModelAndView mav);
	
	// 관리자 저자 검색 및 등록, 리스트 출력 - 심제민
	public void adminWriterSearch(ModelAndView mav);
	public void adminWriterInsertOk(ModelAndView mav);
	public void searchWriter(ModelAndView mav);
	
	// 관리자 고객센터 - 최은지
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
	
	//관리자 주문조회 - 신호용
	public void adminOrderSearch(ModelAndView mav);
	public void adminChange(ModelAndView mav);
	public void adminDelivery(ModelAndView mav);
	public void adminDetail(ModelAndView mav);
	public void adminStatusChange(ModelAndView mav);
	
	//관리자 회원관리 - 김미화
	public void memberManage(ModelAndView mav);
	public void adminMemberDelete(ModelAndView mav);
	public void adminMemberDeleteOK(ModelAndView mav);
	
	//~~~
	public void adminSales(ModelAndView mav);
	
}

