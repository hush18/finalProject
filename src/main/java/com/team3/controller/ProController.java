package com.team3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team3.aop.LogAspect;
import com.team3.service.ServiceInterface;


@Controller
public class ProController {
	
	@Autowired
	private ServiceInterface service;
	
	@RequestMapping(value="/userMain.do", method=RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("userMain.users");
	}
	
	@RequestMapping(value="/wishList.do", method=RequestMethod.GET)
	public ModelAndView wishList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		service.wishList(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/wishListUp.do", method=RequestMethod.GET)
	public ModelAndView wishListUp(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.wishListUp(mav);
		return mav;
	}
	
	@RequestMapping(value="/wishListDel.do", method=RequestMethod.GET)
	public ModelAndView wishListDel(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.wishListDel(mav);
		return mav;
	}
	
//	@RequestMapping(value="/wishListInsert.do", method=RequestMethod.GET)
//	public ModelAndView wishListInsert(HttpServletRequest request,HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("req", request);
//		
//		service.wishListInsert(mav);
//		
//		return mav;
//	}
	
	@RequestMapping(value="/nearestList.do", method=RequestMethod.GET)
	public ModelAndView nearestList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		service.nearestList(mav);
		
		return mav;
//		return new ModelAndView("nearestList.users");
	}
	@RequestMapping(value="/nearestUp.do", method=RequestMethod.GET)
	public ModelAndView nearestUp(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.nearestUp(mav);
		return mav;
	}
	
	@RequestMapping(value="/nearestDel.do", method=RequestMethod.GET)
	public ModelAndView nearestDel(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.nearestDel(mav);
		return mav;
	}
	
	@RequestMapping(value="/loginMember.do", method=RequestMethod.GET)
	public ModelAndView loginMember(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("loginMember.users");
	}
	
	@RequestMapping(value="/createAccount.do", method=RequestMethod.GET)
	public ModelAndView createAccount(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("createAccount.users");
	}
	
	@RequestMapping(value="/diap.do", method=RequestMethod.GET)
	public ModelAndView diap(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("diap.users");
	}
	
	@RequestMapping(value="/findId.do", method=RequestMethod.GET)
	public ModelAndView findId(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("findId.empty");
	}
	
	@RequestMapping(value="/findPwd.do", method=RequestMethod.GET)
	public ModelAndView findPwd(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("findPwd.empty");
	}
	
	@RequestMapping(value="/cart.do", method=RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("cart.users");
	}
		
	@RequestMapping(value="/orderSearch.do", method=RequestMethod.GET)
	public ModelAndView orderSearch(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("orderSearch.users");
	}
	
	@RequestMapping(value="/cancel.do", method=RequestMethod.GET)
	public ModelAndView cancel(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("cancel.users");
	}
	
	@RequestMapping(value="/ordering.do", method=RequestMethod.GET)
	public ModelAndView ordering(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("ordering.users");
	}
	
	@RequestMapping(value="/delivery.do", method=RequestMethod.GET)
	public ModelAndView delivery(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("delivery.users");
	}
	
	@RequestMapping(value="/buyList.do", method=RequestMethod.GET)
	public ModelAndView buyList(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("buyList.users");
	}
	
	@RequestMapping(value="/CustomerService_main.do", method=RequestMethod.GET)
	public ModelAndView CustomerService_main(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("CustomerService_main.users");
	}
	
	@RequestMapping(value="/CustomerService_consulting.do", method=RequestMethod.GET)
	public ModelAndView CustomerService_consulting(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("CustomerService_consulting.users");
	}
	
	@RequestMapping(value="/CustomerService_consultingList.do", method=RequestMethod.GET)
	public ModelAndView CustomerService_consultingList(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("CustomerService_consultingList.users");
	}
	@RequestMapping(value="/CustomerService_faq.do", method=RequestMethod.GET)
	public ModelAndView CustomerService_faq(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("CustomerService_faq.users");
	}
	
	@RequestMapping(value="/CustomerService_order_search.do", method=RequestMethod.GET)
	public ModelAndView CustomerService_order_search(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("CustomerService_order_search.empty");
	}
	
	@RequestMapping(value="/CustomerService_question_search.do", method=RequestMethod.GET)
	public ModelAndView CustomerService_question_search(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("CustomerService_question_search.empty");
	}
	
	@RequestMapping(value="/Map.do", method=RequestMethod.GET)
	public ModelAndView Map(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("Map.users");
	}
	
	@RequestMapping(value="/Introduction.do", method=RequestMethod.GET)
	public ModelAndView Introduction(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("Introduction.users");
	}
	
	@RequestMapping(value="/bookList.do", method=RequestMethod.GET)
	public ModelAndView bookList(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("bookList.users");
	}
	
