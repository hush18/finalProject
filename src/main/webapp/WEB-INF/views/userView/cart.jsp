<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/user/orderSearch.js"></script>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link href="css/user/sideCategory.css" type="text/css" rel="stylesheet"/>
<link href="css/user/cart.css" type="text/css" rel="stylesheet"/>
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script type="text/javascript">
	
</script>
</head>

<body>
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("장바구니에 중복된 도서가 추가될 수 없습니다.");
			history.back();
		</script>
	</c:if>
	<c:if test="${value<1}">
		<script type="text/javascript">
			alert("삭제에 실패한 도서가 있습니다.");
			location.href="cart.do";
		</script>
	</c:if>
	<c:if test="${value==1}">
		<script type="text/javascript">
			alert("장바구니 삭제에 성공하였습니다.");
		</script>
	</c:if>
		<div class="widthline">
			<div class="path_hy">홈 > 장바구니</div>
			<!-- 사이드메뉴 -->
			<div class="side_mh">
				<div class="category_mh">
					<div>
						<input type="hidden" name="listId" value="${list_id }" />
						<input type="hidden" name="cart_pageNumber" value="${cart_pageNumber }"/>
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

			<div class="con_hy">
				<div>
					<h2 class="h2_hy">장바구니</h2>
					<div class="downList_hy">
						<select class="downList_sel_hy" id="array">
							<option value="0" selected="selected">최근등록 순</option>
							<option value="1">상품이름 순</option>
							<option value="2">주문가격 순</option>
						</select> <span><input type="checkbox" class="checkAll_sc" /><label>전체선택</label></span>
						<span><button id="selectDelete">선택삭제</button></span>
					</div>
					<div class="cart_faqlist_header_ej">
						<div>상품명</div>
						<div>수량</div>
						<div class="cart_list_size_hy">등록일자</div>
						<div class="cart_list_size_hy">금액</div>
						<div class="cart_list_size_hy">취소</div>
					</div>
					<c:if test="${cartCount==0}">
						<h3 style="text-align: center;">고객님의 주문내역이 존재하지 않습니다</h3>
					</c:if>
					<c:if test="${cartCount>0 }">
						<div class="recentOrder_hy">
							<div class="cart_list_hy" id="list">
								<c:forEach var="cartList" items="${cartList }">
									<div class="cart_list_con_hy" id="${cartList.isbn }">
										<div>
											<input type="checkbox" class="checkBook_sc" />
										</div>
										<div class="bookName_hy">
											<div>
												<img src="${cartList.image_path }" class="bookimg_hy" />
											</div>
											<div>
												<span><a href="">${cartList.title }</a></span><br />
												<div>
													<span>${cartList.name }</span> | <span>${cartList.publisher }</span>
												</div>
											</div>
										</div>
										<div class="quantity_div_hy">
											<span class="quantity_hy"> <span
												class="quantity_up_jm">▲<input type="hidden"
													value="${cartList.isbn }" /></span>
											<!-- 히든의 값에 도서 고유번호 입력 --> <span class="quantity_down_jm">▼<input
													type="hidden" value="${cartList.isbn }" /></span>
											</span> <input id="${cartList.isbn }" class="quantity_input_hy"
												name="cart_amount" type="text" size="1"
												value="${cartList.cart_amount }">
											<!-- id값 뒤에 도서 고유번호 출력 -->
										</div>
										<div class="cart_list_size_hy text_mid_hy" id="date">
											<fmt:formatDate value="${cartList.cart_day}"
												pattern="yyyy-MM-dd" />
											<input type="hidden" id="cart_date" name="date" value="${cartList.cart_day}" /> 
										</div>

										<fmt:parseNumber var="price"
											value="${cartList.price/cartList.cart_amount}"
											integerOnly="true" />
										<div class="cart_list_size_hy text_mid_hy" id="price">${cartList.price}원</div>
										<input type="hidden" name="price" value="${cartList.price}" />
										<input type="hidden" name="isbn_price" value="${price}" />

										<div class="cart_list_size_hy">
											<button id="cart_delete" class="block_btn_hy">취소</button>
										</div>
										<input type="hidden" name="isbn" value="${cartList.isbn }" />
									</div>
								</c:forEach>

							</div>
						</div>
					</c:if>

				</div>
				<div class="page_count_hy">
					<c:if test="${cartCount>0 }">
						<fmt:parseNumber var="pageCount"
							value="${cartCount/pageSize+(cartCount%pageSize==0 ? 0:1)}"
							integerOnly="true" />
						<c:set var="pageBlock" value="${5 }" />

						<fmt:parseNumber var="rs"
							value="${(cart_pageNumber-1)/pageBlock }" integerOnly="true" />
						<c:set var="startPage" value="${rs*pageBlock +1 }" />

						<c:set var="endPage" value="${startPage+pageBlock -1 }" />
						<c:if test="${endPage >pageCount }">
							<c:set var="endPage" value="${pageCount }" />
						</c:if>

						<c:if test="${startPage> pageBlock }">
							<a
								href="cart.do?cart_pageNumber=${startPage-pageBlock }&list_id=${list_id}">[이전]</a>
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage }">
							<a href="cart.do?cart_pageNumber=${i }&list_id=${list_id}">${i }</a>
						</c:forEach>

						<c:if test="${endPage< pageCount }">
							<a
								href="cart.do?cart_pageNumber=${startPage + pageBlock }&list_id=${list_id}">[다음]</a>
						</c:if>

					</c:if>
				</div>
				<div class="payment_Information_yk">
					<div class="payment_subject_yk">
						<div>주문합계 금액</div>
						<div>배송/포장비</div>
						<div>결제 금액</div>
						<div style="padding: 0px 25px; width: 170px;">포인트</div>
					</div>
					<div class="payment_detail_yk" style="position: relative;">
						<div id="total_price">0원</div>
						<input type="hidden" name="total_price" value="" /> <img
							src="images/plus.png" class="icon_yk"
							style="z-index: 0; top: 68px; left: 167px;">
						<div id="delivery_price">3,000원</div>
						<img src="images/equal.png" class="icon_yk"
							style="z-index: 0; top: 68px; left: 360px;">
						<div id="payment_price">
							<strong>0원</strong>
						</div>
						<div style="padding: 0px 25px; width: 170px;">
							<span>${point }</span>
						</div>
					</div>
				</div>

				<div class="main_order_hy">
					<button class="btn-all">메인</button>
					<button class="btn-all" id="order">주문하기</button>
				</div>
			</div>
		</div>
	
</body>
</html>