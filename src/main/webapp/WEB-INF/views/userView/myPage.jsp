<!-- 
작성자 : 맹인영
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link type="text/css" rel="stylesheet" href="css/user/sideCategory.css" />
<link type="text/css" rel="stylesheet" href="css/user/myPage.css" />
<script type="text/javascript">

	$(function(){
		$(".orderManager_mh > .title_mh").trigger('click');
	});
	
	function deleteMember() {
		window
				.open("deleteAccount.do", "",
						"width=400, height=400, scroll=yes");
	}
</script>
</head>
<body>
	<div class="widthline" style="height: 500px;">
		<div class="path_sc">홈 > 마이페이지</div>
		<div class="side_mh">
			<div class="category_mh">
				<!-- 마이페이지 -->
				<div class="orderManager_mh">
					<div class="title_mh">
						<h3>마이페이지</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li>
								<a href="myPage.do">회원정보</a>
							</li>
							<li>
								<a href="updateAccount.do">회원정보수정</a>
							</li>
							<li>
								<a href="userPoint.do">포인트내역</a>
							</li>
							<li>
								<a href="javascript:deleteMember()">회원탈퇴</a>
							</li>
						</ul>
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

		<div class="member-contents-iy">
			<h2 class="h2-hr">회원정보</h2>
			<div class="member-info-iy">
				<div class="info-box-iy">
					<p>
						<strong>이름</strong>
						${memberDto.name}
					</p>
				</div>
				<div class="info-box-iy">
					<p>
						<strong>아이디</strong>
						${memberDto.id}
					</p>
				</div>
				<div class="info-box-iy">
					<p>
						<strong>주소</strong>
						${memberDto.member_zipcode } ${memberDto.member_address} ${memberDto.member_detail_address}
					</p>
				</div>
				<div class="info-box-iy">
					<p>
						<strong>이메일</strong>
						${memberDto.email}
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>