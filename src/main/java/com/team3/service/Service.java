package com.team3.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.team3.aop.LogAspect;
import com.team3.user.member.dao.MemberDao;
import com.team3.user.member.dto.MemberDto;
import com.team3.user.order.dao.OrderDao;
import com.team3.user.order.dto.OrderDto;

@Component
public class Service implements ServiceInterface {
	
	@Autowired
	private JavaMailSender mailSender;	// email
	
	@Autowired
	private MemberDao memberDao;
	private OrderDao orderDao;

	@Override
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response) {
		String url = "http://rss.donga.com/book.xml";
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);

		int statusCode;
		
		try {
			statusCode = client.executeMethod(method);

			if (statusCode == HttpStatus.SC_OK) {
				String res = method.getResponseBodyAsString();
				PrintWriter out = response.getWriter();
				out.print(res);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void searchPwd(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		int authNum = (int)(Math.random() * 999999) + 100000;
		
		
		String email = req.getParameter("email") + "@" + req.getParameter("emailAddress");
//		System.out.println(email);
		
		// 메일 내용을 작성한다
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("miy003@naver.com");
		msg.setTo(email);
		msg.setSubject("㈜산책 이메일 인증번호");
		msg.setText("귀하의 이메일 인증번호는 " + authNum + "입니다.");
		 
		try {
		    mailSender.send(msg);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		mav.addObject("authNum", authNum);
		mav.setViewName("searchPwd.empty");
	}
	
	@Override
	public void memberLoginOK(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		LogAspect.logger.info("로그인시작:"+id+"\t"+password);
		
		/*MemberDto memberDto=memberDao.*/
	}

	@Override
	public void zipcode(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String dong=request.getParameter("dong");
		
		if(dong!=null) {
//			List<ZipcodeDto> zipList=memberDao.zipcodeDto(dong);
//			mav.addObject("zipcodeList", zipList);
		}
		
		mav.setViewName("zipcode.empty");
	}

	@Override
	public void orderSearch(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String orderSearch_pageNumber=request.getParameter("orderSearch_pageNumber");
		if(request.getParameter("orderSearch_pageNumber")==null) {
			orderSearch_pageNumber="1";
		}
		LogAspect.logger.info(LogAspect.logMsg + "orderSearch_pageNumber:" +orderSearch_pageNumber);
		
		int pageSize=10;
		
		int currentPage=Integer.parseInt(orderSearch_pageNumber);
		int startRow=(currentPage-1)*pageSize-1;
		int endRow=currentPage*pageSize;
		
		int count=orderDao.getOrderSearchCount();
		LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
		
		List<OrderDto> orderSearchList=null;
		if(count >0) {
			orderSearchList=orderDao.orderSearchList(startRow, endRow);
			LogAspect.logger.info(LogAspect.logMsg + "orderSearchList:" +orderSearchList);
		}
		
		mav.addObject("orderSearch_pageNumber", orderSearch_pageNumber);
		mav.addObject("pageSize", pageSize);
		mav.addObject("count", count);
		mav.addObject("orderSearchList", orderSearchList);
		mav.setViewName("orderSearch.users");
		
	}
}
