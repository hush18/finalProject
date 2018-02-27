package com.team3.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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

import com.team3.admin.cst.dao.AdminCstDao;
import com.team3.admin.cst.dto.AdminCstDto;
import com.team3.admin.faq.dao.AdminFaqDao;
import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dao.AdminNctDao;
import com.team3.admin.nct.dto.AdminNctDto;
import com.team3.aop.LogAspect;
import com.team3.user.cst.dto.CstDto;
import com.team3.user.member.dao.MemberDao;

@Component
public class Service implements ServiceInterface {
	
	@Autowired
	private JavaMailSender mailSender;	// email
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private AdminFaqDao adminFaqDao;
	@Autowired
	private AdminNctDao adminNctDao;
	@Autowired
	private AdminCstDao adminCstDao;

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
	public void cstOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		CstDto cstDto = (CstDto)map.get("cstDto");
		
		LogAspect.logger.info(LogAspect.logMsg);
	}

	@Override
	public void adminFaqInsertOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		AdminFaqDto adminFaqDto = (AdminFaqDto)map.get("adminFaqDto");
		
		String content = adminFaqDto.getContent();
		content =content.replace("<br/>", "\r\n");
		content =content.replace("(", "\\(");
		content =content.replace(")", "\\)");
		
		adminFaqDto.setContent(content);
		
		int check = adminFaqDao.faqInsert(adminFaqDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqInsertOk.admin");
	}

	@Override
	public void adminFaqMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null)pageNumber="1";
		
		int currentPage = Integer.parseInt(pageNumber);
		
		int count = adminFaqDao.faqCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " +count);

		List<AdminFaqDto> adminFaqList = null;
		if(count > 0) {
			adminFaqList = adminFaqDao.adminFaqList();
		}
		
		for(int i=0; i<adminFaqList.size(); i++) {
			adminFaqList.get(i).setContent(adminFaqList.get(i).getContent().replace("\r\n", "<br/>"));
		}
		
		mav.addObject("faqList",adminFaqList);
		mav.setViewName("adminFaqMain.admin");
	}

	@Override
	public void adminFaqUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		int faqNumber = (Integer.parseInt(request.getParameter("faq_number")));
		AdminFaqDto adminFaqDto = adminFaqDao.faqSelect(faqNumber);
		
		String content = adminFaqDto.getContent();
		content =content.replace("<br/>", "\r\n");
		content =content.replace("\\(", "(");
		content =content.replace("\\)", ")");
		
		adminFaqDto.setContent(content);
		
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
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqDeleteOk.admin");
	}
	
	@Override
	public void adminNctInsertOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		AdminNctDto adminNctDto = (AdminNctDto)map.get("adminNctDto");

		String content = adminNctDto.getContent();
		content =content.replace("<br/>", "\r\n");
		content =content.replace("(", "\\(");
		content =content.replace(")", "\\)");
		
		adminNctDto.setContent(content);
		adminNctDto.setWrite_date(new Date());
		
		int check = adminNctDao.nctInsert(adminNctDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		
		mav.addObject("check",check);
		
		mav.setViewName("adminNctInsertOk.admin");
	}

	@Override
	public void adminNctMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null)pageNumber="1";
		
		int currentPage = Integer.parseInt(pageNumber);
		
		int count = adminNctDao.nctCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " +count);

		List<AdminNctDto> adminNctList = null;
		if(count > 0) {
			adminNctList = adminNctDao.adminNctList();
		}
		
		for(int i=0; i<adminNctList.size(); i++) {
			adminNctList.get(i).setContent(adminNctList.get(i).getContent().replace("\r\n", "<br/>"));
		}
		
		mav.addObject("nctList",adminNctList);
		mav.setViewName("adminNctMain.admin");	
	}

	@Override
	public void adminNctDeleteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String checked =  request.getParameter("checked");
		StringTokenizer st = new StringTokenizer(checked, ",");
		int checkSize = st.countTokens();
		int check = 0;
		
		for(int i=0; i<checkSize; i++) {
			check = adminNctDao.nctDeleteOk(st.nextToken());
		}
		LogAspect.logger.info(LogAspect.logMsg + "page" +request.getParameter("pageNumber"));
		mav.addObject("check",check);
		
		mav.setViewName("adminNctDeleteOk.admin");
	}
	
	@Override
	public void adminNctUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		int nctNumber = (Integer.parseInt(request.getParameter("notice_number")));
		AdminNctDto adminNctDto = adminNctDao.nctSelect(nctNumber);
		
		String content = adminNctDto.getContent();
		content =content.replace("<br/>", "\r\n");
		content =content.replace("\\(", "(");
		content =content.replace("\\)", ")");
		
		adminNctDto.setContent(content);
		
		mav.addObject("adminNctDto",adminNctDto);
		
		mav.setViewName("adminNctUpdate.admin");
	}

	@Override
	public void adminNctUpdateOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		AdminNctDto adminNctDto = (AdminNctDto)map.get("adminNctDto");
		
		adminNctDto.setContent(adminNctDto.getContent().replace("<br/>", "\r\n"));
		adminNctDto.setWrite_date(new Date());
		int check = adminNctDao.nctUpdateOk(adminNctDto);
		
		mav.addObject("check",check);
		LogAspect.logger.info(LogAspect.logMsg + "check: " + check);

		mav.setViewName("adminNctUpdateOk.admin");
	}

	@Override
	public void adminCstMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null)pageNumber="1";
		
		int currentPage = Integer.parseInt(pageNumber);
		
		int count = adminCstDao.cstCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " +count);

		List<AdminCstDto> adminCstList = null;
		if(count > 0) {
			adminCstList = adminCstDao.adminCstList();
		}
		
		for(int i=0; i<adminCstList.size(); i++) {
			adminCstList.get(i).setContent(adminCstList.get(i).getContent().replace("\r\n", "<br/>"));
			if(adminCstList.get(i).getAdmin_content()!=null) {
				adminCstList.get(i).setAdmin_content(adminCstList.get(i).getAdmin_content().replace("\r\n", "<br/>"));
			}
		}
		
		mav.addObject("cstList",adminCstList);
		mav.setViewName("adminCstMain.admin");	
	}
	
	@Override
	public void adminCstInsertOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		AdminCstDto adminCstDto = (AdminCstDto)map.get("adminCstDto");
		
		String content = adminCstDto.getAdmin_content();
		content =content.replace("<br/>", "\r\n");
		content =content.replace("(", "\\(");
		content =content.replace(")", "\\)");
		
		adminCstDto.setAdmin_content(content);
		
		int check = adminCstDao.cstInsertOk(adminCstDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		
		mav.addObject("check",check);
		
		mav.setViewName("adminNctInsertOk.admin");
	}

	@Override
	public void adminCstUpdateOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		AdminCstDto adminCstDto = (AdminCstDto)map.get("adminCstDto");
		
		adminCstDto.setAdmin_content(adminCstDto.getAdmin_content().replace("<br/>", "\r\n"));
		
		int check = adminCstDao.cstUpdateOk(adminCstDto);
		
		mav.addObject("check",check);
		
		LogAspect.logger.info(LogAspect.logMsg + "check: " + check);

		mav.setViewName("adminCstUpdateOk.admin");
	}
	
	@Override
	public void adminCstDeleteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String checked =  request.getParameter("checked");
		StringTokenizer st = new StringTokenizer(checked, ",");
		int checkSize = st.countTokens();
		int check = 0;
		
		for(int i=0; i<checkSize; i++) {
			check = adminCstDao.cstDeleteOk(st.nextToken());
		}
		mav.addObject("check",check);
		
		mav.setViewName("adminCstDeleteOk.admin");
	}

	@Override
	public void adminFaqTopDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int faqNumber = Integer.parseInt(request.getParameter("faq_number"));
		
		int check = adminFaqDao.faqTopDelete(faqNumber);
		
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqTopDelete.admin");
	}

	@Override
	public void adminFaqTopInsert(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int faqNumber = Integer.parseInt(request.getParameter("faq_number"));
		
		int check = adminFaqDao.faqTopInsert(faqNumber);
		
		mav.addObject("check",check);
		
		mav.setViewName("adminFaqTopInsert.admin");
	}
}
