<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>㈜산책 비밀번호 찾기 이메일 인증</title>
<link type="text/css" rel="stylesheet" href="css/user/searchPwd.css">
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
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
	
	$(function(){ 
		$("button[name=submit]").click(function() {
			if($("input[type=text]").val() == ""){
				alert("인증번호를 입력해주세요.");
				$("input[type=text]").focus();
				return false;
			}
			
			if ($("input[type=text]").val() == $("input[type=hidden]").val()) {
				location.href="searchPwdOK.do?id="+id;
			}else{
				alert("인증번호가 틀립니다. 디시 입력해주세요.")
				$("input[type=text]").focus();
				return false;
			}
		});

		$("button[name=cancle]").click(function() {
			self.close();
		});
	});
</script>
</head>
<body>
	<c:if test="${authNum > 0 }">
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
				<button class="btn-all" name="submit" type="submit"
					style="padding: 0; width: 100px;">확인</button>
				<button class="btn-all" name="cancle"
					style="padding: 0; width: 100px;">취소</button>
			</div>
		</div>
	</c:if>
	
	<c:if test="${authNum == 0 }">
		<script type="text/javascript">
			alert("입력하신 정보와 일치하는 회원이 존재하지 않습니다.");
			location.href="findPwd.do";
		</script>
	</c:if>
</body>
</html>