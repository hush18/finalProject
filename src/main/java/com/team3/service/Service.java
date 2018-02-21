package com.team3.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.team3.admin.book.dao.AdminBook;
import com.team3.aop.LogAspect;
import com.team3.user.book.dao.BookDao;
import com.team3.user.book.dao.BookDaoImp;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.CategoryDto;
import com.team3.user.book.dto.WriterDto;
import com.team3.user.member.dao.MemberDao;
import com.team3.user.member.dto.MemberDto;

@Component
public class Service implements ServiceInterface {
	
	@Autowired
	private JavaMailSender mailSender;	// email
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AdminBook adminBook;

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
	public void bookList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String category_path = request.getParameter("category_path");
		
		String path = request.getParameter("path");
		
		String category_number = null;
		LogAspect.logger.info(LogAspect.logMsg+"요청 카테고리경로 : "+path);
		if(path==null) {
			path = "전체";
			category_number = "J11";
			LogAspect.logger.info(LogAspect.logMsg+"요청 카테고리값 : "+category_number);
		} else {
			category_number = bookDao.getCategoryNumber(","+path.trim());
			
			LogAspect.logger.info(LogAspect.logMsg+"요청 카테고리값 : "+category_number);
		}
		category_path=path;
		
		CategoryDto categoryDto = bookDao.getCategoryPath(category_number);
		LogAspect.logger.info(LogAspect.logMsg+"현재 카테고리정보 : "+categoryDto.toString());
		String[] str = null;
		if(path!=null) {
			str = categoryDto.getCategory_path().split(",");
			if(str.length==4) {
				path = str[str.length-2];
			} else {
				path = str[str.length-1];
			}
		}
		LogAspect.logger.info(LogAspect.logMsg+"현재 카테고리 출력 : "+category_path);
		
		
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		
		//LogAspect.logger.info(LogAspect.logMsg+"상위 카테고리 : "+path+", "+str.length);
		
		String bookListSize = request.getParameter("bookListSize");
		if(bookListSize==null) bookListSize="10";
		
		int count = bookDao.getCount(category_number);
		LogAspect.logger.info(LogAspect.logMsg+"현재 카테고리의 등록된 책의 갯수 : "+count);
		
		int pageCount = count/Integer.parseInt(bookListSize) + (count%Integer.parseInt(bookListSize)==0 ? 0:1);
		int pageBlock = 10;
		int currentPage = Integer.parseInt(pageNumber);
		int startRow = (currentPage-1)*Integer.parseInt(bookListSize)+1;
		int endRow = currentPage*Integer.parseInt(bookListSize);
		
		LogAspect.logger.info(LogAspect.logMsg+startRow+", "+endRow);
		//sortValue='WRITE_DATE
		
		String sortValue = request.getParameter("sortValue");
		if(sortValue==null) sortValue="WRITE_DATE";
		
