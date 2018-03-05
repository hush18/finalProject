<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user/map.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9eb7abeb2fc9f320722ec7633170e3ef"></script>
<link rel="stylesheet" type="text/css" href="css/user/sideCategory.css">
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<script type="text/javascript" src="js/user/map.js"></script>
</head>
<body>
	<div class="div_yk"></div>
	<div class="widthline_yk">
		<div class="path_sc">홈 &gt; 영업점 안내 &gt; 매장 소개</div>
		<!-- 사이드메뉴 -->
		<div class=".sub_boss_yk" style="display: flex;">
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
									<li><a href="cart.do">장바구니</a></li>
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
									<li><a href="CustomerService_faq.do?up_category=회원">회원</a></li>
									<li><a href="CustomerService_faq.do?up_category=상품">상품</a></li>
									<li><a href="CustomerService_faq.do?up_category=입금/결제">입금/결제</a></li>
									<li><a href="CustomerService_faq.do?up_category=취소/교환/환불">취소/교환/환불</a></li>
									<li><a href="CustomerService_faq.do?up_category=주문">주문</a></li>
									<li><a href="CustomerService_faq.do?up_category=배송">배송</a></li>
									<li><a href="CustomerService_faq.do?up_category=적립">적립</a></li>
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

			<div class="content_yk" style="border: 0px solid; margin-left: 20px;">
				<div class="btn-group" style="width: 100%;">
					<c:forEach var="mapDto" items="${mapList}">
						<button id="${mapDto.lat},${mapDto.lng}" type="button" class="btn">${mapDto.store_name}</button>
					</c:forEach>
				</div>

				<div id="map" style="height: 400px;"></div>
				<c:forEach var="mapDto" items="${mapList}">
					<div id="mapDisplay" class="${mapDto.store_name }" style="display: none;">

						<p class="h2-hr">산책 ${mapDto.store_name }</p>
						<div class="map_Explanation_yk">
							<div class="map_Explanation_text_yk">
								<p style="color: #3C7B5e; font-size: 1.2em;">매장 소개</p>
								${mapDto.store_explanation }
							</div>
							<div class="map_Explanation_img_yk">
							<c:set var="img_path_list" value="${fn:split(mapDto.img_path,',') }"></c:set>
								<c:forEach var="img_path_listGet" items="${img_path_list }">
									<img style="width: 149px; height: 101px;" src="adminImg/${img_path_listGet }">
								</c:forEach>
							</div>
						</div>

						<p class="h2-hr">찾아오는 길</p>
						<div class="Directions_Explanation_yk">
							<div>
								<div>
									<div class="phone_Explanation_yk Explanation_div_yk">전화번호</div>
									<div class="phone_detailed detailed_div_yk">${mapDto.phone_fax }</div>
								</div>
								<br>

								<div>
									<div class="address_Explanation_yk Explanation_div_yk">주소</div>
									<div class="address_detailed detailed_div_yk">${mapDto.address }</div>
								</div>
								<br>

								<div>
									<div class="salesTime_Explanation_yk Explanation_div_yk">영업시간</div>
									<div class="salesTime_detailed detailed_div_yk">${mapDto.business_hours }</div>
								</div>
								<br>

								<div>
									<div class="find_Explanation_yk Explanation_div_yk">찾아오시는 길</div>
									<div class="find_detailed detailed_div_yk">${mapDto.directions }</div>
									<br> <br> <br> <br>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<script type="text/javascript">
					load();
				</script>
			</div>

		</div>
	</div>
	<br>
</body>
</html>