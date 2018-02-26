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
	<c:if test="${check==0 && memberDto!=null }">
		<!-- 세션 설정 -->
		<c:set var="id" value="${memberDto.id }" scope="session"/>
		<c:set var="name" value="${memberDto.name }" scope="session"/>
		<c:set var="email" value="${memberDto.email }" scope="session"/>
		
		<script type="text/javascript">
			$(location).attr("href", "userMain.do");
		</script>
	</c:if>
	
	<c:if test="${memberDto==null || check>0 }">
		<script type="text/javascript">
			alert("아이디 또는 비밀번호가 일치하지 않습니다. 아이디와 비밀번호를 확인하신 후, 다시 입력해 주세요.");
			$(location).attr("href", "loginMember.do");
		</script>
	</c:if>
</body>
</html>