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
<script type="text/javascript">
	$(function(){
		var check=$('input[type="hidden"]').val();
		if(check>0){
			alert("위시리스트에 등록되었습니다.");
			history.back();
		}else if(check==0){
			alert("위시리스트에 등록 실패하였습니다.");
			history.back();
		}
	});
</script>
<body>
	<input type="hidden" name="check" value="${check }"/>
</body>
</html>