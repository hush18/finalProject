<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>최근 본 상품</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<link type = "text/css" rel = "stylesheet" href = "css/nearestList.css">
<script type="text/javascript" src="js/sideCategory.js"></script>
<link type = "text/css" rel="stylesheet" href="css/sideCategory.css"/>
<script type="text/javascript">
	$(function(){
		$(".checkAll_sc").click(function(){
			if($(".checkAll_sc").prop("checked")){
				$(".checkBook_sc").each(function(){
					$(this).prop("checked",true);
				});
			}else{
				$(".checkBook_sc").each(function(){
					$(this).prop("checked",false);
				});
			}
		});
			
		$(".menu_sc a").hover(function(){
			$(this).css("color","#5CB38B");
		},
		function(){
			$(this).css("color","#9c9c9c");
		});
	});
	
</script>
</head>
<body>
	<div class="widthline">
	
		<div class="path_sc">홈 > 관심리스트 > 최근 본 상품</div>
		
		<!-- 사이드메뉴 -->
		<div class="side_mh">
		<div class="category_mh">
			<div>
				<!-- 주문관리 -->
				<div class="orderManager_mh">
					<div class="title_mh">
						<h3>주문관리</h3>
						<img src="images/down.png">
						<img src="images/up.png">
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
						<img src="images/down.png">
						<img src="images/up.png">
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
						<img src="images/down.png">
						<img src="images/up.png">
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
						<img src="images/down.png">
						<img src="images/up.png">
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
		<div class="main_sc">
			<div class="bar_sc">
				<div class="bar_ea_sc">총 개수:0개</div>
				<div class="bar_sel_sc">
					<select id="bar_listSel_sc">
						<option value="20" selected>20개씩</option>
						<option value="40">40개씩</option>
					</select>
				</div>
				<button class="delete_sc">삭제</button>
				<button class="cart_sc">장바구니 담기</button>
				<div class="bar_check_sc">
					<input type="checkbox" name="check" value="전체선택" class="checkAll_sc"/>
					<label>전체선택</label>
				</div>
			</div>
			<div class="list_sc">
				<div class="book_sc">
				
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
					<div class="book_chdiv_sc">
						<div class="inputCh_sc">
							<input type="checkbox" name="bookCheck" class="checkBook_sc"/>
						</div>
						<img id="image" src="./images/4088478.jpg" width="150px" height="230px"/>
						<div class="bookCon_sc">
							책제목<br/>
							저자<br/>
							출판사
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>