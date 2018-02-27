package com.team3.service;

import java.io.PrintWriter;
import java.util.Date;
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
import com.team3.user.order.dto.CartDto;
import com.team3.user.order.dto.OrderDto;

@Component
public class Service implements ServiceInterface {
	
	@Autowired
	private JavaMailSender mailSender;	// email
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
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
		
		String check=request.getParameter("check");
		if(check==null)check="2";
		
		int orderingCount=orderDao.getOrderingCount();
		int deliveryCount=orderDao.getDeliveryCount();
		int cancelCount=orderDao.getCancelCount();
		int point=orderDao.getPoint();
		
		String orderSearch_pageNumber=request.getParameter("orderSearch_pageNumber");
		if(request.getParameter("orderSearch_pageNumber")==null) {
			orderSearch_pageNumber="1";
		}
		LogAspect.logger.info(LogAspect.logMsg + "orderSearch_pageNumber:" +orderSearch_pageNumber);
		
		int pageSize=10;
		
		int currentPage=Integer.parseInt(orderSearch_pageNumber);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		int count=orderDao.getOrderSearchCount();
		LogAspect.logger.info(LogAspect.logMsg + "count:" + count);
		
		String list_value=request.getParameter("list_id");
		if(list_value==null) list_value="0";
		
		int list_id=Integer.parseInt(list_value);
		
		LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
		
		List<OrderDto> orderSearchList=null;
		if(count >0) {
			orderSearchList=orderDao.orderSearchList(startRow, endRow, list_id);
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
		mav.setViewName("orderSearch.users");
	}
	
