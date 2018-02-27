package com.team3.service;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.team3.admin.book.dao.AdminBook;
import com.team3.aop.LogAspect;
import com.team3.user.book.dao.BookDao;
import com.team3.user.book.dao.BookDaoImp;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;
import com.team3.admin.map.dao.AdminMapDao;
import com.team3.admin.map.dto.MapDto;
import com.team3.user.member.dto.ZipcodeDto;

import com.team3.aop.LogAspect;
import com.team3.user.interest.dao.InterestDao;
import com.team3.user.interest.dto.InterestDto;
import com.team3.user.map.dao.PaymentDao;
import com.team3.user.member.dao.MemberDao;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.member.dto.MemberDto;

@Component
public class Service implements ServiceInterface {

	@Autowired
	private JavaMailSender mailSender; // email

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private AdminBook adminBook;

	@Autowired
	private InterestDao interestDao;

	@Autowired
	private AdminMapDao adminMapDao;

	@Autowired
	private PaymentDao paymentDao;

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

		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		List<InterestDto> interestList = interestDao.nearestSelect(id);
		int count = interestList.size();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
		LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
		mav.addObject("interestList", interestList);
		mav.addObject("count", count);
		mav.addObject("id", id);
		mav.setViewName("nearestList.users");
	}

	// 최근본상품에서 장바구니로 이동
	@Override
	public void nearestUp(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		String isbn = request.getParameter("isbn");
		String[] strArr = isbn.split("/");
		for (int i = 0; i < strArr.length; i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i] += "/";
		}
		int check = interestDao.nearestUp(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		mav.addObject("check", check);
		mav.setViewName("nearestUp.users");
	}

	// 최근본상품에서 리스트 삭제
	@Override
	public void nearestDel(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		String isbn = request.getParameter("isbn");
		String[] strArr = isbn.split("/");
		for (int i = 0; i < strArr.length; i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i] += "/";
		}
		int check = interestDao.nearestDel(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
		mav.addObject("check", check);
		mav.setViewName("nearestDel.users");

	}

	// 위시리스트 출력
	@Override
	public void wishList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		List<InterestDto> interestList = interestDao.wishListSelect(id);
		int count = interestList.size();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);
		LogAspect.logger.info(LogAspect.logMsg + interestList.toString());
		mav.addObject("interestList", interestList);
		mav.addObject("count", count);
		mav.addObject("id", id);
		mav.setViewName("wishList.users");

	}

	// 위시리스트에서 장바구니 이동
	@Override
	public void wishListUp(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		String isbn = request.getParameter("isbn");
		String[] strArr = isbn.split("/");
		for (int i = 0; i < strArr.length; i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i] += "/";
		}
		int check = interestDao.wishListUp(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);

		mav.addObject("check", check);
		mav.setViewName("wishListUp.users");
	}

	// 위시리스트에서 리스트 삭제
	@Override
	public void wishListDel(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		String isbn = request.getParameter("isbn");
		String[] strArr = isbn.split("/");
		for (int i = 0; i < strArr.length; i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i]);
			strArr[i] += "/";
		}
		int check = interestDao.wishListDel(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);
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
		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		String isbn = request.getParameter("isbn");

		String[] strArr = isbn.split("/");
		for (int i = 0; i < strArr.length; i++) {
			LogAspect.logger.info(LogAspect.logMsg + strArr[i] + "확인");
			strArr[i] += "/";
		}
		int check = interestDao.wishListInsert(id, strArr);
		LogAspect.logger.info(LogAspect.logMsg + "업데이트가 제대로 됬나" + check);

		mav.addObject("check", check);
		mav.setViewName("wishListInsert.users");

	}

	// 최근본상품 Insert
	@Override
	public void nearestInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String isbn = request.getParameter("isbn");
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		String id = "user123";
		int check = interestDao.nearestInsert(id, isbn);
		LogAspect.logger.info(LogAspect.logMsg + "인서트!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + check);
	}

	@Override
	public void scrollBanner(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = "user123";
		// HttpSession session = request.getSession(); //세션받기 ID
		// String id=(String) session.getAttribute("id");
		List<InterestDto> scrollList = interestDao.scrollSelect(id);
		LogAspect.logger.info(LogAspect.logMsg + "여기까진 넘어오나요" + scrollList.toString());
		int scrollCount = scrollList.size();
		if (scrollList.size() > 2)
			scrollCount = 2;
		LogAspect.logger.info(LogAspect.logMsg + "scrollCount: " + scrollCount);
		LogAspect.logger.info(LogAspect.logMsg + scrollList.toString());
		mav.addObject("scrollList", scrollList);
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
	public void payment(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String isbn=request.getParameter("isbn");
		String[] isbnList=isbn.split(",");
		HttpSession session = request.getSession(); //세션받기 ID
		MemberDto memberDto=memberDao.updateAccount(session);
		
		if(isbnList.length==1) {
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			LogAspect.logger.info(LogAspect.logMsg +isbn+"\t"+quantity);
			
			BookDto bookDto=paymentDao.selectBook(isbnList[0]);
			mav.addObject("bookDto",bookDto);
			mav.addObject("quantity",quantity);
			mav.addObject("length",isbnList.length);
			mav.addObject("isbn",isbn);
		}else if(isbnList.length>1) {
			
		}
		mav.addObject("memberDto",memberDto);
		mav.setViewName("payment.users");
	}
	
	@Override
	public void paymentOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		
	}
	
	@Override
	public void addressList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		
		List<MemberAddressDto>memberAddressDtoList=paymentDao.getMemberAddress(id);
		mav.addObject("memberAddressDtoList",memberAddressDtoList);
		mav.addObject("size",memberAddressDtoList.size());
		mav.setViewName("addressList.empty");
	}
	
	@Override
	public void addAddress(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		MemberAddressDto memberAddressDto=(MemberAddressDto) map.get("memberAddressDto");
		String id=(String) request.getSession().getAttribute("id");
		memberAddressDto.setId(id);
		
		int check=paymentDao.insertZipcode(memberAddressDto);
		
		mav.addObject("check",check);
		mav.setViewName("addAddress.empty");
	}
	
	@Override
	public void deleteAddress(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String member_zipcode=request.getParameter("check");
		
		int check=paymentDao.deleteMemeberAddress(member_zipcode);
		LogAspect.logger.info(LogAspect.logMsg +"check : "+ check);
		
		mav.addObject("check",check);
		mav.setViewName("addressDelete.empty");
	}	

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
		String isbn=request.getParameter("isbn");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		LogAspect.logger.info(LogAspect.logMsg+isbn+"\t"+quantity);
		
//		List<BookDto> bookList = adminBook.getAdminBookSearch();
		
//		mav.addObject("bookList", bookList);
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
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String name = request.getParameter("name");
		List<WriterDto> writerList = null;
		LogAspect.logger.info(LogAspect.logMsg+"저자 검색 : "+name);
		if(name!=null) {
			writerList = adminBook.getWriterList(name);
			if(writerList!=null) {
				for(int i=0; i<writerList.size(); i++) {
					String[] bookList = writerList.get(i).getWriter_bookList().split("/");
					String title = adminBook.getTitle(bookList[0]+"/");
					if(bookList.length>1) {
						title +=" 외 "+(bookList.length-1)+"종";
					}
					
					WriterDto writerDto = writerList.get(i);
					LogAspect.logger.info(LogAspect.logMsg+"대표작 타이틀 : "+title);
					writerDto.setTitle(title);
					writerList.set(i, writerDto);
				}
			}
			
			mav.addObject("name", name);
			mav.addObject("writerList", writerList);
			LogAspect.logger.info(LogAspect.logMsg+"저자 검색 수 : "+writerList.size());
		}
	}
}
