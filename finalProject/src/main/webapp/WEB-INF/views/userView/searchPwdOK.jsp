<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>(주)산책 비밀번호 찾기</title>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<style type="text/css">
.btn-all {
	background: #5cb38b;
	color: #fff;
	border: 0px;
	position: relative;
	width: 10%;
	height: 30px;
	padding: 0 2em;
	cursor: pointer;
	transition: 800ms ease all;
	outline: none;
}

.btn-all:hover {
	background: #fff;
	color: #5cb38b;
}

.btn-all:before, .btn-all:after {
	content: '';
	position: absolute;
	top: 0;
	right: 0;
	height: 2px;
	width: 0;
	background: #1AAB8A;
	transition: 400ms ease all;
}

.btn-all:after {
	right: inherit;
	top: inherit;
	left: 0;
	bottom: 0;
}

.btn-all:hover:before, .btn-all:hover:after {
	width: 100%;
	transition: 800ms ease all;
}
.pwd_mh{
	width: 500px; height: 250px;
	margin: auto;
	border: 1px solid #9c9c9c;
	box-shadow: 2px 2px 2px 2px #c0c0c0;
	text-align: center; margin-top: 100px;
	padding-top: 50px;
}

.print_mh{ width: 380px; height:85px; margin: auto;}

.print_mh > h2 {
	color: #5cb38b;
	padding-bottom: 10px; margin-left: 5px;
	display: block; float: left;
}

.print_mh > h2:nth-child(2){
	color: #9c9c9c; font-size: 1.17em;
	height: 42px; line-height: 42px;
}

.btn_mh {margin-top: 40px;}
</style>
<script type="text/javascript">
	$(function(){
		$("button[name=login]").click(function(){
			//alert("OK");
			opener.document.location.href="${root}/loginMember.do";
			self.close();
		});
		
		$("button[name=cancle]").click(function(){
			self.close();
		});
	});
</script>
</head>
<body>
	<div class="pwd_mh">
		<div class="print_mh">
			<h2>귀하의 비밀번호는</h2>
			<h2>123456</h2>
			<h2>입니다.</h2>
		</div>
		
		<div class="btn_mh">
			<button class="btn-all" name="login" style="padding: 0; width: 100px;">로그인하기</button>
			<button class="btn-all" name="cancle" style="padding: 0; width: 100px;">닫기</button>
		</div>
	</div>
</body>
</html>