	@RequestMapping(value="/bookInfo.do", method=RequestMethod.GET)
	public ModelAndView bookInfo(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("bookInfo.users");
	}
		
	@RequestMapping(value="/detailOrder.do", method=RequestMethod.GET)
	public ModelAndView detailOrder(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("detailOrder.users");
	}
	
	@RequestMapping(value="/eventPopup.do", method=RequestMethod.GET)
	public ModelAndView popup(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("event_popup.empty");
	}
	
	@RequestMapping(value="/newsfeed.do", method=RequestMethod.GET)
	public ModelAndView newsfeed(HttpServletRequest request,HttpServletResponse response) {
		service.newsfeedParsing(request, response);
		
		return null;
	}
	
	@RequestMapping(value="/payment.do", method=RequestMethod.GET)
	public ModelAndView payment(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("payment.users");
	}
	
	@RequestMapping(value="/addressList.do", method=RequestMethod.GET)
	public ModelAndView addressList(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("addressList.empty");
	}
	@RequestMapping(value="/searchPwd.do", method=RequestMethod.GET)
	public ModelAndView searchPwd(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("req", request);
		service.searchPwd(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/searchPwdOK.do", method=RequestMethod.GET)
	public ModelAndView pwd(HttpServletRequest request,HttpServletResponse response) {
		
		return new ModelAndView("searchPwdOK.empty");
	}
	
	@RequestMapping(value="/memberLoginOK.do", method=RequestMethod.POST)
	public ModelAndView memberLoginOK(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.memberLoginOK(mav);
		
		return mav;
	}
	@RequestMapping(value="/zipcode.do", method=RequestMethod.GET)
	public ModelAndView zipcode(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.zipcode(mav);
		
		return mav;
	}
	

	@RequestMapping(value="adminBookSearch.do", method=RequestMethod.GET)
	public ModelAndView adminBookSearch(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminBookSearch.admin");
	}
	
	@RequestMapping(value="adminBookInsert.do", method=RequestMethod.GET)
	public ModelAndView adminBookInsert(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminBookInsert.admin");
	}
	
	@RequestMapping(value="adminBookInfo.do", method=RequestMethod.GET)
	public ModelAndView adminBookInfo(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminBookInfo.admin");
	}
	
	@RequestMapping(value="adminWriterSearch.do", method=RequestMethod.GET)
	public ModelAndView adminWriterSearch(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminWriterSearch.adminEmpty");
	}
	@RequestMapping(value="adminWriterInsert.do", method=RequestMethod.GET)
	public ModelAndView adminWriterInsert(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminWriterInsert.adminEmpty");
	}
	
	@RequestMapping(value="adminMemberManage.do", method=RequestMethod.GET)
	public ModelAndView adminMemberManage(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminMemberManage.admin");
	}
	
	@RequestMapping(value="adminMap.do", method=RequestMethod.GET)
	public ModelAndView adminMap(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminMap.admin");
	}
	
	@RequestMapping(value="adminChange.do", method=RequestMethod.GET)
	public ModelAndView adminChange(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminChange.admin");
	}
	
	@RequestMapping(value="adminDelivery.do", method=RequestMethod.GET)
	public ModelAndView adminDelivery(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminDelivery.admin");
	}
	
	@RequestMapping(value="adminOrderSearch.do", method=RequestMethod.GET)
	public ModelAndView adminOrderSearch(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminOrderSearch.admin");
	}
	
	@RequestMapping(value="adminSales.do", method=RequestMethod.GET)
	public ModelAndView adminSales(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminSales.admin");
	}
	
	@RequestMapping(value="adminCstMain.do", method=RequestMethod.GET)
	public ModelAndView adminCstMain(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminCstMain.admin");
	}
	
	@RequestMapping(value="adminFaqInsert.do", method=RequestMethod.GET)
	public ModelAndView adminFaqInsert(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminFaqInsert.admin");
	}
	
	@RequestMapping(value="adminFaqMain.do", method=RequestMethod.GET)
	public ModelAndView adminFaqMain(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminFaqMain.admin");
	}
	
	@RequestMapping(value="adminFaqUpdate.do", method=RequestMethod.GET)
	public ModelAndView adminFaqUpdate(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminFaqUpdate.admin");
	}
	
	@RequestMapping(value="adminNctInsert.do", method=RequestMethod.GET)
	public ModelAndView adminNctInsert(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminNctInsert.admin");
	}
	
	@RequestMapping(value="adminNctMain.do", method=RequestMethod.GET)
	public ModelAndView adminNctMain(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminNctMain.admin");
	}
	
	@RequestMapping(value="adminNctUpdate.do", method=RequestMethod.GET)
	public ModelAndView adminNctUpdate(HttpServletRequest request, HttpServletResponse response) {		
		return new ModelAndView("adminNctUpdate.admin");
	}
}
