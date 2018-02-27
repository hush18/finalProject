<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/orderSearch.js"></script>
<script type="text/javascript" src="js/sideCategory.js"></script>
<link href="css/sideCategory.css" type="text/css" rel="stylesheet"/>
<link href="css/orderSearch.css" type="text/css" rel="stylesheet"/>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div class="widthline">
		<div class="path_hy">홈 > 주문관리 > 주문 상세 조회</div>
		<!-- 사이드메뉴 -->
		<div class="side_mh">
		<div class="category_mh">
			<div>
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
						<div class="info_box_hy"><span><a href="ordering.jsp">1</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>배송중</div>
						<div class="info_box_hy"><span><a href="delivery.jsp">1</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>환불/취소</div>
						<div class="info_box_hy"><span><a href="cancel.jsp">1</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>포인트</div>
						<div class="info_box_hy"><span><a href="">1</a></span></div>
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
				<div class="recentOrder_hy">
					<div class="list_hy">
						<div class="detail_list_con_hy">
							<div><a href="">언어의 온도 외 1개</a></div>
							<div>2권</div>
							<div class="detail_list_size_hy">2018.01.24</div>
							<div class="detail_list_size_hy">2018.01.25</div>
							<div class="detail_list_size_hy"><strong>97,340원</strong></div>
							<div class="detail_list_size_hy"><button class="block_btn_hy">취소</button></div>
						</div>
						
						<div class="detail_list_con_hy">
							<div><a href="">오라클 SQL과 PL/SQL</a></div>
							<div>3권</div>
							<div class="detail_list_size_hy">2018.01.24</div>
							<div class="detail_list_size_hy">2018.01.25</div>
							<div class="detail_list_size_hy"><strong>97,340원</strong></div>
							<div class="detail_list_size_hy"><button class="block_btn_hy">취소</button></div>
						</div>
						
						<div class="detail_list_con_hy">
							<div><a href="">오라클 SQL과 PL/SQL</a></div>
							<div>3권</div>
							<div class="detail_list_size_hy">2018.01.24</div>
							<div class="detail_list_size_hy">2018.01.25</div>
							<div class="detail_list_size_hy"><strong>97,340원</strong></div>
							<div class="detail_list_size_hy"><button class="block_btn_hy">취소</button></div>
						</div>
						<div class="detail_list_con_hy">
							<div><a href="">오라클 SQL과 PL/SQL</a></div>
							<div>3권</div>
							<div class="detail_list_size_hy">2018.01.24</div>
							<div class="detail_list_size_hy">2018.01.25</div>
							<div class="detail_list_size_hy"><strong>97,340원</strong></div>
							<div class="detail_list_size_hy"><button class="block_btn_hy">취소</button></div>
						</div>
						<div class="detail_list_con_hy">
							<div><a href="">오라클 SQL과 PL/SQL</a></div>
							<div>3권</div>
							<div class="detail_list_size_hy">2018.01.24</div>
							<div class="detail_list_size_hy">2018.01.25</div>
							<div class="detail_list_size_hy"><strong>97,340원</strong></div>
							<div class="detail_list_size_hy"><button class="block_btn_hy">취소</button></div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="delivery_info_hy">
				<h2 class="h2_hy">가격정보</h2>
				<div class="payment_Information_yk">
					<div class="payment_subject_yk">
						<div>주문합계 금액</div>
						<div>배송/포장비</div>
						<div style="padding: 0px 25px;">적립금 사용</div>
						<div style="border-right: 0px; width: 170px;">결제 금액</div>
					</div>
					<div class="payment_detail_yk" style="position: relative;">
						<div>11,700원</div>
						<img src="images/plus.png" class="icon_yk" style="z-index: 0; top: 68px; left: 167px;">
						<div>0원</div>
						<img src="images/negative.png" class="icon_yk" style="z-index: 0; top: 68px; left: 360px;">
						<div style="padding: 0px 25px;">
							<input type="text" name="point" value="1000" style="width: 80px; text-align: center;">
							/
							<span>누적 적립금</span>
						</div>
						<img src="images/equal.png" class="icon_yk" style="z-index: 0; top: 68px; left: 603px;">
						<div style="border-right: 0px; width: 170px;">11,700원</div>
					</div>
				</div>
			</div>
			<div class="delivery_info_hy">
				<h2 class="h2_hy">배송 정보</h2>
				<div class="delivery_con_hy">
					<div class="delivery_info1_hy"><div>받는사람</div><div>홍길동</div><div>주문자</div><div>강감찬</div></div>
					<div class="delivery_info1_hy"><div>휴대폰번호</div><div>010-1234-5678</div></div>
					<div class="delivery_info1_hy"><div>수령예상일</div><div>2018-02-26</div></div>
					<div class="delivery_info1_hy"><div>주소</div><div>서울특별시 강남구 역삼1동 한독빌딩 8층</div></div>
				</div>
			</div>
			<div class="payment_info_hy">
				<h2 class="h2_hy">결제 정보</h2>
				<div class="payment_con_hy">
					<div class="payment_info1_hy"><div>결제방법</div><div>카드</div></div>
					<div class="payment_info1_hy"><div>총금액</div><div>80,000</div><div>할인금액</div><div>8,000</div></div>
					<div class="payment_info1_hy"><div>실 결제금액</div><div>72,000</div></div>
				</div>
			</div>
			
			<div class="underimg_hy"><img src="images/info2.png" style="width:85%;"/></div>
		</div>
	</div>
</body>
</html>