<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 조회</title>
<link href="css/CustomerService_order_search.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="widthline_ej">
		<div class="qboss_ej">
			<div class="sub1_ej">
				<span>주문번호 조회</span>
			</div>
			<div class="sub2_ej">▶기간내 주문하신 모든 주문번호가 조회됩니다.</div>
			<div class="sub3_ej">
				<div>기간별 조회&nbsp;&nbsp;&nbsp;|</div>
				<div>
					<a href="#">15일</a> <a href="#">1개월</a> <a href="#">2개월</a> <a
						href="#">3개월</a>
				</div>
			</div>
			<div class="sub2_ej">▶쇼핑내역</div>
			<div class="sub4_ej">
				<div>주문일자</div>
				<div>주문번호</div>
				<div>주문상품</div>
				<div>수량</div>
			</div>
			<c:forEach var="i" begin="1" end="5">
			<div class="sub5_ej">
				<ul>
					<li class="sub5_li1_ej">2018.02.13</li>
					<li class="sub5_li2_ej">1125412485</li>
					<li class="sub5_li3_ej"><a>die냐 Diet냐 - 다이냐 다이어트냐</a></li>
					<li class="sub5_li4_ej">1</li>
				</ul>
			</div>
		</c:forEach>
		</div>
		<div class="page2_ej">< 페이지 ></div>
	</div>
</body>
</html>