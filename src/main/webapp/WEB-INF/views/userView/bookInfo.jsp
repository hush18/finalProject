<!-- 작성자 : 제민 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/user/bookLayout.css" type="text/css" rel="stylesheet" />
<!-- 제민(영역 스타일 및 사이드 카테고리) -->
<link href="css/user/bookInfo.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/user/bookInfo.js"></script>
</head>
<body>
	<div class="widthline">
		<!-- 18-01-18 컨텐츠-->
		<div class="centent_jm"
			style="position: relative; background-color: white; box-sizing: border-box;">
			<!-- 왼쪽 카테고리 메뉴영역 -->
			<div class="centent_route_jm">홈 > <a href="bookList.do?path=전체&category_path=전체">전체</a><c:forTokens begin="1" items="${categoryDto.category_path}" delims="," var="pathList">
						> <a href="bookList.do?path=${pathList}&category_path=${category_path}">${pathList}</a>
						<c:set var="pathValue" value="${pathList}"/>
					</c:forTokens></div>
			<div class="left_category_menu_jm">
				<h2 class="h2_jm">${path}</h2>
				<ul class="category_menu_jm">
					<c:forTokens begin="0" items="${categoryDto.low_category}" delims="," var="low">
						<li><a href="bookList.do?path=${low}&category_path=${category_path}" style="${low==category_path ? 'color:#5cb38b;font-weight: bold;' : ''}">${low}</a></li>
						
					</c:forTokens>
				</ul>
			</div>
			<!-- 도서 상세정보 -->
			<div class="book_area_jm">
				<div class="book_info_jm">
					<img alt="" src="${bookDto.image_path}" style="margin-top: 24px; box-shadow: 1px 1px 2px 0px #9c9c9c;">
					<div class="info_jm" style="margin-left: 10px;">
						<h2>${bookDto.title}</h2>
						<div>${bookDto.name} 저 | ${bookDto.publisher} | ${bookDto.write_date}</div>
						<ul>
							<li>
								<div>정가</div>
								<c:set var="oldPrice" value="${bookDto.price*1.1}"/>
								<fmt:formatNumber var="oldPriceFmt" value="${oldPrice}" pattern="###,###"></fmt:formatNumber>
								<div style="color: red; text-decoration: line-through;">${oldPriceFmt}원</div>
							</li>
							<li>
								<div>판매가</div>
								<c:set var="newPrice" value="${bookDto.price}"/>
								<fmt:formatNumber var="newPriceFmt" value="${newPrice}" pattern="###,###"></fmt:formatNumber>
								<div>${newPriceFmt}원</div>
							</li>
							<li>
								<div>적립금</div>
								<c:set var="point" value="${bookDto.price/100}"/>
								<fmt:formatNumber var="pointFmt" value="${point}" pattern="###,###"></fmt:formatNumber>
								<div>${pointFmt}원</div>
							</li>
							<li>
								<div>리뷰평점</div>
								<div>
									<fmt:formatNumber var="grade" value="${bookDto.grade}" pattern="0.0"></fmt:formatNumber>
									<span class="star-prototype">${grade}</span> 
								</div><label>(${grade})</label>
							</li>
						</ul>
					</div>
					<div class="book_area_button_jm">
						<div class="quantity_div_jm">
							<label>수량</label> 
							<input id="${bookDto.isbn}" class="quantity_input_jm" type="text" size="1" value="1">
							<span class="quantity_area_jm">
								<span class="quantity_up_jm">▲<input type="hidden" value="${bookDto.isbn}" /></span>
								<span class="quantity_down_jm">▼<input type="hidden" value="${bookDto.isbn}" /></span>
							</span>
						</div>
						<br>
						<button class="btn-all btn_info" value="" onclick="cart('${bookDto.isbn}','${mbId}')">장바구니</button><!-- mbId가 null일때 이용불가 -->
						<button class="btn-all btn_info" value="" onclick="payment('${bookDto.isbn}','${mbId}')">즉시구매</button><!-- mbId가 null일때 이용불가 -->
						<button class="btn-all btn_info" value="" onclick="wishList('${bookDto.isbn}','${mbId}')">위시리스트</button><!-- mbId가 null일때 이용불가 -->
					</div>
				</div>
				<!-- 상품정보, 리뷰정보, 환불정보 -->
				<div class="total_info_jm">
					<div class="info_menu_list_jm">
						<div>
							<h2>상품정보</h2>
						</div>
						<div>
							<h2>리뷰정보</h2>
						</div>
						<div>
							<h2>환불정보</h2>
						</div>
					</div>
					<div class="info_goods_jm">
						<div class="info_contents_jm">
							<div>목차</div>
							<p>${bookDto.contents}</p>
						</div>
						<div class="info_intro_jm">
							<div>소개</div>
							<p>${bookDto.book_introduction}</p>
						</div>
						<div class="info_writer_jm">
							<div>저자</div>
							<p>${writerDto.name}</p>
							<p>${writerDto.writer_introduction}</p>
						</div>
						<div class="info_writer_otherBook_jm">
							<div>
								저자의 다른 책
							</div>
							<div class="book_list_jm">
								<c:forEach var="writerBookDto" items="${writerBookList}">
									<c:if test="${writerBookDto!=null}">
										<div>
											<img id="${writerBookDto.isbn}" class="imgBook_jm" alt="" src="${writerBookDto.image_path}" style="box-shadow: 1px 1px 2px 0px #9c9c9c;">
											<input type="hidden" name="isbn" value="${writerBookDto.isbn}">
											<input type="hidden" name="pageNumber" value="${pageNumber}">
											<input type="hidden" name="category_path" value="${path}">
											<div class="book_list_content_jm" style="margin-top: 5px;">
												<div title="${writerBookDto.title}" style="text-overflow: ellipsis; overflow: hidden;">${writerBookDto.title}</div>
												<div title="${writerBookDto.name} 저 | ${writerBookDto.publisher}" style="text-overflow: ellipsis; overflow: hidden;">${writerBookDto.name} 저 | ${writerBookDto.publisher}</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="info_menu_list_jm">
						<div>
							<h2>상품정보</h2>
						</div>
						<div>
							<h2>리뷰정보</h2>
						</div>
						<div>
							<h2>환불정보</h2>
						</div>
					</div>
					<div id="reviewValueDiv" class="info_review_jm" style="box-sizing: border-box;">
						<div style="display: block;">
							<c:if test="${mbId!=null}">
								<label style="display: inline-block; margin-right: 40px;">${mbId}</label>
							</c:if>
							<c:if test="${mbId==null}">
								<label style="display: inline-block; margin-right: 40px;">아이디</label>
							</c:if>
							<span id="starCheck" class="star-prototype">5.0</span>
							<script type="text/javascript">
								$(function () {
									$("#starCheck").click(function (e) {
										 $("input[name='grade']").val(e.offsetX/16);
										 $("#starCheck").find("span").attr("style","width: "+e.offsetX+"px;");
									})
								})
							</script>
						</div>
						<div id="reviewline">
							<!-- 입력창 -->
							<input type="hidden" name="isbn" value="${bookDto.isbn}"/>
							<input type="hidden" name="id" value="${mbId}"/>
							<input type="hidden" name="grade" value="5.0"/>
							<textarea name="content"></textarea>
							<button id="reviewInsert" class="btn-all re_btn">등록</button>
						</div>
						
						<div id="reviewStandard" class="info_review_centent_table" style="border-bottom: 0px;margin: 0px;padding: 0px;"></div><!-- 리뷰기준점 -->
						<!-- 기존리뷰 출력 -->
						<c:forEach var="reviewDto" items="${reviewList}">
							<div class="info_review_centent_table">
								<div>
									<div class="">${reviewDto.id}</div>
									<div style="white-space: pre-line; word-break: break-all; overflow: hidden;" class="test">${reviewDto.content}</div>
									<div>
										<fmt:formatNumber var="grade" value="${reviewDto.grade}" pattern=".0"/>
										<span class="star-prototype">${grade}</span>
									</div>
									<fmt:formatDate pattern="YY-MM-dd" var="fmt_writer_date" value="${reviewDto.writer_date}"/>
									<div>${fmt_writer_date}</div>
								</div>
							</div>
						</c:forEach>

						<div class="page_area_jm">
							<ul id="page_ul_jm" style="padding-left: 0px;"></ul>
						</div>
						<script type="text/javascript">
							function paging(num) {
								//alert(num);
								$(".info_review_centent_table").show();
								if(num!=1){
									$(".info_review_centent_table:eq("+((num-1)*10+1)+")").prevAll(".info_review_centent_table").hide();
								}
								$(".info_review_centent_table:eq("+((num)*10)+")").nextAll(".info_review_centent_table").hide();
							}
						</script>
					</div>

					<div class="info_menu_list_jm">
						<div>
							<h2>상품정보</h2>
						</div>
						<div>
							<h2>리뷰정보</h2>
						</div>
						<div>
							<h2>환불정보</h2>
						</div>
					</div>
					<div class="info_refund_jm">
						<div>상품 설명에 반품/ 교환 관련한 안내가 있는 경우 그 내용을 우선으로 합니다. (업체 사정에 따라
							달라질 수 있습니다)</div>
						<div class="refund_centent">
							<div>
								<div>환불/교환 방법</div>
								<div>홈 > 고객센터 > 자주찾는질문 “반품/교환/환불” 안내 참고 또는 1:1상담게시판</div>
							</div>
							<div>
								<div>반품/교환 가능 기간</div>
								<div>반품,교환은 배송완료 후 7일 이내, 상품의 결함 및 계약내용과 다를 경우 문제발견 후 30일
									이내에 신청가능</div>
							</div>
							<div>
								<div>반품/교환 비용</div>
								<div>변심 혹은 구매착오의 경우에만 반송료 고객 부담(별도 지정 택배사 없음)</div>
							</div>
							<div>
								<div>반품/교환 불가 사유</div>
								<div>
									<ul>
										<li>소비자의 책임 사유로 상품 등이 손실 또는 훼손된 경우</li>
										<li>복제가 가능한 상품 등의 포장을 훼손한 경우</li>
										<li>시간의 경과에 의해 재판매가 곤란한 정도로 가치가 현저히 감소한 경우</li>
										<li>전자상거래 등에서의 소비자보호에 관한 법률이 정하는 소비자 청약철회 제한 내용에 해당되는 경우</li>
										<li>해외주문 상품의 경우(파본/훼손/오발송 상품을 제외)</li>
										<li>그밖에 월간지, 화보집, 사진집, 그림도감, 시집류, 중고학습서, 만화, 입시자료, 여성실용서적
											등(파본/훼손/오발송 상품을 제외)</li>
									</ul>
								</div>
							</div>
							<div>
								<div>
									소비자 피해보상<br>환불지연에 따른 배상
								</div>
								<div>
									<ul>
										<li>상품의 불량에 의한 반품, 교환, A/S, 환불, 품질보증 및 피해보상 등에 관한 사항은 소비자
											분쟁해결기준 (공정거래위원회고시)에 준하여 처리됨</li>
										<li>대금 환불 및 환불지연에 따른 배상금 지급 조건, 절차 등은 전자상거래 등에서의 소비자 보호에
											관한 법률에 따라 처리함</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>