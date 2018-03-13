<!-- 
작성자 : 신호용
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
	<c:if test="${adminOrderCheck==1 }">
		<script type="text/javascript">
			alert("요청 변경에 성공하셨습니다.");
			$(location).attr('href','adminOrderSearch.do');
		</script>
	</c:if>
	<c:if test="${adminOrderCheck<1 }">
		<script type="text/javascript">
			alert("요청 변경에 실패하셨습니다.");
			history.back();
		</script>
	</c:if>
	
	<c:if test="${adminChangeCheck==1 }">
		<script type="text/javascript">
			alert("요청 변경에 성공하셨습니다.");
			$(location).attr('href','adminChange.do');
		</script>
	</c:if>
	<c:if test="${adminChangeCheck<1 }">
		<script type="text/javascript">
			alert("요청 변경에 실패하셨습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>