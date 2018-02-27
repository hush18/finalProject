package com.team3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.team3.admin.map.dto.MapDto;
import com.team3.aop.LogAspect;
import com.team3.service.ServiceInterface;
import com.team3.user.book.dao.BookDao;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.WriterDto;

import com.team3.user.member.dto.MemberDto;
import com.team3.user.oauth.bo.FacebookLoginBO;
import com.team3.user.oauth.bo.NaverLoginBO;

@Controller
public class ProController {
	
	@Autowired
	private ServiceInterface service;

	/* NaverLoginBO */
	@Autowired
	private NaverLoginBO naverLoginBO;
	@Autowired
	private FacebookLoginBO facebookLoginBO;

	// 여기부터 사용자
	// 스크롤배너 최근본상품 출력!! 후에 본인 컨트롤러도 밑의 위시리스트 출력 처럼 리턴값을 바꿔주세요~~
	public ModelAndView scroll(ModelAndView mav) {
		service.scrollBanner(mav);
		return mav;
	}

	@RequestMapping(value = "/userMain.do", method = RequestMethod.GET)
	public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response) {
		return scroll(new ModelAndView("userMain.users"));
	}

	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.myPage(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/userPoint.do", method = RequestMethod.GET)
	public ModelAndView userPoint(HttpServletRequest request, HttpServletResponse response) {

		return scroll(new ModelAndView("userPointView.users"));
	}

	@RequestMapping(value = "/updateAccount.do", method = RequestMethod.GET)
	public ModelAndView updateAccount(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.updateAccount(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/updateAccount.do", method = RequestMethod.POST)
	public ModelAndView updateAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		service.updateAccountOk(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/deleteAccount.do", method = RequestMethod.GET)
	public ModelAndView deleteAccount(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("deleteAccount.empty");
	}

	@RequestMapping(value = "/deleteAccount.do", method = RequestMethod.POST)
	public ModelAndView deleteAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		mav.addObject("request", request);
		service.deleteAccount(mav);

		return mav;
	}

	// 위시리스트 출력
	@RequestMapping(value = "/wishList.do", method = RequestMethod.GET)
	public ModelAndView wishList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.wishList(mav);

		return scroll(mav);
	}

	// 위시리스트에서 장바구니 이동
	@RequestMapping(value = "/wishListUp.do", method = RequestMethod.GET)
	public ModelAndView wishListUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		service.wishListUp(mav);
		return scroll(mav);
	}

	// 위시리스트에서 리스트 삭제
	@RequestMapping(value = "/wishListDel.do", method = RequestMethod.GET)
	public ModelAndView wishListDel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.wishListDel(mav);
		return scroll(mav);
	}

	// 위시리스트로 Insert
	@RequestMapping(value = "/wishListInsert.do", method = RequestMethod.GET)
	public ModelAndView wishListInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		// String id=request.getSession("id");
		mav.addObject("request", request);

		service.wishListInsert(mav);

		return scroll(mav);
	}

	// 최근본상품 리스트 출력
	@RequestMapping(value = "/nearestList.do", method = RequestMethod.GET)
	public ModelAndView nearestList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.nearestList(mav);

		return scroll(mav);
		// return new ModelAndView("nearestList.users");
	}

	// 최근본상품에서 장바구니로 이동
	@RequestMapping(value = "/nearestUp.do", method = RequestMethod.GET)
	public ModelAndView nearestUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.nearestUp(mav);
		// service.nearestInsert(mav);
		return scroll(mav);
	}

	// 최근본상품에서 리스트 삭제
	@RequestMapping(value = "/nearestDel.do", method = RequestMethod.GET)
	public ModelAndView nearestDel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.nearestDel(mav);
		return scroll(mav);
	}

	@RequestMapping(value = "/loginMember.do", method = RequestMethod.GET)
	public ModelAndView loginMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		String facebookUrl = facebookLoginBO.getAuthorizationUrl(session);

		mav.addObject("naverAuthUrl", naverAuthUrl);
		mav.addObject("facebookUrl", facebookUrl);
		mav.setViewName("loginMember.users");

		return mav;
	}

	@RequestMapping(value = "/naverCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView naverCreateAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		if (code != null && state != null) {
			OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
			String apiResult = naverLoginBO.getUserProfile(oauthToken);
			JSONObject jsonObj = new JSONObject(apiResult);
			jsonObj = (JSONObject) jsonObj.get("response");

			String email = (String) jsonObj.get("email");
			String name = (String) jsonObj.get("name");
			String id = (String) jsonObj.get("id");

			mav.addObject("email", email);
			mav.addObject("name", name);
			mav.addObject("id", id);
		}

		mav.setViewName("createAccount.users");
		return mav;
	}

	@RequestMapping(value = "/facebookCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView facebookCreateAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		if (code != null && state != null) {
			OAuth2AccessToken oauthToken = facebookLoginBO.getAccessToken(session, code, state);
			String apiResult = facebookLoginBO.getUserProfile(oauthToken);
			JSONObject jsonObj = new JSONObject(apiResult);

			String name = (String) jsonObj.get("name");
			String id = (String) jsonObj.get("id");

			mav.addObject("name", name);
			mav.addObject("id", id);
		}

		mav.setViewName("createAccount.users");
		return mav;
	}

	@RequestMapping(value = "/createAccount.do", method = RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("memberDto", memberDto);
		service.createAccountOk(mav);

		// LogAspect.logger.info(LogAspect.logMsg + request.getParameter("id"));
		// LogAspect.logger.info(LogAspect.logMsg + "멤버 디티오 : " + memberDto);
		return mav;
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
		ModelAndView mav= new ModelAndView();
		
		service.userMapRead(mav);
		
		return mav;
	}

	@RequestMapping(value = "/Introduction.do", method = RequestMethod.GET)
	public ModelAndView Introduction(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("Introduction.users");
	}
	
	@RequestMapping(value="/bookList.do", method=RequestMethod.GET)
	public ModelAndView bookList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		
		service.bookList(mav);
		
		mav.setViewName("bookList.users");
		return scroll(mav);
	}
	
	@RequestMapping(value="/bookInfo.do", method=RequestMethod.GET)
	public ModelAndView bookInfo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request", request);
		
		service.bookInfo(mav);
		
		mav.setViewName("bookInfo.users");
		return scroll(mav);
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
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		service.searchPwdOK(mav);
		return mav;
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

	
	
	@RequestMapping(value = "/findIdOK.do", method = RequestMethod.POST)
	public ModelAndView findIdOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.findIdOK(mav);

		return mav;
	}

	@RequestMapping(value = "logoutMember.do", method = RequestMethod.GET)
	public ModelAndView logoutMember(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("logoutMember.empty");
	}
	
	@RequestMapping(value = "diapOK.do", method = RequestMethod.POST)
	public ModelAndView diapOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.diapOK(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "searchHeader.do", method = RequestMethod.POST)
	public ModelAndView searchHeader(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("response", response);
		service.searchHeader(mav);
		return null;
	}

	
	
	
	// 여기부터 관리자 ================================================================================================================================================
	
	@RequestMapping(value="adminBookSearch.do", method=RequestMethod.GET)
	public ModelAndView adminBookSearch(HttpServletRequest request, HttpServletResponse response) {	
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookSearch(mav);
		mav.setViewName("adminBookSearch.admin");
		return mav;
	}

	@RequestMapping(value = "adminBookInsert.do", method = RequestMethod.GET)
	public ModelAndView adminBookInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminBookInsert.admin");
	}
	
	@RequestMapping(value="adminBookInfo.do", method=RequestMethod.GET)
	public ModelAndView adminBookInfo(HttpServletRequest request, HttpServletResponse response) {	
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookInfo(mav);
		mav.setViewName("adminBookInfo.admin");
		return mav;
	}
	
	@RequestMapping(value="adminBookUpdate.do", method=RequestMethod.POST)
	public ModelAndView adminBookUpdate(HttpServletRequest request, HttpServletResponse response,BookDto bookDto) {	
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("bookDto", bookDto);
		service.adminBookUpdate(mav);
		mav.setViewName("adminBookUpdateOk.admin");
		return mav;
	}
	
	@RequestMapping(value="adminWriterSearch.do", method=RequestMethod.GET)
	public ModelAndView adminWriterSearch(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminWriterSearch(mav);
		mav.setViewName("adminWriterSearch.adminEmpty");
		return mav;}


	@RequestMapping(value = "adminWriterInsert.do", method = RequestMethod.GET)
	public ModelAndView adminWriterInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminWriterInsert.adminEmpty");
	}

	@RequestMapping(value = "adminMemberManage.do", method = RequestMethod.GET)
	public ModelAndView adminMemberManage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		service.memberManage(mav);
		return mav;
	}
	
	@RequestMapping(value="adminMap.do", method=RequestMethod.GET)
	public ModelAndView adminMap(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		service.readMap(mav);
		return mav;
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
		return new ModelAndView("adminCstMain.admin");
	}

	@RequestMapping(value = "adminFaqInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminFaqInsert.admin");
	}

	@RequestMapping(value = "adminFaqMain.do", method = RequestMethod.GET)
	public ModelAndView adminFaqMain(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminFaqMain.admin");
	}

	@RequestMapping(value = "adminFaqUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminFaqUpdate(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminFaqUpdate.admin");
	}

	@RequestMapping(value = "adminNctInsert.do", method = RequestMethod.GET)
	public ModelAndView adminNctInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminNctInsert.admin");
	}

	@RequestMapping(value = "adminNctMain.do", method = RequestMethod.GET)
	public ModelAndView adminNctMain(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminNctMain.admin");
	}

	@RequestMapping(value = "adminNctUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminNctUpdate(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminNctUpdate.admin");
	}
	
	@RequestMapping(value="adminMapOk.do",method=RequestMethod.POST)
	public ModelAndView adminMapInsert(HttpServletRequest request, HttpServletResponse response, MapDto mapDto) throws Exception {		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("mapDto",mapDto);
		
		service.createMap(mav);
		return mav;
	}
	
	@RequestMapping(value="adminMapUpdate.do",method=RequestMethod.POST)
	public ModelAndView adminMapUpdate(HttpServletRequest request, HttpServletResponse response, MapDto mapDto) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("mapDto",mapDto);
		
		service.updateMap(mav);
		return mav;
	}
	
	@RequestMapping(value="adminMapDelete.do",method=RequestMethod.POST)
	public ModelAndView adminMapDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		
		service.deleteMap(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "adminMemberDelete.do", method = RequestMethod.GET)
	public ModelAndView adminMemberDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("member_number", request.getParameter("member_number"));
		service.adminMemberDelete(mav);
		return mav;
	}
	
	@RequestMapping(value = "adminMemberDeleteOK.do", method = RequestMethod.GET)
	public ModelAndView adminMemberDeleteOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.adminMemberDeleteOK(mav);
		return mav;
	}
	
}
