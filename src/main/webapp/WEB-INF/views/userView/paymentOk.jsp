<!-- 
결제 결과 페이지
작성자 : 김용기
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("결제가 완료되었습니다.")
			location.href="userMain.do";
		</script>
	</c:if>
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("오류때문에 결제를 실패했습니다.")
			history.back()
		</script>
	</c:if>
</body>
</html>