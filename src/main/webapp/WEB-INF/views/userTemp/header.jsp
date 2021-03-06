<!-- 
	작성자: 맹인영
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript" src="jquery_ui/external/jquery/jquery.js"></script>
<script type="text/javascript" src="jquery_ui/jquery-ui.js"></script>
<script type="text/javascript" src="js/user/header.js"></script>

<link rel="stylesheet" type="text/css" href="jquery_ui/jquery-ui.css">
<link rel="stylesheet" href="css/user/header.css">
<!-- 폰트어썸 -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<script type="text/javascript">
	$(function() {
		var jbOffset = $('.mainMenu-iy').offset();

		$(window).scroll(function() {
			if ($(document).scrollTop() > jbOffset.top) {
				$('.mainMenu-iy').addClass('mainMenu-iy-fixed');
				$('.mainMenu-iy hr').css("margin-bottom", "0px");
			} else {
				$('.mainMenu-iy').removeClass('mainMenu-iy-fixed');
				$('.mainMenu-iy hr').css("margin-bottom", "30px");
			}
		});
		
		$(".preparing").click(function () {
			alert("서비스 준비중 입니다.");
			return false;
		})
	});
</script>
</head>

<div class="widthline">
	<div class="topMenu-iy">
		<div class="leftMenu-iy">
			<ul class="ul-iy">
				<c:if test="${mbId==null }">
					<li>
						<a href="loginMember.do">로그인</a>
					</li>
					<li>
						<a href="createAccount.do">회원가입</a>
					</li>
				</c:if>

				<c:if test="${mbId!=null }">
					<li>
						<a href="myPage.do">${mbId } 님</a>
					</li>
					<li>
						<a href="logoutMember.do">로그아웃</a>
					</li>
				</c:if>
				<li>
					<a href="cart.do">장바구니</a>
				</li>
			</ul>
		</div>
		<div class="rightMenu-iy">
			<ul class="ul-iy">
				<li>
					<a href="orderSearch.do">주문관리</a>
					<ul class="ul-iy">
						<li>
							<a href="orderSearch.do">주문/배송조회</a>
						</li>
						<li>
							<a href="cancel.do">취소/반품/교환내역</a>
						</li>
						<li>
							<a href="buyList.do">구매내역</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="nearestList.do">관심리스트</a>
					<ul class="ul-iy">
						<li>
							<a href="nearestList.do">최근 본 상품</a>
						</li>
						<li>
							<a href="wishList.do">위시리스트</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="CustomerService_main.do">고객센터</a>
					<ul class="ul-iy">
						<li>
							<a href="CustomerService_main.do">FAQ</a>
						</li>
						<li>
							<a href="CustomerService_consulting.do">1:1상담하기</a>
						</li>
						<li>
							<a href="CustomerService_consultingList.do">1:1상담내역</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="Map.do">영업점안내</a>
				</li>
				
				<c:if test="${member_number!=null && member_number<1000}">
					<li><a href="adminBookSearch.do">관리자메뉴</a>
						<ul class="ul-iy">
							<li><a href="adminBookSearch.do">도서관리</a></li>
							<li><a href="adminMemberManage.do">회원관리</a></li>
							<li><a href="adminMap.do">영업점관리</a></li>
							<li><a href="adminSales.do?value=1">매출관리</a></li>
							<li><a href="adminFaqMain.do">고객문의관리</a></li>
							<li><a href="adminOrderSearch.do">주문관리</a></li>
						</ul></li>
				</c:if>
				
			</ul>
		</div>
	</div>
	<hr class="hr-iy" />
	<div class="logo-N-search-iy">
		<div class="header-content-iy">
			<div class="logo-iy">
				<img src="images/header/logo1.jpg" width="150px" onclick="javascript:location.href='userMain.do'" />
			</div>
		</div>

		<div class="H-search">
			<form class="search-form-iy" action="searchList.do" method="get">
				<input name="search" id="search_mh" type="text" placeholder="검색어 입력">
				<button type="submit"></button>
			</form>
		</div>
		<div class="basket-iy"></div>
	</div>
	<div class="mainMenu-iy">
		<div class="leftMenu-iy">
			<ul class="ul-iy">
				<li id="all-main-iy">
					<a href="bookList.do?category_path=전체" style="position: relative;">전체</a>
					<div class="all-mainMenu-iy">
						<div>
							<h3 class="h2-hr">소설</h3>
							<p>
								<a href="#" class="preparing">한국소설</a>
							</p>
							<p>
								<a href="#" class="preparing">일본소설</a>
							</p>
							<p>
								<a href="#" class="preparing">영미소설</a>
							</p>
							<p>
								<a href="#" class="preparing">기타 외국소설</a>
							</p>
						</div>
						<div>
							<h3 class="h2-hr">인문/철학</h3>
							<p>
								<a href="#" class="preparing">철학</a>
							</p>
							<p>
								<a href="#" class="preparing">국어학</a>
							</p>
							<p>
								<a href="#" class="preparing">교육학</a>
							</p>
							<p>
								<a href="#" class="preparing">기호학/언어학</a>
							</p>
							<p>
								<a href="#" class="preparing">심리학</a>
							</p>
						</div>
						<div>
							<h3 class="h2-hr">문학</h3>
							<p>
								<a href="bookList.do?category_path=시">시</a>
							</p>
							<p>
								<a href="bookList.do?category_path=에세이">에세이</a>
							</p>
							<p>
								<a href="bookList.do?category_path=기행">기행</a>
							</p>
						</div>
						<div>
							<h3 class="h2-hr">참고서</h3>
							<p>
								<a href="#" class="preparing">초중고</a>
							</p>
							<p>
								<a href="#" class="preparing">외국어</a>
							</p>
							<p>
								<a href="#" class="preparing">사전</a>
							</p>
							<p>
								<a href="#" class="preparing">기타</a>
							</p>
						</div>
						<div>
							<h3 class="h2-hr">기타도서</h3>
							<p>
								<a href="#" class="preparing">요리</a>
							</p>
							<p>
								<a href="#" class="preparing">자녀교육/임신/출산/태교</a>
							</p>
							<p>
								<a href="#" class="preparing">가정/살림/홈인테리어</a>
							</p>
							<p>
								<a href="#" class="preparing">결혼/부부생활</a>
							</p>
							<p>
								<a href="#" class="preparing">잡지</a>
							</p>
							<p>
								<a href="#" class="preparing">건강</a>
							</p>
						</div>
					</div>
				</li>
				<li>
					<a href="#" class="preparing">소설</a>
					<ul class="ul-iy">
						<li>
							<a href="#" class="preparing">한국소설</a>
						</li>
						<li>
							<a href="#" class="preparing">일본소설</a>
						</li>
						<li>
							<a href="#" class="preparing">영미소설</a>
						</li>
						<li>
							<a href="#" class="preparing">기타 외국소설</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#" class="preparing">인문/철학</a>
					<ul class="ul-iy">
						<li>
							<a href="#" class="preparing">철학</a>
						</li>
						<li>
							<a href="#" class="preparing">국어학</a>
						</li>
						<li>
							<a href="#" class="preparing">교육학</a>
						</li>
						<li>
							<a href="#" class="preparing">기호학/언어학</a>
						</li>
						<li>
							<a href="#" class="preparing">심리학</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="bookList.do?category_path=문학">문학</a>
					<ul class="ul-iy">
						<li>
							<a href="bookList.do?category_path=시">시</a>
						</li>
						<li>
							<a href="bookList.do?category_path=에세이">에세이</a>
						</li>
						<li>
							<a href="bookList.do?category_path=기행">기행</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#" class="preparing">참고서</a>
					<ul class="ul-iy">
						<li>
							<a href="#" class="preparing">초중고</a>
						</li>
						<li>
							<a href="#" class="preparing">외국어</a>
						</li>
						<li>
							<a href="#" class="preparing">사전</a>
						</li>
						<li>
							<a href="#" class="preparing">기타</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#" class="preparing">기타도서</a>
					<ul class="ul-iy">
						<li>
							<a href="#" class="preparing">요리</a>
						</li>
						<li>
							<a href="#" class="preparing">자녀교육/임신/출산/태교</a>
						</li>
						<li>
							<a href="#" class="preparing">가정/살림/홈인테리어</a>
						</li>
						<li>
							<a href="#" class="preparing">결혼/부부생활</a>
						</li>
						<li>
							<a href="#" class="preparing">잡지</a>
						</li>
						<li>
							<a href="#" class="preparing">건강</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>

		<div class="rightMenu-iy">
			<ul class="ul-iy">
				<li>
					<a href="#" class="preparing">베스트셀러</a>
				</li>
				<li>
					<a href="#" class="preparing">신간도서</a>
				</li>
			</ul>
		</div>
		<hr class="hr-iy" />
	</div>

	<script type="text/javascript">
		$("#all-main-iy").hover(function() {
			$(".all-mainMenu-iy").css("display", "block");
		}, function() {
			$(".all-mainMenu-iy").css("display", "none");
		});

		// 			$(".all-mainMenu-iy").mouseover(function() {
		// 				$(".all-mainMenu-iy").css("display", "block");
		// 			});

		// 			$(".all-mainMenu-iy").mouseout(function() {
		// 				$(".all-mainMenu-iy").css("display", "none");
		// 			});
	</script>

</div>