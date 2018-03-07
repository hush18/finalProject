package com.team3.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.team3.admin.cst.dto.AdminCstDto;
import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dto.AdminNctDto;
import com.team3.admin.map.dto.MapDto;
import com.team3.service.ServiceInterface;
import com.team3.user.cst.dto.CstDto;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.WriterDto;
import com.team3.user.map.dto.PaymentPointDto;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.member.dto.MemberDto;
import com.team3.user.order.dto.OrderDto;
import com.team3.user.review.dto.ReviewDto;

@Controller
public class ProController {
	
	@Autowired
	private ServiceInterface service;
	
	

	// 여기부터 사용자
	// 스크롤배너 최근본상품 출력!! 후에 본인 컨트롤러도 밑의 위시리스트 출력 처럼 리턴값을 바꿔주세요~~
	public ModelAndView scroll(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		//세션받기 ID
		service.recommend(mav);
		if(session.getAttribute("mbId")!=null) {
			service.scrollBanner(mav);
		}
		return mav;
	}

	@RequestMapping(value = "/userMain.do", method = RequestMethod.GET)
	public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.getMainList(mav);
		
		return scroll(mav);
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
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		service.userPoint(mav);
		
		return scroll(mav);
		//return null;
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

		return mav;
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
	
	// 로그인 - 김미화
	@RequestMapping(value = "/loginMember.do", method = RequestMethod.GET)
	public ModelAndView loginMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("session", session);
		service.loginMember(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/naverCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView naverCreateAccount(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Throwable {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("session", session);
		service.naverCreateAccount(mav);

		return mav;
	}

	@RequestMapping(value = "/facebookCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView facebookCreateAccount(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Throwable {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("session", session);
		service.facebookCreateAccount(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/createAccount.do", method = RequestMethod.GET)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.setViewName("createAccount.users");
		return scroll(mav);
	}

	@RequestMapping(value = "/createAccount.do", method = RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("memberDto", memberDto);
		int check = service.createAccountOk(mav);
		
		
		// LogAspect.logger.info(LogAspect.logMsg + request.getParameter("id"));
		// LogAspect.logger.info(LogAspect.logMsg + "멤버 디티오 : " + memberDto);
		return mav;
	}
	
	// 휴면계정 - 김미화
	@RequestMapping(value = "/diap.do", method = RequestMethod.GET)
	public ModelAndView diap(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("diap.users");
	}
	
	// 아이디찾기 - 김미화
	@RequestMapping(value = "/findId.do", method = RequestMethod.GET)
	public ModelAndView findId(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("findId.empty");
	}
	
	// 비밀번호 찾기 - 김미화
	@RequestMapping(value = "/findPwd.do", method = RequestMethod.GET)
	public ModelAndView findPwd(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("findPwd.empty");
	}
	
	//사용자 장바구니 페이지
	@RequestMapping(value="/cart.do", method=RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.cart(mav);
		
		return scroll(mav);
	}
	
	//사용자 장바구니 삭제
	@RequestMapping(value="/cartListDelete.do", method=RequestMethod.GET)
	public ModelAndView cartListDelete(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.cartListDelete(mav);
		
		return scroll(mav);
	}
		
	//사용자 주문조회 페이지
	@RequestMapping(value="/orderSearch.do", method=RequestMethod.GET)
	public ModelAndView orderSearch(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.orderSearch(mav);
		
		return scroll(mav);
	}
	
	//사용자 주문상태 수정
	@RequestMapping(value="/statusChange.do", method=RequestMethod.GET)
	public ModelAndView statusChange(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.statusChange(mav);
		
		return mav;
	}
	
	//사용자 주문삭제
	@RequestMapping(value="/orderDelete.do", method=RequestMethod.GET)
	public ModelAndView orderDelete(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.orderDelete(mav);
		
		return mav;
	}
	
	//사용자 교환환불 페이지
	@RequestMapping(value="/cancel.do", method=RequestMethod.GET)
	public ModelAndView cancel(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.cancel(mav);
		
		return scroll(mav);
	}
	
	//사용자 주문중인상품 페이지
	@RequestMapping(value="/ordering.do", method=RequestMethod.GET)
	public ModelAndView ordering(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.ordering(mav);
		
		return scroll(mav);
	}
	
	//사용자 배송중인 상품 페이지
	@RequestMapping(value="/delivery.do", method=RequestMethod.GET)
	public ModelAndView delivery(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.delivery(mav);
		
		return scroll(mav);
	}
	
	//사용자 구매내역(배송완료) 페이지
	@RequestMapping(value="/buyList.do", method=RequestMethod.GET)
	public ModelAndView buyList(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.buyList(mav);
		
		return scroll(mav);
	}
	
	//사용자 주문상세정보 페이지
	@RequestMapping(value="/detailOrder.do", method=RequestMethod.GET)
	public ModelAndView detailOrder(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.detailOrder(mav);
		
		return mav;
	}
	
	// 고객센터 메인(TOP10) - 최은지
	@RequestMapping(value = "/CustomerService_main.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.getTopTen(mav);
		return scroll(mav);
	}

	// 고객센터 1:1문의 - 최은지
	@RequestMapping(value = "/CustomerService_consulting.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_consulting(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.cstConsulting(mav);
		return scroll(mav);
	}

	// 고객센터 1:1문의 데이터 입력 - 최은지
	@RequestMapping(value = "/CustomerService_cstOk.do", method = RequestMethod.POST)
	public ModelAndView CustomerService_cstOk(HttpServletRequest request, HttpServletResponse response, CstDto cstDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("cstDto", cstDto);
		service.cstOk(mav);

		return scroll(mav);
	}

	// 고객센터 1:1문의내역 - 최은지
	@RequestMapping(value = "/CustomerService_consultingList.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_consultingList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.cstList(mav);
		return scroll(mav);
	}

	// 고객센터 FAQ - 최은지
	@RequestMapping(value = "/CustomerService_faq.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_faq(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.getFaq(mav);
		return scroll(mav);
	}

	// 고객센터 1:1문의 주문 팝업창 - 최은지
	@RequestMapping(value = "/CustomerService_order_search.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_order_search(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.cstOrder(mav);
		return mav;
	}

	// 고객센터 1:1문의 상품 팝업창 - 최은지
	@RequestMapping(value = "/CustomerService_question_search.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_question_search(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.cstProduct(mav);
		return mav;
	}

	@RequestMapping(value = "/Map.do", method = RequestMethod.GET)
	public ModelAndView Map(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		service.userMapRead(mav);
		return scroll(mav);
	}

	@RequestMapping(value = "/Introduction.do", method = RequestMethod.GET)
	public ModelAndView Introduction(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.setViewName("Introduction.users");
		return scroll(mav);
	}

	//사용자 도서 리스트 출력 - 심제민
	@RequestMapping(value = "/bookList.do", method = RequestMethod.GET)
	public ModelAndView bookList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);

		service.bookList(mav);

		mav.setViewName("bookList.users");
		return scroll(mav);
	}
	
	//사용자 검색리스트 출력 - 심제민
	@RequestMapping(value = "/searchList.do", method = RequestMethod.GET)
	public ModelAndView searchList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.searchList(mav);
		
		mav.setViewName("bookList.users");
		return scroll(mav);
	}

