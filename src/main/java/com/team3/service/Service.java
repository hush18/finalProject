package com.team3.service;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.team3.user.member.dto.ZipcodeDto;
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
		int authNum = 0;
		
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String email = req.getParameter("email") + "@" + req.getParameter("emailAddress");
		LogAspect.logger.info(LogAspect.logMsg+id+"\t"+name+"\t"+email);
		
		MemberDto memberDto=memberDao.memberSelect(id);
		/*LogAspect.logger.info(LogAspect.logMsg+memberDto.toString());*/
		
		if (memberDto != null) {
			if (email.equals(memberDto.getEmail()) && name.equals(memberDto.getName())) {
				// 메일 내용을 작성한다
				authNum = (int) (Math.random() * 999999) + 100000;
				System.out.println(authNum);

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
			}
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
		LogAspect.logger.info(LogAspect.logMsg+"로그인시작:"+id+"\t"+password);
		
		Map<String, Object> hmap=new HashMap<String, Object>();
		hmap.put("id", id);
		hmap.put("password", password);
		
		//마지막 로그인날짜 비교하기(휴면계정)
		Date last_login=memberDao.memberDate(hmap);
		int check=0;
		MemberDto memberDto=null;
		if (last_login != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = sdf.parse(sdf.format(new Date().getTime()));

				Calendar cal = Calendar.getInstance();
				cal.setTime(last_login);
				cal.add(Calendar.DATE, 1);
				Date loginYear = sdf.parse(sdf.format(cal.getTime()));
				System.out.println(loginYear);

				if (now.compareTo(loginYear) < 0) {
					memberDto = memberDao.memberLoginOK(hmap);
					LogAspect.logger.info(LogAspect.logMsg + "로그인확인:" + memberDto.toString());

				} else {
					check = memberDao.memberDiap(hmap);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		mav.addObject("check", check);
		mav.addObject("memberDto", memberDto);
		mav.setViewName("loginMemberOK.users");
	}

	@Override
	public void zipcode(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String dong=request.getParameter("dong");
		
		if(dong!=null) {
//			LogAspect.logger.info(LogAspect.logMsg+dong);
			
			List<ZipcodeDto> zipList=memberDao.zipcodeDto(dong);
//			LogAspect.logger.info(LogAspect.logMsg+zipList.size());
			mav.addObject("zipcodeList", zipList);
		}
		
		mav.setViewName("zipcode.empty");
	}

	//최근본상품 리스트 출력
	@Override
	public void nearestList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
		List<InterestDto> interestList=interestDao.nearestSelect(id);
		int count=interestList.size();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
		LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
		mav.addObject("interestList", interestList);
		mav.addObject("count", count);
		mav.addObject("id", id);
		mav.setViewName("nearestList.users");
	}

	//최근본상품에서 장바구니로 이동
	@Override
	public void nearestUp(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
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

	//최근본상품에서 리스트 삭제
	@Override
	public void nearestDel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
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

	// 위시리스트 출력
	@Override
	public void wishList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
		List<InterestDto> interestList=interestDao.wishListSelect(id);
		int count=interestList.size();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
		LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
		mav.addObject("interestList", interestList);
		mav.addObject("count", count);
		mav.addObject("id", id);
		mav.setViewName("wishList.users");
		
	}
	
	//위시리스트에서 장바구니 이동
	@Override
	public void wishListUp(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
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
	
	//위시리스트에서 리스트 삭제
	@Override
	public void wishListDel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
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
	public void createAccountOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		MemberDto memberDto=(MemberDto) map.get("memberDto");
		
//		LogAspect.logger.info(LogAspect.logMsg + "맴버 디티오 : " + memberDto);
		int check = memberDao.insertAccount(memberDto);
		LogAspect.logger.info(LogAspect.logMsg + "인서트 체크 값 : " + check);
		
		mav.setViewName("redirect:http://localhost:8081/mountainBooks/index.jsp");
//		mav.setViewName("userMain.users");
	}
<<<<<<< HEAD
	
	@Override
	public void findIdOK(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String name=request.getParameter("name");
		String email=request.getParameter("email")+"@"+request.getParameter("email_address");
		LogAspect.logger.info(LogAspect.logMsg + "아이디찾기 : " + name+"\t"+email);
		
		String id=memberDao.findId(name, email);
		LogAspect.logger.info(LogAspect.logMsg + "아이디찾기 : " + id);
		
		mav.addObject("id", id);
		mav.setViewName("findIdOK.empty");
	}
	
	@Override
	public void searchPwdOK(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		String password=memberDao.findPwd(id);
		LogAspect.logger.info(LogAspect.logMsg + password);
		
		mav.addObject("password", password);
		mav.setViewName("searchPwdOK.empty");
=======

	//위시리스트로 Insert
	@Override
	public void wishListInsert(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
		String isbn=request.getParameter("isbn");
		String[] strArr=isbn.split("/");
		for(int i=0;i<strArr.length;i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i]+="/";
		}
		int check=interestDao.wishListInsert(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		
		mav.addObject("check",check);
		mav.setViewName("wishListInsert.users");
		
	}

	//최근본상품 Insert
	@Override
	public void nearestInsert(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String isbn=request.getParameter("isbn");
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
		String id="user123";
		int check=interestDao.nearestInsert(id, isbn);
		LogAspect.logger.info(LogAspect.logMsg + "인서트!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + check);
	}

	@Override
	public void scrollBanner(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id="user123";
//		HttpSession session = request.getSession();		//세션받기 ID
//		String id=(String) session.getAttribute("id");
		List<InterestDto> scrollList=interestDao.scrollSelect(id);
		LogAspect.logger.info(LogAspect.logMsg + "여기까진 넘어오나요" + scrollList.toString());
		int scrollCount=scrollList.size();
		if(scrollList.size() > 2) scrollCount=2;
		LogAspect.logger.info(LogAspect.logMsg + "scrollCount: " + scrollCount);
		LogAspect.logger.info(LogAspect.logMsg + scrollList.toString());
		mav.addObject("scrollList", scrollList);
>>>>>>> 7b918c40bf4e8d69d380a2fe34c3756ed3c5e83b
	}
}
