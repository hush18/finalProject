<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객센터 회원</title>
<link href="css/user/CustomerService_faq.css" rel="stylesheet" type="text/css" />
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script src="js/user/CustomerService.js" type="text/javascript"></script>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link type="text/css" rel="stylesheet" href="css/user/sideCategory.css" />

<!-- iCheck -->
<link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

</head>
<body>
	<div class="widthline">
		<div class="boss_ej">
			<div class="url_ej">홈&nbsp;&gt;&nbsp;FAQ&nbsp;&gt;&nbsp;${upCategory}</div>
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
				<div class="content_ej" style="display: block;">
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
								<button type="submit" class="btn-all btn_ej" style="height: 27px; padding-top: 0px;">검색</button>
							</div>
						</div>
					</form>

					<div class="FAQ_TOP_ej">
						<div class="FAQ_TOP_1_ej">
							<h3>자주 찾는 질문</h3>
							<p>
								고객님께서 가장 궁금하신 부분을 <br />FAQ를 통해 간편하게 해결할 수 있습니다.
							</p>
						</div>
						<div class=FAQ_TOP_2_ej>
							<h2 class="h2_hr_ej">${upCategory}FAQ</h2>
							<div class="sub_category_ej" id="회원">
								<a href="CustomerService_faq.do?up_category=회원">전체</a> <a href="CustomerService_faq.do?up_category=회원&down_category=회원가입">회원가입</a> <a href="CustomerService_faq.do?up_category=회원&down_category=회원정보확인">회원정보확인</a> <a href="CustomerService_faq.do?up_category=회원&down_category=회원정보수정">회원정보수정</a> <a href="CustomerService_faq.do?up_category=회원&down_category=회원탈퇴">회원탈퇴</a> <a href="CustomerService_faq.do?up_category=회원&down_category=휴먼계정">휴먼계정</a>
							</div>

							<div class="sub_category_ej" id="상품">
								<a href="CustomerService_faq.do?up_category=상품">전체</a> <a href="CustomerService_faq.do?up_category=상품&up_category=상품불량">상품불량</a> <a href="CustomerService_faq.do?up_category=상품&up_category=입고/품절/절판">입고/품절/절판</a> <a href="CustomerService_faq.do?up_category=상품&up_category=상품정보/가격">상품정보/가격</a>
							</div>

							<div class="sub_category_ej" id="입금">
								<a href="CustomerService_faq.do?up_category=입금/결제">전체</a> <a href="CustomerService_faq.do?up_category=입금&up_category=신용카드">신용카드</a> <a href="CustomerService_faq.do?up_category=입금&up_category=핸드폰결제">핸드폰결제</a> <a href="CustomerService_faq.do?up_category=입금&up_category=실시간계좌이체">실시간계좌이체</a> <a href="CustomerService_faq.do?up_category=입금&up_category=직접입금">직접입금</a>
							</div>

							<div class="sub_category_ej" id="취소">
								<a href="CustomerService_faq.do?up_category=취소/교환/환불">전체</a> <a href="CustomerService_faq.do?up_category=취소&up_category=취소/교환/환불 문의">취소/교환/환불 문의</a> <a href="CustomerService_faq.do?up_category=취소&up_category=취소/교환/환불 신청">취소/교환/환불 신청</a> <a href="CustomerService_faq.do?up_category=취소&up_category=취소/교환/환불 취소">취소/교환/환불 취소</a>
							</div>

							<div class="sub_category_ej" id="주문">
								<a href="CustomerService_faq.do?up_category=주문">전체</a> <a href="CustomerService_faq.do?up_category=주문&up_category=주문조회">주문조회</a> <a href="CustomerService_faq.do?up_category=주문&up_category=주문변경">주문변경</a> <a href="CustomerService_faq.do?up_category=주문&up_category=주문취소">주문취소</a>
							</div>

							<div class="sub_category_ej" id="배송">
								<a href="CustomerService_faq.do?up_category=배송">전체</a> <a href="CustomerService_faq.do?up_category=배송&up_category=배송문의">배송문의</a> <a href="CustomerService_faq.do?up_category=배송&up_category=배송/출고예정일">배송/출고예정일</a>
							</div>

							<div class="sub_category_ej" id="적립">
								<a href="CustomerService_faq.do?up_category=적립">전체</a> <a href="CustomerService_faq.do?up_category=적립&up_category=포인트문의">포인트문의</a> <a href="CustomerService_faq.do?up_category=적립&up_category=포인트적립">포인트적립</a> <a href="CustomerService_faq.do?up_category=적립&up_category=포인트사용">포인트사용</a> <a href="CustomerService_faq.do?up_category=적립&up_category=포인트소멸">포인트소멸</a>
							</div>
							<div class="faqlist_header_ej">
								<div>번호</div>
								<div>질문유형</div>
								<div>제목</div>
							</div>

							<c:choose>
								<c:when test="${faqUpList.size()>0}">
									<c:set var="i" value="1" />
									<c:forEach items="${faqUpList}" var="list">
										<div class="jlist_ej faqlist_ej">
											<div class="faqlistrow_ej">
												<span class="faqlistcell1_ej">${list.rNum}</span> 
												<span class="faqlistcell2_ej">${list.up_category}&nbsp;&gt;&nbsp;${list.down_category}</span> 
												<span class="faqlistcell3_ej number${i}_ej" onclick="content('${i}','${list.content}')">${list.title}</span>
											</div>
										</div>
										<c:set var="i" value="${i+1}" />
									</c:forEach>
								</c:when>

								<c:when test="${faqDownList.size()>0}">
									<c:set var="i" value="1" />
									<c:forEach items="${faqDownList}" var="list">
										<div class="jlist_ej faqlist_ej">
											<div class="faqlistrow_ej">
												<span class="faqlistcell1_ej">${list.rNum}</span> <span class="faqlistcell2_ej">${list.up_category}&nbsp;&lt;&nbsp;${list.down_category}</span> <span class="faqlistcell3_ej number${i}_ej" onclick="content('${i}','${content}')">${list.title}</span>
											</div>
										</div>
										<c:set var="i" value="${i+1}" />
									</c:forEach>
								</c:when>
								
								<c:when test="${faqSearchList.size()>0}">
									<c:set var="i" value="1" />
									<c:forEach items="${faqSearchList}" var="list">
										<div class="jlist_ej faqlist_ej">
											<div class="faqlistrow_ej">
												<span class="faqlistcell1_ej">${list.rNum}</span> <span class="faqlistcell2_ej">${list.up_category}&nbsp;&lt;&nbsp;${list.down_category}</span> <span class="faqlistcell3_ej number${i}_ej" onclick="content('${i}','${content}')">${list.title}</span>
											</div>
										</div>
										<c:set var="i" value="${i+1}" />
									</c:forEach>
								</c:when>
							</c:choose>
							
							<c:if test="${faqListCount==0}">
								<div style="text-align: center;padding-top: 20px;">검색하신 질문이 존재하지 않습니다.</div>
							</c:if>							
							<c:if test="${faqListCount>0}">
							<div class="page_ej">
								
								<fmt:parseNumber var="pageCount" value="${faqListCount / boardSize + (faqListCount%boardSize==0 ? 0:1)}" integerOnly="true" />
								<c:set var="pageBlock" value="${10}" />
								<fmt:parseNumber var="startPage" value="${((pageNumber-1)/pageBlock) }" integerOnly="true" />
								<c:set var="startPage" value="${startPage*pageBlock+1}" />
								<c:set var="endPage" value="${startPage+pageBlock-1 }" />

								<c:if test="${endPage > pageCount }">
									<c:set var="endPage" value="${endPage=pageCount }" />
								</c:if>
								
								<c:choose>
								<c:when test="${(downCategory eq null || downCategory=='')&&(search eq null || search=='')}">
								<c:if test="${startPage > pageBlock }">
									<a href="CustomerService_faq.do?up_category=${upCategory}&pageNumber=${startPage-pageBlock}" style="color: #5cb38b;">&nbsp;&lt;&nbsp;</a>
								</c:if>
									<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
										<c:choose>
											<c:when test="${pageNumber==i}">
												<a href="CustomerService_faq.do?up_category=${upCategory}&pageNumber=${i}" style="color: #5cb38b;">${i}</a>
											</c:when>
											<c:otherwise>
												<a href="CustomerService_faq.do?up_category=${upCategory}&pageNumber=${i}" style="color: black">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${endPage < pageCount}">
										<a href="CustomerService_faq.do?up_category=${upCategory}&pageNumber=${startPage+pageBlock }" style="color: #5cb38b;">&nbsp;&gt;&nbsp;</a>
									</c:if>
								</c:when>
								
								<c:when test="${search ne null || search!=''}">
								<c:if test="${startPage > pageBlock }">
									<a href="CustomerService_faq.do?up_category=${upCategory}&search=${search}&pageNumber=${startPage-pageBlock}" style="color: #5cb38b;">&nbsp;&lt;&nbsp;</a>
								</c:if>
									<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
										<c:choose>
											<c:when test="${pageNumber==i}">
												<a href="CustomerService_faq.do?up_category=${upCategory}&search=${search}&pageNumber=${i}" style="color: #5cb38b;">${i}</a>
											</c:when>
											<c:otherwise>
												<a href="CustomerService_faq.do?up_category=${upCategory}&search=${search}&pageNumber=${i}" style="color: black">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${endPage < pageCount}">
										<a href="CustomerService_faq.do?up_category=${upCategory}&search=${search}&pageNumber=${startPage+pageBlock }" style="color: #5cb38b;">&nbsp;&gt;&nbsp;</a>
									</c:if>
								</c:when>

								<c:when test="${downCategory!='' || downCategory ne null}">
								<c:if test="${startPage > pageBlock }">
									<a href="CustomerService_faq.do?up_category=${upCategory}&down_category=${downCategory}&pageNumber=${startPage-pageBlock}" style="color: #000;">&nbsp;&lt;&nbsp;</a>
								</c:if>
									<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
										<c:choose>
											<c:when test="${pageNumber==i}">
												<a href="CustomerService_faq.do?up_category=${upCategory}&down_category=${downCategory}&pageNumber=${i}" style="color: #5cb38b;">${i}</a>
											</c:when>
											<c:otherwise>
												<a href="CustomerService_faq.do?up_category=${upCategory}&down_category=${downCategory}&pageNumber=${i}" style="color: black">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${endPage < pageCount}">
										<a href="CustomerService_faq.do?up_category=${upCategory}&down_category=${downCategory}&pageNumber=${startPage+pageBlock }" style="color: #000;">&nbsp;&gt;&nbsp;</a>
									</c:if>
								</c:when>
								</c:choose>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- FastClick -->
	<script src="vendors/fastclick/lib/fastclick.js"></script>
	<!-- iCheck -->
	<script src="vendors/iCheck/icheck.min.js"></script>
	<!-- DateJS -->
	<script src="vendors/DateJS/build/date.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="vendors/moment/min/moment.min.js"></script>
	<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- Datatables -->
	<script src="js/user/userDataTables.js"></script>
	<script src="vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script src="vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script src="vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script src="vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script src="vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script src="vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script src="vendors/datatables.net-scroller/js/dataTables.scroller.js"></script>
</body>
</html>