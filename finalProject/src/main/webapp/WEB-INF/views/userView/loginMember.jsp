<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->

<c:set var="root" value="${pageContext.request.contextPath }"/>

<link href="css/loginMember.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/loginMember.js"></script>
</head>
<body>
	<div class="widthline">
		<div class="loginline_mh">
			<div>
				<h2 class="h2-hr">로그인</h2>
			</div>
			<!-- 로그인 안 상자 -->
			<div class="login_mh">
				<ul>
					<li class="on_mh">회원 로그인</li>
					<li class="alt_mh">비회원 주문조회</li>
				</ul>
			</div><br/><br/>
			
			<!-- 회원 로그인 입력 칸 -->
			<div class="on_login_mh">
			<form action="${root }/memberLoginOK.do" method=post onsubmit="return loginForm(this)">
				<div class="login_input_mh">
					<!-- 회원 로그인 창 -->
					<div class="input_on_mh">
						<div>
							<input type="text" name="id" value="아이디" /><br />
							<input type="text" name="password" value="비밀번호" /><br />
						</div>
					</div>

					<div class="input_check_mh">
						<div>
							<input type="checkbox" name="id_remember" />아이디저장 
							<input	type="checkbox" name="login_remember" />로그인유지
						</div>
					</div>
				</div>
				<div class="btn_on_mh">
					<div>
						<button type="submit" class="btn-all" style="height: 80px;" onKeydown="javascript:if(event.keyCode == 13) form.submit();">
							로그인</button>
					</div>
				</div><br/><br/>
			</form>
				
				<!-- 회원 부가 서비스 -->
				<div class="on_sub_mh">
					<div>
						<a href="" class="find_id_mh" onclick="idFind()">아이디 찾기</a>|
						<a href="" class="find_pwd_mh" onclick="pwdFind()">비밀번호 찾기</a>|
						<a href="createAccount.do" class="join_mh" style="font-weight: bold;">회원가입</a>|
						<a href="diap.do" class="find_diap_mh">휴면계정조회</a>
					</div>
				</div>

			<!-- 네이버/ 페이스북 연동 -->
			<div class="interwork_mh" >
				<div class="interwork_naver_mh">
					<div>
						<button class="btn-all">네이버 아이디 가입/로그인</button>
					</div>
				</div>
				
				<div class="interwork_facebook_mh">
					<div>
						<button class="btn-all">페이스북 아이디 가입/로그인</button>
					</div>
				</div>
			</div>
			</div>
			
			<!-- 비회원 조회 창 -->
			<form action="userMain.do" method=get onsubmit="return inquiryForm(this)">
			<div class="alt_login_mh">
				<div class="input_alt_mh">
					<div>
						<input type="text" name="name" value="이름" />
						<div>
							<input type="text" name="email" value="이메일" /> @
							<input type="text" name="eAddress" />
						</div>
						<input type="text" name="password" 
						value="주문 시 입력한 비밀번호를 입력해주세요." />
					</div>
				</div>
				
				<div class="btn_alt_mh">
					<button type="submit" class="btn-all">조회하기</button>
				</div><br/>
				
				<div class="epn_alt_mh">
					<ul>
						<li><span>• </span> 비회원으로 주문하신 내역이 있는 분만 이용이 가능합니다.</li>
						<li><span>• </span> 비회원 주문 시 입력한 이름과 이메일, 비밀번호로 로그인해주세요.</li>
						<li><span>• </span> 비회원으로 로그인 시 일부 서비스 제한이 있을 수 있습니다.</li>
					</ul>
				</div>
				
			<div class="alt_join_mh">
				<a href="createAccount.do" style="font-weight: bold;">회원가입</a>
			</div>
			</div>
			
			</form>
		</div>
	</div>
</body>
</html>