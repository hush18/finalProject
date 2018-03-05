<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 조회</title>
<link href="css/user/CustomerService_order_search.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="js/user/CustomerService_consulting.js" type="text/javascript"></script>
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
					<a href="CustomerService_order_search.do?date=15">15일</a> 
					<a href="CustomerService_order_search.do?date=1">1개월</a> 
					<a href="CustomerService_order_search.do?date=2">2개월</a> 
					<a href="CustomerService_order_search.do?date=3">3개월</a>
				</div>
			</div>
			<div class="sub2_ej">▶쇼핑내역</div>
			<div class="sub4_ej">
				<div>주문일자</div>
				<div>주문상품</div>
				<div>주문금액</div>
				<div>수량</div>
			</div>
			
			<c:if test="${cstOrderList.size()==0}">
				<div style="text-align: center; font-size: 15px; padding: 10px;">${date}일내 주문하신 상품이 존재하지 않습니다.</div>
			</c:if>

			<c:if test="${cstOrderList.size()>0}">
				<c:choose>
					<c:when test="${cstOrderList.size()==0}">
						<div style="text-align: center; font-size: 15px; padding: 10px;">7일내 주문하신 상품이 존재하지 않습니다.</div>
					</c:when>
					<c:when test="${cstOrderList.size()>0}">
						<c:forEach items="${cstOrderList}" var="list">
							<div class="sub5_ej">
								<ul>
									<li class="sub5_li1_ej">
										<fmt:formatDate value="${list.order_date}" pattern="yyyy-MM-dd"/>
									</li>
									<li class="sub5_li2_ej">
										<a class="sub_title_ej" style="color:#000;" href="javascript:cstPopValue('${list.title}','order')">${list.title}</a>
									</li>
									<li class="sub5_li3_ej">${list.price}</li>
									<li class="sub5_li4_ej">${list.order_account}</li>
								</ul>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:if>
		</div>
	</div>
</body>
