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
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("일치하는 회원정보가 없습니다. 아이디와 비밀번호를 확인하세요.");
			location.href="diap.do";
		</script>
	</c:if>
	
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("휴면 계정 해지 되었습니다.");
			location.href="diap.do";
		</script>
	</c:if>
	
	<c:if test="${check<0 }">
		<script type="text/javascript">
			alert("회원님은 휴면 대상자가 아닙니다.");
			location.href="diap.do";
		</script>
	</c:if>
</body>
</html>