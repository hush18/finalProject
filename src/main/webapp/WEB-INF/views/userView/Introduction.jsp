<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Introduction.css">
<link rel="stylesheet" href="css/map.css">
<script type="text/javascript" src="js/Introduction.js"></script>
<link rel="stylesheet" type="text/css" href="css/sideCategory.css">
<script type="text/javascript" src="js/sideCategory.js"></script>
</head>
<body>
	<div class="widthline_yk">
		<div class="path_sc">홈 &gt; 영업점 안내 &gt; 회사 소개</div>
		<div class="sub_boss_ej" style="display: flex;">
			<div class="category_ej">
				<div class="category_mh">
					<div>
						<!-- 주문관리 -->
						<div class="orderManager_mh">
							<div class="title_mh">
								<h3>주문관리</h3>
								<img src="images/down.png"> <img src="images/up.png">
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
								<img src="images/down.png"> <img src="images/up.png">
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
								<img src="images/down.png"> <img src="images/up.png">
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
									<li><a href="CustomerService_consultingList.do">1:1
											상담내역</a></li>
								</ul>
							</div>
						</div>

						<!-- 영업점 안내 -->
						<div class="map_mh">
							<div class="title_mh">
								<h3>영업점 안내</h3>
								<img src="images/down.png"> <img src="images/up.png">
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

			<div style="margin-left: 20px;">
				<div style="text-align: center;margin-bottom: 40px;">
					<img src="images/header/logo.png" width="50%"
						style="margin: 0px; height: 200px;">
				</div>
				<h2 class="h2-hr">산책소개</h2>
				<div class="mountainbook_introduction_yk" style="margin-bottom: 40px;">
					<div>
						회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~<br> 
						회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~회사 설명~<br>
					</div>
				</div>

				<h2 class="h2-hr">산책의 의미</h2>
				<div class="mountainbook_meaning_yk" style="margin-bottom: 20px;">
					산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~<br> 
					산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~산책 설명~<br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>