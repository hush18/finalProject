<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 상담하기</title>
<link href="css/user/CustomerService_consulting.css" rel="stylesheet" type="text/css" />
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script src="js/user/CustomerService_consulting.js" type="text/javascript"></script>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link type = "text/css" rel="stylesheet" href="css/user/sideCategory.css"/>
</head>
<body>
<div class="widthline">
<form action="CustomerService_cstOk.do" method="post">
		<div class="boss_ej">
			<div class="url_ej">홈 > 고객센터 > 1:1 상담하기</div>
			<div class="sub_boss_ej" style="display:flex;">
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
				<div class="content_ej">
					<div class="search_ej">
							<div class="search_choice_ej">
								<select>
									<option>FAQ 분류</option>
									<option>회원</option>
									<option>상품</option>
									<option>입금/결제</option>
									<option>취소/교환/환불</option>
									<option>주문</option>
									<option>배송</option>
									<option>적립</option>
								</select>
							</div>

							<div class="search_sub_ej">
								<input type="text" name="search" size="40"/> 
								<a href="#" class="btn-all btn_ej">검색</a>
							</div>
					</div>
					
					<div class="FAQ_TOP_ej">
						<div class="FAQ_TOP_1_ej">
							<h3>1:1 상담</h3>
							<p>불편 사항이나 문의 사항을 남겨 주시면 신속하고<br/>친절하게 안내해 드리겠습니다.</p>
						</div>
						
						<div class=FAQ_TOP_2_ej>
							<h2 class="h2_hr_ej">문의 유형 선택</h2>
							<div class="consulting_ej">
								<div class="consulting_boss1_ej">
									<div class="consulting_sub1_ej">
										문의 유형&nbsp;&nbsp;&nbsp;&nbsp;|
									</div>
									
									<div class="consulting_sub2_ej">
										<div>
											<input type="radio" name="up_category" value="회원">회원
										</div>
										<div>
											<input type="radio" name="up_category" value="상품">상품
										</div>
										<div>
											<input type="radio" name="up_category" value="입금/결제">입금/결제
										</div>
										<div>
											<input type="radio" name="up_category" value="취소/교환/환불">취소/교환/환불
										</div>
										<div>
											<input type="radio" name="up_category" value="주문">주문
										</div>
										<div>
											<input type="radio" name="up_category" value="배송">배송
										</div>
										<div>
											<input type="radio" name="up_category" value="적립">적립
										</div>
									</div>
								</div>
								<div>
									<div class="consulting_sub1_ej">
										세부 유형&nbsp;&nbsp;&nbsp;&nbsp;|
									</div>
									
									<div class="consulting_sub2_1_ej" id="회원">
										<div>
											<input type="radio" name="down_category" value="회원가입">회원가입
										</div>
										<div>
											<input type="radio" name="down_category" value="회원정보확인">회원정보확인
										</div>
										<div>
											<input type="radio" name="down_category" value="회원정보수정">회원정보수정
										</div>
										<div>
											<input type="radio" name="down_category" value="회원탈퇴">회원탈퇴
										</div>
										<div>
											<input type="radio" name="down_category" value="휴먼계정">휴먼계정
										</div>
									</div>
									<div class="consulting_sub2_1_ej" id="상품">
										<div>
											<input type="radio" name="down_category" value="상품불량">상품불량
										</div>
										<div>
											<input type="radio" name="down_category" value="입고/품절/절판">입고/품절/절판
										</div>
										<div>
											<input type="radio" name="down_category" value="상품정보/가격">상품정보/가격
										</div>
									</div>
									<div class="consulting_sub2_1_ej" id="입금">
										<div>
											<input type="radio" name="down_category" value="신용카드">신용카드
										</div>
										<div>
											<input type="radio" name="down_category" value="핸드폰결제">핸드폰결제
										</div>
										<div>
											<input type="radio" name="down_category" value="실시간계좌이체">실시간계좌이체
										</div>
										<div>
											<input type="radio" name="down_category" value="직접입금">직접입금
										</div>
									</div>
									<div class="consulting_sub2_1_ej" id="취소">
										<div>
											<input type="radio" name="down_category" value="취소/교환/환불 문의">취소/교환/환불 문의
										</div>
										<div>
											<input type="radio" name="down_category" value="취소/교환/환불 신청">취소/교환/환불 신청
										</div>
										<div>
											<input type="radio" name="down_category" value="취소/교환/환불 취소">취소/교환/환불 취소
										</div>
									</div>
									<div class="consulting_sub2_1_ej" id="주문">
										<div>
											<input type="radio" name="down_category" value="주문조회">주문조회
										</div>
										<div>
											<input type="radio" name="down_category" value="주문변경">주문변경
										</div>
										<div>
											<input type="radio" name="down_category" value="주문취소">주문취소
										</div>
									</div>
									<div class="consulting_sub2_1_ej" id="배송">
										<div>
											<input type="radio" name="down_category" value="배송문의">배송문의
										</div>
										<div>
											<input type="radio" name="down_category" value="배송/출고예정일">배송/출고예정일
										</div>
									</div>
									<div class="consulting_sub2_1_ej" id="적립">
										<div>
											<input type="radio" name="down_category" value="포인트문의">포인트문의
										</div>
										<div>
											<input type="radio" name="down_category" value="포인트적립">포인트적립										
										</div>
										<div>
											<input type="radio" name="down_category" value="포인트사용">포인트사용
										</div>
										<div>
											<input type="radio" name="down_category" value="포인트소멸">포인트소멸
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class=FAQ_TOP_2_ej>
							<h2 class="h2_hr_ej">문의 내용 작성</h2>
							<div class="consulting_ej">
								<div class="consulting_boss1_ej">
									<div class="consulting_sub1_ej">
										제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
									</div>
									
									<div class="consulting_sub3_ej">
										<input type="text" name="title" size="50">
									</div>
								</div>
								
								<div class="consulting_boss1_ej conpop1_ej">
									<div class="consulting_sub1_ej">
										문의 상품명&nbsp;&nbsp;|
									</div>
									
									<div class="consulting_sub3_ej">
										<input type="text" name="counsel_product" size="20" readonly style="cursor: not-allowed;">
										<a id="questionSearch" class="consulting_abtn1_ej">조회</a>
									</div>
								</div>
								
								<div class="consulting_boss1_ej conpop2_ej">
									<div class="consulting_sub1_ej">
										주문 상품명&nbsp;&nbsp;|
									</div>
									
									<div class="consulting_sub3_ej">
										<input type="text" name="order_number" size="20" readonly style="cursor: not-allowed;">
										<a id="orderSearch" class="consulting_abtn1_ej">조회</a>
									</div>
								</div>
								
								<div class="consulting_boss2_ej">
									<div class="consulting_sub4_ej">
										문의 내용&nbsp;&nbsp;&nbsp;&nbsp;|
									</div>
									
									<div class="consulting_sub5_ej">
										<textarea rows="10" cols="57" name="content"></textarea>
									</div>
								</div>
								
								<div>
									<div>
										<div class="consulting_sub1_ej">
											답변 여부&nbsp;&nbsp;&nbsp;&nbsp;|
										</div>
									</div>
									<div class="consulting_sub2_2_ej">
										<input type="radio" name="emailing" value="1"> 이메일 허용
										<input type="radio" name="emailing" value="0"> 허용 안함
										<input type="text" name="email" size="30">
									</div>
								</div>
							</div>
							<div class="consulting_btn_ej">
								<div>
									<button type="submit" class="consulting_abtn2_ej">문의하기</button>
									<button type="reset" class="consulting_abtn2_ej">내용 초기화</button>								
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>