	public String status(int order_status, String status) {
		switch (order_status) {
		case 0 : status="입금 대기중";	break;
		case 1 : status="상품준비완료";	break;
		case 2 : status="출고 준비중";	break;
		case 3 : status="출고 완료";	break;
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
		
		int deliveryCount=orderDao.getDeliveryCount();
		int cancelCount=orderDao.getCancelCount();
		int point=orderDao.getPoint();
		
		String ordering_pageNumber=request.getParameter("ordering_pageNumber");
		if(request.getParameter("ordering_pageNumber")==null) {
			ordering_pageNumber="1";
		}
		LogAspect.logger.info(LogAspect.logMsg + "ordering_pageNumber:" +ordering_pageNumber);
		
		int pageSize=10;
		
		int currentPage=Integer.parseInt(ordering_pageNumber);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		int orderingCount=orderDao.getOrderingCount();
		LogAspect.logger.info(LogAspect.logMsg + "orderingCount:" + orderingCount);
		
		String list_value=request.getParameter("list_id");
		if(list_value==null) list_value="0";
		
		int list_id=Integer.parseInt(list_value);
		
		LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
		
		List<OrderDto> orderingList=null;
		if(orderingCount >0) {
			orderingList=orderDao.orderingList(startRow, endRow, list_id);
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
		mav.setViewName("ordering.users");
	}

	@Override
	public void delivery(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int orderingCount=orderDao.getOrderingCount();
		int deliveryCount=orderDao.getDeliveryCount();
		int cancelCount=orderDao.getCancelCount();
		int point=orderDao.getPoint();
		
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
			deliveryList=orderDao.deliveryList(startRow, endRow, list_id);
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
		mav.setViewName("delivery.users");
	}
	
	public void cancel(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int orderingCount=orderDao.getOrderingCount();
		int deliveryCount=orderDao.getDeliveryCount();
		int cancelCount=orderDao.getCancelCount();
		int point=orderDao.getPoint();
		
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
		
		List<OrderDto> cancelList=null;
		if(cancelCount >0) {
			cancelList=orderDao.cancelList(startRow, endRow, list_id);
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
		mav.setViewName("cancel.users");
	}

	@Override
	public void buyList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		int orderingCount=orderDao.getOrderingCount();
		int deliveryCount=orderDao.getDeliveryCount();
		int cancelCount=orderDao.getCancelCount();
		int point=orderDao.getPoint();
		
		String buyList_pageNumber=request.getParameter("buyList_pageNumber");
		if(request.getParameter("buyList_pageNumber")==null) {
			buyList_pageNumber="1";
		}
		LogAspect.logger.info(LogAspect.logMsg + "buyList_pageNumber:" +buyList_pageNumber);
		
		int pageSize=10;
		
		int currentPage=Integer.parseInt(buyList_pageNumber);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		int buyListCount=orderDao.getBuyListCount();
		LogAspect.logger.info(LogAspect.logMsg + "buyListCount:" + buyListCount);
		
		String list_value=request.getParameter("list_id");
		if(list_value==null) list_value="0";
		
		int list_id=Integer.parseInt(list_value);
		
		LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
		
		List<OrderDto> buyListList=null;
		if(buyListCount >0) {
			buyListList=orderDao.buyListList(startRow, endRow, list_id);
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
		mav.setViewName("buyList.users");
	}
	
	@Override
	public void cart(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
	
		int value=2;
		String isbnList=request.getParameter("isbnList");
		/*String isbnList="9788934977346/9788970655499/";*/
		String[] isbnArr=null;
		if(isbnList!=null) {
			isbnArr=isbnList.split("/");
		}
	
		String quantityList=request.getParameter("quantityList");
		/*String quantityList="4/3/";*/
		String[] quantityArr=null;
		if(quantityList!=null) {
			quantityArr=quantityList.split("/");
		}
		
		int check=2;
		if(isbnArr!=null && quantityArr!=null) {
			for(int i=0; i< isbnArr.length; i++) {
				check=orderDao.insertCart(isbnArr[i]+"/", quantityArr[i]);
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
		
		int cartCount=orderDao.getCartCount();
		LogAspect.logger.info(LogAspect.logMsg + "cartCount:" + cartCount);
		
		String list_value=request.getParameter("list_id");
		if(list_value==null) list_value="0";
		
		int list_id=Integer.parseInt(list_value);
		
		LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
		int point=orderDao.getPoint();
		List<CartDto> cartList=null;
		if(cartCount >0) {
			cartList=orderDao.cartList(startRow, endRow, list_id);
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
		mav.setViewName("cart.users");
	}
	
	@Override
	public void cartListDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String isbnList=request.getParameter("isbnList");
		int value=2;
		if(isbnList!=null) {
			String[] isbn=isbnList.split("/");
			
			for(int i=0; i<isbn.length; i++) {
				value=orderDao.cartListDelete(isbn[i]+"/");
				LogAspect.logger.info(LogAspect.logMsg + "isbn[i]:" +isbn[i]);
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
		
		int cartCount=orderDao.getCartCount();
		LogAspect.logger.info(LogAspect.logMsg + "cartCount:" + cartCount);
		
		String list_value=request.getParameter("list_id");
		if(list_value==null) list_value="0";
		
		int list_id=Integer.parseInt(list_value);
		
		LogAspect.logger.info(LogAspect.logMsg + "list_id:" +list_id);
		int point=orderDao.getPoint();
		List<CartDto> cartList=null;
		if(cartCount >0) {
			cartList=orderDao.cartList(startRow, endRow, list_id);
			LogAspect.logger.info(LogAspect.logMsg + "cartList:" +cartList.toString());
		}		
		

		mav.addObject("cart_pageNumber", cart_pageNumber);
		mav.addObject("pageSize", pageSize);
		mav.addObject("cartList", cartList);
		mav.addObject("list_id", list_id);
		mav.addObject("cartCount", cartCount);
		mav.addObject("point", point);
		mav.addObject("value", value);
		mav.setViewName("cart.users");
	}
	
	@Override
	public void statusChange(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String pageStatus=request.getParameter("pageStatus");
		int page=Integer.parseInt(pageStatus);
		LogAspect.logger.info(LogAspect.logMsg + "page:" + page);
		String order_number=request.getParameter("order_number");
		String status=request.getParameter("status");
		int orderCheck=2;
		int orderingCheck=2;
		int deliveryCheck=2;
		int cancelCheck=2;
		int detailCheck=2;
		switch(page) {
		case 1:orderCheck=orderDao.statusChange(order_number, status); break;
		case 2:orderingCheck=orderDao.statusChange(order_number, status); break;
		case 3:deliveryCheck=orderDao.statusChange(order_number, status); break;
		case 4:cancelCheck=orderDao.statusChange(order_number, status); break;
		case 5:detailCheck=orderDao.statusChange(order_number, status); break;
		default : break;
		}
		
		LogAspect.logger.info(LogAspect.logMsg + "cancelCheck:" + cancelCheck);
		mav.addObject("orderCheck", orderCheck);
		mav.addObject("orderingCheck", orderingCheck);
		mav.addObject("deliveryCheck", deliveryCheck);
		mav.addObject("cancelCheck", cancelCheck);
		mav.addObject("detailCheck", detailCheck);
		mav.setViewName("returnPoint.users");
	}
	
@Override
	public void orderDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String pageStatus=request.getParameter("pageStatus");
		int page=Integer.parseInt(pageStatus);
		LogAspect.logger.info(LogAspect.logMsg + "page:" + page);
		String order_number=request.getParameter("order_number");
		int orderDeleteCheck=2;
		int orderingDeleteCheck=2;
		int detailDeleteCheck=2;
		switch(page) {
		case 1:orderDeleteCheck=orderDao.orderDelete(order_number); break;
		case 2:orderingDeleteCheck=orderDao.orderDelete(order_number); break;
		case 5:detailDeleteCheck=orderDao.orderDelete(order_number); break;
		default : break;
		}
		
		mav.addObject("orderDeleteCheck", orderDeleteCheck);
		mav.addObject("orderingDeleteCheck", orderingDeleteCheck);
		mav.addObject("detailDeleteCheck", detailDeleteCheck);
		mav.setViewName("returnPoint.users");
	}

	@Override
	public void detailOrder(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String order_number=request.getParameter("order_number");
		Date order_date=orderDao.getorderDate(order_number);
		OrderDto orderDto=orderDao.getDetailOrder(order_number);
		String goods=orderDto.getGoods();
		String order_amount=orderDto.getOrder_account();
		String[] isbnArr=goods.split("/");
		String[] amountArr=order_amount.split("/");
		
		List<OrderDto> detailList=null;
		OrderDto detailDto=null;
		for(int i=0; i<isbnArr.length; i++) {
			String isbn=isbnArr[i]+"/";
			String amount=amountArr[i];
			String title=orderDao.getDetailTitle(isbn);
			detailDto.setTitle(title);
			detailDto.setOrder_account(amount);
			long price=orderDao.getDetailPrice(isbn);
			detailDto.setTotal_price(price);
			
			detailList.add(detailDto);
		}
			
		mav.addObject("detailList", detailList);
		mav.addObject("order_date", order_date);
		mav.setViewName("detailOrder.users");
	}
}
