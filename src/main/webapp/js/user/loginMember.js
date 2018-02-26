/**
 * 
 */

$(function(){
	/*$(".login_mh li:eq(1)").click(function(){
		$(".login_mh li:eq(0)").removeClass();
		$(".login_mh li:eq(0)").addClass("alt_mh");
		
		$(".login_mh li:eq(1)").removeClass();
		$(".login_mh li:eq(1)").addClass("on_mh");
		
		$(".on_login_mh").css("display","none");
		$(".alt_login_mh").removeClass();
	});
	
	$(".login_mh li:eq(0)").click(function(){
		$(".login_mh li:eq(1)").removeClass();
		$(".login_mh li:eq(1)").addClass("alt_mh");
		
		$(".login_mh li:eq(0)").removeClass();
		$(".login_mh li:eq(0)").addClass("on_mh");
		
		$(".on_login_mh").css("display", "inline");
		$(".input_alt_mh").parent().addClass("alt_login_mh");
	});*/
	
	/*$(".input_on_mh").find("input").click(function(){
		if($(this).val()=="아이디를 입력하세요." || $(this).val()== "아이디" || $(this).val()== "비밀번호"){
			$(this).val("");
		}
	});*/
	
	$(".input_on_mh").find("input").focus(function(){
		$(this).val("");
	});
	
	$(".input_on_mh").find("input:eq(0)").blur(function() {
		if ($(this).val() == "") {
			$(this).val("아이디를 입력하세요.");
		}
	});
	
	$(".input_alt_mh").find("input").focus(function(){
		$(this).val("");
	});
});

/*유효성 검사*/
/*function loginForm(obj){
//	alert("OK");
		if($("input[name=id]").val()=="아이디" || $("input[name=id]").val()=="아이디를 입력하세요." || $("input[name=id]").val()=="" ){
			alert("아이디를 입력하세요.");
			$("input[name=id]").focus();
			return false;
		}
		
		if($("input[name=password]").val()=="" || $("input[name=password]").val()=="비밀번호"){
			alert("비밀번호를 입력하세요.");
			$("input[name=password]").focus();
			return false;
		}
	}

function inquiryForm(obj){
	if($("input[name=name]").val()=="이름" || $("input[name=name]").val()==""){
		alert("이름를 입력하세요.");
		$("input[name=name]").focus();
		return false;
	}
	
	if($("input[name=email]").val()=="이메일" || $("input[name=email]").val()==""){
		alert("이메일을 입력하세요.");
		$("input[name=email]").focus();
		return false;
	}
	
	if($("input[name=eAddress]").val()==""){
		alert("이메일을 입력하세요.");
		$("input[name=eAddress]").focus();
		return false;
	}
	
	if($("input[name=password]").val()=="주문 시 입력한 비밀번호를 입력해주세요." || $("input[name=password]").val()==""){
		alert("비밀번호를 입력하세요.");
		$("input[name=password]").focus();
		return false;
	}
}*/

var url="";
function idFind(){
	url="findId.do";
	window.open(url,"","width=716, height=600, location=1, top=100px, left=500px");
}

function pwdFind(){
	url="findPwd.do";
	window.open(url,"","width=716, height=600, top=100px, left=500px");
}


//아이디 저장 & 비밀번호 저장 설정
var userInputId="";
var userInputPwd="";
$(function(){
	//아이디 저장
		userInputId =getCookie("userInputId");
		if (userInputId != "") {
		$("input[name=id]").val(userInputId);
	}
	
	if($("input[name=id]").val()!="아이디"||$("input[name=id]").val()!="아이디를 입력하세요."){
		$("input[name=id_remember]").attr("checked", true);
	}
		
	//체크박스의 체크가 될 때 저장
	$("input[name=id_remember]").change(function(){
		if($("input[name=id_remember]").is(":checked")){
			var userInputId=$("input[name=id]").val();
			setCookie("userInputId", userInputId, 7);
		}else{
			deleteCookie("userInputId");
		}
	});
	
	//체크박스가 체크되있는 상태에서 아이디를 작성할 때 저장
	$("input[name=id]").keyup(function(){
		if($("input[name=id_remember]").is(":checked")){
			var userInputId=$("input[name=id]").val();
			setCookie("userInputId", userInputId, 7);
		}
	});
	

	// 비밀번호 저장
	userInputPwd = getCookie("userInputPwd");
	if (userInputPwd != "") {
		$("input[name=password]").val(userInputPwd);
	}

	if ($("input[name=password]").val() != "비밀번호") {
		$("input[name=pwd_remember]").attr("checked", true);
	}

	// 체크박스의 체크가 될 때 저장
	$("input[name=pwd_remember]").change(function() {
		if ($("input[name=pwd_remember]").is(":checked")) {
			var userInputPwd = $("input[name=password]").val();
			setCookie("userInputPwd", userInputPwd, 7);
		} else {
			deleteCookie("userInputPwd");
		}
	});

	// 체크박스가 체크되있는 상태에서 아이디를 작성할 때 저장
	$("input[name=id]").keyup(function() {
		if ($("input[name=pwd_remember]").is(":checked")) {
			var userInputPwd = $("input[name=password]").val();
			setCookie("userInputPwd", userInputPwd, 7);
		}
	});
	
	if($("input[name=password]").val()=="비밀번호"){
		$("input[name=password]").attr("type","text");
		$("input[name=password]").keyup(function(){
			$("input[name=password]").attr("type","password");
		});
	}
});

function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1); //어제날짜를 쿠키 소멸날짜로 설정
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}
