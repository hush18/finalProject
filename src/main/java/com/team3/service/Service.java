package com.team3.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.*;
import javax.servlet.http.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.ModelAndView;
import com.team3.admin.cst.dao.AdminCstDao;
import com.team3.admin.cst.dto.AdminCstDto;
import com.team3.admin.faq.dao.AdminFaqDao;
import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dao.AdminNctDao;
import com.team3.admin.nct.dto.AdminNctDto;
import com.team3.admin.order.dao.AdminOrderDao;
import com.team3.aop.LogAspect;
import com.team3.user.cst.dao.CstDao;
import com.team3.user.cst.dto.CstDto;
import com.team3.user.cst.dto.CstOrderDto;
import com.team3.user.cst.dto.CstQuestionDto;
import com.team3.user.cstList.dao.CstListDao;
import com.team3.user.cstList.dto.CstListDto;
import com.team3.user.member.dao.MemberDao;
import com.team3.admin.book.dao.AdminBook;
import com.team3.user.book.dao.BookDao;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;
import com.team3.admin.map.dao.AdminMapDao;
import com.team3.admin.map.dto.MapDto;
import com.team3.admin.member.dao.MemberManageDao;
import com.team3.user.member.dto.ZipcodeDto;
import com.team3.admin.sales.dao.SalesDao;
import com.team3.admin.sales.dto.SalesDto;

import com.team3.user.oauth.bo.FacebookLoginBO;
import com.team3.user.oauth.bo.NaverLoginBO;
import com.team3.user.faq.dao.FaqDao;
import com.team3.user.faq.dto.FaqDto;
import com.team3.user.interest.dao.InterestDao;
import com.team3.user.interest.dto.InterestDto;
import com.team3.user.map.dao.PaymentDao;
import com.team3.user.map.dto.PaymentPointDto;
import com.team3.user.map.dto.PointDto;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.member.dto.MemberDto;
import com.team3.user.order.dao.OrderDao;
import com.team3.user.order.dto.CartDto;
import com.team3.user.order.dto.OrderDto;
import com.team3.user.review.dao.ReviewDao;
import com.team3.user.review.dto.ReviewDto;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Component
public class Service implements ServiceInterface {

	@Autowired
	private JavaMailSender mailSender; // email

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private AdminOrderDao adminOrderDao;
	@Autowired
	private AdminFaqDao adminFaqDao;
	@Autowired
	private AdminNctDao adminNctDao;
	@Autowired
	private AdminCstDao adminCstDao;

	@Autowired
	private AdminBook adminBook;

	@Autowired
	private InterestDao interestDao;

	@Autowired
	private SalesDao salesDao;

	@Autowired
	private AdminMapDao adminMapDao;

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private BookDao bookDao;

	@Autowired
	private MemberManageDao memberManageDao;

	@Autowired
	private FaqDao faqDao;
	
	@Autowired
	private CstDao cstDao;
	
	@Autowired
	private CstListDao cstListDao;

	/* NaverLoginBO */
	@Autowired
	private NaverLoginBO naverLoginBO;

	@Autowired
	private FacebookLoginBO facebookLoginBO;
	
	@Autowired
	private ReviewDao reviewDao;

	@Override
	public void getMainList(ModelAndView mav) {
		List<BookDto> bestBookList = bookDao.getMainList();
		List<BookDto> hotBookList = bookDao.getMainList();
		List<BookDto> newBookList = bookDao.getMainList();
		List<AdminNctDto> nctList = adminNctDao.getNctList();
		List<AdminFaqDto> faqList = adminFaqDao.getFaqList();

		// LogAspect.logger.info(LogAspect.logMsg + "메인에 뿌려줄 리스트 : " + bookList);

		mav.addObject("bestBookList", bestBookList);
		mav.addObject("hotBookList", hotBookList);
		mav.addObject("newBookList", newBookList);
		mav.addObject("nctList", nctList);
		mav.addObject("faqList", faqList);

		mav.setViewName("userMain.users");
	}

	@Override
	public void loginMember(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpSession session = (HttpSession) map.get("session");

		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		String facebookUrl = facebookLoginBO.getAuthorizationUrl(session);

		mav.addObject("naverAuthUrl", naverAuthUrl);
		mav.addObject("facebookUrl", facebookUrl);
		mav.setViewName("loginMember.users");
	}

	@Override
	public void naverCreateAccount(ModelAndView mav) throws Throwable {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");

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

	}

	@Override
	public void facebookCreateAccount(ModelAndView mav) throws Throwable {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");

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

	}

	@Override
	public void myPage(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		HttpSession session = request.getSession();
		MemberDto memberDto = memberDao.updateAccount(session);

		mav.addObject("memberDto", memberDto);
		mav.setViewName("myPage.users");
	}

	@Override
	public void updateAccount(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		HttpSession session = request.getSession();
		MemberDto memberDto = memberDao.updateAccount(session);

		mav.addObject("memberDto", memberDto);
		mav.setViewName("updateAccount.users");
	}

