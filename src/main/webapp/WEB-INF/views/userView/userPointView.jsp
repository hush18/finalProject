<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link rel="stylesheet" type="text/css" href="css/user/sideCategory.css">
<link rel="stylesheet" type="text/css" href="css/user/userPointView.css">
<title>포인트</title>
<script type="text/javascript">
	$(function(){
		$(".orderManager_mh > .title_mh").trigger('click');
	});

	function deleteMember() {
		window
				.open("deleteAccount.do", "",
						"width=400, height=400, scroll=yes");
	}
</script>
</head>
<body>
	<c:if test="${check==1 }">
		<div class="widthline" style="overflow: hidden;">
			<div class="path_sc">홈 > 마이페이지</div>
			<div class="side_mh" style="margin-bottom: 275px;">
				<div class="category_mh">
					<!-- 마이페이지 -->
					<div class="orderManager_mh">
						<div class="title_mh">
							<h3>마이페이지</h3>
							<img src="images/down.png"> <img src="images/up.png">
						</div>
						<div class="sub_mh">
							<ul>
								<li><a href="myPage.do">회원정보</a></li>
								<li><a href="updateAccount.do">회원정보수정</a></li>
								<li><a href="userPoint.do">포인트내역</a></li>
								<li><a href="javascript:deleteMember()">회원탈퇴</a></li>
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
								<span><a href="ordering.do">${selectMap.orderingCount }</a></span>
							</div>
						</div>
						<div class="info_head_hy">
							<div>배송중</div>
							<div class="info_box_hy">
								<span><a href="delivery.do">${selectMap.deliveryCount}</a></span>
							</div>
						</div>
						<div class="info_head_hy">
							<div>환불/취소</div>
							<div class="info_box_hy">
								<span><a href="cancel.do">${selectMap.cancelCount}</a></span>
							</div>
						</div>
						<div class="info_head_hy">
							<div>포인트</div>
							<div class="info_box_hy">
								<span><a href="userPoint.do">${point }p</a></span>
							</div>
						</div>
					</div>
				</div>

				<h2 class="h2_hy">현재 포인트</h2>
				<div class="search_faqlist_header_ej">
					<div style="width: 20%;">주문번호</div>
					<div style="width: 40%;">상품명</div>
					<div style="width: 10%;">결제 금액</div>
					<div style="width: 11%;">결제 날짜</div>
					<div style="width: 9.5%;">사용 포인트</div>
					<div style="width: 9.5%;">적립 포인트</div>
				</div>
				<!-- 리스트 출력 -->
				<div class="pointList_yk">
					<c:forEach var="pointDto" items="${list }">
						<div style="height: 36px;">
							<div>
								<div>${pointDto.order_number }</div>
								<div>${pointDto.title }</div>
								<div>${pointDto.total_price }</div>
								<div>${pointDto.str_order_date }</div>
								<div>${pointDto.point_history }</div>
								<div>${pointDto.save_point }</div>
							</div>
						</div>
					</c:forEach>
					<div class="line_yk"></div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("세션이 만료되었습니다. 로그인 페이지로 이동합니다.")
			location.href="loginMember.do";
		</script>
	</c:if>
</body>
</html>