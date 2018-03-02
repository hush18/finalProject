<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>㈜산책</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="jquery_cookie/jquery.cookie.js"></script>
<script type="text/javascript">
	function closeWin() {
		if (document.event_Fcondo.event.checked)
			$.cookie("event_condo", "no", {
				expires : 1
			});
	}
</script>
</head>
<body BGCOLOR="#FFFFFF" onunload="closeWin()" style="background-image: url('images/mainPage/popup01.jpg'); background-repeat: no-repeat; background-size: cover;">
	<form name="event_Fcondo" style="padding-top: 260px;">
		<input type="checkbox" name="event">
		<font size=2>오늘하루 이 창을 열지않음</font>
		<input type=button value="닫기" onclick="self.close()">
	</form>
</body>
</html>