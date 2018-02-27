<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>㈜산책 비밀번호 찾기 이메일 인증</title>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<link type="text/css" rel="stylesheet" href="css/searchPwd.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function getQuerystring(paramName) {

		var _tempUrl = window.location.search.substring(1); //url에서 처음부터 '?'까지 삭제
		var _tempArray = _tempUrl.split('&'); // '&'을 기준으로 분리하기 

		for (var i = 0; _tempArray.length; i++) {
			var _keyValuePair = _tempArray[i].split('='); // '=' 을 기준으로 분리하기

			if (_keyValuePair[0] == paramName) { // _keyValuePair[0] : 파라미터 명 // _keyValuePair[1] : 파라미터 값
				return _keyValuePair[1];
			}
		}
	}
	
	var id = getQuerystring('id');
	$(function() {
		$("button[name=submit]").click(function() {
			if ($("input[type=text]").val() == $("input[type=hidden]").val()) {
				location.href="${root}/searchPwdOK.do?id="+id;
			}
		});
		
		$("button[name=cancle]").click(function() {
			self.close();
		});
	});
</script>
</head>
<body>
	<input type="hidden" name="code" value="${authNum}" />
	<%-- <h2>${authNum}</h2> --%>
	<div class="line_mh">
		<div>
			<label class="lb_mh">귀하 이메일로 전송된 인증번호를 입력해주세요.</label>
		</div>

		<div>
			<input type="text" class="txt_mh" />
		</div>

		<div class="btn_mh">
			<button class="btn-all" name="submit"
				style="padding: 0; width: 100px;">확인</button>
			<button class="btn-all" name="cancle" style="padding: 0; width: 100px;">취소</button>
		</div>
	</div>
</body>
</html>