	@Override
	public void updateAccountOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto) map.get("memberDto");

		// System.out.println(memberDto);
		int check = memberDao.updateAccountOk(memberDto);

		mav.addObject("check", check);
		mav.setViewName("updateAccountOk.users");
	}

	@Override
	public void deleteAccount(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		MemberDto memberDto = (MemberDto) map.get("memberDto");

		memberDao.deleteDataOfCart(memberDto);
		memberDao.deleteDataOfDel(memberDto);
		int check = memberDao.deleteAccount(memberDto);

		if (check > 0) {
			HttpSession session = request.getSession();
			session.removeAttribute("mbId");
		}
		mav.addObject("check", check);
		mav.setViewName("deleteAccountOk.users");
	}

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

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email") + "@" + req.getParameter("emailAddress");
		LogAspect.logger.info(LogAspect.logMsg + id + "\t" + name + "\t" + email);

		MemberDto memberDto = memberDao.memberSelect(id);
		/* LogAspect.logger.info(LogAspect.logMsg+memberDto.toString()); */

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

					
	// 회원로그인하기
	@Override
	public void memberLoginOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LogAspect.logger.info(LogAspect.logMsg + "로그인시작:" + id + "\t" + password);

		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("id", id);
		hmap.put("password", password);

		// 마지막 로그인날짜 비교하기(휴면계정)
		Date last_login = memberDao.memberDate(hmap);
		int check = 0;
		MemberDto memberDto = null;
		if (last_login != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = sdf.parse(sdf.format(new Date().getTime()));

				Calendar cal = Calendar.getInstance();
				cal.setTime(last_login);
				cal.add(Calendar.YEAR, 1);
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
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String dong = request.getParameter("dong");

		if (dong != null) {
			List<ZipcodeDto> zipList = memberDao.zipcodeDto(dong);
			mav.addObject("zipcodeList", zipList);
		}
		mav.setViewName("zipcode.empty");
	}

	@Override
	public void adminFaqInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AdminFaqDto adminFaqDto = (AdminFaqDto) map.get("adminFaqDto");

		String content = adminFaqDto.getContent();
		content = content.replace("<br/>", "\r\n");
		content = content.replace("(", "\\(");
		content = content.replace(")", "\\)");

		adminFaqDto.setContent(content);

		int check = adminFaqDao.faqInsert(adminFaqDto);
		LogAspect.logger.info(LogAspect.logMsg + check);

		mav.addObject("check", check);
		mav.setViewName("adminFaqInsertOk.admin");
	}


	/*
	 * public void deleteFile(MapDto mapDto,String[] oldPathList) { String realPath
	 * = Service.class.getResource("").getPath()
	 * .replace("apache-tomcat-8.0.47/wtpwebapps", "workspace")
	 * .replace("WEB-INF/classes/com/team3/service/", "src/main/webapp");
	 * 
	 * for(int i=0;i<oldPathList.length;i++) { File file=new File(realPath+
	 * "/adminImg",oldPathList[i]); file.delete(); } }
	 */
	@Override
	public void adminFaqMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int count = adminFaqDao.faqCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);

		List<AdminFaqDto> adminFaqList = null;
		if (count > 0) {
			adminFaqList = adminFaqDao.adminFaqList();
		}

		for (int i = 0; i < adminFaqList.size(); i++) {
			adminFaqList.get(i).setContent(adminFaqList.get(i).getContent().replace("\r\n", "<br/>"));
		}

		mav.addObject("faqList", adminFaqList);
		mav.setViewName("adminFaqMain.admin");
	}

	@Override
	public void adminFaqUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int faqNumber = (Integer.parseInt(request.getParameter("faq_number")));
		AdminFaqDto adminFaqDto = adminFaqDao.faqSelect(faqNumber);

		String content = adminFaqDto.getContent();
		content = content.replace("<br/>", "\r\n");
		content = content.replace("\\(", "(");
		content = content.replace("\\)", ")");

		adminFaqDto.setContent(content);

		mav.addObject("adminFaqDto", adminFaqDto);

		mav.setViewName("adminFaqUpdate.admin");
	}

	@Override
	public void adminFaqUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		AdminFaqDto adminFaqDto = (AdminFaqDto) map.get("adminFaqDto");

		adminFaqDto.setContent(adminFaqDto.getContent().replace("<br/>", "\r\n"));

		int check = adminFaqDao.faqUpdateOk(adminFaqDto);
		mav.addObject("check", check);

		mav.setViewName("adminFaqUpdateOk.admin");
	}

	@Override
	public void adminFaqDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String checked = request.getParameter("checked");
		StringTokenizer st = new StringTokenizer(checked, ",");
		int checkSize = st.countTokens();
		int check = 0;

		for (int i = 0; i < checkSize; i++) {
			check = adminFaqDao.faqDeleteOk(st.nextToken());
		}
		mav.addObject("check", check);

		mav.setViewName("adminFaqDeleteOk.admin");
	}

	@Override
	public void adminNctInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AdminNctDto adminNctDto = (AdminNctDto) map.get("adminNctDto");

		String content = adminNctDto.getContent();
		content = content.replace("<br/>", "\r\n");
		content = content.replace("(", "\\(");
		content = content.replace(")", "\\)");

		adminNctDto.setContent(content);
		adminNctDto.setWrite_date(new Date());

		int check = adminNctDao.nctInsert(adminNctDto);
		LogAspect.logger.info(LogAspect.logMsg + check);

		mav.addObject("check", check);

		mav.setViewName("adminNctInsertOk.admin");
	}

	@Override
	public void adminNctMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int count = adminNctDao.nctCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);

		List<AdminNctDto> adminNctList = null;
		if (count > 0) {
			adminNctList = adminNctDao.adminNctList();
		}

		for (int i = 0; i < adminNctList.size(); i++) {
			adminNctList.get(i).setContent(adminNctList.get(i).getContent().replace("\r\n", "<br/>"));
		}

		mav.addObject("nctList", adminNctList);
		mav.setViewName("adminNctMain.admin");
	}

	@Override
	public void adminNctDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String checked = request.getParameter("checked");
		StringTokenizer st = new StringTokenizer(checked, ",");
		int checkSize = st.countTokens();
		int check = 0;

		for (int i = 0; i < checkSize; i++) {
			check = adminNctDao.nctDeleteOk(st.nextToken());
		}
		LogAspect.logger.info(LogAspect.logMsg + "page" + request.getParameter("pageNumber"));
		mav.addObject("check", check);

		mav.setViewName("adminNctDeleteOk.admin");
	}

	@Override
	public void adminNctUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int nctNumber = (Integer.parseInt(request.getParameter("notice_number")));
		AdminNctDto adminNctDto = adminNctDao.nctSelect(nctNumber);

		String content = adminNctDto.getContent();
		content = content.replace("<br/>", "\r\n");
		content = content.replace("\\(", "(");
		content = content.replace("\\)", ")");

		adminNctDto.setContent(content);

		mav.addObject("adminNctDto", adminNctDto);

		mav.setViewName("adminNctUpdate.admin");
	}

	@Override
	public void adminNctUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		AdminNctDto adminNctDto = (AdminNctDto) map.get("adminNctDto");

		adminNctDto.setContent(adminNctDto.getContent().replace("<br/>", "\r\n"));
		adminNctDto.setWrite_date(new Date());
		int check = adminNctDao.nctUpdateOk(adminNctDto);

		mav.addObject("check", check);
		LogAspect.logger.info(LogAspect.logMsg + "check: " + check);

		mav.setViewName("adminNctUpdateOk.admin");
	}

	@Override
	public void adminCstMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();

		int count = adminCstDao.cstCount();

		List<AdminCstDto> adminCstList = new ArrayList<AdminCstDto>();
		if (count > 0) {
			adminCstList = adminCstDao.adminCstList();
		}

		for (int i = 0; i < adminCstList.size(); i++) {
			adminCstList.get(i).setContent(adminCstList.get(i).getContent().replace("\r\n", "<br/>"));
			if (adminCstList.get(i).getAdmin_content() != null) {
				adminCstList.get(i).setAdmin_content(adminCstList.get(i).getAdmin_content().replace("\r\n", "<br/>"));
			}
		}

		mav.addObject("cstList", adminCstList);
		mav.setViewName("adminCstMain.admin");
	}

	@Override
	public void adminCstInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		AdminCstDto adminCstDto = (AdminCstDto) map.get("adminCstDto");

		String content = adminCstDto.getAdmin_content();
		content = content.replace("<br/>", "\r\n");
		content = content.replace("(", "\\(");
		content = content.replace(")", "\\)");

		adminCstDto.setAdmin_content(content);

		int check = adminCstDao.cstInsertOk(adminCstDto);
		LogAspect.logger.info(LogAspect.logMsg + check);

		mav.addObject("check", check);

		mav.setViewName("adminCstInsertOk.admin");
	}

	@Override
	public void adminCstUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		AdminCstDto adminCstDto = (AdminCstDto) map.get("adminCstDto");

		adminCstDto.setAdmin_content(adminCstDto.getAdmin_content().replace("<br/>", "\r\n"));

		int check = adminCstDao.cstUpdateOk(adminCstDto);

		mav.addObject("check", check);

		LogAspect.logger.info(LogAspect.logMsg + "check: " + check);

		mav.setViewName("adminCstUpdateOk.admin");
	}

	@Override
	public void adminCstDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String checked = request.getParameter("checked");
		StringTokenizer st = new StringTokenizer(checked, ",");
		int checkSize = st.countTokens();
		int check = 0;

		for (int i = 0; i < checkSize; i++) {
			check = adminCstDao.cstDeleteOk(st.nextToken());
		}
		mav.addObject("check", check);

		mav.setViewName("adminCstDeleteOk.admin");
	}

	@Override
	public void adminFaqTopDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int faqNumber = Integer.parseInt(request.getParameter("faq_number"));

		int check = adminFaqDao.faqTopDelete(faqNumber);

		mav.addObject("check", check);

		mav.setViewName("adminFaqTopDelete.admin");
	}

	@Override
	public void adminFaqTopInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int faqNumber = Integer.parseInt(request.getParameter("faq_number"));

		int check = adminFaqDao.faqTopInsert(faqNumber);

		mav.addObject("check", check);

		mav.setViewName("adminFaqTopInsert.admin");
	}

	@Override
	public void createMap(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");

		MapDto mapDto = (MapDto) map.get("mapDto");
		List<MultipartFile> fileList = request.getFiles("map_img_file");
		addfile(fileList, mapDto);

		mapDto.setDirections(mapDto.getDirections().replace("\r\n", "<br>"));
		mapDto.setStore_explanation(mapDto.getStore_explanation().replace("\r\n", "<br>"));

		LogAspect.logger.info(LogAspect.logMsg + mapDto.toString());
		int check = adminMapDao.mapInsert(mapDto);
		LogAspect.logger.info(LogAspect.logMsg + check);

		mav.addObject("check", check);
		mav.setViewName("adminMapOk.admin");
	}

	public void addfile(List<MultipartFile> fileList, MapDto mapDto) {
		for (int i = 0; i < fileList.size(); i++) {
			long fileSize = fileList.get(i).getSize();
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + fileList.get(i).getOriginalFilename();
			if (fileSize != 0) {
				String realPath = Service.class.getResource("").getPath()
						.replace("apache-tomcat-8.0.47/wtpwebapps", "workspace")
						.replace("WEB-INF/classes/com/team3/service/", "src/main/webapp");
				File path = new File(realPath + "/adminImg");
				path.mkdir();

				if (path.exists() && path.isDirectory()) {
					File file = new File(path, fileName);
					try {
						fileList.get(i).transferTo(file);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (mapDto.getImg_path() == null) {
						mapDto.setImg_path(fileName);
					} else {
						mapDto.setImg_path(mapDto.getImg_path() + "," + fileName);
					}
				}
			}
		}
	}

	@Override
	public void readMap(ModelAndView mav) {
		List<MapDto> mapList = adminMapDao.mapRead();

		mav.addObject("mapList", mapList);
		mav.setViewName("adminMap.admin");
	}

	@Override
	public void updateMap(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		MapDto mapDto = (MapDto) map.get("mapDto");

		String[] oldPathList = mapDto.getImg_path().split(",");
		List<MultipartFile> fileList = request.getFiles("map_img_file");

		mapDto.setImg_path(null);
		// 여기가 파일사이즈출력하는거
		addfile(fileList, mapDto);
		int check = 0;

		check = adminMapDao.mapUpdate(mapDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		if (check > 0&&mapDto.getImg_path()!=null) {
			deleteFile(oldPathList);
		}

		mav.addObject("check", check);
		mav.setViewName("adminMapUpdate.admin");
	}

	/*
	 * public void deleteFile(MapDto mapDto,String[] oldPathList) { String realPath
	 * = Service.class.getResource("").getPath()
	 * .replace("apache-tomcat-8.0.47/wtpwebapps", "workspace")
	 * .replace("WEB-INF/classes/com/team3/service/", "src/main/webapp");
	 * 
	 * for(int i=0;i<oldPathList.length;i++) { File file=new File(realPath+
	 * "/adminImg",oldPathList[i]); file.delete(); } }
	 */

	// 파일삭제 메소드
	public void deleteFile(String[] oldPathList) {
		String realPath = Service.class.getResource("").getPath()
				.replace("apache-tomcat-8.0.47/wtpwebapps", "workspace")
				.replace("WEB-INF/classes/com/team3/service/", "src/main/webapp");

		for (int i = 0; i < oldPathList.length; i++) {
			File file = new File(realPath + "/adminImg", oldPathList[i]);
			if (file.delete()) {
				LogAspect.logger.info(LogAspect.logMsg + oldPathList[i] + "파일삭제 완료");
			}
		}
	}

	@Override
	public void deleteMap(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String store_name = request.getParameter("store_name");

		// HashMap<String, String> infoMap = new HashMap<String, String>();
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("id", id);
		infoMap.put("password", password);
		infoMap.put("name", name);
		infoMap.put("store_name", store_name);
		LogAspect.logger.info(LogAspect.logMsg + infoMap.toString());
		MemberDto memberDto = adminMapDao.getMemberInfo(infoMap);

		String[] oldPathList = request.getParameter("hidden_path").split(",");
		int check = 0;
		// 아이디 비번이 일치할때
		if (memberDto != null) {
			// 아이디가 관리자일때 삭제
			if (Integer.parseInt(memberDto.getMember_number()) > 0
					&& Integer.parseInt(memberDto.getMember_number()) < 100
					&& memberDto.getName().equals(infoMap.get("name"))) {
				LogAspect.logger.info(LogAspect.logMsg + "앙삭제띠");
				check = adminMapDao.mapDelete(infoMap.get("store_name"));
			} else {
				LogAspect.logger.info(LogAspect.logMsg + "이름이 틀려서 삭제실패띠");
			}
		} else {
			LogAspect.logger.info(LogAspect.logMsg + "아이디 비밀번호 불일치");
		}
		if (check > 0) {
			deleteFile(oldPathList);
		}
		mav.addObject("check", check);
		mav.setViewName("adminMapDelete.admin");
	}

	// 최근본상품 리스트 출력
	@Override
	public void nearestList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID
		if (session.getAttribute("mbId") != null) {
			String id = (String) session.getAttribute("mbId");
			List<InterestDto> interestList = interestDao.nearestSelect(id);
			int count = interestList.size();
			LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
			LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
			mav.addObject("interestList", interestList);
			mav.addObject("count", count);
			mav.addObject("id", id);
		}
		mav.setViewName("nearestList.users");
	}

	// 최근본상품에서 장바구니로 이동
	@Override
	public void nearestUp(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID

		int check = 0;
		if (session.getAttribute("mbId") != null) {
			String id = (String) session.getAttribute("mbId");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			check = interestDao.nearestUp(id, strArr);
			mav.addObject("check", check);
		}
		mav.setViewName("nearestUp.users");
	}

	// 최근본상품에서 리스트 삭제
	@Override
	public void nearestDel(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID

		int check = 0;
		if (session.getAttribute("mbId") != null) {
			String id = (String) session.getAttribute("mbId");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			check = interestDao.nearestDel(id, strArr);
			mav.addObject("check", check);
		}
		mav.setViewName("nearestDel.users");

	}

	// 위시리스트 출력
	@Override
	public void wishList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID
		if (session.getAttribute("mbId") != null) {
			String id = (String) session.getAttribute("mbId");
			List<InterestDto> interestList = interestDao.wishListSelect(id);
			int count = interestList.size();
			LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
			LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
			mav.addObject("interestList", interestList);
			mav.addObject("count", count);
			mav.addObject("id", id);
		}
		mav.setViewName("wishList.users");

	}

	// 위시리스트에서 장바구니 이동
	@Override
	public void wishListUp(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID
		int check = 0;
		if (session.getAttribute("mbId") != null) {
			String id = (String) session.getAttribute("mbId");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			check = interestDao.wishListUp(id, strArr);
			mav.addObject("check", check);
		}
		mav.setViewName("wishListUp.users");
	}

	// 위시리스트에서 리스트 삭제
	@Override
	public void wishListDel(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID
		int check = 0;
		if (session.getAttribute("mbId") != null) {
			String id = (String) session.getAttribute("mbId");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			check = interestDao.wishListDel(id, strArr);
			mav.addObject("check", check);
		}
		mav.setViewName("wishListDel.users");

	}

	public int createAccountOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		MemberDto memberDto = (MemberDto) map.get("memberDto");

		// LogAspect.logger.info(LogAspect.logMsg + "맴버 디티오 : " + memberDto);
		int check = memberDao.insertAccount(memberDto);
		LogAspect.logger.info(LogAspect.logMsg + "인서트 체크 값 : " + check);
		
		mav.setViewName("createAccount.users");
		mav.addObject("check", check);

		//mav.setViewName("redirect:http://localhost:8081/mountainBooks/index.jsp");
		return check;
	}

	// 회원 아이디 찾기
	@Override
	public void findIdOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String name = request.getParameter("name");
		String email = request.getParameter("email") + "@" + request.getParameter("email_address");
		LogAspect.logger.info(LogAspect.logMsg + "아이디찾기 : " + name + "\t" + email);

		String id = memberDao.findId(name, email);
		LogAspect.logger.info(LogAspect.logMsg + "아이디찾기 : " + id);

		mav.addObject("id", id);
		mav.setViewName("findIdOK.empty");
	}

	// 비밀번호 찾기
	@Override
	public void searchPwdOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("mbId");
		String password = memberDao.findPwd(id);
		LogAspect.logger.info(LogAspect.logMsg + password);

		mav.addObject("password", password);
		mav.setViewName("searchPwdOK.empty");
	}

	// 위시리스트로 Insert
	@Override
	public void wishListInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); // 세션받기 ID
		String id = (String) session.getAttribute("mbId");
		String isbn = request.getParameter("isbn");

		String[] strArr = isbn.split("/");
		for (int i = 0; i < strArr.length; i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i] + "확인");
			strArr[i] += "/";
		}
		int check = interestDao.wishListInsert(id, strArr);

		mav.addObject("check", check);
		mav.setViewName("wishListInsert.users");

	}

	// 최근본상품 Insert
	@Override
	public void nearestInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String isbn = request.getParameter("isbn");
		HttpSession session = request.getSession(); // 세션받기 ID
		String id = (String) session.getAttribute("mbId");
		interestDao.nearestInsert(id, isbn);
	}

	@Override
	public void scrollBanner(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();		//세션받기 ID
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			List<InterestDto> scrollList=interestDao.scrollSelect(id);
			LogAspect.logger.info(LogAspect.logMsg + "리스트 출력!!!" + scrollList);
			int scrollCount=scrollList.size();
			if(scrollList.size() > 2) scrollCount=2;
			mav.addObject("scrollList", scrollList);
		}
	}

	@Override
	public void adminSales(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String value = request.getParameter("value");
		List<SalesDto> salesList = salesDao.salesSelect(value);
		mav.addObject("value", value);
		mav.addObject("salesList", salesList);
		mav.setViewName("adminSales.admin");
	}

	@Override
	public void userMapRead(ModelAndView mav) {
		List<MapDto> mapList = adminMapDao.mapRead();

		LogAspect.logger.info(LogAspect.logMsg + mapList.size());
		for (int i = 0; i < mapList.size(); i++) {
			mapList.get(i).setDirections(mapList.get(i).getDirections().replace("\r\n", "<br>"));
			mapList.get(i).setStore_explanation(mapList.get(i).getStore_explanation().replace("\r\n", "<br>"));
		}
		mav.addObject("mapList", mapList);
		mav.setViewName("Map.users");
	}

	// 휴면계정해지하기
	@Override
	public void diapOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("id", id);
		hmap.put("password", password);

		int check = 0;
		Date last_login = memberDao.memberDate(hmap);
		if (last_login != null) {
			try {
				check = -1;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = sdf.parse(sdf.format(new Date().getTime()));

				Calendar cal = Calendar.getInstance();
				cal.setTime(last_login);
				cal.add(Calendar.YEAR, 1);
				Date loginYear = sdf.parse(sdf.format(cal.getTime()));
				System.out.println(loginYear);

				if (now.compareTo(loginYear) > 0) {
					check = memberDao.diapOK(hmap);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		LogAspect.logger.info(LogAspect.logMsg + "휴면해지했수꽈? " + check);
		mav.addObject("check", check);
		mav.setViewName("diapOK.empty");
	}

	// 회원관리(관리자)
	@Override
	public void memberManage(ModelAndView mav) {
		List<MemberDto> memberList = memberManageDao.memberManage();
		LogAspect.logger.info(LogAspect.logMsg + memberList.size());

					
		for (int i = 0; i < memberList.size(); i++) {
			Date last_login = memberList.get(i).getLast_login();

			// 휴면계정 처리하기
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = sdf.parse(sdf.format(new Date().getTime()));

				Calendar cal = Calendar.getInstance();
				cal.setTime(last_login);
				cal.add(Calendar.YEAR, 1);
				Date loginYear = sdf.parse(sdf.format(cal.getTime()));

				if (now.compareTo(loginYear) > 0) {
					memberManageDao.memberDiapCheck();
				}else {
					memberManageDao.memberDiapChecking();
				}
				
				memberList = memberManageDao.memberManage();

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		mav.addObject("memberList", memberList);
		mav.setViewName("adminMemberManage.admin");
	}

	// 회원 삭제하기 (관리자)
	@Override
	public void adminMemberDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		mav.addObject(map.get("id"));
		mav.setViewName("adminMemberDelete.adminEmpty");
	}

	@Override
	public void adminMemberDeleteOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		int check = 0;

		List<MemberDto> abminPassword = memberManageDao.adminGetPassword();

		for (int i = 0; i < abminPassword.size(); i++) {
			if (password.equals(abminPassword.get(i).getPassword())) {
				check = memberManageDao.adminMemberDelete(id);
			}
		}

		mav.addObject("check", check);
		mav.setViewName("adminMemberDeleteOK.adminEmpty");
	}

	// Header 도서, 저자 검색하기
	@Override
	public void searchHeader(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");

		List<BookDto> bookList = bookDao.bookListMH();
		LogAspect.logger.info(LogAspect.logMsg + bookList.size());

		try {
			JSONArray arrTitle = new JSONArray();
			JSONArray arrName = new JSONArray();
			String jsonStr = null;
			/* HashMap<String, String> hmap=null; */

			for (int i = 0; i < bookList.size(); i++) {
				BookDto bookDto = bookList.get(i);

				String searchValue = bookDto.getTitle() + " - " + bookDto.getName();

				arrTitle.add(searchValue);
			}

			jsonStr = JSONValue.toJSONString(arrTitle);
			response.setContentType("application/x-json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void searchTitle(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String category = request.getParameter("category");
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리 값 : " + category);

		List<BookDto> bookTitleList = bookDao.getBookTitleList(category.trim());
		LogAspect.logger.info(LogAspect.logMsg + bookTitleList.size());

		try {
			JSONArray arrTitle = new JSONArray();
			JSONArray arrName = new JSONArray();
			String jsonStr = null;
			/* HashMap<String, String> hmap=null; */

			for (int i = 0; i < bookTitleList.size(); i++) {
				BookDto bookDto = bookTitleList.get(i);
				bookDto.setIsbn(bookDto.getIsbn().split("/")[0]);
				String searchValue = bookDto.getTitle() + " - " + bookDto.getName();

				arrTitle.add(searchValue);
			}

			jsonStr = JSONValue.toJSONString(arrTitle);
			response.setContentType("application/x-json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void searchWriter(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		List<String> nameList = adminBook.getWriterNameList();
		LogAspect.logger.info(LogAspect.logMsg +"저자 size : "+ nameList.size());
		
		try {
			JSONArray arrName = new JSONArray();
			String jsonStr = null;
			/* HashMap<String, String> hmap=null; */

			for (int i = 0; i < nameList.size(); i++) {
				String name = nameList.get(i);
				
				arrName.add(name);
			}

			jsonStr = JSONValue.toJSONString(arrName);
			response.setContentType("application/x-json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void searchList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String search = request.getParameter("search");
		LogAspect.logger.info(LogAspect.logMsg + "검색어 : " + search);

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		String bookListSize = request.getParameter("bookListSize");
		if (bookListSize == null)
			bookListSize = "10";

		String path = request.getParameter("path");
		if (path == null || path == "")
			path = "전체";
		String category_path = request.getParameter("category_path");
		if (category_path == null || category_path == "")
			category_path = "전체";

		String category_number = null;
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리경로 : " + category_path);
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리경로 : " + path);

		String categoryValue = "," + path.trim();

		category_number = bookDao.getCategoryNumber("," + path.trim());
		if (category_number == null) {
			category_number = bookDao.getCategoryNumber(category_path.trim());
			categoryValue = categoryValue.split(",")[1];
		}

		List<BookDto> searchList = null;
		Map<String, String> dataMap = new HashMap<String, String>();
		LogAspect.logger.info(LogAspect.logMsg + "검색 카테고리 : " + categoryValue);
		if (search.indexOf("-") == -1) {
			dataMap.put("search", search);
			dataMap.put("categoryValue", categoryValue);
			searchList = bookDao.searchList(dataMap);
		} else {
			String[] str = search.split("-");
			LogAspect.logger.info(LogAspect.logMsg + "검색어 구분 갯수 : " + str.length);
			String title = "";
			for(int i=0;i<str.length-1;i++) {
				if(i==str.length-2) {
					title+=str[i];
				} else {
					title+=str[i]+"-";
				}
			}
			String name = str[str.length-1];
			LogAspect.logger.info(LogAspect.logMsg + "검색어 책 : " + title);
			LogAspect.logger.info(LogAspect.logMsg + "검색어 저자 : " + name);
			
			dataMap.put("title", title.trim());
			dataMap.put("name", name.trim());
			dataMap.put("categoryValue", categoryValue);
			searchList = bookDao.searchBook(dataMap);
		}

		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리값 : " + category_number);

		category_path = path;

		CategoryDto categoryDto = bookDao.getCategoryPath(category_number);
		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리정보 : " + categoryDto.toString());
		String[] str = null;
		if (path != null) {
			str = categoryDto.getCategory_path().split(",");
			if (str.length == 4) {
				path = str[str.length - 2];
			} else {
				path = str[str.length - 1];
			}
		}
		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리 출력 : " + category_path);

		int count = 0;
		if (searchList != null) {
			count = searchList.size();
		}
		
		LogAspect.logger.info(LogAspect.logMsg + "현재 검색된 책의 갯수 : " + count);

		int pageCount = count / Integer.parseInt(bookListSize) + (count % Integer.parseInt(bookListSize) == 0 ? 0 : 1);
		int pageBlock = 10;
		int currentPage = Integer.parseInt(pageNumber);
		int startRow = (currentPage - 1) * Integer.parseInt(bookListSize) + 1;
		int endRow = currentPage * Integer.parseInt(bookListSize);
		if (count < endRow) {
			endRow = count;
		}
		List<BookDto> bookList = null;
		if (count != 0) {
			bookList = new ArrayList<BookDto>();
			for (int i = startRow - 1; i < endRow; i++) {
				BookDto bookDto = searchList.get(i);
				String isbn = bookDto.getIsbn();
				float grade = bookDao.getGrade(isbn);
				
				bookDto.setGrade(grade);
				
				searchList.set(i, bookDto);
				
				bookList.add(searchList.get(i));
			}
		}

		LogAspect.logger.info(LogAspect.logMsg + startRow + ", " + endRow);

		int startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;

		int endPage = startPage + pageBlock;
		if (endPage > pageCount)
			endPage = pageCount + 1;
		
		String view = request.getParameter("view");
		if(view==null) view="detail";
		
		mav.addObject("view", view);

		mav.addObject("search", search);
		mav.addObject("category_number", category_number);
		mav.addObject("path", path);
		mav.addObject("categoryDto", categoryDto);
		mav.addObject("bookList", bookList);
		mav.addObject("pageCount", pageCount);
		mav.addObject("pageBlock", pageBlock);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("count", count);
		mav.addObject("category_path", category_path);
		mav.addObject("bookListSize", bookListSize);
	}
	
	@Override/*리뷰*/
	public void reviewInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		ReviewDto reviewDto = (ReviewDto) map.get("reviewDto");
		
		LogAspect.logger.info(LogAspect.logMsg + "입력된 리뷰 정보 : " + reviewDto.toString());
		
		Date writer_date = new Date();
		reviewDto.setContent(reviewDto.getContent().replace("\n", "<br>"));
		reviewDto.setWriter_date(writer_date);
		int check = reviewDao.reviewInsert(reviewDto);
		LogAspect.logger.info(LogAspect.logMsg + "리뷰 작성 결과 : " + check);
		
		if(check!=0) {
			try {
				HashMap<String, Object> dataMap = new HashMap<String, Object>();
				JSONArray reviewValue = new JSONArray();
				String jsonStr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
				
				dataMap.put("id", reviewDto.getId());
				dataMap.put("grade", reviewDto.getGrade());
				dataMap.put("content", reviewDto.getContent());
				dataMap.put("writer_date", sdf.format(writer_date));
				LogAspect.logger.info(LogAspect.logMsg + "리뷰 작성 결과 : " + dataMap.toString());
				reviewValue.add(dataMap);

				jsonStr = JSONValue.toJSONString(reviewValue);
				response.setContentType("application/x-json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(jsonStr);
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 고객센터 메인 TOP10
	@Override
	public void payment(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String isbn=request.getParameter("isbn");
		
		String[] isbnList=isbn.split(",");
		String trantIsbn="";
		
		for(int i=0;i<isbnList.length;i++) {
			trantIsbn+=isbnList[i];
		}
		
		HttpSession session = request.getSession(); //세션받기 ID
		MemberDto memberDto=memberDao.updateAccount(session);
		
		LogAspect.logger.info(LogAspect.logMsg +isbnList.length);
		
		if(request.getParameter("val").equals("1")) {
			int quantity=Integer.parseInt(request.getParameter("quantity").split(",")[0]);
			LogAspect.logger.info(LogAspect.logMsg +isbn+"\t"+quantity);
			
			BookDto bookDto=paymentDao.selectBook(isbnList[0]);
			mav.addObject("bookDto",bookDto);
			mav.addObject("quantity",quantity);
			mav.addObject("length",isbnList.length);
			mav.addObject("isbn",isbn);
		}else if(request.getParameter("val").equals("2")) {
			String quantity=request.getParameter("quantity");
			String[] quantityList=quantity.split(",");
			String transQuantity="";
			for(int i=0;i<quantityList.length;i++) {
				transQuantity+=quantityList[i]+"/";
			}
			BookDto firstBookDto=new BookDto();
			int count=0;
			long price=0;
			List<BookDto>bookList=new ArrayList<BookDto>();
			int bookCount=quantityList.length;
			for(int i=0;i<isbnList.length;i++) {
				count+=Integer.parseInt(quantityList[i]);
				BookDto bookDto=paymentDao.selectBook(isbnList[i]);
				if(i==0) {
					firstBookDto=bookDto;
				}
				price+=Integer.parseInt(quantityList[i])* bookDto.getPrice();
				bookList.add(bookDto);
			}
			mav.addObject("bookCount",bookCount);
			mav.addObject("quantity",transQuantity);
			mav.addObject("isbn",trantIsbn);
			mav.addObject("price",price);
			mav.addObject("bookListSize",bookList.size()-1);
			mav.addObject("bookDto",firstBookDto);
			mav.addObject("count",count);
		}
		mav.addObject("memberDto",memberDto);
		mav.setViewName("payment.users");
	}

	public void getTopTen(ModelAndView mav) {
		List<FaqDto> faqDtoTTList = faqDao.getTopTenList();
		
		for (int i = 0; i < faqDtoTTList.size(); i++) {
			faqDtoTTList.get(i).setContent(faqDtoTTList.get(i).getContent().replace("\r\n", "<br />"));
		}
		
		mav.addObject("faqDtoTTList", faqDtoTTList);
		mav.setViewName("CustomerService_main.users");
	}

	// 고객센터 FAQ
	@Override
	public void getFaq(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String upCategory = request.getParameter("up_category");
		String downCategory = request.getParameter("down_category");
		String search = request.getParameter("search");
		
		int boardSize = 10;
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null)pageNumber="1";
		
		int currentPage = Integer.parseInt(pageNumber);
		int startNum = (currentPage-1)*boardSize+1;
		int endNum = currentPage*boardSize;
		
		int faqListCount = faqDao.faqListCount(upCategory,downCategory,search);
		
		List<FaqDto> faqUpList = new ArrayList<FaqDto>();
		List<FaqDto> faqDownList = new ArrayList<FaqDto>();
		List<FaqDto> faqSearchList = new ArrayList<FaqDto>();
		
		if(upCategory!=null && downCategory==null && search==null) {
			faqUpList = faqDao.faqList(upCategory,startNum,endNum);
			for (int i = 0; i < faqUpList.size(); i++) {
				faqUpList.get(i).setContent(faqUpList.get(i).getContent().replace("\r\n", "<br />"));
			}
			mav.addObject("faqList", faqUpList);
		}else if(upCategory!=null && downCategory!=null && search==null) {
			faqDownList = faqDao.faqDownList(downCategory,startNum,endNum);
			for (int i = 0; i < faqDownList.size(); i++) {
				faqDownList.get(i).setContent(faqDownList.get(i).getContent().replace("\r\n", "<br />"));
			}
			mav.addObject("faqList", faqDownList);
		}else if(search!=null) {
			faqSearchList = faqDao.faqSearchList(upCategory,search,startNum,endNum);
			for (int i = 0; i < faqSearchList.size(); i++) {
				faqSearchList.get(i).setContent(faqSearchList.get(i).getContent().replace("\r\n", "<br />"));
			}
			mav.addObject("faqList", faqSearchList);
		}
		
		mav.addObject("upCategory", upCategory);
		mav.addObject("downCategory", downCategory);
		mav.addObject("boardSize", boardSize);
		mav.addObject("pageNumber", currentPage);
		mav.addObject("faqListCount", faqListCount);
		mav.addObject("search", search);
		mav.setViewName("CustomerService_faq.users");
	}
	
	// 고객센터 1:1문의내역
	@Override
	public void cstList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("mbId");
		String date = request.getParameter("date");
		if(date==null) {
			date="7";
		}
		
		if(id==null) {
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			String facebookUrl = facebookLoginBO.getAuthorizationUrl(session);

			mav.addObject("naverAuthUrl", naverAuthUrl);
			mav.addObject("facebookUrl", facebookUrl);
			mav.setViewName("loginMember.users");
		}
		
		if(id!=null) {
			int boardSize = 10;
			String pageNumber = request.getParameter("pageNumber");
			if(pageNumber==null)pageNumber="1";
			
			int currentPage = Integer.parseInt(pageNumber);
			int startNum = (currentPage-1)*boardSize+1;
			int endNum = currentPage*boardSize;
			int listCount = cstListDao.cstListCount(id);
			
			List<CstListDto> cstList = new ArrayList<CstListDto>();
			if(listCount>0) {
				cstList = cstListDao.cstList(id,startNum,endNum,date);
			}
			
			for (int i = 0; i < cstList.size(); i++) {
				cstList.get(i).setContent(cstList.get(i).getContent().replace("\r\n", "<br/>"));
				if (cstList.get(i).getAdmin_content() != null) {
					cstList.get(i).setAdmin_content(cstList.get(i).getAdmin_content().replace("\r\n", "<br/>"));
				}
			}
			
			LogAspect.logger.info(LogAspect.logMsg + cstList.toString());
			
			mav.addObject("pageNumber",pageNumber);
			mav.addObject("boardSize",boardSize);
			mav.addObject("cstList",cstList);
			mav.addObject("listCount",listCount);
			mav.addObject("date",date);
			
			mav.setViewName("CustomerService_consultingList.users");
		}
	}
	
	// 고객센터 1:1문의
	@Override
	public void cstConsulting(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("mbId");
		
		if(id==null) {
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			String facebookUrl = facebookLoginBO.getAuthorizationUrl(session);

			mav.addObject("naverAuthUrl", naverAuthUrl);
			mav.addObject("facebookUrl", facebookUrl);
			mav.setViewName("loginMember.users");
		}
		
		if(id!=null) {
			mav.setViewName("CustomerService_consulting.users");
			List<FaqDto> faqDtoList = faqDao.getTopTenList();
			
			for (int i = 0; i < faqDtoList.size(); i++) {
				faqDtoList.get(i).setContent(faqDtoList.get(i).getContent().replace("\r\n", "<br />"));
			}
		}
	}

	// 고객센터 1:1문의 입력
	@Override
	public void cstOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		CstDto cstDto = (CstDto) map.get("cstDto");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("mbId");
		cstDto.setId(id);
		int emailing = Integer.parseInt(request.getParameter("emailing"));
		cstDto.setUp_category(cstDto.getUp_category().replace(",", ""));
		cstDto.setDown_category(cstDto.getDown_category().replace(",", ""));
		
		if(id==null) {
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			String facebookUrl = facebookLoginBO.getAuthorizationUrl(session);

			mav.addObject("naverAuthUrl", naverAuthUrl);
			mav.addObject("facebookUrl", facebookUrl);
			mav.setViewName("loginMember.users");
		}
		
		if(id!=null) {
			if(emailing==0) {
				cstDto.setEmail("X");
			}
			if(cstDto.getCounsel_product()==null) {
				cstDto.setCounsel_product("X");
			}
			if(cstDto.getOrder_number()==null) {
				cstDto.setOrder_number("X");
			};
			
			int check = cstDao.userInsert(cstDto);
			mav.addObject("check",check);
			mav.setViewName("CustomerService_cstOk.users");
		}
	}
	
	// 고객센터 1:1문의 상품팝업창
	@Override
	public void cstProduct(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String search = request.getParameter("search");
		
		int boardSize = 5;
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null)pageNumber="1";
		
		int currentPage = Integer.parseInt(pageNumber);
		int startNum = (currentPage-1)*boardSize+1;
		int endNum = currentPage*boardSize;
		int producCount=0;
		List<CstQuestionDto> cstProductList = new ArrayList<CstQuestionDto>();
		int count = 0;
		if(search!=null) {
			producCount = cstDao.cstProductCount(search);
			cstProductList = cstDao.cstProductList(search,startNum,endNum);
			count++;
			mav.addObject("cstProductList",cstProductList);
		}
		
		mav.addObject("count",count);
		mav.addObject("producCount",producCount);
		mav.addObject("pageNumber",currentPage);
		mav.addObject("boardSize",boardSize);
		mav.addObject("search",search);
		mav.setViewName("CustomerService_question_search.empty");
	}
	
	// 고객센터 1:1문의 주문팝업창
	@Override
	public void cstOrder(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("mbId");
		String date = request.getParameter("date");
		if(date==null)date="7";
		
		if(id==null) {
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			String facebookUrl = facebookLoginBO.getAuthorizationUrl(session);

			mav.addObject("naverAuthUrl", naverAuthUrl);
			mav.addObject("facebookUrl", facebookUrl);
			mav.setViewName("loginMember.users");
		}
		
		if(id!=null) {
			List<CstOrderDto> cstOrNumberList = cstDao.cstOrNumberList(id);
			List<CstOrderDto> cstOrderList = new ArrayList<CstOrderDto>(); 
			CstOrderDto cstOrderDto = new CstOrderDto();
			String[] goods = null;
			String[] account = null;
			String order_number = null;
			if(cstOrNumberList.size()>0) {
				for(int i=0; i<cstOrNumberList.size(); i++) {
					goods = cstOrNumberList.get(i).getGoods().split("/");
					account = cstOrNumberList.get(i).getOrder_account().split("/");
					order_number = cstOrNumberList.get(i).getOrder_number();
					for(int j=0; j<goods.length; j++) {
						cstOrderDto = cstDao.cstOrderList(goods[j]+"/",order_number,date);
						cstOrderDto.setOrder_account(account[j]);
						cstOrderList.add(cstOrderDto); 
					}
				}
				mav.addObject("cstOrderList",cstOrderList);
				mav.addObject("date",date);
			}
			mav.setViewName("CustomerService_order_search.empty");
		}
	}
		

	@Override
	public void paymentOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int flag=0;
		if(request.getParameter("flag")!=null)
			flag=Integer.parseInt(request.getParameter("flag"));
		
		PaymentPointDto paymentPointDto=(PaymentPointDto)map.get("paymentPointDto");
		OrderDto orderDto=(OrderDto) map.get("orderDto");
		
		HttpSession session=request.getSession();
		MemberDto memberDto=memberDao.updateAccount(session);
		
		String order_number=Long.toString(System.currentTimeMillis())+"_"+memberDto.getMember_number();
		paymentPointDto.setOrder_number(order_number);
		orderDto.setOrder_number(order_number);
		orderDto.setId(memberDto.getId());
		
		if(paymentPointDto.getDirect_deposit()==null||paymentPointDto.getDirect_deposit().equals("")) {
			orderDto.setOrder_status(1);
		}
		
		LogAspect.logger.info(LogAspect.logMsg+paymentPointDto.toString());
		LogAspect.logger.info(LogAspect.logMsg+orderDto.toString());
		
		HashMap<String, Object>hmap=new HashMap<String, Object>();
		hmap.put("total_price", orderDto.getTotal_price());
		hmap.put("point_history",paymentPointDto.getPoint_history());
		hmap.put("sales_total", orderDto.getTotal_price()+paymentPointDto.getPoint_history());
		hmap.put("save_point",(paymentPointDto.getSave_point()-paymentPointDto.getPoint_history()));
		hmap.put("id",orderDto.getId());
		hmap.put("goods",orderDto.getGoods());
		
		String[] goods=((String)hmap.get("goods")).split("/");
		if(flag==1)
			LogAspect.logger.info(LogAspect.logMsg+goods.length);
		
		HashMap<String, Object> cartMap=new HashMap<String, Object>();
		ArrayList<String>isbnList=null;
		if(flag==1) {
			isbnList=new ArrayList<String>();
			for(int i=0;i<goods.length;i++) {
				isbnList.add(goods[i]+"/");
			}
		}
		cartMap.put("id", orderDto.getId());
		cartMap.put("isbnList", isbnList);
		
		int check=paymentDao.paymentOk(paymentPointDto,orderDto,hmap,cartMap);
		LogAspect.logger.info(LogAspect.logMsg+"paymentOk check : "+check);
		mav.addObject("check",check);
		mav.setViewName("paymentOk.users");
	}

	@Override
	public void addressList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mbId");

		List<MemberAddressDto> memberAddressDtoList = paymentDao.getMemberAddress(id);
		mav.addObject("memberAddressDtoList", memberAddressDtoList);
		mav.addObject("size", memberAddressDtoList.size());
		mav.setViewName("addressList.empty");
	}

	@Override
	public void addAddress(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		MemberAddressDto memberAddressDto = (MemberAddressDto) map.get("memberAddressDto");
		String id = (String) request.getSession().getAttribute("mbId");
		memberAddressDto.setId(id);
		
		int check = paymentDao.insertZipcode(memberAddressDto);

		mav.addObject("check", check);
		mav.setViewName("addAddress.empty");
	}

	@Override
	public void deleteAddress(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String member_zipcode = request.getParameter("check");

		int check = paymentDao.deleteMemeberAddress(member_zipcode);
		LogAspect.logger.info(LogAspect.logMsg + "check : " + check);

		mav.addObject("check", check);
		mav.setViewName("addressDelete.empty");
	}

	public void bookList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String category_path = request.getParameter("category_path");
		if (category_path == null)
			category_path = "전체";

		String path = request.getParameter("path");
		if (path == null)
			path = "전체";

		String category_number = null;
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리경로 : " + category_path);
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리경로 : " + path);

		category_number = bookDao.getCategoryNumber("," + path.trim());
		if (category_number == null) {
			category_number = bookDao.getCategoryNumber(category_path.trim());
		}

		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리값 : " + category_number);

		category_path = path;

		CategoryDto categoryDto = bookDao.getCategoryPath(category_number);
		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리정보 : " + categoryDto.toString());
		String[] str = null;
		if (path != null) {
			str = categoryDto.getCategory_path().split(",");
			if (str.length == 4) {
				path = str[str.length - 2];
			} else {
				path = str[str.length - 1];
			}
		}
		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리 출력 : " + category_path);

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		// LogAspect.logger.info(LogAspect.logMsg+"상위 카테고리 : "+path+", "+str.length);

		String bookListSize = request.getParameter("bookListSize");
		if (bookListSize == null)
			bookListSize = "10";

		int count = bookDao.getCount(category_number);
		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리의 등록된 책의 갯수 : " + count);

		int pageCount = count / Integer.parseInt(bookListSize) + (count % Integer.parseInt(bookListSize) == 0 ? 0 : 1);
		int pageBlock = 10;
		int currentPage = Integer.parseInt(pageNumber);
		int startRow = (currentPage - 1) * Integer.parseInt(bookListSize) + 1;
		int endRow = currentPage * Integer.parseInt(bookListSize);

		LogAspect.logger.info(LogAspect.logMsg + startRow + ", " + endRow);
		// sortValue='WRITE_DATE

		String sortValue = request.getParameter("sortValue");
		if (sortValue == null)
			sortValue = "WRITE_DATE";

		List<BookDto> bookListSearch = null;
		List<BookDto> bookList = null;
		if (count != 0) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("category_number", category_number);
			dataMap.put("sortValue", sortValue);

			if (count < endRow) {
				endRow = count;
			}

			LogAspect.logger
					.info(LogAspect.logMsg + startRow + ", " + endRow + ", " + category_number + ", " + sortValue);
			bookListSearch = bookDao.getBookList(dataMap);
			LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 갯수 : " + bookListSearch.size());
			
			

			bookList = new ArrayList<BookDto>();
			for (int i = startRow - 1; i < endRow; i++) {
				BookDto bookDto = bookListSearch.get(i);
				String isbn = bookDto.getIsbn();
				float grade = bookDao.getGrade(isbn);
				
				bookDto.setGrade(grade);
				
				bookListSearch.set(i, bookDto);
				
				
				bookList.add(bookListSearch.get(i));
			}
		}

		int startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;

		int endPage = startPage + pageBlock;
		if (endPage > pageCount)
			endPage = pageCount + 1;
		
		String view = request.getParameter("view");
		if(view==null) view="detail";
		
		mav.addObject("view", view);
		mav.addObject("category_number", category_number);
		mav.addObject("sortValue", sortValue);
		mav.addObject("path", path);
		mav.addObject("categoryDto", categoryDto);
		mav.addObject("bookList", bookList);
		mav.addObject("pageCount", pageCount);
		mav.addObject("pageBlock", pageBlock);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("count", count);
		mav.addObject("category_path", category_path);
		mav.addObject("bookListSize", bookListSize);
	}

	@Transactional
	@Override
	public void bookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");

		BookDto bookDto = bookDao.getBookInfo(isbn);
		float grade = bookDao.getGrade(isbn);
		LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 평점 : " + grade);
		bookDto.setGrade(grade);
		
		LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 정보 : " + bookDto.toString());
		if (bookDto.getContents() != null) {
			bookDto.setContents(bookDto.getContents().replace("\n", "<br>"));
		}
		if (bookDto.getBook_introduction() != null) {
			bookDto.setBook_introduction(bookDto.getBook_introduction().replace("\n", "<br>"));
		}

		WriterDto writerDto = bookDao.getWriterInfo(bookDto.getWriter_number());
		LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 저자정보 : " + writerDto.toString());

		ArrayList<BookDto> writerBookList = new ArrayList<BookDto>();
		String searchBook = null;
		if(writerDto.getWriter_bookList()!=null) {
			searchBook = writerDto.getWriter_bookList().replace(isbn, "");
			LogAspect.logger.info(LogAspect.logMsg + "해당 저자의 다른 책 번호 : " + searchBook + "," + writerDto.getWriter_bookList());
			String[] bookNumberList = searchBook.split("/");

			for (int i = 0; i < bookNumberList.length; i++) {
				writerBookList.add(bookDao.getBookInfo(bookNumberList[i] + "/"));
			}
		}
		
		LogAspect.logger.info(LogAspect.logMsg + "해당 저자의 다른 책 갯수 : " + writerBookList.size());

		CategoryDto categoryDto = bookDao.getCategoryPath(bookDto.getCategory_number());
		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리정보 : " + categoryDto.toString());

		String path = categoryDto.getCategory_path();
		String category_path = null;
		String[] str = null;
		if (path != null) {
			str = path.split(",");
			if (str.length == 4) {
				path = str[str.length - 2];
				category_path = str[str.length - 1];
			} else {
				path = str[str.length - 1];
				category_path = str[str.length];
			}
		}

		LogAspect.logger.info(LogAspect.logMsg + "현재 카테고리 출력 : " + path);

		mav.addObject("writerBookList", writerBookList);
		mav.addObject("writerDto", writerDto);
		mav.addObject("bookDto", bookDto);
		mav.addObject("path", path);
		mav.addObject("category_path", category_path);
		mav.addObject("categoryDto", categoryDto);
	}
	
	@Override
	public void reviewList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String isbn = request.getParameter("isbn");
		
		List<ReviewDto> reviewList = reviewDao.getReviewList(isbn);
		
		
		mav.addObject("reviewList", reviewList);
	}

	@Override
	public void adminBookSearch(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		List<BookDto> bookList = adminBook.getAdminBookSearch();
		mav.addObject("bookList", bookList);
	}

	@Override
	public void adminBookInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		List<CategoryDto> categoryList = adminBook.adminBookCategogyList();
		mav.addObject("categoryList", categoryList);
	}

	@Override
	public void adminBookInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		BookDto bookDto = (BookDto) map.get("bookDto");

		bookDto.setIsbn(bookDto.getIsbn() + "/");

		LogAspect.logger.info(LogAspect.logMsg + "책 등록정보 : " + bookDto.toString());

		int check = adminBook.adminBookInsert(bookDto);
		WriterDto writerDto = null;
		if (check != 0) {
			writerDto = adminBook.getWriter(bookDto.getWriter_number());
			LogAspect.logger.info(LogAspect.logMsg + "저자 정보 : " + writerDto.toString());
			String writer_bookList = writerDto.getWriter_bookList();
			if (writer_bookList == null) {
				writer_bookList = bookDto.getIsbn();
			} else if (writer_bookList.indexOf(bookDto.getIsbn()) == -1) {
				writer_bookList += bookDto.getIsbn();
			}

			writerDto.setWriter_bookList(writer_bookList);
			if (writerDto.getWriter_number() != 0) {
				adminBook.updateWriter(writerDto);
			}
		}

		mav.addObject("check", check);
		mav.addObject("isbn", bookDto.getIsbn());
	}

	@Override
	public void adminBookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");
		LogAspect.logger.info(LogAspect.logMsg + "요청 책 번호 : " + isbn);

		BookDto bookDto = adminBook.adminBookInfo(isbn);
		LogAspect.logger.info(LogAspect.logMsg + "책 정보 : " + bookDto.toString());

		WriterDto writerDto = adminBook.getWriter(bookDto.getWriter_number());
		LogAspect.logger.info(LogAspect.logMsg + "저자 정보 : " + writerDto.toString());

		List<CategoryDto> categoryList = adminBook.adminBookCategogyList();

		if (writerDto != null) {
			LogAspect.logger.info(LogAspect.logMsg + "저자확인 : " + writerDto.toString());

			bookDto.setName(writerDto.getName());
			bookDto.setWriter_number(writerDto.getWriter_number());
		}
		bookDto.setIsbn(bookDto.getIsbn().split("/")[0]);
		mav.addObject("categoryList", categoryList);
		mav.addObject("bookDto", bookDto);
	}

	@Transactional
	@Override
	public void adminBookUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		BookDto bookDto = (BookDto) map.get("bookDto");
		bookDto.setIsbn(bookDto.getIsbn() + "/");
		LogAspect.logger.info(LogAspect.logMsg + "책 수정정보 : " + bookDto.toString());

		int check = adminBook.adminBookUpdate(bookDto);

		if (check != 0) {
			WriterDto writerDto = adminBook.getWriter(bookDto.getWriter_number());
			LogAspect.logger.info(LogAspect.logMsg + "저자 정보 : " + writerDto.toString());
			String writer_bookList = writerDto.getWriter_bookList();

			if (writer_bookList == null) {
				writer_bookList = bookDto.getIsbn();
			} else if (writer_bookList.indexOf(bookDto.getIsbn()) == -1) {
				writer_bookList += bookDto.getIsbn();
			}

			if (writerDto.getWriter_number() != 0) {
				adminBook.updateWriter(writerDto);
			}
		}

		mav.addObject("check", check);
		mav.addObject("isbn", bookDto.getIsbn());
	}

	@Transactional
	@Override
	public void adminBookDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");
		LogAspect.logger.info(LogAspect.logMsg + "삭제요청 책 번호 : " + isbn);

		long writer_number = Long.parseLong(request.getParameter("writer_number"));
		LogAspect.logger.info(LogAspect.logMsg + "삭제요청 책의 저자번호 : " + writer_number);

		int check = adminBook.adminBookDelete(isbn);

		if (check != 0) {
			WriterDto writerDto = adminBook.getWriter(writer_number);
			writerDto.setWriter_bookList(writerDto.getWriter_bookList().replace(isbn, ""));
			adminBook.adminWriterBookListUpdate(writerDto);
		}

		mav.addObject("check", check);
	}

	@Override
	public void adminWriterSearch(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String name = request.getParameter("name");
		List<WriterDto> writerList = null;
		LogAspect.logger.info(LogAspect.logMsg + "저자 검색 : " + name);
		if (name != null) {
			writerList = adminBook.getWriterList(name);
			if (writerList != null) {
				for (int i = 0; i < writerList.size(); i++) {
					String title = "등록도서 없음";
					if (writerList.get(i).getWriter_bookList() != null) {
						String[] bookList = writerList.get(i).getWriter_bookList().split("/");
						title = adminBook.getTitle(bookList[0] + "/");
						if (bookList.length > 1) {
							title += " 외 " + (bookList.length - 1) + "종";
						}
					}

					WriterDto writerDto = writerList.get(i);
					LogAspect.logger.info(LogAspect.logMsg + "대표작 타이틀 : " + title);
					writerDto.setTitle(title);
					writerList.set(i, writerDto);
				}
			}

			mav.addObject("name", name);
			mav.addObject("writerList", writerList);
			LogAspect.logger.info(LogAspect.logMsg + "저자 검색 수 : " + writerList.size());
		}

	}

	@Override
	public void adminWriterInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		WriterDto writerDto = (WriterDto) map.get("writerDto");
		LogAspect.logger.info(LogAspect.logMsg + "저자 등록 정보 : " + writerDto.toString());
		int check = adminBook.adminWriterInsert(writerDto);
		LogAspect.logger.info(LogAspect.logMsg + "저자 등록 확인 : " + check);

		mav.addObject("check", check);
	}

	@Override
	public void orderSearch(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String check=request.getParameter("check");
			if(check==null)check="2";
			
			int orderingCount=orderDao.getOrderingCount(id);
			int deliveryCount=orderDao.getDeliveryCount(id);
			int cancelCount=orderDao.getCancelCount(id);
			int point=orderDao.getPoint(id);
			
			String orderSearch_pageNumber=request.getParameter("orderSearch_pageNumber");
			if(request.getParameter("orderSearch_pageNumber")==null) {
				orderSearch_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "orderSearch_pageNumber:" +orderSearch_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(orderSearch_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			int count=orderDao.getOrderSearchCount(id);
			LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			
			String dateValue=null;
			String dateValueList=null;
			List<OrderDto> orderSearchList=null;
			if(count >0) {
				dateValue=request.getParameter("dateValue");
				if(dateValue==null) dateValue="0";
				
				dateValueList=request.getParameter("dateValueList");
				if(dateValueList==null) dateValueList="0";
				
				LogAspect.logger.info(LogAspect.logMsg + "dateValue:" +dateValue);
				LogAspect.logger.info(LogAspect.logMsg + "dateValueList:" +dateValueList);
				orderSearchList=orderDao.orderSearchList(startRow, endRow, list_id, id, dateValue, dateValueList);
				LogAspect.logger.info(LogAspect.logMsg + "orderSearchList:" +orderSearchList.size());
				for(int i=0; i<orderSearchList.size(); i++) {
					OrderDto orderDto=orderSearchList.get(i);
					orderDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=orderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					
					String title=orderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						orderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						orderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_name():" +orderDto.getGoods_name());
					
					String[] str1=orderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					int order_status=orderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					orderDto.setStatus(status);
					
					LogAspect.logger.info(LogAspect.logMsg + "status:" +status);
					
					orderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_account():" +orderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.toString():" +orderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "orderSearchList:" +orderSearchList.toString());
			}		
			
			mav.addObject("orderSearch_pageNumber", orderSearch_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("count", count);
			mav.addObject("orderingCount", orderingCount);
			mav.addObject("deliveryCount", deliveryCount);
			mav.addObject("cancelCount", cancelCount);
			mav.addObject("point", point);
			mav.addObject("orderSearchList", orderSearchList);
			mav.addObject("list_id", list_id);
			mav.addObject("check", Integer.parseInt(check));
			mav.addObject("dateValueList", dateValueList);
			mav.addObject("dateValue", dateValue);
		}
		mav.setViewName("orderSearch.users");
	}

	public String status(int order_status, String status) {
		switch (order_status) {
		case 0 : status="입금대기중";	break;
		case 1 : status="상품준비완료";	break;
		case 2 : status="출고준비중";	break;
		case 3 : status="출고완료";	break;
		case 4 : status="배송중";	break;
		case 5 : status="배송완료";	break;
		case 11 : status="환불요청";	break;
		case 12 : status="환불요청배송";	break;
		case 13 : status="환불처리완료";	break;
		case 21 : status="교환요청";	break;
		case 22 : status="교환요청배송";	break;
		case 23 : status="교환처리완료";	break;
		case 31 : status="취소요청";	break;
		case 32 : status="취소처리완료";	break;
		default : break;
		}

		return status;
	}

	@Override
	public void ordering(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int deliveryCount=orderDao.getDeliveryCount(id);
			int cancelCount=orderDao.getCancelCount(id);
			int point=orderDao.getPoint(id);
			
			String ordering_pageNumber=request.getParameter("ordering_pageNumber");
			if(request.getParameter("ordering_pageNumber")==null) {
				ordering_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "ordering_pageNumber:" +ordering_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(ordering_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			int orderingCount=orderDao.getOrderingCount(id);
			LogAspect.logger.info(LogAspect.logMsg + "orderingCount:" + orderingCount);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			
			List<OrderDto> orderingList=null;
			if(orderingCount >0) {
				orderingList=orderDao.orderingList(startRow, endRow, list_id, id);
				for(int i=0; i<orderingList.size(); i++) {
					OrderDto orderDto=orderingList.get(i);
					orderDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=orderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					String title=orderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						orderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						orderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_name():" +orderDto.getGoods_name());
					
					String[] str1=orderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					int order_status=orderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					orderDto.setStatus(status);
					
					orderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_account():" +orderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.toString():" +orderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "orderingList:" +orderingList.toString());
			}		
			
			mav.addObject("ordering_pageNumber", ordering_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("orderingCount", orderingCount);
			mav.addObject("deliveryCount", deliveryCount);
			mav.addObject("cancelCount", cancelCount);
			mav.addObject("point", point);
			mav.addObject("orderingList", orderingList);
			mav.addObject("list_id", list_id);
		}
		mav.setViewName("ordering.users");
	}

	@Override
	public void delivery(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int orderingCount=orderDao.getOrderingCount(id);
			int deliveryCount=orderDao.getDeliveryCount(id);
			int cancelCount=orderDao.getCancelCount(id);
			int point=orderDao.getPoint(id);
			
			String delivery_pageNumber=request.getParameter("delivery_pageNumber");
			if(request.getParameter("delivery_pageNumber")==null) {
				delivery_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "delivery_pageNumber:" +delivery_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(delivery_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			
			LogAspect.logger.info(LogAspect.logMsg + "deliveryCount:" + deliveryCount);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			
			List<OrderDto> deliveryList=null;
			if(deliveryCount >0) {
				deliveryList=orderDao.deliveryList(startRow, endRow, list_id, id);
				for(int i=0; i<deliveryList.size(); i++) {
					OrderDto orderDto=deliveryList.get(i);
					orderDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=orderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					String title=orderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						orderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						orderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_name():" +orderDto.getGoods_name());
					
					String[] str1=orderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					int order_status=orderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					orderDto.setStatus(status);
					
					orderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_account():" +orderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.toString():" +orderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "deliveryList:" +deliveryList.toString());
			}		
			
			
			mav.addObject("delivery_pageNumber", delivery_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("orderingCount", orderingCount);
			mav.addObject("deliveryCount", deliveryCount);
			mav.addObject("cancelCount", cancelCount);
			mav.addObject("point", point);
			mav.addObject("deliveryList", deliveryList);
			mav.addObject("list_id", list_id);
		}
		mav.setViewName("delivery.users");
	}

	public void cancel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int orderingCount=orderDao.getOrderingCount(id);
			int deliveryCount=orderDao.getDeliveryCount(id);
			int cancelCount=orderDao.getCancelCount(id);
			int point=orderDao.getPoint(id);
			
			String cancel_pageNumber=request.getParameter("cancel_pageNumber");
			if(request.getParameter("cancel_pageNumber")==null) {
				cancel_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "cancel_pageNumber:" +cancel_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(cancel_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			
			LogAspect.logger.info(LogAspect.logMsg + "cancelCount:" + cancelCount);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			
			String dateValue=null;
			String dateValueList=null;
			List<OrderDto> cancelList=null;
			if(cancelCount >0) {
				dateValue=request.getParameter("dateValue");
				if(dateValue==null) dateValue="0";
				
				dateValueList=request.getParameter("dateValueList");
				if(dateValueList==null) dateValueList="0";
				
				cancelList=orderDao.cancelList(startRow, endRow, list_id, id, dateValue, dateValueList);
				for(int i=0; i<cancelList.size(); i++) {
					OrderDto orderDto=cancelList.get(i);
					orderDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=orderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					String title=orderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						orderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						orderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_name():" +orderDto.getGoods_name());
					
					String[] str1=orderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					int order_status=orderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					orderDto.setStatus(status);
					
					orderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_account():" +orderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.toString():" +orderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "cancelList:" +cancelList.toString());
			}		
			
			
			mav.addObject("cancel_pageNumber", cancel_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("orderingCount", orderingCount);
			mav.addObject("deliveryCount", deliveryCount);
			mav.addObject("cancelCount", cancelCount);
			mav.addObject("point", point);
			mav.addObject("cancelList", cancelList);
			mav.addObject("list_id", list_id);
			mav.addObject("dateValueList", dateValueList);
			mav.addObject("dateValue", dateValue);
		}
		mav.setViewName("cancel.users");
	}

	@Override
	public void buyList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int orderingCount=orderDao.getOrderingCount(id);
			int deliveryCount=orderDao.getDeliveryCount(id);
			int cancelCount=orderDao.getCancelCount(id);
			int point=orderDao.getPoint(id);
			
			String buyList_pageNumber=request.getParameter("buyList_pageNumber");
			if(request.getParameter("buyList_pageNumber")==null) {
				buyList_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "buyList_pageNumber:" +buyList_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(buyList_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			int buyListCount=orderDao.getBuyListCount(id);
			LogAspect.logger.info(LogAspect.logMsg + "buyListCount:" + buyListCount);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			
			String dateValue=null;
			String dateValueList=null;
			List<OrderDto> buyListList=null;
			if(buyListCount >0) {
				dateValue=request.getParameter("dateValue");
				if(dateValue==null) dateValue="0";
				
				dateValueList=request.getParameter("dateValueList");
				if(dateValueList==null) dateValueList="0";
				
				buyListList=orderDao.buyListList(startRow, endRow, list_id, id, dateValue, dateValueList);
				for(int i=0; i<buyListList.size(); i++) {
					OrderDto orderDto=buyListList.get(i);
					orderDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=orderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					String title=orderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						orderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						orderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_name():" +orderDto.getGoods_name());
					
					String[] str1=orderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					int order_status=orderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					orderDto.setStatus(status);
					
					LogAspect.logger.info(LogAspect.logMsg + "status:" +status);
					orderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.getGoods_account():" +orderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "orderDto.toString():" +orderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "buyListList:" +buyListList.toString());
			}		
			
			
			mav.addObject("buyList_pageNumber", buyList_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("orderingCount", orderingCount);
			mav.addObject("deliveryCount", deliveryCount);
			mav.addObject("cancelCount", cancelCount);
			mav.addObject("point", point);
			mav.addObject("buyListList", buyListList);
			mav.addObject("list_id", list_id);
			mav.addObject("buyListCount", buyListCount);
			mav.addObject("dateValueList", dateValueList);
			mav.addObject("dateValue", dateValue);
		}
		mav.setViewName("buyList.users");
	}

	@Override
	public void cart(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
	
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int value=2;
			String isbnList=request.getParameter("isbnList");
			if(isbnList==null) {
				isbnList=request.getParameter("isbn");
			}
			String[] isbnArr=null;
			if(isbnList!=null) {
				isbnArr=isbnList.split("/");
			}
		
			String quantityList=request.getParameter("quantityList");
			if(quantityList==null) {
				quantityList=request.getParameter("quantity");
			}
			String[] quantityArr=null;
			if(quantityList!=null) {
				quantityArr=quantityList.split("/");
			}
			
			int check=2;
			if(isbnArr!=null && quantityArr!=null) {
				for(int i=0; i< isbnArr.length; i++) {
					check=orderDao.insertCart(isbnArr[i]+"/", quantityArr[i], id);
					if(check==0) break;
				}
			}
			
			String cart_pageNumber=request.getParameter("cart_pageNumber");
			if(request.getParameter("cart_pageNumber")==null) {
				cart_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "cart_pageNumber:" +cart_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(cart_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			int cartCount=orderDao.getCartCount(id);
			LogAspect.logger.info(LogAspect.logMsg + "cartCount:" + cartCount);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			int point=orderDao.getPoint(id);
			List<CartDto> cartList=null;
			if(cartCount >0) {
				cartList=orderDao.cartList(startRow, endRow, list_id, id);
				LogAspect.logger.info(LogAspect.logMsg + "cartList:" +cartList.toString());
			}		
			
			
			mav.addObject("cart_pageNumber", cart_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("cartList", cartList);
			mav.addObject("list_id", list_id);
			mav.addObject("cartCount", cartCount);
			mav.addObject("point", point);
			mav.addObject("check", check);
			mav.addObject("value", value);
		}
		mav.setViewName("cart.users");
	}

	@Override
	public void cartListDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String isbnList=request.getParameter("isbnList");
			int value=2;
			if(isbnList!=null) {
				String[] isbn=isbnList.split("/");
				
				for(int i=0; i<isbn.length; i++) {
					isbn[i]+="/";
					LogAspect.logger.info(LogAspect.logMsg + "isbn[i]:" +isbn[i]);
					value=orderDao.cartListDelete(isbn[i], id);
					LogAspect.logger.info(LogAspect.logMsg + "value:" +value);
					if(value==0)break;
				}
				LogAspect.logger.info(LogAspect.logMsg + "value:" +value);
			}
			
			String cart_pageNumber=request.getParameter("cart_pageNumber");
			if(request.getParameter("cart_pageNumber")==null) {
				cart_pageNumber="1";
			}
			LogAspect.logger.info(LogAspect.logMsg + "cart_pageNumber:" +cart_pageNumber);
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(cart_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			int cartCount=orderDao.getCartCount(id);
			LogAspect.logger.info(LogAspect.logMsg + "cartCount:" + cartCount);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
			int point=orderDao.getPoint(id);
			List<CartDto> cartList=null;
			if(cartCount >0) {
				cartList=orderDao.cartList(startRow, endRow, list_id, id);
				LogAspect.logger.info(LogAspect.logMsg + "cartList:" +cartList.toString());
			}		
			
	
			mav.addObject("cart_pageNumber", cart_pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.addObject("cartList", cartList);
			mav.addObject("list_id", list_id);
			mav.addObject("cartCount", cartCount);
			mav.addObject("point", point);
			mav.addObject("value", value);
		}
		mav.setViewName("cart.users");
	}

	@Override
	public void statusChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String pageStatus=request.getParameter("pageStatus");
			int page=Integer.parseInt(pageStatus);
			LogAspect.logger.info(LogAspect.logMsg + "page:" + page);
			String order_number=request.getParameter("order_number");
			LogAspect.logger.info(LogAspect.logMsg + "order_number:" + order_number);
			String status=request.getParameter("status");
			LogAspect.logger.info(LogAspect.logMsg + "status:" + status);
			int orderCheck=2;
			int orderingCheck=2;
			int deliveryCheck=2;
			int cancelCheck=2;
			switch(page) {
			case 1:orderCheck=orderDao.statusChange(order_number, status, id); break;
			case 2:orderingCheck=orderDao.statusChange(order_number, status, id); break;
			case 3:deliveryCheck=orderDao.statusChange(order_number, status, id); break;
			case 4:cancelCheck=orderDao.statusChange(order_number, status, id); break;
			default : break;
			}
			LogAspect.logger.info(LogAspect.logMsg + "orderCheck:" + orderCheck);
			mav.addObject("orderCheck", orderCheck);
			mav.addObject("orderingCheck", orderingCheck);
			mav.addObject("deliveryCheck", deliveryCheck);
			mav.addObject("cancelCheck", cancelCheck);
		}
		mav.setViewName("returnPoint.users");
	}

	@Override
	public void orderDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String pageStatus=request.getParameter("pageStatus");
			int page=Integer.parseInt(pageStatus);
			LogAspect.logger.info(LogAspect.logMsg + "page:" + page);
			String order_number=request.getParameter("order_number");
			int orderDeleteCheck=2;
			int orderingDeleteCheck=2;
			int deliveryDeleteCheck=2;
			int detailDeleteCheck=2;
			switch(page) {
			case 1:orderDeleteCheck=orderDao.orderDelete(order_number, id); break;
			case 2:orderingDeleteCheck=orderDao.orderDelete(order_number, id); break;
			case 5:deliveryDeleteCheck=orderDao.orderDelete(order_number, id); break;
			case 6:detailDeleteCheck=orderDao.orderDelete(order_number, id); break;
			default : break;
			}
			
			mav.addObject("orderDeleteCheck", orderDeleteCheck);
			mav.addObject("orderingDeleteCheck", orderingDeleteCheck);
			mav.addObject("deliveryDeleteCheck", deliveryDeleteCheck);
			mav.addObject("detailDeleteCheck", detailDeleteCheck);
		}
		mav.setViewName("returnPoint.users");
	}

	@Override
	public void detailOrder(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String order_name=orderDao.getOrder_name(id);
		
			String order_number=request.getParameter("order_number");
			Date order_date=orderDao.getOrderDate(order_number);
			LogAspect.logger.info(LogAspect.logMsg+ "order_date:" + order_date);
			List<OrderDto> orderDtoList=orderDao.getDetailOrder(order_number, id);
			OrderDto orderDto=orderDtoList.get(0);
			LogAspect.logger.info(LogAspect.logMsg+ "orderDto:" + orderDto.toString());
			String receive_name=orderDto.getReceive_name();
			String receive_phone=orderDto.getReceive_phone();
			String receive_addr=orderDto.getReceive_addr();
			int order_status=orderDto.getOrder_status();
			long total_price=orderDto.getTotal_price();
			
			int use_point=orderDao.getUse_point(order_number);
			
			//결제방법 뽑아오기
			String payment_way="";
			if(orderDto.getCredit_card()!=null)	payment_way="신용카드";
			if(orderDto.getPhone_payment()!=null) payment_way="휴대폰결제";
			if(orderDto.getRealtime_account_transfer()!=null) payment_way="실시간이체";
			if(orderDto.getDirect_deposit()!=null) payment_way="직접입금";
			
			LogAspect.logger.info(LogAspect.logMsg+ "보기보기보기payment_way:" + payment_way);
			Date maybe_date=null;
			String goods=orderDto.getGoods();
			String order_amount=orderDto.getOrder_account();
			String[] isbnArr=goods.split("/");
			String[] amountArr=order_amount.split("/");
			int count=isbnArr.length;
			List<OrderDto> detailList=new ArrayList<OrderDto>();
			for(int i=0; i<isbnArr.length; i++) {
				OrderDto detailDto=new OrderDto();
				String isbn=isbnArr[i]+"/";
				detailDto.setIsbn(isbn);
				LogAspect.logger.info(LogAspect.logMsg+ "isbn:" + isbn);
				String amount=amountArr[i];
				String title=orderDao.getTitle(isbn);
				detailDto.setGoods_name(title);
				detailDto.setOrder_account(amount);
				long price=orderDao.getDetailPrice(isbn);
				detailDto.setTotal_price(price);
				detailDto.setOrder_date(order_date);
				detailDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
				detailList.add(detailDto);
				maybe_date=detailDto.getMaybe_date();
			}
			LogAspect.logger.info(LogAspect.logMsg+ "detailList:" + detailList.toString());
			
			int orderingCount=orderDao.getOrderingCount(id);
			int deliveryCount=orderDao.getDeliveryCount(id);
			int cancelCount=orderDao.getCancelCount(id);
			int point=orderDao.getPoint(id);
			
			mav.addObject("use_point", use_point);
			mav.addObject("payment_way", payment_way);
			mav.addObject("total_price", total_price);
			mav.addObject("order_number", order_number);
			mav.addObject("order_status", order_status);
			mav.addObject("order_name", order_name);
			mav.addObject("receive_name", receive_name);
			mav.addObject("receive_phone", receive_phone);
			mav.addObject("receive_addr", receive_addr);
			mav.addObject("maybe_date", maybe_date);
			mav.addObject("count", count);
			mav.addObject("orderingCount", orderingCount);
			mav.addObject("deliveryCount", deliveryCount);
			mav.addObject("cancelCount", cancelCount);
			mav.addObject("point", point);
			mav.addObject("detailList", detailList);
			mav.addObject("order_date", order_date);
		}
		mav.setViewName("detailOrder.users");
	}
	
	@Override
	public void adminOrderSearch(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int count=adminOrderDao.getAdminOrderCount();
			LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
			
			List<OrderDto> adminOrderList=null;
			if(count >0) {
				adminOrderList=adminOrderDao.adminOrderList();
				LogAspect.logger.info(LogAspect.logMsg + "adminOrderList:" +adminOrderList.size());
				for(int i=0; i<adminOrderList.size(); i++) {
					OrderDto adminOrderDto=adminOrderList.get(i);
					adminOrderDto.setMaybe_date(new Date(adminOrderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=adminOrderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					
					String title=adminOrderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						adminOrderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						adminOrderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.getGoods_name():" +adminOrderDto.getGoods_name());
					
					String[] str1=adminOrderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					String payment_way="";
					if(adminOrderDto.getCredit_card()!=null) payment_way="신용카드";
					if(adminOrderDto.getPhone_payment()!=null) payment_way="휴대폰결제";
					if(adminOrderDto.getRealtime_account_transfer()!=null) payment_way="실시간이체";
					if(adminOrderDto.getDirect_deposit()!=null) payment_way="직접입금";
					adminOrderDto.setPayment_way(payment_way);
					
					int order_status=adminOrderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					adminOrderDto.setStatus(status);
					
					LogAspect.logger.info(LogAspect.logMsg + "status:" +status);
					
					adminOrderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.getGoods_account():" +adminOrderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.toString():" +adminOrderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "adminOrderList:" +adminOrderList.toString());
			}		
			
			mav.addObject("count", count);
			mav.addObject("adminOrderList", adminOrderList);
		}
		mav.setViewName("adminOrderSearch.admin");
	}
	
	@Override
	public void adminChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int count=adminOrderDao.getAdminChangeCount();
			LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
			
			List<OrderDto> adminChangeList=null;
			if(count >0) {
				adminChangeList=adminOrderDao.adminChangeList();
				LogAspect.logger.info(LogAspect.logMsg + "adminChangeList:" +adminChangeList.size());
				for(int i=0; i<adminChangeList.size(); i++) {
					OrderDto adminOrderDto=adminChangeList.get(i);
					adminOrderDto.setMaybe_date(new Date(adminOrderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=adminOrderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					
					String title=adminOrderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						adminOrderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						adminOrderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.getGoods_name():" +adminOrderDto.getGoods_name());
					
					String[] str1=adminOrderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					String payment_way="";
					if(adminOrderDto.getCredit_card()!=null) payment_way="신용카드";
					if(adminOrderDto.getPhone_payment()!=null) payment_way="휴대폰결제";
					if(adminOrderDto.getRealtime_account_transfer()!=null) payment_way="실시간이체";
					if(adminOrderDto.getDirect_deposit()!=null) payment_way="직접입금";
					adminOrderDto.setPayment_way(payment_way);
					
					int order_status=adminOrderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					adminOrderDto.setStatus(status);
					
					LogAspect.logger.info(LogAspect.logMsg + "status:" +status);
					
					adminOrderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.getGoods_account():" +adminOrderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.toString():" +adminOrderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "adminChangeList:" +adminChangeList.toString());
			}		
			
			mav.addObject("count", count);
			mav.addObject("adminChangeList", adminChangeList);
		}
		mav.setViewName("adminChange.admin");
	}
	
	@Override
	public void adminDelivery(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			int count=adminOrderDao.getAdminDeliveryCount();
			LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
			
			List<OrderDto> adminDeliveryList=null;
			if(count >0) {
				adminDeliveryList=adminOrderDao.adminDeliveryList();
				LogAspect.logger.info(LogAspect.logMsg + "adminDeliveryList:" +adminDeliveryList.size());
				for(int i=0; i<adminDeliveryList.size(); i++) {
					OrderDto adminOrderDto=adminDeliveryList.get(i);
					adminOrderDto.setMaybe_date(new Date(adminOrderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					String[] str=adminOrderDto.getGoods().split("/");
					LogAspect.logger.info(LogAspect.logMsg + "str.length:" +str.length);
					
					String title=adminOrderDto.getTitle();
					LogAspect.logger.info(LogAspect.logMsg + "title:" +title);
					if(str.length>1) {
						adminOrderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
					}else if(str.length==1) {
						adminOrderDto.setGoods_name(title);
					}
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.getGoods_name():" +adminOrderDto.getGoods_name());
					
					String[] str1=adminOrderDto.getOrder_account().split("/");
					int account=0;  
					for(int j=0; j<str.length; j++) {
						account+=Integer.parseInt(str1[j]);
					}
					
					String payment_way="";
					if(adminOrderDto.getCredit_card()!=null) payment_way="신용카드";
					if(adminOrderDto.getPhone_payment()!=null) payment_way="휴대폰결제";
					if(adminOrderDto.getRealtime_account_transfer()!=null) payment_way="실시간이체";
					if(adminOrderDto.getDirect_deposit()!=null) payment_way="직접입금";
					adminOrderDto.setPayment_way(payment_way);
					
					int order_status=adminOrderDto.getOrder_status();
					String status="";
					status=status(order_status, status);
					adminOrderDto.setStatus(status);
					
					LogAspect.logger.info(LogAspect.logMsg + "status:" +status);
					
					adminOrderDto.setGoods_account(account);
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.getGoods_account():" +adminOrderDto.getGoods_account());
					LogAspect.logger.info(LogAspect.logMsg + "adminOrderDto.toString():" +adminOrderDto.toString());
					
				}
				LogAspect.logger.info(LogAspect.logMsg + "adminDeliveryList:" +adminDeliveryList.toString());
			}		
			
			mav.addObject("count", count);
			mav.addObject("adminDeliveryList", adminDeliveryList);
		}
		mav.setViewName("adminDelivery.admin");
	}
	
	@Override
	public void adminDetail(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			String order_number=request.getParameter("order_number");
			String order_id=adminOrderDao.getOrder_id(order_number);
			String order_name=adminOrderDao.getOrder_name(order_id);
			Date order_date=orderDao.getOrderDate(order_number);
			LogAspect.logger.info(LogAspect.logMsg+ "order_date:" + order_date);
			
			OrderDto orderDto=adminOrderDao.getAdminDetail(order_number);
			LogAspect.logger.info(LogAspect.logMsg+ "orderDto:" + orderDto.toString());
			
			String receive_name=orderDto.getReceive_name();
			String receive_phone=orderDto.getReceive_phone();
			String receive_addr=orderDto.getReceive_addr();
			
			String goods=orderDto.getGoods();
			String order_amount=orderDto.getOrder_account();
			String[] isbnArr=goods.split("/");
			String[] amountArr=order_amount.split("/");
			int count=isbnArr.length;
			
			List<OrderDto> adminDetailList=new ArrayList<OrderDto>();
			if(count>0) {
				for(int i=0; i<isbnArr.length; i++) {
					OrderDto adminDetailDto=new OrderDto();
					String isbn=isbnArr[i]+"/";
					adminDetailDto.setIsbn(isbnArr[i]);
					LogAspect.logger.info(LogAspect.logMsg+ "isbn:" + isbn);
					String publisher=adminOrderDao.getPublisher(isbn);
					adminDetailDto.setPublisher(publisher);
					String author=adminOrderDao.getAuthor(isbn);
					adminDetailDto.setAuthor(author);
					String amount=amountArr[i];
					String title=orderDao.getTitle(isbn);
					adminDetailDto.setGoods_name(title);
					adminDetailDto.setOrder_account(amount);
					long price=orderDao.getDetailPrice(isbn);
					adminDetailDto.setTotal_price(price);
					adminDetailDto.setOrder_date(order_date);
					adminDetailDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
					adminDetailList.add(adminDetailDto);
				}
			}
			LogAspect.logger.info(LogAspect.logMsg+ "adminDetailList:" + adminDetailList.size());
			
			mav.addObject("order_number", order_number);
			mav.addObject("order_id", order_id);
			mav.addObject("order_name", order_name);
			mav.addObject("receive_name", receive_name);
			mav.addObject("receive_phone", receive_phone);
			mav.addObject("receive_addr", receive_addr);
			mav.addObject("count", count);
			mav.addObject("adminDetailList", adminDetailList);
		}
		mav.setViewName("adminDetail.admin");
	}
	
	public void adminStatusChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String pageStatus=request.getParameter("pageStatus");
			int page=Integer.parseInt(pageStatus);
			LogAspect.logger.info(LogAspect.logMsg + "page:" + page);
			String order_number=request.getParameter("order_number");
			String status=request.getParameter("status");
			
			int adminOrderCheck=2;
			int adminChangeCheck=2;
			switch(page) {
			case 1:adminOrderCheck=orderDao.statusChange(order_number, status, id); break;
			case 2:adminChangeCheck=orderDao.statusChange(order_number, status, id); break;
			default : break;
			}
			
			mav.addObject("adminOrderCheck", adminOrderCheck);
			mav.addObject("adminChangeCheck", adminChangeCheck);
		}
		mav.setViewName("adminReturnPoint.admin");
	}

	@Override
	public void recommend(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		InterestDto scrollDto=interestDao.scrollRecommend();
		LogAspect.logger.info(LogAspect.logMsg + "리스트 출력!!!" + scrollDto);
		mav.addObject("scrollDto", scrollDto);
	}	
	@Override
	public void userPoint(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		int point=0;
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			int check=1;
			List<PointDto>list=paymentDao.selectPoint(id);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<list.size();i++) {
				list.get(i).setStr_order_date(sdf.format(list.get(i).getOrder_date()));
			}
			point=(int)list.get(0).getPoint();
			
			HashMap<String, Object> selectMap=paymentDao.selectState(id);
			
			mav.addObject("check",check);
			mav.addObject("point",point);
			mav.addObject("list",list);
			mav.addObject("selectMap",selectMap);
			mav.setViewName("userPointView.users");
		}else {
			int check=0;
			mav.addObject("check",check);
			mav.setViewName("userPointView.users");
		}
		
	}
}