	//사용자 도서 상세정보출력 - 심제민
	@RequestMapping(value = "/bookInfo.do", method = RequestMethod.GET)
	public ModelAndView bookInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mbId");
		if(id!=null) {
			service.nearestInsert(mav);
		}
		service.bookInfo(mav);
		service.reviewList(mav);

		mav.setViewName("bookInfo.users");
		return scroll(mav);
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
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("request",request);
		service.payment(mav);
		
		return scroll(mav);
	}
	
	@RequestMapping(value="/paymentOk.do", method=RequestMethod.POST)
	public ModelAndView paymentOk(HttpServletRequest request, HttpServletResponse response,PaymentPointDto paymentPointDto,OrderDto orderDto) {
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("request",request);
		mav.addObject("paymentPointDto",paymentPointDto);
		mav.addObject("orderDto",orderDto);
		service.paymentOk(mav);
		return mav;
	}

	@RequestMapping(value = "/addressList.do", method = RequestMethod.GET)
	public ModelAndView addressList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("request",request);
		service.addressList(mav);
		return mav;
	}
	
	// 비밀번호찾기-인증메일보내기 - 맹인영
	@RequestMapping(value = "/searchPwd.do", method = RequestMethod.GET)
	public ModelAndView searchPwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("req", request);
		service.searchPwd(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/addAddress.do",method=RequestMethod.GET)
	public ModelAndView addAddress(HttpServletRequest request, HttpServletResponse response,MemberAddressDto memberAddressDto) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("memberAddressDto",memberAddressDto);
		mav.addObject("request",request);
		service.addAddress(mav);
		return mav;
	}
	@RequestMapping(value="/addressDelete.do",method=RequestMethod.GET)
	public ModelAndView addressDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		service.deleteAddress(mav);
		
		return mav;
	}
	
	// 비밀번호 찾기 - 김미화
	@RequestMapping(value = "/searchPwdOK.do", method = RequestMethod.GET)
	public ModelAndView pwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.searchPwdOK(mav);
		return mav;
	}
	
	// 로그인 하기 - 김미화
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
	
	// 아이디 찾기 - 김미화
	@RequestMapping(value = "/findIdOK.do", method = RequestMethod.POST)
	public ModelAndView findIdOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.findIdOK(mav);

		return mav;
	}
	
	// 로그인아웃 - 김미화
	@RequestMapping(value = "logoutMember.do", method = RequestMethod.GET)
	public ModelAndView logoutMember(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("logoutMember.empty");
	}
	
	// 휴면계정해지하기 - 김미화
	@RequestMapping(value = "diapOK.do", method = RequestMethod.POST)
	public ModelAndView diapOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.diapOK(mav);
		
		return mav;
	}
	
	// 헤더-검색 창 - 김미화
	@RequestMapping(value = "searchHeader.do", method = RequestMethod.POST)
	public ModelAndView searchHeader(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("response", response);
		service.searchHeader(mav);
		return null;
	}
	
	//사용자 카테고리별 검색기능 - 심제민
	@RequestMapping(value = "/searchTitle.do", method = RequestMethod.POST)
	public ModelAndView searchTitle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("response", response);
		mav.addObject("request", request);
		service.searchTitle(mav);
		return null;
	}
	
	//사용자 리뷰 등록 - 심제민
	@RequestMapping(value = "/reviewInsert.do", method = RequestMethod.POST)
	public ModelAndView reviewInsert(HttpServletRequest request, HttpServletResponse response, ReviewDto reviewDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("response", response);
		mav.addObject("request", request);
		mav.addObject("reviewDto", reviewDto);
		service.reviewInsert(mav);
		return null;
	}
	
	//사용자 지정도서 검색기능 - 심제민
	@RequestMapping(value = "/searchWriter.do", method = RequestMethod.POST)
	public ModelAndView searchWriter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("response", response);
		mav.addObject("request", request);
		service.searchWriter(mav);
		return null;
	}
	
	// 여기부터 관리자
	// ================================================================================================================================================
	
	//관리자 도서리스트 출력 - 심제민
	@RequestMapping(value = "adminBookSearch.do", method = RequestMethod.GET)
	public ModelAndView adminBookSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookSearch(mav);
		mav.setViewName("adminBookSearch.admin");
		return mav;
	}

	//관리자 도서등록 - 심제민
	@RequestMapping(value = "adminBookInsert.do", method = RequestMethod.GET)
	public ModelAndView adminBookInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookInsert(mav);
		mav.setViewName("adminBookInsert.admin");
		return mav;
	}
	
	//관리자 도서 등록체크 - 심제민
	@RequestMapping(value = "adminBookInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminBookInsertOk(HttpServletRequest request, HttpServletResponse response, BookDto bookDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("bookDto", bookDto);
		service.adminBookInsertOk(mav);
		mav.setViewName("adminBookInsertOk.admin");
		return mav;
	}
	
	//관리자 도서 정보 - 심제민
	@RequestMapping(value = "adminBookInfo.do", method = RequestMethod.GET)
	public ModelAndView adminBookInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookInfo(mav);
		mav.setViewName("adminBookInfo.admin");
		return mav;
	}

	//관리자 도서 업데이트 및 체크 - 심제민
	@RequestMapping(value = "adminBookUpdate.do", method = RequestMethod.POST)
	public ModelAndView adminBookUpdate(HttpServletRequest request, HttpServletResponse response, BookDto bookDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("bookDto", bookDto);
		service.adminBookUpdate(mav);
		mav.setViewName("adminBookUpdateOk.admin");
		return mav;
	}

	//관리자 도서 삭제 및 체크 - 심제민
	@RequestMapping(value="adminBookDelete.do", method=RequestMethod.GET)
	public ModelAndView adminBookDelete(HttpServletRequest request, HttpServletResponse response,BookDto bookDto) {	
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookDelete(mav);
		mav.setViewName("adminBookDeleteOk.admin");
		return mav;
	}
	
	//관리자 저자 검색리스트 - 심제민
	@RequestMapping(value="adminWriterSearch.do", method=RequestMethod.GET)
	public ModelAndView adminWriterSearch(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminWriterSearch(mav);
		mav.setViewName("adminWriterSearch.adminEmpty");
		return mav;
	}

	//관리자 저자 등록 - 심제민
	@RequestMapping(value = "adminWriterInsert.do", method = RequestMethod.GET)
	public ModelAndView adminWriterInsert(HttpServletRequest request, HttpServletResponse response, WriterDto writerDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.setViewName("adminWriterInsert.adminEmpty");
		return mav;
	}
	
	//관리자 저자 등록체크 - 심제민
	@RequestMapping(value = "adminWriterInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminWriterInsertOk(HttpServletRequest request, HttpServletResponse response, WriterDto writerDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("writerDto", writerDto);
		service.adminWriterInsertOk(mav);
		mav.setViewName("adminWriterInsertOk.adminEmpty");
		return mav;
	}

	// 회원관리(관리자) - 김미화
	@RequestMapping(value = "adminMemberManage.do", method = RequestMethod.GET)
	public ModelAndView adminMemberManage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		service.memberManage(mav);
		return mav;
	}

	@RequestMapping(value = "adminMap.do", method = RequestMethod.GET)
	public ModelAndView adminMap(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.readMap(mav);
		return mav;
	}
	
	//관리자 교환환불 페이지
	@RequestMapping(value = "adminChange.do", method = RequestMethod.GET)
	public ModelAndView adminChange(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		service.adminChange(mav);
		return mav;
	}

	//관리자 구매내역(배송완료) 페이지
	@RequestMapping(value = "adminDelivery.do", method = RequestMethod.GET)
	public ModelAndView adminDelivery(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		service.adminDelivery(mav);
		return mav;
	}

	//관리자 주문조회 페이지
	@RequestMapping(value = "adminOrderSearch.do", method = RequestMethod.GET)
	public ModelAndView adminOrderSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		service.adminOrderSearch(mav);
		return mav;
	}
	
	//관리자 주문 상세정보 페이지
	@RequestMapping(value = "adminDetail.do", method = RequestMethod.GET)
	public ModelAndView adminDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		service.adminDetail(mav);