		List<BookDto> bookList = null;
		if(count!=0) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("startRow", startRow);
			dataMap.put("endRow", endRow);
			dataMap.put("category_number", category_number);
			dataMap.put("sortValue", sortValue);
			LogAspect.logger.info(LogAspect.logMsg+startRow+", "+endRow+", "+category_number+", "+sortValue);
			bookList = bookDao.getBookList(dataMap);
			LogAspect.logger.info(LogAspect.logMsg+"읽어온 책의 갯수 : "+bookList.size());
		}
		
		int startPage = (int)((currentPage-1)/pageBlock) * pageBlock + 1;
		
		int endPage = startPage+pageBlock;
		if(endPage>pageCount) endPage=pageCount+1;
		
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
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String category_path = request.getParameter("category_path");
		
		String path = request.getParameter("path");
		
		String category_number = null;
		LogAspect.logger.info(LogAspect.logMsg+"요청 카테고리경로 : "+path);
		if(path==null) {
			path = "전체";
			category_number = "J11";
			LogAspect.logger.info(LogAspect.logMsg+"요청 카테고리값 : "+category_number);
		} else {
			category_number = bookDao.getCategoryNumber(","+path.trim());
			
			LogAspect.logger.info(LogAspect.logMsg+"요청 카테고리값 : "+category_number);
		}
		category_path=path;
		
		CategoryDto categoryDto = bookDao.getCategoryPath(category_number);
		LogAspect.logger.info(LogAspect.logMsg+"현재 카테고리정보 : "+categoryDto.toString());
		String[] str = null;
		if(path!=null) {
			str = categoryDto.getCategory_path().split(",");
			if(str.length==4) {
				path = str[str.length-2];
			} else {
				path = str[str.length-1];
			}
		}
		LogAspect.logger.info(LogAspect.logMsg+"현재 카테고리 출력 : "+category_path);
		
		String isbn = request.getParameter("isbn");
		
		BookDto bookDto = bookDao.getBookInfo(isbn);
		LogAspect.logger.info(LogAspect.logMsg+"읽어온 책의 정보 : "+bookDto.toString());
		if(bookDto.getContents()!=null) {
			bookDto.setContents(bookDto.getContents().replace("\r\n", "<br>"));
		}
		if(bookDto.getBook_introduction()!=null) {
			bookDto.setBook_introduction(bookDto.getBook_introduction().replace("\r\n", "<br>"));
		}
		
		WriterDto writerDto = bookDao.getWriterInfo(bookDto.getWriter_number());
		LogAspect.logger.info(LogAspect.logMsg+"읽어온 책의 저자정보 : "+writerDto.toString());
		
		ArrayList<BookDto> writerBookList = new ArrayList<BookDto>();
		String searchBook = writerDto.getWriter_bookList().replace(isbn, "");
		LogAspect.logger.info(LogAspect.logMsg+"해당 저자의 다른 책 번호 : "+searchBook+",   "+writerDto.getWriter_bookList());
		String[] bookNumberList = searchBook.split("/");
		
		for(int i=0; i<bookNumberList.length; i++) {
			writerBookList.add(bookDao.getBookInfo(bookNumberList[i]+"/"));
		}
		
		LogAspect.logger.info(LogAspect.logMsg+"해당 저자의 다른 책 갯수 : "+writerBookList.size());
		
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
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		List<BookDto> bookList = adminBook.getAdminBookSearch();
		
		mav.addObject("bookList", bookList);
	}
	
	@Override
	public void adminBookInfo(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String isbn = request.getParameter("isbn");
		
		BookDto bookDto = adminBook.adminBookInfo(isbn);
		CategoryDto categoryDto = adminBook.adminBookInfoCategory(bookDto.getCategory_number());
		
		String[] category1 = {"문학"};//adminBook.adminBookCategogy1().split(","); 현재는 일부 카테고리만 사용하기때문에
		
		HashMap<String, HashMap<String, HashMap<String, String[]>>> categoryMap = new HashMap<String, HashMap<String, HashMap<String, String[]>>>();
		
		for(int i=0; i<category1.length;i++) {
			HashMap<String, HashMap<String, String[]>> categoryMap_1 = new HashMap<String, HashMap<String, String[]>>();
			
			String key = category1[i];
			String[] category2 = adminBook.adminBookCategogy2(key).split(",");
			for(int x=0; x<category2.length;x++) {
				HashMap<String, String[]> categoryMap_2 = new HashMap<String, String[]>();
				
				String xkey = category2[x];
				String[] category3 = adminBook.adminBookCategogy2(xkey).split(",");
				for(int y=0; y<category3.length;y++) {
					
					String ykey = category3[y];
					String[] category4 = adminBook.adminBookCategogy2(ykey).split(",");
					
					categoryMap_2.put(ykey, category4);
				}
				
				categoryMap_1.put(xkey, categoryMap_2);
			}
			
			categoryMap.put(key, categoryMap_1);
		}
		
		
		String[] date = bookDto.getWrite_date().split("\\.");
		LogAspect.logger.info(LogAspect.logMsg+"날짜확인 : "+bookDto.getWrite_date()+", "+date.length);
		bookDto.setWrite_date(date[1]+"/"+date[2]+"/"+date[0]);
		
		mav.addObject("categoryMap", categoryMap);
		mav.addObject("category1", category1);
		mav.addObject("bookDto", bookDto);
	}
}























