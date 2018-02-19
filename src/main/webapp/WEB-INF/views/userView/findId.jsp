<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디찾기</title>
<link href="css/findId.css" type="text/css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$(".input_email_mh > select").change(function(){
			//alert("OK, ");
			var str=$(this).val();
			$(".email_address").val(str);
		});
	});
</script>
</head>
<body>
<form>
	<div class="widthline">
		<div class="fi_mh">
			<h2 class="h2-hr" style="padding-left: 20px;">아이디 찾기</h2>
			<div class="fi_epn_mh">
				<ul>
					<li><span>• </span> 회원 정보로 찾으실 경우 가입 시의 이름, 생년월일, 휴대폰 번호, 이메일을
						입력 해 주십시오.</li>
					<li><span>• </span> 1년 이상 미접속 회원이시라면 휴면계정 해지 후 이용해 주시기 바랍니다.
						<button class="btn-all" style="padding: 0; width: 100px;">휴면해제하기</button></li>
					<li><span>• </span> 위 방법으로 찾기 힘드신 경우 1:1상담을 이용 하시면 빠르게 답변
						드리겠습니다.
						<button class="btn-all" style="padding: 0; width: 100px;">1:1상담하기</button></li>
				</ul>
			</div>
			<!-- 회원정보 입력란 -->
			<div class="fi_input_mh">
					<div class="input_title_mh">
						<div>이름</div>
						<div>생년월일</div>
						<div>휴대폰 번호</div>
						<div style="line-height: 70px;">이메일 주소</div>
					</div>
					
					<div class="input_sub_mh">
						<div><input type="text" name="name" /> &nbsp;&nbsp;
							<span style="color: #3C7B5e; font-size:12px;">가입 시 등록된 실명</span>
						</div>
						<div>
							<select>
								<option>년도</option>
								<c:forEach var="i" begin="1974" end="2018">
									<option value="${i }"><c:out value="${i }"></c:out></option>
								</c:forEach>
							</select> <span>년</span>&nbsp;&nbsp; <select>
								<option value="">월</option>
								<c:forEach var="i" begin="1" end="12">
									<option value="${i }"><c:out value="${i }"></c:out></option>
								</c:forEach>
							</select> <span>월</span>&nbsp;&nbsp; <select>
								<option>일</option>
								<c:forEach var="i" begin="1" end="31">
									<option value="${i }"><c:out value="${i }"></c:out></option>
								</c:forEach>
							</select> <span>일</span>&nbsp;&nbsp; <span
								style="color: #3C7B5e; font-size: 12px;">신분증 상의 생년월일</span>
						</div>
						<div >
						<select>
							<option>선택</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
						</select>&nbsp; <span>-</span>&nbsp;
						<input class="phone_m" type="text" name="phon_middle" maxlength="4" size="3"/>&nbsp; 
						<span>-</span>&nbsp; 
						<input class="phone_e" type="text" name="phon_end" maxlength="4" size="3"/>
						&nbsp;&nbsp;<span style="color: #3C7B5e; font-size:12px;">가입 시 등록된 번호</span>
					</div>
					<div class="input_email_mh">
						<input type="text" name="email">&nbsp; &nbsp;<span>@</span>&nbsp;
						<input type="text" name="email_address" class="email_address">&nbsp; <select>
							<option value="">직접입력</option>
							<option value="gmail.com">구글(G메일)</option>
							<option value='nownuri.net'>나우누리</option>
							<option value='naver.com'>네이버</option>
							<option value='nate.com'>네이트</option>
							<option value='dreamwiz.com'>드림위즈</option>
							<option value='yahoo.com'>야후</option>
							<option value='empal.com'>엠팔</option>
							<option value='unitel.co.kr'>유니텔</option>
							<option value='intizen.com'>인티즌</option>
							<option value='chol.com'>천리안</option>
							<option value='paran.com'>파란닷컴</option>
							<option value='korea.com'>코리아닷컴</option>
							<option value='freechal.com'>프리첼</option>
							<option value='hanafos.com'>하나포스</option>
							<option value='hanmail.net'>다음</option>
							<option value='hanmir.com'>한미르</option>
							<option value='hotmail.com'>핫메일</option>
							<option value='msn.com'>MSN</option>
						</select><br/>
						<span>가입 시 등록된 메일</span>
					</div>
				</div>
			</div>
			
			<!-- 입력버튼 -->
			<div class="fi_btn_mh" align="center">
				<input class="btn-all" style="width: 17%;" type="submit" value="확인"/>
				<input class="btn-all" style="width: 17%;" type="reset" value="다시입력"/>
			</div>
		</div>
	</div>
</form>
</body>
</html>