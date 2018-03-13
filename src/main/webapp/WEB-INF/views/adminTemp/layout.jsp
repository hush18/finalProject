<!-- 
	작성자: 맹인영
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap -->
<link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="css/admin/custom.css" rel="stylesheet">

<!-- jQuery -->
<script src="vendors/jquery/dist/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="jquery_ui/jquery-ui.css">
<script type="text/javascript" src="jquery_ui/jquery-ui.js"></script>
</head>
<body class="nav-md">
	<!-- header -->
	<tiles:insertAttribute name="adminHeader" />
	<script type="text/javascript">
		var mbId = "<c:out value="${mbId}"/>";
		var member_number = "<c:out value="${member_number}"/>";
		if(mbId!=null){
			if(member_number>1000){
				alert("관리자로 로그인 해 주시기 바랍니다.");
				$(location).attr("href", "userMain.do");
			}
		} else{
			alert("로그인 시간이 만료되었습니다.");
			$(location).attr("href", "loginMember.do");
		}
	</script>

	<!-- content -->
	<tiles:insertAttribute name="adminContent" />

	<!-- foot -->
	<tiles:insertAttribute name="adminFoot" />


	<!-- Bootstrap -->
	<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="js/admin/custom.js"></script>
</body>
</html>