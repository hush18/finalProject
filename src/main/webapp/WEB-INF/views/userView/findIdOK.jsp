<!-- 
아이디 찾기 완료 페이지
작성자 : 김미화
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
<link href="css/user/findId.css" type="text/css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("button[name=cancle]").click(function() {
			self.close();
		});
		
		$("button[name=login]").click(function(){
			//alert("OK");
			opener.document.location.href="loginMember.do";
			self.close();
		});
		
		$("button[name=findId]").click(function(){
			$(location).attr("href", "findId.do");
		});
	});
</script>
</head>
<body>
	<div class="widthline">
		<div class="fi_mh">
			<h2 class="h2-hr" style="padding-left: 20px;">아이디 찾기</h2>
			<div class="fi_epn_mh">
				<ul>
					<li><span>• </span> 회원 정보로 찾으실 경우 가입 시의 이름, 생년월일, 휴대폰 번호, 이메일을
						입력 해 주십시오.</li>
					<li><span>• </span> 1년 이상 미접속 회원이시라면 휴면계정 해지 후 이용해 주시기 바랍니다.
						<button class="btn-all" style="padding: 0; width: 100px;">휴면해제하기</button>
					</li>
					<li><span>• </span> 위 방법으로 찾기 힘드신 경우 1:1상담을 이용 하시면 빠르게 답변
						드리겠습니다.
						<button class="btn-all" style="padding: 0; width: 100px;">1:1상담하기</button>
					</li>
				</ul>
			</div>

			<div class="box_id_mh">
				<c:if test="${id != null }">
					<div>
						<p>회원님의 아이디는 아래와 같습니다.</p>
						<strong>${id }</strong>
					</div>
					<div>
						<button class="btn-all" style="padding: 0; width: 100px;"
							name="login">로그인</button>
						<button class="btn-all"
							style="padding: 0; width: 100px; margin-left: 5px;" name="cancle">닫기</button>
					</div>
				</c:if>

				<c:if test="${id == null }">
					<div>
						<p>입력하신 이름, 생년월일, 휴대폰 번호, 이메일이 일치하는 회원정보를 찾을 수 없습니다.</p>
						<p>가입시 등록하신 정보를 확인 하신 후 다시 입력해 주시기 바랍니다.</p>
					</div>
					<div style="margin-top: 105px;">
						<button class="btn-all" style="padding: 0; width: 17%;"
							name="findId">아이디찾기</button>
						<button class="btn-all"
							style="padding: 0; width: 17%;" name="cancle">닫기</button>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>