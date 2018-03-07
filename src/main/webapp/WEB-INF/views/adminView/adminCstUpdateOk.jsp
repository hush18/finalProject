<!-- 
작성자 : 최은지
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="root" value="${pageContext.request.contextPath }" />
<title>㈜산책 FAQ등록</title>
</head>
<body>
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("수정이 완료 되었습니다.");
			$(location).attr("href","adminCstMain.do");
		</script>
	</c:if>
	
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("오류가 발생하였습니다. 다시 입력해 주세요");
			$(location).attr("href","adminCstMain.do");
		</script>
	</c:if>
</body>
