<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="root" value="${pageContext.request.contextPath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<!-- Custom style -->
<link rel="stylesheet" href="css/user/account.css">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/user/account.js"></script>
<script type="text/javascript" src="js/user/zipcode.js"></script>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
</head>
<body>
	<div class="container">
		<h2 class="h2-hr" style="margin-left: 20px;">회원정보수정</h2>
		<form action="updateAccount.do" method="post" onsubmit="">
			<div class="col-iy">
				<label id="icon" for="name">
					<i class="fa fa-user fa-lg"></i>
				</label>
				<input type="text" name="name" id="name" placeholder="Name" value="${memberDto.name}"/>
			</div>

			<div class="col-iy">
				<label id="icon" for="id">
					<i class="fa fa-id-card-o fa-lg"></i>
				</label>
				<input style="cursor: not-allowed;" type="text" name="id" id="id" value="${memberDto.id}" readonly />
			</div>

			<div class="col-iy">
				<label id="icon" for="password">
					<i class="fa fa-key fa-lg"></i>
				</label>
				<input type="password" name="password" id="password" placeholder="Password" />
			</div>

			<div class="col-iy">
				<label id="icon" for="passwordCheck">
					<i class="fa fa-check-circle fa-lg"></i>
				</label>
				<input type="password" name="passwordCheck" id="passwordCheck" placeholder="Password Check" />
			</div>

			<div class="col-iy">
				<label id="icon" for="address">
					<i class="fa fa-map-marker fa-lg"></i>
				</label>
				<input type="text" name="member_zipcode" id="member_zipcode" placeholder="Zipcode" value="${memberDto.member_zipcode}"/>
				<input type="text" name="member_address" id="member_address" placeholder="Address" value="${memberDto.member_address}"/>
				<br />
				<input type="text" name="member_detail_address" id="member_detail_address" placeholder="DetailAddress" value="${memberDto.member_detail_address}"/>
				<a href="#" class="button" onclick="zipcode('${root}')">주소찾기</a>
			</div>

			<div class="col-iy">
				<label id="icon" for="email">
					<i class="fa fa-envelope-o fa-lg"></i>
				</label>
				<input type="text" name="email" id="email" placeholder="Email" value="${memberDto.email}"/>
			</div>
			<div class="btn-iy">
				<button id="reset" class="button" style="margin-left: 30px;">수정취소</button>
				<button id="confirmUpdate" class="button">정보수정</button>
			</div>
		</form>
	</div>
</body>