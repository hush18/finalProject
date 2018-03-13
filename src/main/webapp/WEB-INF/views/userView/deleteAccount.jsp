<!-- 
작성자 : 맹인영
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
<link type="text/css" rel="stylesheet" href="css/user/deleteAccount.css" />
</head>
<body>
	<div class="login-check">
		<h1>Login Check</h1>
		<form action="deleteAccount.do" method="post">
			<input id="id" name="id" type="text" placeholder="ID" required="required" />
			<input id="password" name="password" type="password" placeholder="Password" required="required" />
			<button id="confirm" type="submit" class="btn btn-primary btn-block btn-large">탈퇴</button>
		</form>
	</div>
</body>
</html>