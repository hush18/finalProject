<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link rel="stylesheet" type="text/css" href="css/user/sideCategory.css">
<link rel="stylesheet" type="text/css" href="css/user/userPointView.css">
<title>포인트</title>
<script type="text/javascript">
	function deleteMember() {
		window
				.open("deleteAccount.do", "",
						"width=400, height=400, scroll=yes");
	}
</script>
</head>
<body>
	<div class="widthline" style="overflow: hidden;">
		<div class="path_sc">홈 > 마이페이지</div>
		<div class="side_mh">
			<div class="category_mh">
				<!-- 마이페이지 -->
				<div class="orderManager_mh">
					<div class="title_mh">
						<h3>마이페이지</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li>
								<a href="myPage.do">회원정보</a>
							</li>
							<li>
								<a href="updateAccount.do">회원정보수정</a>
							</li>
							<li>
								<a href="userPoint.do">포인트내역</a>
							</li>
							<li>
								<a href="javascript:deleteMember()">회원탈퇴</a>
							</li>
						</ul>
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
		<!-- 사이드 메뉴 끝 -->


		<div class="con_hy">
			<div class="con_info_hy">
				<div>
					<h2 class="h2_hy">아이디님의 정보</h2>
				</div>
				<div class="con_info1_hy">
					<div class="info_head_hy">
						<div>진행중 주문 건</div>
						<div class="info_box_hy">
							<span><a href="ordering.jsp">1</a></span>
						</div>
					</div>
					<div class="info_head_hy">
						<div>배송중</div>
						<div class="info_box_hy">
							<span><a href="delivery.jsp">1</a></span>
						</div>
					</div>
					<div class="info_head_hy">
						<div>환불/취소</div>
						<div class="info_box_hy">
							<span><a href="cancel.jsp">1</a></span>
						</div>
					</div>
					<div class="info_head_hy">
						<div>포인트</div>
						<div class="info_box_hy">
							<span><a href="">1</a></span>
						</div>
					</div>
				</div>
			</div>
			<h2 class="h2_hy">포인트 조회</h2>
			<div class="search_faqlist_header_ej">
				<div style="width: 25%;">주문번호</div>
				<div style="width: 20%;">상품명</div>
				<div style="width: 15%;">결제 금액</div>
				<div style="width: 15%;">결제 날짜</div>
				<div style="width: 12.5%;">획득 포인트</div>
				<div style="width: 12.5%;">누적 포인트</div>
			</div>
			<!-- 리스트 출력 -->
			<div class="pointList_yk">
				<div>
					<div class="">
						<div>B0635_20180115174023</div>
						<div>언어의 온도 외 1개</div>
						<div>18,000워</div>
						<div>2018.02.09</div>
						<div>-2,000P</div>
						<div>2,000P</div>
					</div>
				</div>
				<div class="line_yk"></div>
				<div style="overflow: hidden;">
					<br>
					<a href="" style="float:right;">더보기</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

















