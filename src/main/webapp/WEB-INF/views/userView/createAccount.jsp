<!-- 
작성자 : 맹인영
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="root" value="${pageContext.request.contextPath }"/>
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
	<script type="text/javascript">
		$(function () {
			var check = "<c:out value="${check}"/>";
			if(check!=0){
				alert("회원가입이 완료되었습니다.");
				$(location).attr("href", "userMain.do");
			}
		})
	</script>
	
	<div class="container">
		<h2 class="h2-hr" style="margin-left: 20px;">회원가입</h2>
		<form action="createAccount.do" method="post" onsubmit="">
			<div class="col-iy">
				<label id="icon" for="name">
					<i class="fa fa-user fa-lg"></i>
				</label>
				<input type="text" name="name" id="name" placeholder="Name" value="${name}"  required="required" />
			</div>

			<div class="col-iy">
				<label id="icon" for="id">
					<i class="fa fa-id-card-o fa-lg"></i>
				</label>
				<input type="text" name="id" id="id" placeholder="ID" value="${id}" required="required" />
			</div>

			<div class="col-iy">
				<label id="icon" for="password">
					<i class="fa fa-key fa-lg"></i>
				</label>
				<input type="password" name="password" id="password" placeholder="Password" required="required" />
			</div>

			<div class="col-iy">
				<label id="icon" for="passwordCheck">
					<i class="fa fa-check-circle fa-lg"></i>
				</label>
				<input type="password" name="passwordCheck" id="passwordCheck" placeholder="Password Check" required="required" />
			</div>

			<div class="col-iy">
				<label id="icon" for="address">
					<i class="fa fa-map-marker fa-lg"></i>
				</label>
				<input type="text" name="member_zipcode" id="member_zipcode" placeholder="Zipcode" required="required" />
				<input type="text" name="member_address" id="member_address" placeholder="Address" required="required" />
				<br />
				<input type="text" name="member_detail_address" id="member_detail_address" placeholder="DetailAddress" required="required" />
				<a href="#" class="button" onclick="zipcode('${root}')">주소찾기</a>
			</div>

			<div class="col-iy">
				<label id="icon" for="email">
					<i class="fa fa-envelope-o fa-lg"></i>
				</label>
				<input type="text" name="email" id="email" placeholder="Email" value="${email}" required="required" />
			</div>
			<div class="btn-iy">
				<button id="reset" class="button" style="margin-left: 30px;">가입취소</button>
				<button id="confirm" class="button">회원가입</button>

			</div>
			<!-- 			<div class="btn-iy"> -->
			<!-- 				<a href="userMain.do" class="button">가입취소</a> -->
			<!-- 				<a href="javascript:createAccount()" class="button" id="createAccount" style="margin-right: 50px;">회원가입</a> -->
			<!-- 			</div> -->
		</form>
	</div>
</body>