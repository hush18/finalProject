<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user/payment.css">
<link rel="stylesheet" href="css/user/btn_yk.css">
<script type="text/javascript" src="js/user/payment.js"></script>
<c:set var="root" value="${pageContext.request.contextPath }"/>
</head>
<body>
	<script type="text/javascript">
		$(function () {
			var root="${root}";
			$("button[name='find_zipcode']").click(function() {
				window.open(root+"/zipcode.do","", "width=400, height=400, scroll=yes");
			});
		});
	</script>
	<div class="widthline">
		<h2 class="h2-hr">주문상품 확인</h2>
		
		<div class="order_item_Confirm_yk">
			<c:if test="${length==1 }">
				<div class="order_item_subject_yk">
					<div style="width: 33%;">주문상품</div>
					<div style="width: 8%;">수량</div>
					<div style="width: 14%;">주문금액</div>
					<div style="width: 14%;">배송비</div>
					<div style="width: 14%;">예상적립금</div>
					<!-- 적립금은 10%로 예상 -->
					<div style="width: 15%; border-right: 0px;">주문 금액 합계</div>
				</div>
			</c:if>
			<c:if test="${count>=0 }">
				<div class="order_item_subject_yk">
					<div style="width: 36%;">주문상품</div>
					<div style="width: 15%;">수량</div>
					<div style="width: 15%;">배송비</div>
					<div style="width: 16%;">예상적립금</div>
					<!-- 적립금은 10%로 예상 -->
					<div style="width: 17%; border-right: 0px;">주문 금액 합계</div>
				</div>
			</c:if>
			<c:if test="${length==1 }">
				<div class="order_item_list_yk">
					<c:set var="shppingCharge"/>
					<c:if test="${bookDto.price*quantity>10000 }">
						<c:set var="shppingCharge" value="0"/>
					</c:if>
					<c:if test="${bookDto.price*quantity<=10000 }">
						<c:set var="shppingCharge" value="3000"/>
					</c:if>
					<div id="title" style="width: 33%;">${bookDto.title }</div>
					<div style="width: 8%;">${quantity }개</div>
					<c:set var="price" value="${bookDto.price*quantity }"/>
					<fmt:formatNumber var="Allprice" value="${price }" pattern="#,###"/>
					<div style="width: 14%;">${Allprice}원</div>
					
					<div style="width: 14%;"><fmt:formatNumber value="${shppingCharge }" pattern="#,###" />원</div>
					<fmt:formatNumber var="point" value="${bookDto.price*quantity*0.1 }" pattern="#,###"/>
					<div style="width: 14%;">${point }p</div>
					<div style="width: 15%; border-right: 0px;">${Allprice }원</div>
				</div>
			</c:if>
			<c:if test="${count>=0 }">
				<div class="order_item_list_yk">
					<c:set var="shppingCharge"/>
					<c:if test="${price>10000 }">
						<c:set var="shppingCharge" value="0"/>
					</c:if>
					<c:if test="${price<=10000 }">
						<c:set var="shppingCharge" value="3000"/>
					</c:if>
					<c:if test="${bookCount>1 }">
						<div id="title" style="width: 36%;">${bookDto.title } 외 ${bookListSize } 개</div>
						<div style="width: 15%;">총 ${count } 개</div>
					</c:if>
					<c:if test="${bookCount==1 }">
						<div id="title" style="width: 36%;">${bookDto.title }</div>
						<div style="width: 15%;">${count } 개</div>
					</c:if>
					<fmt:formatNumber var="Allprice" value="${price }" pattern="#,###"/>
					
					<div style="width: 15%;"><fmt:formatNumber value="${shppingCharge }" pattern="#,###" />원</div>
					<fmt:formatNumber var="point" value="${price*0.1 }" pattern="#,###"/>
					<div style="width: 16%;">${point }p</div>
					<div style="width: 17%; border-right: 0px;">${Allprice }원</div>
					<input type="hidden" name="order_account" value="${quantity }">
					<input type="hidden" name="goods" value="${isbn }">
				</div>
			</c:if>
		</div>
		
		<form action="paymentOk.do" name="order" method="post">
			
			<input type="hidden" name="order_account" value="${quantity }">
			<input type="hidden" name="goods" value="${isbn }">
			<fmt:formatNumber var="save_point" value="${price*0.1}" pattern="#"/>
			<input type="hidden" name="save_point" value="${save_point }">
			<input type="hidden" name="title">
			<h2 class="h2-hr">배송지 정보</h2>
			<div class="Shipping_Information_yk">
				<div class="Shipping_subject_yk">
					<div>배송지 선택</div>
					<div>받으시는 분</div>
					<div>휴대폰</div>
					<div>유선전화</div>
					<div style="height: 100px; line-height: 100px;">주소</div>
					<div>배송메세지</div>
				</div>
				
				<div class="Shipping_input_yk">
					<div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="shipping_address" value="normal">
						배송지 목록&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="shipping_address" value="enter_new">
						새로 입력&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="shipping_address" value="member_address_same">
						회원정보와 동일&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" name="receive_name" required />
					</div>
					<div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="cellphone_first">
							<option>010</option>
							<option>011</option>
							<option>017</option>
							<option>019</option>
						</select>&nbsp;&nbsp;
						-&nbsp;&nbsp;
						<input type="text" name="cellphone_second" style="width: 100px;">&nbsp;&nbsp;
						-&nbsp;&nbsp;
						<input type="text" name="cellphone_third" style="width: 100px;">
						<!-- 위 3개 값 합쳐서 밑에다 넣음 -->
						<input type="hidden" name="receive_phone">
						<script type="text/javascript">
							$("input[name='cellphone_third']").change(function() {
								var cellphone_first=$("select[name='cellphone_first']").val();
								var cellphone_second=$("input[name='cellphone_second']").val();
								var cellphone_third=$("input[name='cellphone_third']").val();
								$("input[name='receive_phone']").attr("value",(cellphone_first+"-"+	cellphone_second+"-"+cellphone_third));
							});	
						</script>
					</div>
					<div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="telephone_first">
							<option>02</option>
							<option>031</option>
							<option>032</option>
							<option>041</option>
							<option>042</option>
							<option>043</option>
							<option>051</option>
							<option>052</option>
							<option>053</option>
							<option>054</option>
						</select>&nbsp;&nbsp;
						-&nbsp;&nbsp;
						<input type="text" name="telephone_second" style="width: 100px;">&nbsp;&nbsp;
						-&nbsp;&nbsp;
						<input type="text" name="telephone_third" style="width: 100px;">
						<!-- 위 3개 값 합쳐서 밑에다 넣음 -->
						<input type="hidden" name="receive_home">
						<script type="text/javascript">
							$("input[name='telephone_third']").change(function() {
								var telephone_first=$("select[name='telephone_first']").val();
								var telephone_second=$("input[name='telephone_second']").val();
								var telephone_third=$("input[name='telephone_third']").val();
								$("input[name='receive_home']").attr("value",(telephone_first+"-"+	telephone_second+"-"+telephone_third));
							});	
						</script>
					</div>
					<div style="height: 100px;">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" name="member_zipcode" disabled="disabled" style="width: 50px;text-align: center;">
						<input type="hidden" name="member_zipcode">
						&nbsp;&nbsp;	
						<button type="button" name="find_zipcode" style="display: none;">주소찾기</button>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" name="member_address" disabled="disabled" style="width: 215px; text-align: center;">
						&nbsp;&nbsp;
						<input type="text" name="member_detail_address" style="width: 80px; text-align: center;" value="">
						<input type="hidden" name="receive_addr">
					</div>
					<div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" name="delivery_msg" style="width: 400px;">
					</div>
				</div>
				<script type="text/javascript">
					if($("input[value='member_address_same']").click(function () {
						$("input[name='member_zipcode']").attr("value","${memberDto.member_zipcode}")
						$("input[name='member_address']").attr("value","${memberDto.member_address}")
						$("input[name='member_detail_address']").attr("value","${memberDto.member_detail_address}")
					}));
				</script>

				<div class="order_Information_yk">
					<br>
					<br>
					<div>
						<span>주문자 정보</span>
					</div>
					<div>
						<span>이&nbsp;&nbsp;&nbsp;름</span>
						&nbsp;&nbsp;
						<input type="text" name="buyer_name" value="${memberDto.name }" disabled="disabled">
						<input type="hidden" name="buyer_name" value="${memberDto.name }">
					</div>
					<div>
						<span>이메일</span>
						&nbsp;&nbsp;
						<input type="text" name="buyer_email" value="${memberDto.email }" disabled="disabled">
						<input type="hidden" name="buyer_email" value="${memberDto.email }">
					</div>
				</div>
			</div>
			
			<div class="payment_Information_yk">
				<div class="payment_subject_yk">
					<div>주문합계 금액</div>
					<div>배송/포장비</div>
					<div>적립금 사용</div>
					<div style="border-right: 0px;">결제 금액</div>
				</div>
				<div class="payment_detail_yk">
					<div>${Allprice }원</div>
					<img src="images/plus.png" class="icon_yk" style="margin-left: -747px; margin-top: 65px; z-index: 0">
					<div>${shppingCharge }원</div>
					<img src="images/negative.png" class="icon_yk" style="margin-left: -506px; margin-top: 65px; z-index: 0">
					<div>
						<input type="text" name="point_history" value="0" style="width: 80px; text-align: center;">
						/
						<span>${memberDto.point }p</span>
					</div>
					<img src="images/equal.png" class="icon_yk" style="margin-left: -265px; margin-top: 65px; z-index: 0">
					<div class="final_price" style="border-right: 0px;"></div>
					<input type="hidden" name="total_price">
					
					<fmt:formatNumber var="finalPrice" value="" pattern="#,###"/>
					<c:if test="${length==1 }">
						<script type="text/javascript">
							var finalPrice=${bookDto.price*quantity};
							var point=$("input[name='point_history']").val();
							$("input[name='total_price']").attr("value",finalPrice);
							$(".final_price").text(finalPrice+"원");
							var idPoint=${memberDto.point }
							$("input[name='point_history']").change(function() {
								if($("input[name='point_history']").val()>idPoint){
									alert("포인트가 부족합니다.");
									$("input[name='point_history']").val(0);
									return false
								}
								
								point=$("input[name='point_history']").val();
								$(".final_price").text(finalPrice-point+"원");
								$("input[name='total_price']").attr("value",finalPrice-point);
								$("input[name='save_point']").attr("value",(finalPrice-point)*0.1);
							});
						</script>
					</c:if>
					<c:if test="${count>=0 }">
						<input type="hidden" name="flag" value="1">
						<script type="text/javascript">
							var finalPrice=${price};
							$(".final_price").text(finalPrice+"원");
							var point=$("input[name='point_history']").val();
							$("input[name='total_price']").attr("value",finalPrice-point);
							var idPoint=${memberDto.point }
							$("input[name='point_history']").change(function() {
								if($("input[name='point_history']").val()>idPoint){
									alert("포인트가 부족합니다.");
									$("input[name='point_history']").val(0);
									return false
								}
								
								point=$("input[name='point_history']").val();
								$(".final_price").text(finalPrice-point+"원");
								$("input[name='total_price']").attr("value",finalPrice-point);
								$("input[name='save_point']").attr("value",(finalPrice-point)*0.1);
							});
						</script>
					</c:if>
					<input type="hidden" name="save_point" value="">
				</div>
			</div>
			
			<h2 class="h2-hr" style="margin-top: 25px;">결제 수단</h2>
			
			<div class="how_to_pay_yk">
				<div class="payment_choice_yk">
					<input type="radio" name="payment" value="credit_card">
					신용카드
					<input type="radio" name="payment" value="phone_payment">
					휴대폰 결제
					<input type="radio" name="payment" value="realtime_account_transfer">
					실시간 계좌이체
					<input type="radio" name="payment" value="direct_deposit">
					직접 입금
				</div>
				
				<div class="payment_Detail_input_yk">
					<div id="credit_card" class="credit_card payment_Detail_display_yk">
						<div>
							<div>
								<span>카드사 선택</span>
							</div>
							<div>
								<select name="card">
									<option>카드사 선택</option>
									<option>현대</option>
									<option>신한</option>
									<option>삼성</option>
									<option>하나</option>
									<option>하나(구 외환)</option>
									<option>롯데</option>
									<option>국민</option>
									<option>NH채움</option>
									<option>비씨</option>
									<option>우리</option>
									<option>씨티</option>
									<option>수협</option>
									<option>MG새마을</option>
									<option>제주</option>
									<option>하나BC</option>
									<option>전북</option>
									<option>신협</option>
									<option>우체국</option>
									<option>광주</option>
									<option>KDB산은</option>
									<option>저축</option>
									<option>KB증권(현대증권)</option>
									<option>카카오뱅크</option>
								</select>
							</div>
							<div>
								<span>할부 선택</span>
								<select name="fix_inst">
									<option value="0">일시불</option>
									<option value="2">2개월</option>
									<option value="3">3개월</option>
									<option value="4">4개월</option>
									<option value="5">5개월</option>
									<option value="6">6개월</option>
									<option value="7">7개월</option>
									<option value="8">8개월</option>
									<option value="9">9개월</option>
									<option value="10">10개월</option>
									<option value="11">11개월</option>
									<option value="12">12개월</option>
								</select>
							</div>
							<div>
								<input type="text" name="credit_card_1" style="width: 70px;"> -
								<input type="text" name="credit_card_2" style="width: 70px;"> -
								<input type="text" name="credit_card_3" style="width: 70px;"> -
								<input type="text" name="credit_card_4" style="width: 70px;">
							</div>
						</div>
						<input type="hidden" name="credit_card">
					</div>
					
					<div id="phone_payment" class="phone_payment payment_Detail_display_yk">
						<div>
							<div>
								<span>이동 통신사 </span>
							</div>
							<div>
								<input type="radio" name="news_agency" value="SKT">SKT
								<input type="radio" name="news_agency" value="KT">KT
								<input type="radio" name="news_agency" value="LG">LG U+
							</div>
						</div>
						<div>
							<div>
								<span>휴대폰 번호</span>
							</div>
							<div>
								<select name="phone_payment_first">
									<option>010</option>
									<option>011</option>
									<option>017</option>
									<option>019</option>
								</select>
								-
								<input type="text" name="phone_payment_second" style="width: 50px;">
								-
								<input type="text" name="phone_payment_third" style="width: 50px;">
							</div>
							<input type="hidden" name="phone_payment">
						</div>
						<div>
							<div>
								<span>주민등록번호</span>
							</div>
							<div>
								<input type="text" name="Resident_registration_number_first" style="width: 50px; text-align: center;">
								-
								<input type="password" name="Resident_registration_number_second" style="width: 50px;text-align: center;">
							</div>
						</div>
					</div>
					
					<div id="realtime_account_transfer" class="realtime_account_transfer payment_Detail_display_yk">
						<div>
							<div>
								<span>거래은행</span>
							</div>
							<div>
								<select name="acountType">
									<option>우리은행</option>
									<option>국민은행</option>
									<option>신한은행</option>
									<option>농협은행</option>
									<option>기업은행</option>
									<option>하나(구 외환)은행</option>
									<option>스탠다드차타드은행</option>
									<option>하나은행</option>
								</select>
							</div>
							<input type="hidden" name="realtime_account_transfer">
						</div>
						<div>
							<div>
								<span>계좌번호</span>
							</div>
							<div>
								<input type="text" name="account_number">
							</div>
						</div>
						<div>
							<div>
								<span>주민등록번호</span>
							</div>
							<div>
								<input type="text" name="Resident_registration_number_first" style="width: 50px; text-align: center;">
								-
								<input type="password" name="Resident_registration_number_second" style="width: 50px;text-align: center;">
							</div>
						</div>
					</div>
					
					<div id="direct_deposit" class="direct_deposit payment_Detail_display_yk">
						<div>
							<div>
								<span>입금은행/계좌번호</span>
							</div>
							<div>
								<select name="acountType">
									<option>우리은행 100-402-800704</option>
									<option>국민은행 0067-04-003335</option>
									<option>신한은행 140-011-05578</option>
									<option>농협은행 301-0178-261-11</option>
									<option>기업은행 024-07262-01-014</option>
									<option>하나(구 외환)은행 630-01093-670</option>
									<option>스탠다드차타드은행 569-20-14452</option>
									<option>하나은행 549-91021-1854</option>
								</select>
							</div>
						</div>
						<div>
							<div>
								<span>예금주</span>
							</div>
							<div>
								<span>(주)산책</span>
							</div>
						</div>
						<div>
							<div>
								<span>입금자 성명</span>
							</div>
							<div>
								<input type="text" name="depositor">
							</div>
						</div>
						<input type="hidden" name="direct_deposit">
					</div>
				</div>
			</div>
			<div class="btn_div_yk">
				<button id="payment_submit" class="btn_submit" style="width: 190px; height: 60px;" >결제하기</button>
				<button class="btn_reset_yk" style="width: 190px; height: 60px;">취소</button>
			</div>
		</form>
	</div>
</body>
</html>