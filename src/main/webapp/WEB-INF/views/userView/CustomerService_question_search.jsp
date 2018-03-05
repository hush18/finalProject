<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 조회</title>
<link href="css/user/CustomerService_question_search.css" rel="stylesheet" type="text/css" />
<script src="js/user/CustomerService_consulting.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="widthline_ej">
		<div class="qboss_ej">
			<form action="CustomerService_question_search.do" method="GET">
				<div class="qsub1_ej">
					<span>문의상품 조회</span>
				</div>
				<div class="qsub2_ej">▶검색하신 상품명이 포함된 모든 상품이 조회됩니다.</div>
				<div class="qsub3_ej">
					<div class="qsearch_sub_ej">
						<input type="text" id="search" name="search" size="30" />
						<button type="submit" class="btn-all btn_ej" style="outline: 0; border: 0px;">검색</button>
					</div>
				</div>
			</form>
			<div class="qsub2_ej">▶상품내역</div>
			<div class="qsub4_ej">
				<div>제목</div>
				<div>저자</div>
				<div>발행일</div>
				<div>가격</div>
			</div>

			<c:if test="${count==0}">
				<div></div>
			</c:if>

			<c:if test="${count>0}">
				<c:choose>
					<c:when test="${cstProductList.size()==0}">
						<div style="text-align: center; font-size: 15px; padding: 10px;">검색 결과가 존재하지않습니다.</div>
					</c:when>

					<c:when test="${cstProductList.size()>0}">
						<c:forEach items="${cstProductList}" var="list">
							<div class="qsub5_ej">
								<ul>
									<li class="qsub5_li1_ej"><a class="qsub_title_ej" style="color: #000;" href="javascript:cstPopValue('${list.title}','product')">${list.title}</a></li>
									<li class="qsub5_li2_ej">${list.name}</li>
									<li class="qsub5_li3_ej">${list.write_date}</li>
									<li class="qsub5_li4_ej">${list.price}</li>
								</ul>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:if>
		</div>
		<div class="page2_ej">
			<c:if test="${producCount>5 }">
				<fmt:parseNumber var="pageCount" value="${producCount / boardSize + (producCount%boardSize==0 ? 0:1)}" integerOnly="true" />
				<c:set var="pageBlock" value="${5}" />
				<fmt:parseNumber var="startPage" value="${((pageNumber-1)/pageBlock) }" integerOnly="true" />
				<c:set var="startPage" value="${startPage*pageBlock+1}" />
				<c:set var="endPage" value="${startPage+pageBlock-1 }" />

				<c:if test="${endPage > pageCount }">
					<c:set var="endPage" value="${endPage=pageCount }" />
				</c:if>

				<c:if test="${startPage > pageBlock }">
					<a href="CustomerService_question_search.do?pageNumber=${startPage-pageBlock }&search=${search}" style="color: #000; text-decoration: none;">&nbsp;&lt;&nbsp;</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
					<c:choose>
						<c:when test="${pageNumber==i}">
							<a href="CustomerService_question_search.do?pageNumber=${i}&search=${search}" style="color: #5cb38b; text-decoration: none;">${i}</a>
						</c:when>
						<c:otherwise>
							<a href="CustomerService_question_search.do?pageNumber=${i}&search=${search}" style="color: black; text-decoration: none;">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${endPage < pageCount}">
					<a href="CustomerService_question_search.do?pageNumber=${startPage+pageBlock }&search=${search}" style="color: #000; text-decoration: none;">&nbsp;&gt;&nbsp;</a>
				</c:if>
			</c:if>
		</div>
	</div>
</body>
