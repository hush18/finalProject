<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>휴면계정해지</title>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<link href="css/diap.css" type="text/css" rel="stylesheet" />
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script type="text/javascript" src="js/loginMember.js"></script>
<script type="text/javascript">
	$(function(){
		$(".diap_text_mh").find("input:eq(0)").blur(function() {
			if ($(this).val() == "") {
				$(this).val("아이디를 입력하세요.");
			}
		});
		
		$(".diap_text_mh").find("input").focus(function(){
			$(this).val("");
		});
		
		$("input[name=password]").click(function(){
			$(this).attr("type","password");
		});
	});
	
	function diapForm(obj){
	//	alert("OK");
		if($("input[name=id]").val()=="" || $("input[name=id]").val()=="아이디" || $("input[name=id]").val()=="아이디를 입력하세요."){
			alert("아이디를 입력하세요.")
			$("input[name=id]").focus();
			return false;
		}
		
		if($("input[name=password]").val()=="" || $("input[name=password]").val()=="비밀번호"){
			alert("비밀번호를 입력하세요.")
			$("input[name=password]").focus();
			return false;
		}
	}
</script>
<script type="text/javascript" src="script/scrollBanner.js"></script>
</head>
<body>
	<div class="widthline">
		<div>
			<h2 class="h2-hr">휴면계정 복원 안내</h2>
			<div class="diap_sub_mh">
				<p>정보통신망 이용촉진 및 정보보호 등에 관한 법률 제 29조 및 동법시행령 제 16조에 의거하여, <br/>
				1년 이상 로그인, 구매 등의 서비스 이용이 없는 회원은 휴면계정으로 전환되어 안전하게 분리 관리됩니다.</p>
			
				<p>산책 서비스이용에 차질 없도록 로그인하셔서 휴면 해지해 주시기 바랍니다. <br/>
				휴면회원 중 아이디/비밀번호가 기억나지 않으신 회원님은 아이디/비밀번호찾기 또는 1:1 상담을 통해 문의해 주시기 바랍니다.</p>
			</div>
		</div>
		
		<!-- 휴면 회원 해지 -->
	<div class="diap_main_mh">
		<form action="userMain.do" method=get onsubmit="return diapForm(this)">
			<div>
				<h2 class="h2-hr">휴면계정 복원 하기</h2>
			</div>
			
			<div class="diap_input_mh">
				<div class="diap_text_mh">
					<input type="text" name="id" value="아이디"/>
					<input type="text" name="password" value="비밀번호"/>
				</div>
				<div class="diap_btn_mh">
					<button type="submit" class="btn-all">해지하기</button>
				</div>
			</div>
		</form>
			
			<div class="sub_mh">
				<p>휴면계정의 <strong>아이디/비밀번호가 기억나지 않는 경우</strong></p>
				<p>아이디/비밀번호찾기 또는 1:1 상담을 통해 확인해 주시기 바랍니다.</p>
			</div>
			
			<!-- 하단 버튼 -->
			<div class="input_button_mh">
				<button class="btn-all" onclick="idFind()">아이디 찾기</button>
				<button class="btn-all" onclick="pwdFind()">비밀번호 찾기</button>
				<button class="btn-all" onclick="javascripct:location.href='CustomerService_consulting.do'">1:1상담하기</button>
			</div>
			
			<!-- 하단 서브메뉴 -->
			<div class="input_sub_mh">
				<a href="createAccount.do">회원가입</a>
			</div>
		</div>
	</div>
</body>
</html>