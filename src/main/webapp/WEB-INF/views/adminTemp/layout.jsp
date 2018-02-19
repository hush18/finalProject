<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap -->
<link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="css/custom.css" rel="stylesheet">

<!-- jQuery -->
<script src="vendors/jquery/dist/jquery.min.js"></script>
</head>
<body class="nav-md">
	<!-- header -->
	<tiles:insertAttribute name="adminHeader" />

	<!-- content -->
	<tiles:insertAttribute name="adminContent" />

	<!-- foot -->
	<tiles:insertAttribute name="adminFoot" />


	<!-- Bootstrap -->
	<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="js/custom.js"></script>
</body>
</html>