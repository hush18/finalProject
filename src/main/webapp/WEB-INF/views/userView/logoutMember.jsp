<!-- 
로그아웃 페이지
작성자 : 김미화
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:remove var="mbId" scope="session"/>
	<c:remove var="mbName" scope="session"/>
	<c:remove var="mbEmail" scope="session"/>
	<c:remove var="member_number" scope="session"/>
	
	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		location.href="userMain.do";
	</script>
</body>
</html>