<!--
회원삭제페이지(관리자) 
작성자 : 김미화
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 회원관리</title>
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
.box_line_mh {
	margin: 20px auto; border : solid 1px #9c9c9c;
	width: 500px; background-color: white;
	height: 400px; text-align: center;
	/* box-shadow: 2px 2px 2px 2px #9c9c9c; */
	border: solid 1px #9c9c9c;
}

.sub_mh {
	font-size: 1.17em; margin-top: 100px;
	color: #5cb38b;
	font-weight: bold;
}

.password {
	height: 30px;
	border: 2px solid #3C7B5e;
	font-size: 1.17em;
	color: #3C7B5e;
	text-align: center;
}

.btn_mh{
	margin-top: 40px;
}
</style>
</head>
<body>
	<div class="box_line_mh">
		<input type="hidden" name="id" value="${id }"/>
		<div class="sub_mh">
			<c:if test="${member_num<1000 }">
				<p>본 계정은 관리자 계정입니다. 삭제 시 유의해주십시오.</p>
			</c:if>
		
			<p>관리자님의 비밀번호를 입력해주세요.</p>
		</div>
		<div class="input_mh">
			<input type="password" class="password" name="password"/>
		</div>
		<div class="btn_mh">
			<button class="btn-all" name="submit" type="submit"
				style="padding: 0; width: 100px;">확인</button>
			<button class="btn-all" name="cancle"
				style="padding: 0; width: 100px;">취소</button>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){ 
	$("button[name=submit]").click(function() {
		var id=$("input[name=id]").val();
		var password=$("input[name=password]").val();
		
		var value=confirm("삭제할 경우 재사용 및 복구가 불가능합니다. 정말로 삭제하시겠습니까?");
		if(value==true){
			location.href="adminMemberDeleteOK.do?id="+id+"&password="+password;
		}else{
			return false;
		}
	});

	$("button[name=cancle]").click(function() {
		self.close();
	});
});
</script>
</html>