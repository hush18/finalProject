<!-- 
작성자 : 최은지
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/user/CustomerService_consultingList.css" rel="stylesheet" type="text/css" />
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link type="text/css" rel="stylesheet" href="css/user/sideCategory.css" />
<script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
<script src="js/user/CustomerService.js" type="text/javascript"></script>
</head>
<body>
	<div class="widthline">
		<div class="boss_ej">
			<div class="url_ej">홈 > 고객센터 > 1:1 상담내역</div>
			<div class="sub_boss_ej" style="display: flex;">
				<!-- 사이드메뉴 -->
				<div class="side_mh">
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
										<li><a href="CustomerService_consultingList.do">1:1 상담내역</a></li>
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
				<div class="content_ej">
					<form action="CustomerService_faq.do" method="get" onsubmit="return up_search(this)">
					<div class="search_ej">
							<div class="search_choice_ej">
								<select name="up_category">
									<option value="default">FAQ 분류</option>
									<option value="회원">회원</option>
									<option value="상품">상품</option>
									<option value="입금/결제">입금/결제</option>
									<option value="취소/교환/환불">취소/교환/환불</option>
									<option value="주문">주문</option>
									<option value="배송">배송</option>
									<option value="적립">적립</option>
								</select>
							</div>

							<div class="search_sub_ej">
								<input type="text" name="search" size="40" />
								<button type="submit" class="btn-all btn_ej up_search_ej" style="height: 27px; padding-top: 0px;">검색</button>
							</div>
						</div>
					</form>

					<div class="FAQ_TOP_ej">
						<div class="FAQ_TOP_1_ej">
							<h3>1:1 상담내역</h3>
							<p>
								1:1 상담은 24시간 신청가능하며 접수된<br />내용은 빠른 시일 내에 답변 해드리겠습니다.
							</p>
						</div>

						<div class="sub3_ej">
							<div>기간별 조회&nbsp;&nbsp;&nbsp;|</div>
							<div>
								<a href="CustomerService_consultingList.do?date=15">15일</a> 
								<a href="CustomerService_consultingList.do?date=1">1개월</a> 
								<a href="CustomerService_consultingList.do?date=2">2개월</a> 
								<a href="CustomerService_consultingList.do?date=3">3개월</a>
							</div>
						</div>
						<div class="sub4_ej">
							<div>작성일</div>
							<div>상담구분</div>
							<div>상담명</div>
							<div>답변유무</div>
						</div>
						
						<c:if test="${cstList.size()==0}">
							<div style="text-align: center; font-size: 15px; padding: 10px;">${date}일동안의 문의가 존재하지 않습니다.</div>
						</c:if>
						
						<c:if test="${cstList.size()>0}">
						<c:set value="0" var="count" />
						<c:forEach items="${cstList}" var="list">
							<fmt:formatDate value="${list.admin_write_date}" pattern="yyyy-MM-dd" var="Awrite_date"/>
							<div class="list_ej">
								<div class="listrow_ej">
									<span class="listcell1_ej"> 
										<fmt:formatDate value="${list.write_date}" pattern="yyyy-MM-dd" />
									</span> 
									<span class="listcell2_ej">${list.up_category}&nbsp;&gt;&nbsp;${list.down_category}</span> 
									<span class="listcell3_ej number${count}_ej" onclick="javascript:cstReply('${count}','${list.content}','${list.admin_content}','${Awrite_date}')">${list.title}</span> 
									<span class="listcell4_ej">${list.reply_check}</span>
								</div>
							</div>
							<c:set var="count" value="${count + 1}" />
						</c:forEach>
						
						<div class="page_ej">
							<c:if test="${listCount>10 }">
								<fmt:parseNumber var="pageCount" value="${listCount / boardSize + (listCount%boardSize==0 ? 0:1)}" integerOnly="true" />
								<c:set var="pageBlock" value="${2}" />
								<fmt:parseNumber var="startPage" value="${((pageNumber-1)/pageBlock) }" integerOnly="true" />
								<c:set var="startPage" value="${startPage*pageBlock+1}" />
								<c:set var="endPage" value="${startPage+pageBlock-1 }" />

								<c:if test="${endPage > pageCount }">
									<c:set var="endPage" value="${endPage=pageCount }" />
								</c:if>

								<c:if test="${startPage > pageBlock }">
									<a href="CustomerService_consultingList.do?pageNumber=${startPage-pageBlock }" style="color: black">&nbsp;&lt;&nbsp;</a>
								</c:if>
								<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
									<c:choose>
										<c:when test="${pageNumber==i}">
											<a href="CustomerService_consultingList.do?pageNumber=${i}" style="color: #5cb38b;">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="CustomerService_consultingList.do?pageNumber=${i}" style="color: black">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${endPage < pageCount}">
									<a href="CustomerService_consultingList.do?pageNumber=${startPage+pageBlock }" style="color: black">&nbsp;&gt;&nbsp;</a>
								</c:if>
							</c:if>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>