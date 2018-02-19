<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 조회</title>
<link href="css/CustomerService_question_search.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<div class="widthline_ej">
	<div class="qboss_ej">
		<div class="qsub1_ej">
			<span>문의상품 조회</span>
		</div>
		<div class="qsub2_ej">▶검색하신 상품명이 포함된 모든 상품이 조회됩니다.</div>
		<div class="qsub3_ej">
			<div class="qsearch_sub_ej">
				<input type="text" name="search" size="30" /> <a href="#"
					class="btn-all btn_ej">검색</a>
			</div>
		</div>
		<div class="qsub2_ej">▶상품내역</div>
		<div class="qsub4_ej">
			<div>제목</div>
			<div>저자</div>
			<div>발행일</div>
			<div>가격</div>
		</div>
		<c:forEach var="i" begin="1" end="5">
		<div class="qsub5_ej">
			<ul>
				<li class="qsub5_li1_ej"><a>die냐 Diet냐 - 다이냐 다이어트냐</a></li>
				<li class="qsub5_li2_ej">김민석</li>
				<li class="qsub5_li3_ej">2006.12.15</li>
				<li class="qsub5_li4_ej">69,000</li>
			</ul>
		</div>
		</c:forEach>
	</div>
	<div class="page2_ej">< 페이지 ></div>
</div>	
</body>
</html>