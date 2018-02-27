<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Layout</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
	function toServer() {
		
	}
</script>
</head>
<body onload="toServer()">
	<!-- div, table 전체 레이아웃 -->
	<!-- header -->
	<tiles:insertAttribute name="userHeader" />

	<!-- content -->
	<tiles:insertAttribute name="userContent" />

	<!-- footer -->
	<tiles:insertAttribute name="userFooter" />

</body>
</html>