//		new ModelAndView("adminDetail.admin");
		return mav;
	}
	
	//관리자 주문상태 수정
	@RequestMapping(value="/adminStatusChange.do", method=RequestMethod.GET)
	public ModelAndView adminStatusChange(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.adminStatusChange(mav);
		
		return mav;
	}

	@RequestMapping(value = "adminSales.do", method = RequestMethod.GET)
	public ModelAndView adminSales(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		service.adminSales(mav);
		return mav;
		
	}

	// 관리자 고객센터 1:1문의 - 최은지
	@RequestMapping(value = "adminCstMain.do", method = RequestMethod.GET)
	public ModelAndView adminCstMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.adminCstMain(mav);
		return mav;
	}

	// 관리자 고객센터 1:1문의 답변 - 최은지
	@RequestMapping(value = "adminCstInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminCstInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminCstDto adminCstDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("adminCstDto", adminCstDto);

		service.adminCstInsertOk(mav);

		return mav;
	}

	// 관리자 고객센터 1:1문의 답변수정 - 최은지
	@RequestMapping(value = "adminCstUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminCstUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminCstDto adminCstDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminCstDto", adminCstDto);

		service.adminCstUpdateOk(mav);

		return mav;
	}

	// 관리자 고객센터 1:1문의 삭제 - 최은지
	@RequestMapping(value = "adminCstDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminCstDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminCstDeleteOk(mav);

		return mav;
	}

	// 관리자 고객센터 FAQ 입력 - 최은지
	@RequestMapping(value = "adminFaqInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminFaqInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminFaqDto adminFaqDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("adminFaqDto", adminFaqDto);

		service.adminFaqInsertOk(mav);

		return mav;
	}

	// 관리자 고객센터 메인 FAQ - 최은지
	@RequestMapping(value = "adminFaqMain.do", method = RequestMethod.GET)
	public ModelAndView adminFaqMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.adminFaqMain(mav);
		return mav;
	}

	// 관리자 고객센터 FAQ 수정 - 최은지
	@RequestMapping(value = "adminFaqUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminFaqUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqUpdate(mav);

		return mav;
	}

	// 관리자 고객센터 FAQ수정 입력 - 최은지
	@RequestMapping(value = "adminFaqUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminFaqUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminFaqDto adminFaqDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminFaqDto", adminFaqDto);

		service.adminFaqUpdateOk(mav);

		return mav;
	}

	// 관리자 고객센터 FAQ TOP10 입력 - 최은지
	@RequestMapping(value = "adminFaqTopInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqTopInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqTopInsert(mav);

		return mav;
	}

	// 관리자 고객센터 FAQ TOP10 삭제 - 최은지
	@RequestMapping(value = "adminFaqTopDelete.do", method = RequestMethod.GET)
	public ModelAndView adminFaqTopDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqTopDelete(mav);

		return mav;
	}

	// 관리자 고객센터 FAQ 삭제 완료 - 최은지
	@RequestMapping(value = "adminFaqDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminFaqDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminFaqDeleteOk(mav);
		return mav;
	}

	// 관리자 고객센터 FAQ 입력 - 최은지
	@RequestMapping(value = "adminFaqInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminFaqInsert.admin");
	}

	// 관리자 고객센터 공지사항 입력 - 최은지
	@RequestMapping(value = "adminNctInsert.do", method = RequestMethod.GET)
	public ModelAndView adminNctInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminNctInsert.admin");
	}

	// 관리자 고객센터 공지사항 입력완료 - 최은지
	@RequestMapping(value = "adminNctInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminNctInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminNctDto adminNctDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("adminNctDto", adminNctDto);

		service.adminNctInsertOk(mav);

		return mav;
	}

	// 관리자 고객센터 공지사항 메인 - 최은지
	@RequestMapping(value = "adminNctMain.do", method = RequestMethod.GET)
	public ModelAndView adminNctMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		service.adminNctMain(mav);

		return mav;
	}

	// 관리자 고객센터 공지사항 삭제 완료 - 최은지
	@RequestMapping(value = "adminNctDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminNctDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctDeleteOk(mav);

		return mav;
	}

	// 관리자 고객센터 공지사항 수정 - 최은지
	@RequestMapping(value = "adminNctUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminNctUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctUpdate(mav);

		return mav;
	}

	// 관리자 고객센터 공지사항 수정 완료 - 최은지
	@RequestMapping(value = "adminNctUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminNctUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminNctDto adminNctDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminNctDto", adminNctDto);

		service.adminNctUpdateOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminMapOk.do", method = RequestMethod.POST)
	public ModelAndView adminMapInsert(HttpServletRequest request, HttpServletResponse response, MapDto mapDto)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("mapDto", mapDto);

		service.createMap(mav);
		return mav;
	}

	@RequestMapping(value = "adminMapUpdate.do", method = RequestMethod.POST)
	public ModelAndView adminMapUpdate(HttpServletRequest request, HttpServletResponse response, MapDto mapDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("mapDto", mapDto);

		service.updateMap(mav);
		return mav;
	}

	@RequestMapping(value = "adminMapDelete.do", method = RequestMethod.POST)
	public ModelAndView adminMapDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.deleteMap(mav);

		return mav;
	}
	
	// 회원 삭제하기(관리자) - 김미화
	@RequestMapping(value = "adminMemberDelete.do", method = RequestMethod.GET)
	public ModelAndView adminMemberDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("id", request.getParameter("id"));
		mav.addObject("member_num", request.getParameter("member_num"));
		service.adminMemberDelete(mav);
		return mav;
	}
	
	// 회원 삭제하기(관리자) - 김미화
	@RequestMapping(value = "adminMemberDeleteOK.do", method = RequestMethod.GET)
	public ModelAndView adminMemberDeleteOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.adminMemberDeleteOK(mav);
		return mav;
	}

}
