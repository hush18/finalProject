package com.team3.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

import com.team3.admin.faq.dao.AdminFaqDao;
import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.aop.LogAspect;
import com.team3.user.member.dao.MemberDao;

@Component
public class Service implements ServiceInterface {
	
	@Autowired
	private JavaMailSender mailSender;	// email
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private AdminFaqDao adminFaqDao;

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
	public void adminFaqInsertOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		AdminFaqDto adminFaqDto = (AdminFaqDto)map.get("adminFaqDto");
		adminFaqDto.setContent(adminFaqDto.getContent().replace("<br/>", "\r\n"));
		
		int check = adminFaqDao.faqInsert(adminFaqDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqInsertOk.admin");
	}

	@Override
	public void adminFaqMain(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int listSize = 5;
		String pageNumber = request.getParameter("pageNumber");
		String category = request.getParameter("category");
		LogAspect.logger.info(LogAspect.logMsg + category);
		if(pageNumber==null)pageNumber="1";
		
		int currentPage=Integer.parseInt(pageNumber);
		int startRow = (currentPage-1)*listSize+1;
		int endRow = currentPage*listSize;
		
		Map<String,List<AdminFaqDto>> maplist = new HashMap<String,List<AdminFaqDto>>();
		
		ArrayList<String> EkeyList=new ArrayList<String>();
		EkeyList.add("member");
		EkeyList.add("product");
		EkeyList.add("payment");
		EkeyList.add("cancel");
		EkeyList.add("order");
		EkeyList.add("delivery");
		EkeyList.add("saving");
		
		ArrayList<String> keyList=new ArrayList<String>();
		keyList.add("회원");
		keyList.add("상품");
		keyList.add("입금/결제");
		keyList.add("취소/교환/환불");
		keyList.add("주문");
		keyList.add("배송");
		keyList.add("적립");
		
		ArrayList<Integer> countList = new ArrayList<Integer>();
		
			for(int i=0;i<keyList.size();i++) {
				countList.add(adminFaqDao.faqCount(keyList.get(i)));
				LogAspect.logger.info(LogAspect.logMsg + countList.toString());
				List<AdminFaqDto> list = adminFaqDao.adminFaqList(keyList.get(i),startRow,endRow);
				LogAspect.logger.info(LogAspect.logMsg +list.size());
				if(list!=null) {
					maplist.put(EkeyList.get(i), list);
				}
				
				for(int j=0;j<list.size();j++) {
					maplist.get(EkeyList.get(i)).get(j).setContent(maplist.get(EkeyList.get(i)).get(j).getContent().replace("\r\n", "<br/>"));
					LogAspect.logger.info(LogAspect.logMsg + maplist.get(EkeyList.get(i)).get(j).toString());
				}
			}
			
		mav.addObject("maplist",maplist);
		mav.addObject("pageNumber",currentPage);
		mav.addObject("listSize",listSize);
		mav.addObject("startRow",startRow);
		mav.addObject("endRow",endRow);
		mav.addObject("countList",countList);
		mav.addObject("EkeyList",EkeyList);
		
		mav.setViewName("adminFaqMain.admin");
	}

	@Override
	public void adminFaqUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		int faqNumber = (Integer.parseInt(request.getParameter("faq_number")));
		AdminFaqDto adminFaqDto = adminFaqDao.faqSelect(faqNumber);
		
		mav.addObject("adminFaqDto",adminFaqDto);
		
		mav.setViewName("adminFaqUpdate.admin");
	}

	@Override
	public void adminFaqUpdateOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		AdminFaqDto adminFaqDto = (AdminFaqDto)map.get("adminFaqDto");
		
		adminFaqDto.setContent(adminFaqDto.getContent().replace("<br/>", "\r\n"));
		
		int check = adminFaqDao.faqUpdateOk(adminFaqDto);
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqUpdateOk.admin");
	}

	@Override
	public void adminFaqDeleteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String checked =  request.getParameter("checked");
		StringTokenizer st = new StringTokenizer(checked, ",");
		int checkSize = st.countTokens();
		int check = 0;
		
		for(int i=0; i<checkSize; i++) {
			check = adminFaqDao.faqDeleteOk(st.nextToken());
		}
		LogAspect.logger.info(LogAspect.logMsg + "page" +request.getParameter("pageNumber"));
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqDeleteOk.admin");
	}
	
}
