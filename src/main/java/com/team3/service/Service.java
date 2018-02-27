package com.team3.service;

import java.io.File;

import java.io.*;
import java.util.*;
import java.text.*;
import javax.servlet.http.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.*;
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
import com.team3.admin.book.dao.AdminBook;
import com.team3.user.book.dao.BookDao;
import com.team3.user.book.dao.BookDaoImp;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;
import com.team3.admin.map.dao.AdminMapDao;
import com.team3.admin.map.dto.MapDto;
import com.team3.user.member.dto.ZipcodeDto;
import com.team3.admin.sales.dao.SalesDao;
import com.team3.admin.sales.dto.SalesDto;

import com.team3.user.oauth.bo.FacebookLoginBO;
import com.team3.user.oauth.bo.NaverLoginBO;
import com.team3.user.faq.dao.FaqDao;
import com.team3.user.faq.dto.FaqDto;
import com.team3.user.interest.dao.InterestDao;
import com.team3.user.interest.dto.InterestDto;
import com.team3.user.map.dao.MapDao;
import com.team3.user.member.dto.MemberDto;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Component
public class Service implements ServiceInterface {

	@Autowired
	private JavaMailSender mailSender; // email

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private AdminFaqDao adminFaqDao;
	@Autowired
	private AdminNctDao adminNctDao;
	@Autowired
	private AdminCstDao adminCstDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private AdminBook adminBook;

	@Autowired
	private InterestDao interestDao;
	
	@Autowired
	private SalesDao salesDao;

	@Autowired
	private AdminMapDao adminMapDao;

	@Autowired
	private MapDao mapDao;

	@Autowired
	private FaqDao faqDao;

	/* NaverLoginBO */
	@Autowired
	private NaverLoginBO naverLoginBO;

	@Autowired
	private FacebookLoginBO facebookLoginBO;

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

		int check = memberDao.deleteAccount(memberDto);

		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("password");

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
				cal.add(Calendar.DATE, 7);
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
	public void cstOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		CstDto cstDto = (CstDto) map.get("cstDto");

		LogAspect.logger.info(LogAspect.logMsg);
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

