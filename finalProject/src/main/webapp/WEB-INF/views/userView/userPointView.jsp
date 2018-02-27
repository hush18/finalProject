<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/sideCategory.js"></script>
<link rel="stylesheet" type="text/css" href="css/sideCategory.css">
<link rel="stylesheet" type="text/css" href="css/userPointView.css">
<title>포인트</title>
</head>
<body>
	<div class="widthline">
		<!-- 사이드 메뉴 -->
		<div style="display: inline-block;">
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
		</div>
		<!-- 사이드 메뉴 끝 -->


		<div class="con_hy">
			<div class="con_info_hy">
				<div>
					<h2 class="h2_hy">아이디님의 정보</h2>
				</div>
				<div class="con_info1_hy">
					<div class="info_head_hy">
						<div>진행중 주문 건</div>
						<div class="info_box_hy">
							<span><a href="ordering.jsp">1</a></span>
						</div>
					</div>
					<div class="info_head_hy">
						<div>배송중</div>
						<div class="info_box_hy">
							<span><a href="delivery.jsp">1</a></span>
						</div>
					</div>
					<div class="info_head_hy">
						<div>환불/취소</div>
						<div class="info_box_hy">
							<span><a href="cancel.jsp">1</a></span>
						</div>
					</div>
					<div class="info_head_hy">
						<div>포인트</div>
						<div class="info_box_hy">
							<span><a href="">1</a></span>
						</div>
					</div>
				</div>
			</div>
			<h2 class="h2_hy">포인트 조회</h2>
			<div class="search_box_hy">
				<div>
					<span>기간별 조회</span>
				</div>
				<div style="margin-top: 5px;">
					<span>
						<button class="block_btn_hy">15일이내</button>
						<button class="block_btn_hy">1개월</button>
						<button class="block_btn_hy">3개월</button>
						<button class="block_btn_hy">6개월</button>
					</span>
				</div>
				<div style="margin-top: 5px;">
					<span class="block_day_hy">
						<span id="fromYear"><select id="fromYear" name="fromYear" ><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018" selected="selected">2018</option></select></span>
						<span id="fromMonth"><select id="fromMonth" name="fromMonth"><option value="1" selected="selected">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select></span>
						<span id="fromDay"><select id="fromDay" name="fromDay"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20" selected="selected">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select></span> 
						<span>-</span>
						<span id="toYear"><select id="toYear" name="toYear"><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018" selected="selected">2018</option></select></span>
						<span id="toMonth"><select id="toMonth" name="toMonth"><option value="1" selected="selected">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select></span>
						<span id="toDay"><select id="toDay" name="toDay"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23" selected="selected">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select></span>
						<span><button class="block_btn_hy">조회</button></span>
					</span>
				</div>
				<p>조회기간은 최대  6개월 단위로 설정하실 수 있으며, 주문번호를 클릭하시면 주문에 대한 상세정보를 보실 수 있습니다.</p>
			</div>
			
			<div class="downList_hy">
				<select class="downList_sel_hy" name="array">
					<option value="recentOrder" selected="selected">최근주문 순</option>
					<option value="orderNum">주문번호 순</option>
					<option value="goodsName">상품이름 순</option>
					<option value="orderPrice">주문가격 순</option>
				</select>
			</div>
			<div class="search_faqlist_header_ej">
				<div style="width: 25%;">주문번호</div>
				<div style="width: 20%;">상품명</div>
				<div style="width: 15%;">결제 금액</div>
				<div style="width: 15%;">결제 날짜</div>
				<div style="width: 12.5%;">획득 포인트</div>
				<div style="width: 12.5%;">누적 포인트</div>
			</div>
			<!-- 리스트 출력 -->
			<div class="pointList_yk">
				<div>
					<div class="">
						<div>B0635_20180115174023</div>
						<div>언어의 온도 외 1개</div>
						<div>18,000워</div>
						<div>2018.02.09</div>
						<div>-2,000P</div>
						<div>2,000P</div>
					</div>
				</div>
				
				<div class="line_yk"></div>
				<div>
					<div class="">
						<div>B0635_20180115174023</div>
						<div>언어의 온도 외 1개</div>
						<div>20,000워</div>
						<div>2018.02.09</div>
						<div>2,000P</div>
						<div>4,000P</div>
					</div>
				</div>
				
				<div class="line_yk"></div>
				<div>
					<div class="">
						<div>B0635_20180115174023</div>
						<div>언어의 온도 외 1개</div>
						<div>20,000워</div>
						<div>2018.01.24</div>
						<div>2,000P</div>
						<div>2,000P</div>
					</div>
				</div>
				
			</div>
			
			<div class="page_count_hy">
				<a href="">1</a><a href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a><a href="">[다음]</a>
			</div>
		</div>
	</div>
</body>
</html>

















