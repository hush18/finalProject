package com.team3.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
import com.team3.user.interest.dao.InterestDao;
import com.team3.user.interest.dto.InterestDto;
import com.team3.user.member.dao.MemberDao;
import com.team3.user.member.dto.MemberDto;

@Component
public class Service implements ServiceInterface {
	
	@Autowired
	private JavaMailSender mailSender;	// email
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private InterestDao interestDao;

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
	public void nearestList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id="user123";
		List<InterestDto> interestList=interestDao.nearestSelect(id);
		int count=interestList.size();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
		LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
		mav.addObject("interestList", interestList);
		mav.addObject("count", count);
		mav.addObject("id", id);
		mav.setViewName("nearestList.users");
	}

	@Override
	public void nearestUp(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
		String isbn=request.getParameter("isbn");
		String[] strArr=isbn.split("/");
		for(int i=0;i<strArr.length;i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i]+="/";
		}
		int check=interestDao.nearestUp(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		mav.addObject("check",check);
		mav.setViewName("nearestUp.users");
	}

	@Override
	public void nearestDel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
		String isbn=request.getParameter("isbn");
		String[] strArr=isbn.split("/");
		for(int i=0;i<strArr.length;i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i]+="/";
		}
		int check=interestDao.nearestDel(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		mav.addObject("check",check);
		mav.setViewName("nearestDel.users");
		
	}

	@Override
	public void wishList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id="user123";
		List<InterestDto> interestList=interestDao.wishListSelect(id);
		int count=interestList.size();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
		LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
		mav.addObject("interestList", interestList);
		mav.addObject("count", count);
		mav.addObject("id", id);
		mav.setViewName("wishList.users");
		
	}
	@Override
	public void wishListUp(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
		String isbn=request.getParameter("isbn");
		String[] strArr=isbn.split("/");
		for(int i=0;i<strArr.length;i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i]+="/";
		}
		int check=interestDao.wishListUp(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		mav.addObject("check",check);
		mav.setViewName("wishListUp.users");
	}
	@Override
	public void wishListDel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
		String isbn=request.getParameter("isbn");
		String[] strArr=isbn.split("/");
		for(int i=0;i<strArr.length;i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i]+="/";
		}
		int check=interestDao.wishListDel(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		mav.addObject("check",check);
		mav.setViewName("wishListDel.users");
		
	}
	
	
}
