<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="root" value="${pageContext.request.contextPath }"/>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("삭제 완료.");
			location.href="nearestList.do";
		</script>
	</c:if>
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("삭제 실패.");
			location.href="nearestList.do";
		</script> 
	</c:if>
	<c:if test="${check==-1 }">
		<script type="text/javascript">
			alert("로그인 하여 주시기 바랍니다.");
			location.href="nearestList.do";
		</script> 
	</c:if>
</body>
</html>