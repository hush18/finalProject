<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
<script type="text/javascript" src="js/user/jquery.js"></script>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
	<c:choose>
		<c:when test="${check==0 }">
			<script type="text/javascript">
				alert("회원탈퇴에 실패하셨습니다.");
			</script>
		</c:when>
		<c:when test="${check>0 }">
			<script type="text/javascript">
				alert("회원탈퇴가 완료되었습니다.");
			</script>
		</c:when>
	</c:choose>

	<script type="text/javascript">
		opener.parent.location = "userMain.do";
		self.close();
	</script>
</body>
</html>