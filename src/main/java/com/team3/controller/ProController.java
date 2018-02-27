package com.team3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.team3.admin.cst.dto.AdminCstDto;
import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dto.AdminNctDto;
import com.team3.aop.LogAspect;
import com.team3.service.ServiceInterface;
import com.team3.user.cst.dto.CstDto;

@Controller
public class ProController {

	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/userMain.do", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("userMain.users");
	}

	@RequestMapping(value = "/wishList.do", method = RequestMethod.GET)
	public ModelAndView wishList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("wishList.users");
	}

	@RequestMapping(value = "/nearestList.do", method = RequestMethod.GET)
	public ModelAndView nearestList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("nearestList.users");
	}

	@RequestMapping(value = "/loginMember.do", method = RequestMethod.GET)
	public ModelAndView loginMember(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("loginMember.users");
	}

	@RequestMapping(value = "/createAccount.do", method = RequestMethod.GET)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("createAccount.users");
	}

	@RequestMapping(value = "/diap.do", method = RequestMethod.GET)
	public ModelAndView diap(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("diap.users");
	}

	@RequestMapping(value = "/findId.do", method = RequestMethod.GET)
	public ModelAndView findId(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("findId.empty");
	}

	@RequestMapping(value = "/findPwd.do", method = RequestMethod.GET)
	public ModelAndView findPwd(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("findPwd.empty");
	}

	@RequestMapping(value = "/cart.do", method = RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("cart.users");
	}

	@RequestMapping(value = "/orderSearch.do", method = RequestMethod.GET)
	public ModelAndView orderSearch(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("orderSearch.users");
	}

	@RequestMapping(value = "/cancel.do", method = RequestMethod.GET)
	public ModelAndView cancel(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("cancel.users");
	}

	@RequestMapping(value = "/ordering.do", method = RequestMethod.GET)
	public ModelAndView ordering(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("ordering.users");
	}

	@RequestMapping(value = "/delivery.do", method = RequestMethod.GET)
	public ModelAndView delivery(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("delivery.users");
	}

	@RequestMapping(value = "/buyList.do", method = RequestMethod.GET)
	public ModelAndView buyList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("buyList.users");
	}

	@RequestMapping(value = "/CustomerService_main.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_main(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_main.users");
	}

	@RequestMapping(value = "/CustomerService_consulting.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_consulting(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_consulting.users");
	}
	
	@RequestMapping(value = "/CustomerService_cstOk.do", method = RequestMethod.POST)
	public ModelAndView CustomerService_cstOk(HttpServletRequest request, HttpServletResponse response, CstDto cstDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("cstDto", cstDto);
		service.cstOk(mav);

		return mav;
	}

	@RequestMapping(value = "/CustomerService_consultingList.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_consultingList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_consultingList.users");
	}

	@RequestMapping(value = "/CustomerService_faq.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_faq(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_faq.users");
	}

	@RequestMapping(value = "/CustomerService_order_search.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_order_search(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_order_search.empty");
	}

	@RequestMapping(value = "/CustomerService_question_search.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_question_search(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_question_search.empty");
	}

	@RequestMapping(value = "/Map.do", method = RequestMethod.GET)
	public ModelAndView Map(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("Map.users");
	}

	@RequestMapping(value = "/Introduction.do", method = RequestMethod.GET)
	public ModelAndView Introduction(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("Introduction.users");
	}

	@RequestMapping(value = "/bookList.do", method = RequestMethod.GET)
	public ModelAndView bookList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("bookList.users");
	}

	@RequestMapping(value = "/bookInfo.do", method = RequestMethod.GET)
	public ModelAndView bookInfo(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("bookInfo.users");
	}

	@RequestMapping(value = "/detailOrder.do", method = RequestMethod.GET)
	public ModelAndView detailOrder(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("detailOrder.users");
	}

	@RequestMapping(value = "/eventPopup.do", method = RequestMethod.GET)
	public ModelAndView popup(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("event_popup.empty");
	}

	@RequestMapping(value = "/newsfeed.do", method = RequestMethod.GET)
	public ModelAndView newsfeed(HttpServletRequest request, HttpServletResponse response) {
		service.newsfeedParsing(request, response);

		return null;
	}

	@RequestMapping(value = "/payment.do", method = RequestMethod.GET)
	public ModelAndView payment(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("payment.users");
	}

	@RequestMapping(value = "/addressList.do", method = RequestMethod.GET)
	public ModelAndView addressList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("addressList.empty");
	}

	@RequestMapping(value = "/searchPwd.do", method = RequestMethod.GET)
	public ModelAndView searchPwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("req", request);
		service.searchPwd(mav);

		return mav;
	}

	@RequestMapping(value = "/searchPwdOK.do", method = RequestMethod.GET)
	public ModelAndView pwd(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("searchPwdOK.empty");
	}

	@RequestMapping(value = "/memberLoginOK.do", method = RequestMethod.POST)
	public ModelAndView memberLoginOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.memberLoginOK(mav);

		return mav;
	}

	@RequestMapping(value = "/zipcode.do", method = RequestMethod.GET)
	public ModelAndView zipcode(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.zipcode(mav);

		return mav;
	}

	@RequestMapping(value = "adminBookSearch.do", method = RequestMethod.GET)
	public ModelAndView adminBookSearch(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminBookSearch.admin");
	}

	@RequestMapping(value = "adminBookInsert.do", method = RequestMethod.GET)
	public ModelAndView adminBookInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminBookInsert.admin");
	}

	@RequestMapping(value = "adminBookInfo.do", method = RequestMethod.GET)
	public ModelAndView adminBookInfo(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminBookInfo.admin");
	}

	@RequestMapping(value = "adminWriterSearch.do", method = RequestMethod.GET)
	public ModelAndView adminWriterSearch(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminWriterSearch.adminEmpty");
	}

	@RequestMapping(value = "adminWriterInsert.do", method = RequestMethod.GET)
	public ModelAndView adminWriterInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminWriterInsert.adminEmpty");
	}

	@RequestMapping(value = "adminMemberManage.do", method = RequestMethod.GET)
	public ModelAndView adminMemberManage(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminMemberManage.admin");
	}

	@RequestMapping(value = "adminMap.do", method = RequestMethod.GET)
	public ModelAndView adminMap(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminMap.admin");
	}

	@RequestMapping(value = "adminChange.do", method = RequestMethod.GET)
	public ModelAndView adminChange(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminChange.admin");
	}

	@RequestMapping(value = "adminDelivery.do", method = RequestMethod.GET)
	public ModelAndView adminDelivery(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminDelivery.admin");
	}

	@RequestMapping(value = "adminOrderSearch.do", method = RequestMethod.GET)
	public ModelAndView adminOrderSearch(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminOrderSearch.admin");
	}

	@RequestMapping(value = "adminSales.do", method = RequestMethod.GET)
	public ModelAndView adminSales(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminSales.admin");
	}

	@RequestMapping(value = "adminCstMain.do", method = RequestMethod.GET)
	public ModelAndView adminCstMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);

		service.adminCstMain(mav);

		return mav;
	}
	
	@RequestMapping(value = "adminCstInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminCstInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminCstDto adminCstDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("adminCstDto", adminCstDto);

		service.adminCstInsertOk(mav);

		return mav;
	}
	
	@RequestMapping(value = "adminCstUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminCstUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminCstDto adminCstDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("adminCstDto", adminCstDto);

		service.adminCstUpdateOk(mav);

		return mav;
	}
	
	@RequestMapping(value = "adminCstDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminCstDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminCstDeleteOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminFaqInsert.admin");
	}

	@RequestMapping(value = "adminFaqInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminFaqInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminFaqDto adminFaqDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("adminFaqDto", adminFaqDto);

		service.adminFaqInsertOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqMain.do", method = RequestMethod.GET)
	public ModelAndView adminFaqMain(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqMain(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminFaqUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqUpdate(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminFaqUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminFaqDto adminFaqDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("adminFaqDto", adminFaqDto);

		service.adminFaqUpdateOk(mav);

		return mav;
	}
	
	@RequestMapping(value = "adminFaqTopInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqTopInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqTopInsert(mav);

		return mav;
	}
	
	@RequestMapping(value = "adminFaqTopDelete.do", method = RequestMethod.GET)
	public ModelAndView adminFaqTopDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqTopDelete(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminFaqDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqDeleteOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctInsert.do", method = RequestMethod.GET)
	public ModelAndView adminNctInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminNctInsert.admin");
	}

	@RequestMapping(value = "adminNctInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminNctInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminNctDto adminNctDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("adminNctDto", adminNctDto);

		service.adminNctInsertOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctMain.do", method = RequestMethod.GET)
	public ModelAndView adminNctMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctMain(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminNctDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctDeleteOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminNctUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctUpdate(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminNctUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminNctDto adminNctDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("adminNctDto", adminNctDto);

		service.adminNctUpdateOk(mav);

		return mav;
	}
}
