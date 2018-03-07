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
	
	//사용자 검색 공통기능 - 심제민
	public void searchDtoUtil(HttpServletResponse response, List<BookDto> bookDtoList, List<String> stringList) {
		try {
			JSONArray arrSearchValue = new JSONArray();
			String jsonStr = null;
			
			if(bookDtoList!=null) {
				for (int i = 0; i < bookDtoList.size(); i++) {
					BookDto bookDto = bookDtoList.get(i);
					bookDto.setIsbn(bookDto.getIsbn().split("/")[0]);
					String searchValue = bookDto.getTitle() + " - " + bookDto.getName();

					arrSearchValue.add(searchValue);
				}
			}
			
			if(stringList!=null) {
				for (int i = 0; i < stringList.size(); i++) {
					String name = stringList.get(i);
					
					arrSearchValue.add(name);
				}
			}
			
			jsonStr = JSONValue.toJSONString(arrSearchValue);
			response.setContentType("application/x-json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//사용자 카테고리별 검색기능 - 심제민
	@Override
	public void searchTitle(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String category = request.getParameter("category");

		List<BookDto> bookTitleList = bookDao.getBookTitleList(category.trim());
		
		//searchDtoUtil(HttpServletResponse, List<BookDto>, List<String>)
		searchDtoUtil(response, bookTitleList, null);
	}
	
	//사용자 지정도서 검색기능 - 심제민
	@Override
	public void searchWriter(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		List<String> nameList = adminBook.getWriterNameList();
		
		//searchDtoUtil(HttpServletResponse, List<BookDto>, List<String>)
		searchDtoUtil(response, null, nameList);
	}
	
	//사용자 도서 리스트 출력 페이징 연산 - 심제민
	public HashMap<String, Integer> bookListPage(String countBook,List<BookDto> list, String pageNumber, String bookListSize){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		int count=0;
		if (list != null) {
			count = list.size();
		}
		if(countBook!=null) {
			count = Integer.parseInt(countBook);
		}

		int pageCount = count / Integer.parseInt(bookListSize) + (count % Integer.parseInt(bookListSize) == 0 ? 0 : 1);
		int pageBlock = 10;
		int currentPage = Integer.parseInt(pageNumber);
		int startRow = (currentPage - 1) * Integer.parseInt(bookListSize) + 1;
		int endRow = currentPage * Integer.parseInt(bookListSize);
		if (count < endRow) {
			endRow = count;
		}

		int startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;

		int endPage = startPage + pageBlock;
		if (endPage > pageCount)
			endPage = pageCount + 1;
		
		map.put("count", count);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("pageCount", pageCount);
		map.put("pageBlock", pageBlock);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}

	//사용자 검색리스트 출력 - 심제민
	@Override
	public void searchList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String search = request.getParameter("search");
		String pageNumber = request.getParameter("pageNumber")==null ? "1":request.getParameter("pageNumber");
		String bookListSize = request.getParameter("bookListSize")==null ? "10":request.getParameter("bookListSize");
		String category_path = request.getParameter("category_path")==null ? "전체":request.getParameter("category_path");
		String view = request.getParameter("view")==null ? "detail":request.getParameter("view");

		String categoryValue = "," + category_path.trim();

		String category_number = bookDao.getCategoryNumber(categoryValue);
		if (category_number == null) {
			category_number = bookDao.getCategoryNumber(category_path.trim());
			categoryValue = categoryValue.split(",")[1];
		}

		List<BookDto> searchList = null;
		Map<String, String> dataMap = new HashMap<String, String>();
		if (search.indexOf("-") == -1) {
			dataMap.put("search", search);
			dataMap.put("categoryValue", categoryValue);
			searchList = bookDao.searchList(dataMap);
		} else {
			String[] str = search.split("-");
			String title = "";
			for(int i=0;i<str.length-1;i++) {
				if(i==str.length-2) {
					title+=str[i];
				} else {
					title+=str[i]+"-";
				}
			}
			String name = str[str.length-1];
			
			dataMap.put("title", title.trim());
			dataMap.put("name", name.trim());
			dataMap.put("categoryValue", categoryValue);
			searchList = bookDao.searchBook(dataMap);
		}

		CategoryDto categoryDto = bookDao.getCategoryPath(category_number);
		String path = getPath(category_path, categoryDto);

		HashMap<String, Integer> pageMap = bookListPage(null, searchList, pageNumber, bookListSize);
		
		List<BookDto> bookList = null;
		if (pageMap.get("count") != 0) {
			bookList = new ArrayList<BookDto>();
			for (int i = pageMap.get("startRow") - 1; i < pageMap.get("endRow"); i++) {
				BookDto bookDto = searchList.get(i);
				String isbn = bookDto.getIsbn();
				float grade = bookDao.getGrade(isbn);
				
				bookDto.setGrade(grade);
				
				searchList.set(i, bookDto);
				
				bookList.add(searchList.get(i));
			}
		}

		mav.addObject("view", view);
		mav.addObject("search", search);
		mav.addObject("category_number", category_number);
		mav.addObject("path", path);
		mav.addObject("categoryDto", categoryDto);
		mav.addObject("bookList", bookList);
		mav.addObject("pageCount", pageMap.get("pageCount"));
		mav.addObject("pageBlock", pageMap.get("pageBlock"));
		mav.addObject("startPage", pageMap.get("startPage"));
		mav.addObject("endPage", pageMap.get("endPage"));
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("count", pageMap.get("count"));
		mav.addObject("category_path", category_path);
		mav.addObject("bookListSize", bookListSize);
	}
	
	//사용자 카테고리 경로 연산 - 심제민
	public String getPath(String category_path, CategoryDto categoryDto) {
		String[] str = null;
		String path = null;
		if (category_path != null) {
			str = categoryDto.getCategory_path().split(",");
			if (str.length == 4) {
				path = str[str.length - 2];
			} else {
				path = str[str.length - 1];
			}
		}
		
		return path;
	}
	
	//사용자 도서 리스트 출력 - 심제민
	@Override
	public void bookList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String category_path = request.getParameter("category_path")==null ? "전체":request.getParameter("category_path");
		String sortValue = request.getParameter("sortValue")==null ? "WRITE_DATE":request.getParameter("sortValue");
		String pageNumber = request.getParameter("pageNumber")==null ? "1":request.getParameter("pageNumber");
		String bookListSize = request.getParameter("bookListSize")==null ? "10":request.getParameter("bookListSize");
		String view = request.getParameter("view")==null ? "detail":request.getParameter("view");

		String category_number = bookDao.getCategoryNumber("," + category_path.trim());
		if (category_number == null) {
			category_number = bookDao.getCategoryNumber(category_path.trim());
		}
		
		CategoryDto categoryDto = bookDao.getCategoryPath(category_number);
		String path = getPath(category_path, categoryDto);
		
		HashMap<String, Integer> pageMap = bookListPage(bookDao.getCount(category_number)+"", null, pageNumber, bookListSize);
		
		List<BookDto> bookListSearch = null;
		List<BookDto> bookList = null;
		if (pageMap.get("count") != 0) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("category_number", category_number);
			dataMap.put("sortValue", sortValue);
			
			bookListSearch = bookDao.getBookList(dataMap);
			LogAspect.logger.info(LogAspect.logMsg+pageMap.get("startRow"));
			LogAspect.logger.info(LogAspect.logMsg+pageMap.get("endRow"));
			bookList = new ArrayList<BookDto>();
			for (int i = pageMap.get("startRow") - 1; i < pageMap.get("endRow"); i++) {
				BookDto bookDto = bookListSearch.get(i);
				String isbn = bookDto.getIsbn();
				float grade = bookDao.getGrade(isbn);
				
				bookDto.setGrade(grade);
				
				bookListSearch.set(i, bookDto);
				
				bookList.add(bookListSearch.get(i));
			}
		}
		
		mav.addObject("view", view);
		mav.addObject("category_number", category_number);
		mav.addObject("sortValue", sortValue);
		mav.addObject("path", path);
		mav.addObject("categoryDto", categoryDto);
		mav.addObject("bookList", bookList);
		mav.addObject("pageCount", pageMap.get("pageCount"));
		mav.addObject("pageBlock", pageMap.get("pageBlock"));
		mav.addObject("startPage", pageMap.get("startPage"));
		mav.addObject("endPage", pageMap.get("endPage"));
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("count", pageMap.get("count"));
		mav.addObject("category_path", category_path);
		mav.addObject("bookListSize", bookListSize);
	}
	
	//사용자 도서 상세정보출력 - 심제민
	@Transactional
	@Override
	public void bookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");

		BookDto bookDto = bookDao.getBookInfo(isbn);
		float grade = bookDao.getGrade(isbn);
		bookDto.setGrade(grade);
		
		if (bookDto.getContents() != null) {
			bookDto.setContents(bookDto.getContents().replace("\n", "<br>"));
		}
		if (bookDto.getBook_introduction() != null) {
			bookDto.setBook_introduction(bookDto.getBook_introduction().replace("\n", "<br>"));
		}

		WriterDto writerDto = bookDao.getWriterInfo(bookDto.getWriter_number());

		ArrayList<BookDto> writerBookList = new ArrayList<BookDto>();
		String searchBook = null;
		if(writerDto.getWriter_bookList()!=null) {
			searchBook = writerDto.getWriter_bookList().replace(isbn, "");
			String[] bookNumberList = searchBook.split("/");

			for (int i = 0; i < bookNumberList.length; i++) {
				writerBookList.add(bookDao.getBookInfo(bookNumberList[i] + "/"));
			}
		}

		CategoryDto categoryDto = bookDao.getCategoryPath(bookDto.getCategory_number());

		String path = categoryDto.getCategory_path();
		String category_path = null;
		String[] str = null;
		if (path != null) {
			str = categoryDto.getCategory_path().split(",");
			if (str.length == 4) {
				path = str[str.length - 2];
				category_path = str[str.length - 1];
			} else {
				path = str[str.length - 1];
				category_path = str[str.length];
			}
		}

		mav.addObject("writerBookList", writerBookList);
		mav.addObject("writerDto", writerDto);
		mav.addObject("bookDto", bookDto);
		mav.addObject("path", path);
		mav.addObject("category_path", category_path);
		mav.addObject("categoryDto", categoryDto);
	}
	
	//사용자 리뷰 등록 - 심제민
	@Override
	public void reviewInsert(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		ReviewDto reviewDto = (ReviewDto) map.get("reviewDto");
		
		Date writer_date = new Date();
		reviewDto.setContent(reviewDto.getContent().replace("\n", "<br>"));
		reviewDto.setWriter_date(writer_date);
		int check = reviewDao.reviewInsert(reviewDto);
		
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
	
	//사용자 리뷰리스트 출력 - 심제민
	@Override
	public void reviewList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String isbn = request.getParameter("isbn");
		
		List<ReviewDto> reviewList = reviewDao.getReviewList(isbn);
		
		mav.addObject("reviewList", reviewList);
	}
	
	//관리자 도서리스트 출력 - 심제민
	@Override
	public void adminBookSearch(ModelAndView mav) {
		List<BookDto> bookList = adminBook.getAdminBookSearch();
		mav.addObject("bookList", bookList);
	}
	
	//관리자 도서 정보 - 심제민
	@Override
	public void adminBookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");

		BookDto bookDto = adminBook.adminBookInfo(isbn);

		WriterDto writerDto = adminBook.getWriter(bookDto.getWriter_number());

		List<CategoryDto> categoryList = adminBook.adminBookCategogyList();

		if (writerDto != null) {
			bookDto.setName(writerDto.getName());
			bookDto.setWriter_number(writerDto.getWriter_number());
		}
		bookDto.setIsbn(bookDto.getIsbn().split("/")[0]);
		mav.addObject("categoryList", categoryList);
		mav.addObject("bookDto", bookDto);
	}
	
	//도서의 저자정보 업데이트 로직 - 심제민
	public void bookWriterUpdate(int check, BookDto bookDto) {
		if (check != 0) {
			WriterDto writerDto = adminBook.getWriter(bookDto.getWriter_number());
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
	}
	
	//관리자 도서 업데이트 및 체크 - 심제민
	@Transactional
	@Override
	public void adminBookUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		BookDto bookDto = (BookDto) map.get("bookDto");
		bookDto.setIsbn(bookDto.getIsbn() + "/");

		int check = adminBook.adminBookUpdate(bookDto);
		bookWriterUpdate(check, bookDto);

		mav.addObject("check", check);
		mav.addObject("isbn", bookDto.getIsbn());
	}
	
	//관리자 도서 삭제 및 체크 - 심제민
	@Transactional
	@Override
	public void adminBookDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String isbn = request.getParameter("isbn");

		long writer_number = Long.parseLong(request.getParameter("writer_number"));

		int check = adminBook.adminBookDelete(isbn);
		if (check != 0) {
			WriterDto writerDto = adminBook.getWriter(writer_number);
			writerDto.setWriter_bookList(writerDto.getWriter_bookList().replace(isbn, ""));
			adminBook.adminWriterBookListUpdate(writerDto);
		}
		mav.addObject("check", check);
	}
	
	//관리자 도서등록 - 심제민
	@Override
	public void adminBookInsert(ModelAndView mav) {
		List<CategoryDto> categoryList = adminBook.adminBookCategogyList();
		mav.addObject("categoryList", categoryList);
	}
	
	//관리자 도서 등록체크 - 심제민
	@Override
	public void adminBookInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		BookDto bookDto = (BookDto) map.get("bookDto");
		bookDto.setIsbn(bookDto.getIsbn() + "/");

		int check = adminBook.adminBookInsert(bookDto);
		bookWriterUpdate(check, bookDto);

		mav.addObject("check", check);
		mav.addObject("isbn", bookDto.getIsbn());
	}
	
	//관리자 저자 검색리스트 - 심제민
	@Override
	public void adminWriterSearch(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String name = request.getParameter("name");
		List<WriterDto> writerList = null;
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
					writerDto.setTitle(title);
					writerList.set(i, writerDto);
				}
			}
			mav.addObject("name", name);
			mav.addObject("writerList", writerList);
		}
	}
	
	//관리자 저자 등록체크 - 심제민
	@Override
	public void adminWriterInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		WriterDto writerDto = (WriterDto) map.get("writerDto");
		
		int check = adminBook.adminWriterInsert(writerDto);

		mav.addObject("check", check);
	}

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

	// 로그인 - 김미화
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

	// 비밀번호 찾기-인증 메일보내기 - 맹인영
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

					
	// 로그인하기 - 김미화
	@Override
	public void memberLoginOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("id", id);
		hmap.put("password", password);

		// 마지막 로그인날짜 비교하기(휴면계정 처리)
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

				if (now.compareTo(loginYear) < 0) {
					memberDto = memberDao.memberLoginOK(hmap);

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

	// 관리자 고객센터 FAQ 입력 - 최은지
	@Override
	public void adminFaqInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
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
	
	// 관리자 고객센터 메인 FAQ - 최은지
	@Override
	public void adminFaqMain(ModelAndView mav) {
		int count = adminFaqDao.faqCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);

		List<AdminFaqDto> adminFaqList = new ArrayList<AdminFaqDto>();
		if (count > 0) {
			adminFaqList = adminFaqDao.adminFaqList();
		}

		for (int i = 0; i < adminFaqList.size(); i++) {
			adminFaqList.get(i).setContent(adminFaqList.get(i).getContent().replace("\r\n", "<br/>"));
		}

		mav.addObject("faqList", adminFaqList);
		mav.setViewName("adminFaqMain.admin");
	}

	// 관리자 고객센터 FAQ 수정 - 최은지
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

	// 관리자 고객센터 FAQ수정 입력 - 최은지
	@Override
	public void adminFaqUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		AdminFaqDto adminFaqDto = (AdminFaqDto) map.get("adminFaqDto");

		adminFaqDto.setContent(adminFaqDto.getContent().replace("<br/>", "\r\n"));

		int check = adminFaqDao.faqUpdateOk(adminFaqDto);
		mav.addObject("check", check);

		mav.setViewName("adminFaqUpdateOk.admin");
	}

	// 관리자 고객센터 1:1문의 삭제 - 최은지
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

	// 관리자 고객센터 공지사항 입력완료 - 최은지
	@Override
	public void adminNctInsertOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
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

	// 관리자 고객센터 공지사항 메인 - 최은지
	@Override
	public void adminNctMain(ModelAndView mav) {
		int count = adminNctDao.nctCount();
		LogAspect.logger.info(LogAspect.logMsg + "count: " + count);

		List<AdminNctDto> adminNctList = new ArrayList<AdminNctDto>();
		if (count > 0) {
			adminNctList = adminNctDao.adminNctList();
		}

		for (int i = 0; i < adminNctList.size(); i++) {
			adminNctList.get(i).setContent(adminNctList.get(i).getContent().replace("\r\n", "<br/>"));
		}

		mav.addObject("nctList", adminNctList);
		mav.setViewName("adminNctMain.admin");
	}

	// 관리자 고객센터 공지사항 삭제 완료 - 최은지
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

	// 관리자 고객센터 공지사항 수정 - 최은지
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

	// 관리자 고객센터 공지사항 수정 완료 - 최은지
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

	// 관리자 고객센터 1:1문의 - 최은지
	@Override
	public void adminCstMain(ModelAndView mav) {
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

	// 관리자 고객센터 1:1문의 답변 - 최은지
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

	// 관리자 고객센터 1:1문의 답변수정 - 최은지
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

	// 관리자 고객센터 1:1문의 삭제 - 최은지
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

	// 관리자 고객센터 FAQ TOP10 삭제 - 최은지
	@Override
	public void adminFaqTopDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int faqNumber = Integer.parseInt(request.getParameter("faq_number"));

		int check = adminFaqDao.faqTopDelete(faqNumber);

		mav.addObject("check", check);

		mav.setViewName("adminFaqTopDelete.admin");
	}

	// 관리자 고객센터 FAQ TOP10 입력 - 최은지
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

	// 아이디 찾기 - 김미화
	@Override
	public void findIdOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String name = request.getParameter("name");
		String email = request.getParameter("email") + "@" + request.getParameter("email_address");

		String id = memberDao.findId(name, email);

		mav.addObject("id", id);
		mav.setViewName("findIdOK.empty");
	}

	// 비밀번호 찾기 - 김미화
	@Override
	public void searchPwdOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = memberDao.findPwd(id);

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

	// 휴면계정해지하기 - 김미화
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

	// 회원관리(관리자) - 김미화
	@Override
	public void memberManage(ModelAndView mav) {
		List<MemberDto> memberList = memberManageDao.memberManage();

					
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

	// 회원 삭제하기 (관리자) - 김미화
	@Override
	public void adminMemberDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		mav.addObject(map.get("id"));
		mav.addObject(map.get("member_num"));
		mav.setViewName("adminMemberDelete.adminEmpty");
	}
	
	// 회원 삭제하기 (관리자) - 김미화
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

	// Header 도서, 저자 검색하기 - 김미화
	@Override
	public void searchHeader(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletResponse response = (HttpServletResponse) map.get("response");

		List<BookDto> bookList = bookDao.bookListMH();

		try {
			JSONArray arrTitle = new JSONArray();
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

	// 고객센터 TopTen - 최은지
	public void getTopTen(ModelAndView mav) {
		List<FaqDto> faqDtoTTList = faqDao.getTopTenList();
		
		for (int i = 0; i < faqDtoTTList.size(); i++) {
			faqDtoTTList.get(i).setContent(faqDtoTTList.get(i).getContent().replace("\r\n", "<br />"));
		}
		
		mav.addObject("faqDtoTTList", faqDtoTTList);
		mav.setViewName("CustomerService_main.users");
	}

	// 고객센터 FAQ - 최은지
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
	
	// 고객센터 1:1문의내역 - 최은지
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
	
	// 고객센터 1:1문의 - 최은지
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

	// 고객센터 1:1문의 입력 - 최은지
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
	
	// 고객센터 1:1문의 상품팝업창 - 최은지
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
	
	// 고객센터 1:1문의 주문팝업창 - 최은지
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

	//사용자 리스트 출력 코드중복
	public void orderUtil(List<OrderDto> OrderList) {
		for(int i=0; i<OrderList.size(); i++) {
			OrderDto orderDto=OrderList.get(i);
			orderDto.setMaybe_date(new Date(orderDto.getOrder_date().getTime() + 1000*60*60*24*2));
			String[] str=orderDto.getGoods().split("/");
			
			String title=orderDto.getTitle();
			
			if(str.length>1) {
				orderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
			}else if(str.length==1) {
				orderDto.setGoods_name(title);
			}
			
			String[] str1=orderDto.getOrder_account().split("/");
			int account=0;  
			for(int j=0; j<str.length; j++) {
				account+=Integer.parseInt(str1[j]);
			}
			
			orderDto.setGoods_account(account);
			
			int order_status=orderDto.getOrder_status();
			String status=status(order_status);
			orderDto.setStatus(status);
		}
	}

	//주문상태 변환
	public String status(int order_status) {
		String status=null;
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
	
	//페이지 기법
	public ArrayList<Integer> pageRow(String pageNumber){
		ArrayList<Integer> pageRow = new ArrayList<Integer>();
		
		int pageSize=10;
		
		int currentPage=Integer.parseInt(pageNumber);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		pageRow.add(pageSize);
		
		pageRow.add(startRow);
		pageRow.add(endRow);
		
		return pageRow;
	}
	
	//Count들 출력
	public ArrayList<Integer> getCount(String id){
		ArrayList<Integer> getCount=new ArrayList<Integer>();
		
		int count=orderDao.getOrderSearchCount(id);
		int orderingCount=orderDao.getOrderingCount(id);
		int deliveryCount=orderDao.getDeliveryCount(id);
		int cancelCount=orderDao.getCancelCount(id);
		int point=orderDao.getPoint(id);
		
		getCount.add(count);
		getCount.add(orderingCount);
		getCount.add(deliveryCount);
		getCount.add(cancelCount);
		getCount.add(point);
		
		return getCount;
	}
	
	//사용자 주문 배송 조회페이지
	@Override
	public void orderSearch(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String check=request.getParameter("check");
			if(check==null)check="2";
			
			ArrayList<Integer> getCount=getCount(id);
			
			String orderSearch_pageNumber=request.getParameter("orderSearch_pageNumber")==null ? "1":request.getParameter("orderSearch_pageNumber");
			
			ArrayList<Integer> pageRow = pageRow(orderSearch_pageNumber);
			
			
			String list_value=request.getParameter("list_id")==null ? "0":request.getParameter("list_id");
			
			int list_id=Integer.parseInt(list_value);
			
			String dateValue=null;
			String dateValueList=null;
			List<OrderDto> orderSearchList=null;
			if(getCount.get(0) > 0) {
				dateValue=request.getParameter("dateValue")==null ? "0":request.getParameter("dateValue");
				
				dateValueList=request.getParameter("dateValueList")==null ? "0":request.getParameter("dateValueList");
				
				orderSearchList=orderDao.orderSearchList(pageRow.get(1), pageRow.get(2), list_id, id, dateValue, dateValueList);
				
				orderUtil(orderSearchList);
			}
			mav.addObject("orderSearch_pageNumber", orderSearch_pageNumber);
			mav.addObject("pageSize", pageRow.get(0));
			mav.addObject("count", getCount.get(0));
			mav.addObject("orderingCount", getCount.get(1));
			mav.addObject("deliveryCount", getCount.get(2));
			mav.addObject("cancelCount", getCount.get(3));
			mav.addObject("point", getCount.get(4));
			mav.addObject("orderSearchList", orderSearchList);
			mav.addObject("list_id", list_id);
			mav.addObject("check", Integer.parseInt(check));
			mav.addObject("dateValueList", dateValueList);
			mav.addObject("dateValue", dateValue);
		}
		mav.setViewName("orderSearch.users");
	}
	
	//사용자 주문중인 상품 페이지
	@Override
	public void ordering(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			ArrayList<Integer> getCount=getCount(id);
			
			String ordering_pageNumber=request.getParameter("ordering_pageNumber");
			if(request.getParameter("ordering_pageNumber")==null) {
				ordering_pageNumber="1";
			}
			
			ArrayList<Integer> pageRow = pageRow(ordering_pageNumber);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			List<OrderDto> orderingList=null;
			if(getCount.get(1) >0) {
				orderingList=orderDao.orderingList(pageRow.get(1), pageRow.get(2), list_id, id);

				orderUtil(orderingList);
			}		
			
			mav.addObject("ordering_pageNumber", ordering_pageNumber);
			mav.addObject("pageSize", pageRow.get(0));
			mav.addObject("orderingCount", getCount.get(1));
			mav.addObject("deliveryCount", getCount.get(2));
			mav.addObject("cancelCount", getCount.get(3));
			mav.addObject("point", getCount.get(4));
			mav.addObject("orderingList", orderingList);
			mav.addObject("list_id", list_id);
		}
		mav.setViewName("ordering.users");
	}

	//사용자 배달중인 상품 페이지
	@Override
	public void delivery(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			ArrayList<Integer> getCount=getCount(id);
			
			String delivery_pageNumber=request.getParameter("delivery_pageNumber");
			if(request.getParameter("delivery_pageNumber")==null) {
				delivery_pageNumber="1";
			}
			
			ArrayList<Integer> pageRow = pageRow(delivery_pageNumber);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			List<OrderDto> deliveryList=null;
			if(getCount.get(2) >0) {
				deliveryList=orderDao.deliveryList(pageRow.get(1), pageRow.get(2), list_id, id);
				
				orderUtil(deliveryList);
			}		
			
			mav.addObject("delivery_pageNumber", delivery_pageNumber);
			mav.addObject("pageSize", pageRow.get(0));
			mav.addObject("orderingCount", getCount.get(1));
			mav.addObject("deliveryCount", getCount.get(2));
			mav.addObject("cancelCount", getCount.get(3));
			mav.addObject("point", getCount.get(4));
			mav.addObject("deliveryList", deliveryList);
			mav.addObject("list_id", list_id);
		}
		mav.setViewName("delivery.users");
	}

	//사용자 교환환불 페이지
	public void cancel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			ArrayList<Integer> getCount=getCount(id);
			
			String cancel_pageNumber=request.getParameter("cancel_pageNumber");
			if(request.getParameter("cancel_pageNumber")==null) {
				cancel_pageNumber="1";
			}
			
			ArrayList<Integer> pageRow = pageRow(cancel_pageNumber);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			String dateValue=null;
			String dateValueList=null;
			List<OrderDto> cancelList=null;
			if(getCount.get(3) >0) {
				dateValue=request.getParameter("dateValue");
				if(dateValue==null || dateValue=="") dateValue="0";
				
				dateValueList=request.getParameter("dateValueList");
				if(dateValueList==null ||dateValueList=="") dateValueList="0";
				
				cancelList=orderDao.cancelList(pageRow.get(1), pageRow.get(2), list_id, id, dateValue, dateValueList);
				
				orderUtil(cancelList);
			}		
			
			
			mav.addObject("cancel_pageNumber", cancel_pageNumber);
			mav.addObject("pageSize", pageRow.get(0));
			mav.addObject("orderingCount", getCount.get(1));
			mav.addObject("deliveryCount", getCount.get(2));
			mav.addObject("cancelCount", getCount.get(3));
			mav.addObject("point", getCount.get(4));
			mav.addObject("cancelList", cancelList);
			mav.addObject("list_id", list_id);
			mav.addObject("dateValueList", dateValueList);
			mav.addObject("dateValue", dateValue);
		}
		mav.setViewName("cancel.users");
	}

	//사용자 구매내역 페이지
	@Override
	public void buyList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			ArrayList<Integer> getCount=getCount(id);
			
			String buyList_pageNumber=request.getParameter("buyList_pageNumber");
			if(request.getParameter("buyList_pageNumber")==null) {
				buyList_pageNumber="1";
			}
			
			ArrayList<Integer> pageRow = pageRow(buyList_pageNumber);

			int buyListCount=orderDao.getBuyListCount(id);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			String dateValue=null;
			String dateValueList=null;
			List<OrderDto> buyListList=null;
			if(buyListCount >0) {
				dateValue=request.getParameter("dateValue");
				if(dateValue==null) dateValue="0";
				
				dateValueList=request.getParameter("dateValueList");
				if(dateValueList==null) dateValueList="0";
				
				buyListList=orderDao.buyListList(pageRow.get(1), pageRow.get(2), list_id, id, dateValue, dateValueList);
				
				orderUtil(buyListList);
			}		
			
			
			mav.addObject("buyList_pageNumber", buyList_pageNumber);
			mav.addObject("pageSize", pageRow.get(0));
			mav.addObject("orderingCount", getCount.get(1));
			mav.addObject("deliveryCount", getCount.get(2));
			mav.addObject("cancelCount", getCount.get(3));
			mav.addObject("point", getCount.get(4));
			mav.addObject("buyListList", buyListList);
			mav.addObject("list_id", list_id);
			mav.addObject("buyListCount", buyListCount);
			mav.addObject("dateValueList", dateValueList);
			mav.addObject("dateValue", dateValue);
		}
		mav.setViewName("buyList.users");
	}

	//사용자 장바구니 페이지
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
			
			ArrayList<Integer> pageRow = pageRow(cart_pageNumber);
			
			int cartCount=orderDao.getCartCount(id);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			
			int point=orderDao.getPoint(id);
			List<CartDto> cartList=null;
			if(cartCount >0) {
				cartList=orderDao.cartList(pageRow.get(1), pageRow.get(2), list_id, id);
			}		
			
			mav.addObject("cart_pageNumber", cart_pageNumber);
			mav.addObject("pageSize", pageRow.get(0));
			mav.addObject("cartList", cartList);
			mav.addObject("list_id", list_id);
			mav.addObject("cartCount", cartCount);
			mav.addObject("point", point);
			mav.addObject("check", check);
			mav.addObject("value", value);
		}
		mav.setViewName("cart.users");
	}

	//사용자 장바구니 삭제
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
					value=orderDao.cartListDelete(isbn[i], id);
					if(value==0)break;
				}
			}
			
			String cart_pageNumber=request.getParameter("cart_pageNumber");
			if(request.getParameter("cart_pageNumber")==null) {
				cart_pageNumber="1";
			}
			
			int pageSize=10;
			
			int currentPage=Integer.parseInt(cart_pageNumber);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			
			int cartCount=orderDao.getCartCount(id);
			
			String list_value=request.getParameter("list_id");
			if(list_value==null) list_value="0";
			
			int list_id=Integer.parseInt(list_value);
			int point=orderDao.getPoint(id);
			List<CartDto> cartList=null;
			if(cartCount >0) {
				cartList=orderDao.cartList(startRow, endRow, list_id, id);
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

	//사용자 주문상태 수정
	@Override
	public void statusChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String pageStatus=request.getParameter("pageStatus");
			int page=Integer.parseInt(pageStatus);
			String order_number=request.getParameter("order_number");
			String status=request.getParameter("status");
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
			mav.addObject("orderCheck", orderCheck);
			mav.addObject("orderingCheck", orderingCheck);
			mav.addObject("deliveryCheck", deliveryCheck);
			mav.addObject("cancelCheck", cancelCheck);
		}
		mav.setViewName("returnPoint.users");
	}

	//사용자 주문 삭제
	@Override
	public void orderDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String pageStatus=request.getParameter("pageStatus");
			int page=Integer.parseInt(pageStatus);
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

	//사용자 주문상세정보
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
			List<OrderDto> orderDtoList=orderDao.getDetailOrder(order_number, id);
			OrderDto orderDto=orderDtoList.get(0);
			String receive_name=orderDto.getReceive_name();
			String receive_phone=orderDto.getReceive_phone();
			String receive_addr=orderDto.getReceive_addr();
			int order_status=orderDto.getOrder_status();
			long total_price=orderDto.getTotal_price();
			
			int use_point=orderDao.getUse_point(order_number);
			
			String payment_way=getPayment_way(orderDto);
			
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
			
			ArrayList<Integer> getCount=getCount(id);
			
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
			mav.addObject("orderingCount", getCount.get(1));
			mav.addObject("deliveryCount", getCount.get(2));
			mav.addObject("cancelCount", getCount.get(3));
			mav.addObject("point", getCount.get(4));
			mav.addObject("detailList", detailList);
			mav.addObject("order_date", order_date);
		}
		mav.setViewName("detailOrder.users");
	}
	
	//관리자 리스트출력 중복코드
	public void adminOrderUtil(List<OrderDto> adminOrderList) {
		for(int i=0; i<adminOrderList.size(); i++) {
			OrderDto adminOrderDto=adminOrderList.get(i);
			adminOrderDto.setMaybe_date(new Date(adminOrderDto.getOrder_date().getTime() + 1000*60*60*24*2));
			String[] str=adminOrderDto.getGoods().split("/");
			
			String title=adminOrderDto.getTitle();
			if(str.length>1) {
				adminOrderDto.setGoods_name(title + " 외 " + (str.length-1) +"종");
			}else if(str.length==1) {
				adminOrderDto.setGoods_name(title);
			}
			
			String[] str1=adminOrderDto.getOrder_account().split("/");
			int account=0;  
			for(int j=0; j<str.length; j++) {
				account+=Integer.parseInt(str1[j]);
			}
			
			adminOrderDto.setGoods_account(account);
			
			String payment_way=getPayment_way(adminOrderDto);
			adminOrderDto.setPayment_way(payment_way);
			
			int order_status=adminOrderDto.getOrder_status();
			String status=status(order_status);
			adminOrderDto.setStatus(status);
		}
	}
	
	//결제방법 변환
	public String getPayment_way(OrderDto adminOrderDto) {
		String payment_way="";
		if(adminOrderDto.getCredit_card()!=null) payment_way="신용카드";
		if(adminOrderDto.getPhone_payment()!=null) payment_way="휴대폰결제";
		if(adminOrderDto.getRealtime_account_transfer()!=null) payment_way="실시간이체";
		if(adminOrderDto.getDirect_deposit()!=null) payment_way="직접입금";
		
		return payment_way;
	}
	
	//관리자 주문조회
	@Override
	public void adminOrderSearch(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int count=adminOrderDao.getAdminOrderCount();
		
		List<OrderDto> adminOrderList=null;
		if(count >0) {
			adminOrderList=adminOrderDao.adminOrderList();
			adminOrderUtil(adminOrderList);
		}		
		
		mav.addObject("count", count);
		mav.addObject("adminOrderList", adminOrderList);
		mav.setViewName("adminOrderSearch.admin");
	}
	
	//관리자 교환환불
	@Override
	public void adminChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int count=adminOrderDao.getAdminChangeCount();
		
		List<OrderDto> adminChangeList=null;
		if(count >0) {
			adminChangeList=adminOrderDao.adminChangeList();
			adminOrderUtil(adminChangeList);
		}		
		
		mav.addObject("count", count);
		mav.addObject("adminChangeList", adminChangeList);
		mav.setViewName("adminChange.admin");
	}
	
	//관리자 구매내역(배송완료)
	@Override
	public void adminDelivery(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
	
		int count=adminOrderDao.getAdminDeliveryCount();
		LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
		
		List<OrderDto> adminDeliveryList=null;
		if(count >0) {
			adminDeliveryList=adminOrderDao.adminDeliveryList();
			adminOrderUtil(adminDeliveryList);
		}		
		
		mav.addObject("count", count);
		mav.addObject("adminDeliveryList", adminDeliveryList);
		mav.setViewName("adminDelivery.admin");
	}
	
	//관리자 주문상세정보
	@Override
	public void adminDetail(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String order_number=request.getParameter("order_number");
		String order_id=adminOrderDao.getOrder_id(order_number);
		String order_name=adminOrderDao.getOrder_name(order_id);
		Date order_date=orderDao.getOrderDate(order_number);
		
		OrderDto orderDto=adminOrderDao.getAdminDetail(order_number);
		
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
				adminDetailDto.setIsbn(isbn);
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
		
		mav.addObject("order_number", order_number);
		mav.addObject("order_id", order_id);
		mav.addObject("order_name", order_name);
		mav.addObject("receive_name", receive_name);
		mav.addObject("receive_phone", receive_phone);
		mav.addObject("receive_addr", receive_addr);
		mav.addObject("count", count);
		mav.addObject("adminDetailList", adminDetailList);
		mav.setViewName("adminDetail.admin");
	}
	
	//관리자 주문상태변경
	public void adminStatusChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mbId")!=null) {
			String id=(String) session.getAttribute("mbId");
			
			String pageStatus=request.getParameter("pageStatus");
			int page=Integer.parseInt(pageStatus);
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
