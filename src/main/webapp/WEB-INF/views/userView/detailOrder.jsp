<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String id = (String)session.getAttribute("mbId"); 
	if(id==null){%>
	<script type="text/javascript">
		alert("로그인을 해주세요");
		$(location).attr('href', "loginMember.do");
	</script>
	<% 
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/user/orderSearch.js"></script>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link href="css/user/sideCategory.css" type="text/css" rel="stylesheet"/>
<link href="css/user/orderSearch.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
	$(function() {
		$(".orderManager_mh > .title_mh").trigger('click');
		
		$(".titleClick").click(function() {
			var isbn=$(this).next().next().val();
			var url="bookInfo.do?isbn="+isbn;
			alert(url);
			$(location).attr('href', url);
		});
		
		
		
		$(".change_cancel").click(function(){
			var order_status=$('input[name="order_status"]').val();
			var order_number=$('input[name="order_number"]').val();
			var url="";
			
 			if(order_status == 0){
 				alert("주문을 취소하겠습니다.");
 				url="orderDelete.do?order_number="+order_number+"&pageStatus=1";
				$(location).attr('href', url); 				
 			}else if(order_status == 31){
 				alert("이미 취소요청된 주문입니다. 다시 주문하시려면 취소/반품/교환 목록으로 가세요");
 				history.back();
 			}else if(order_status == 11 || order_status == 21){
 				alert("배송을 처음부터 다시 시작하겠습니다.");
 				url="statusChange.do?order_number="+order_number+"&status=1&pageStatus=1";
 				$(location).attr('href', url);	
 			}else if(order_status == 3 || order_status == 4){
 				alert("현재 배송중인 상품이므로 요청이 불가합니다.");	
 			}else{
 				url="statusChange.do?order_number="+order_number+"&status=31&pageStatus=1";
				$(location).attr('href', url);
 			}
 		});
	});
</script>
</head>

<body>
	<div class="widthline">
		<div class="path_hy">홈 > 주문관리 > 주문 상세 조회</div>
		<!-- 사이드메뉴 -->
		<div class="side_mh">
		<div class="category_mh">
			<div>
			<input type="hidden" name="order_status" value="${order_status }"/>
			<input type="hidden" name="order_number" value="${order_number }"/>
				<!-- 주문관리 -->
				<div class="orderManager_mh">
					<div class="title_mh">
						<h3>주문관리</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li><a href="orderSearch.do">주문/배송 조회</a></li>
							<li><a href="cancel.do">취소/반품/교환 내역</a></li>
							<li><a href="buyList.do">구매내역</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 관심리스트 -->
				<div class="wishList_mh">
					<div class="title_mh">
						<h3>관심리스트</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li><a href="nearestList.do">최근본 상품</a></li>
							<li><a href="wishList.do">위시리스트</a></li>
							<li><a href="buyList.do">장바구니</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 고객센터 -->
				<div class="client_mh">
					<div class="title_mh">
						<h3>고객센터</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<p class="faq_sc">FAQ</p> 
						<ul>
							<li><a href="CustomerService_faq.do">회원</a></li>
							<li><a href="CustomerService_faq.do">상품</a></li>
							<li><a href="CustomerService_faq.do">입금/결제</a></li>
							<li><a href="CustomerService_faq.do">취소/교환/환불</a></li>
							<li><a href="CustomerService_faq.do">주문</a></li>
							<li><a href="CustomerService_faq.do">배송</a></li>
							<li><a href="CustomerService_faq.do">적립</a></li>
						</ul>
	
						<p class="consulting_sc">1:1 상담</p>
						<ul>
							<li><a href="CustomerService_consulting.do">1:1 상담하기</a></li>
							<li><a href="CustomerService_consultingList.do">1:1 상담내역</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 영업점 안내 -->
				<div class="map_mh">
					<div class="title_mh">
						<h3>영업점 안내</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
	
						<ul>
							<li><a href="Introduction.do">회사 소개</a></li>
							<li><a href="Map.do">매장 소개</a></li>
						</ul>
	
					</div>
				</div>
			</div>
		</div>

		<div class="category_time_mh">
			<div style="text-align: center;">
				<h3>고객센터</h3>
				<h2>0000-0000</h2>
			</div>
			<div style="text-align: center;">월~금 09:00 ~ 18:00</div>
			<div style="text-align: center;">(토요일,일요일,공휴일 휴무)</div>
		</div>
		</div>
		<div class="con_hy">
			<div class="con_info_hy">
				<div><h2 class="h2_hy">아이디님의 정보</h2></div>
				<div class="con_info1_hy">
					<div class="info_head_hy">
						<div>진행중 주문 건</div>
						<div class="info_box_hy"><span><a href="ordering.do">${orderingCount }</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>배송중</div>
						<div class="info_box_hy"><span><a href="delivery.do">${deliveryCount}</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>환불/취소</div>
						<div class="info_box_hy"><span><a href="cancel.do">${cancelCount }</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>포인트</div>
						<div class="info_box_hy"><span><a href="userPoint.do">${point }p</a></span></div>
					</div>
				</div>
			</div>
			<div>
				<h2 class="h2_hy">상세 주문 내역</h2>	 
				<div class="detail_faqlist_header_ej">
					<div>상품명</div>
					<div>수량</div>
					<div class="detail_list_size_hy">주문일자</div>
					<div class="detail_list_size_hy">수령예상일</div>
					<div class="detail_list_size_hy">주문금액</div>
					<div class="detail_list_size_hy">취소</div>
				</div>
				<c:if test="${count==0}">
					<script type="text/javascript">
						alert("잘못 조회된 주문입니다.");
						history.back();
					</script>
				</c:if>
				<c:if test="${count>0 }">
					<div class="recentOrder_hy">
						<div class="list_hy">
							<c:forEach var="detailList" items="${detailList}">
								<div class="detail_list_con_hy">
									<div class="title_hy titleClick" >${detailList.goods_name }</div>
									<div>${detailList.order_account }권</div>
									<input type="hidden" name="isbn" value="${detailList.isbn }"/>
									<div class="detail_list_size_hy"><fmt:formatDate value="${detailList.order_date}" pattern="yyyy.MM.dd"/></div>
									<div class="detail_list_size_hy"><fmt:formatDate value="${detailList.maybe_date}" pattern="yyyy.MM.dd"/></div>
									<fmt:parseNumber var="price"
											value="${detailList.total_price*detailList.order_account}"
											integerOnly="true" pattern="#,###,###"/>
									<div class="detail_list_size_hy"><strong><fmt:formatNumber value="${price }" pattern="#,###,###"/></strong></div>
									<div class="detail_list_size_hy"><button class="block_btn_hy change_cancel" >취소</button></div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
			<div class="delivery_info_hy">
				<h2 class="h2_hy">배송 정보</h2>
				<div class="delivery_con_hy">
					<div class="delivery_info1_hy"><div>받는사람</div><div>${receive_name }</div><div>주문자</div><div>${order_name }</div></div>
					<div class="delivery_info1_hy"><div>휴대폰번호</div><div>${receive_phone }</div></div>
					<div class="delivery_info1_hy"><div>수령예상일</div><div><fmt:formatDate value="${maybe_date}" pattern="yyyy.MM.dd"/></div></div>
					<div class="delivery_info1_hy"><div>주소</div><div>${receive_addr }</div></div>
				</div>
			</div>
			<div class="payment_info_hy">
				<h2 class="h2_hy">결제 정보</h2>
				<div class="payment_con_hy">
					<div class="payment_info1_hy"><div>결제방법</div><div>${payment_way }</div></div>
					<div class="payment_info1_hy"><div>총금액</div><div><fmt:formatNumber value="${total_price+use_point}" pattern="#,###,###"/></div><div>사용포인트</div><div><fmt:formatNumber value="${use_point }" pattern="#,###,###"/></div></div>
					<div class="payment_info1_hy"><div>실 결제금액</div><div><fmt:formatNumber value="${total_price }" pattern="#,###,###"/></div></div>
				</div>
			</div>
			
			<div class="underimg_hy"><img src="images/info2.png" style="width:85%;"/></div>
		</div>
	</div>
</body>
</html>