	@Override
	public void adminFaqMain(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber);

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

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber);

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

		mav.setViewName("adminNctInsertOk.admin");
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
		if (check > 0) {
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
		HttpSession session = request.getSession(); //세션받기 ID
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
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
		HttpSession session = request.getSession(); //세션받기 ID
		
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			int check = interestDao.nearestUp(id, strArr);
		}
		int check=-1;
		mav.addObject("check", check);
		mav.setViewName("nearestUp.users");
	}

	// 최근본상품에서 리스트 삭제
	@Override
	public void nearestDel(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); //세션받기 ID
		
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			int check = interestDao.nearestDel(id, strArr);
		}
		int check=-1;
		mav.addObject("check", check);
		mav.setViewName("nearestDel.users");

	}

	// 위시리스트 출력
	@Override
	public void wishList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); //세션받기 ID
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
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
		HttpSession session = request.getSession(); //세션받기 ID
		
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			int check = interestDao.wishListUp(id, strArr);
		}
		int check=-1;
		mav.addObject("check", check);
		mav.setViewName("wishListUp.users");
	}

	// 위시리스트에서 리스트 삭제
	@Override
	public void wishListDel(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession(); //세션받기 ID
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
			String isbn = request.getParameter("isbn");
			String[] strArr = isbn.split("/");
			for (int i = 0; i < strArr.length; i++) {
				LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
				strArr[i] += "/";
			}
			int check = interestDao.wishListDel(id, strArr);
		}
		int check=-1;
		mav.addObject("check", check);
		mav.setViewName("wishListDel.users");

	}

	public void createAccountOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		MemberDto memberDto = (MemberDto) map.get("memberDto");

		// LogAspect.logger.info(LogAspect.logMsg + "맴버 디티오 : " + memberDto);
		int check = memberDao.insertAccount(memberDto);
		LogAspect.logger.info(LogAspect.logMsg + "인서트 체크 값 : " + check);

		mav.setViewName("redirect:http://localhost:8081/mountainBooks/index.jsp");
		// mav.setViewName("userMain.users");
	}

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

	@Override
	public void searchPwdOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
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
		HttpSession session = request.getSession(); //세션받기 ID
		String id=(String) session.getAttribute("id");
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
		HttpSession session = request.getSession(); //세션받기 ID
		String id=(String) session.getAttribute("id");
		interestDao.nearestInsert(id, isbn);
	}

	@Override
	public void scrollBanner(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();		//세션받기 ID
		if(session.getAttribute("id")!=null) {
			String id=(String) session.getAttribute("id");
			List<InterestDto> scrollList=interestDao.scrollSelect(id);
			int scrollCount=scrollList.size();
			if(scrollList.size() > 2) scrollCount=2;
			mav.addObject("scrollList", scrollList);
		}
	}

	@Override
	public void adminSales(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String value=request.getParameter("value");
		List<SalesDto> salesList=salesDao.salesSelect(value);
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

	@Override
	public void getTopTen(ModelAndView mav) {
		List<FaqDto> faqDtoTTList = faqDao.getTopTenList();
		mav.addObject("faqDtoTTList", faqDtoTTList);
		mav.setViewName("CustomerService_main.users");
	}

	@Override
	public void getFaq(ModelAndView mav) {
		List<FaqDto> faqDtoList = faqDao.getTopTenList();

		for (int i = 0; i < faqDtoList.size(); i++) {
			faqDtoList.get(i).setContent(faqDtoList.get(i).getContent().replace("\r\n", "<br />"));
		}

		mav.addObject("faqDtoList", faqDtoList);
		mav.setViewName("CustomerService_faq.users");
	}

	@Override
	public void bookList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String category_path = request.getParameter("category_path");

		String path = request.getParameter("path");

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

		List<BookDto> bookList = null;
		if (count != 0) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("startRow", startRow);
			dataMap.put("endRow", endRow);
			dataMap.put("category_number", category_number);
			dataMap.put("sortValue", sortValue);
			LogAspect.logger
					.info(LogAspect.logMsg + startRow + ", " + endRow + ", " + category_number + ", " + sortValue);
			bookList = bookDao.getBookList(dataMap);
			LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 갯수 : " + bookList.size());
		}

		int startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;

		int endPage = startPage + pageBlock;
		if (endPage > pageCount)
			endPage = pageCount + 1;

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

	@Override
	public void bookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String category_path = request.getParameter("category_path");

		String path = request.getParameter("path");

		String category_number = null;
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리경로 : " + category_path);
		LogAspect.logger.info(LogAspect.logMsg + "요청 카테고리경로 : " + path);

		category_number = bookDao.getCategoryNumber("," + path.trim());
		if (category_number == null) {
			category_number = bookDao.getCategoryNumber(category_path.trim());
		}

		if (request.getParameter("category_number") != null) {
			category_number = request.getParameter("category_number");
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

		String isbn = request.getParameter("isbn");

		BookDto bookDto = bookDao.getBookInfo(isbn);
		LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 정보 : " + bookDto.toString());
		if (bookDto.getContents() != null) {
			bookDto.setContents(bookDto.getContents().replace("\r\n", "<br>"));
		}
		if (bookDto.getBook_introduction() != null) {
			bookDto.setBook_introduction(bookDto.getBook_introduction().replace("\r\n", "<br>"));
		}

		WriterDto writerDto = bookDao.getWriterInfo(bookDto.getWriter_number());
		LogAspect.logger.info(LogAspect.logMsg + "읽어온 책의 저자정보 : " + writerDto.toString());

		ArrayList<BookDto> writerBookList = new ArrayList<BookDto>();
		String searchBook = writerDto.getWriter_bookList().replace(isbn, "");
		LogAspect.logger
				.info(LogAspect.logMsg + "해당 저자의 다른 책 번호 : " + searchBook + ",   " + writerDto.getWriter_bookList());
		String[] bookNumberList = searchBook.split("/");

		for (int i = 0; i < bookNumberList.length; i++) {
			writerBookList.add(bookDao.getBookInfo(bookNumberList[i] + "/"));
		}

		LogAspect.logger.info(LogAspect.logMsg + "해당 저자의 다른 책 갯수 : " + writerBookList.size());

		mav.addObject("writerBookList", writerBookList);
		mav.addObject("writerDto", writerDto);
		mav.addObject("bookDto", bookDto);
		mav.addObject("path", path);
		mav.addObject("categoryDto", categoryDto);
		mav.addObject("category_path", category_path);
	}

	@Override
	public void adminBookSearch(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		List<BookDto> bookList = adminBook.getAdminBookSearch();

		mav.addObject("bookList", bookList);
	}

	@Override
	public void adminBookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");

		BookDto bookDto = adminBook.adminBookInfo(isbn);

		WriterDto writerDto = adminBook.getWriter(isbn);

		List<CategoryDto> categoryList = adminBook.adminBookCategogyList();

		String imagePath = request.getRealPath("/").split("apache")[0]
				+ "workspace\\finalProject\\src\\main\\webapp\\images\\books";
		LogAspect.logger.info(LogAspect.logMsg + "프로젝트 경로 : " + imagePath);

		String[] date = bookDto.getWrite_date().split("\\.");
		LogAspect.logger.info(LogAspect.logMsg + "날짜확인 : " + bookDto.getWrite_date() + ", " + date.length);
		bookDto.setWrite_date(date[1] + "/" + date[2] + "/" + date[0]);

		if (writerDto != null) {
			LogAspect.logger.info(LogAspect.logMsg + "저자확인 : " + writerDto.toString());

			bookDto.setName(writerDto.getName());
			bookDto.setWriter_number(writerDto.getWriter_number());
		}

		mav.addObject("categoryList", categoryList);
		mav.addObject("bookDto", bookDto);
	}

	@Override
	public void adminBookUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");

		String imagePath = request.getRealPath("/").split("apache")[0]
				+ "workspace\\finalProject\\src\\main\\webapp\\images\\books";
		LogAspect.logger.info(LogAspect.logMsg + "프로젝트 이미지폴더 경로 : " + imagePath);

		MultipartFile upImage = request.getFile("image");

		String fileName = Long.toString(System.currentTimeMillis()) + "_" + upImage.getOriginalFilename();
		long fileSize = upImage.getSize();
		LogAspect.logger.info(LogAspect.logMsg + "파일명 : " + fileName + ", 파일크기 : " + fileSize);

		BookDto bookDto = (BookDto) map.get("bookDto");

		LogAspect.logger.info(LogAspect.logMsg + "책 수정정보 : ");

		LogAspect.logger.info(LogAspect.logMsg + "책 수정정보 : " + bookDto.toString());

		int check;
		if (fileSize != 0) {
			check = adminBook.adminBookUpdateFile(bookDto);
		} else {
			check = adminBook.adminBookUpdate(bookDto);
		}

		if (check != 0) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("isbn", bookDto.getIsbn());
			hashMap.put("writer_number", bookDto.getWriter_number());
			adminBook.writerListUpdate(hashMap);
		}

		mav.addObject("check", check);
		mav.addObject("isbn", bookDto.getIsbn());

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
					String[] bookList = writerList.get(i).getWriter_bookList().split("/");
					String title = adminBook.getTitle(bookList[0] + "/");
					if (bookList.length > 1) {
						title += " 외 " + (bookList.length - 1) + "종";
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
}
