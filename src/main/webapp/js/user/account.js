function getCharCode(data, count) {
	var charData = password.val().split("");
	for(var i = 0 ; i < charData.length ; i++) {
		var charCode = charData[i].charCodeAt(0);
		if(charCode > 31 && charCode < 48)
			count++;
		else if(charCode == 64)	// 64가 @
			count = 1000;
	}
}

$(function() {
	var password;
	var id;
	var email;
	
	    $("form").submit(function(){
	    	// 유효성검사를 위한 필드
			password = $("#updateForm #password");
			id = $("#updateForm #id");
			email = $("#updateForm #email");
			
			var countPwd = 0;
			var countId = 0;
			var countEmail = 0;
			getCharCode(password.val(), countPwd);
			getCharCode(id.val(), countId);
			getCharCode(email.val(), countEmail);
			
			// 유효성 검사
			if(password.val().length < 5) {
				alert("비밀번호는 5자 이상으로 해주세요.");
				password.focus();
				return false;
			} else if(countPwd < 1) {
				alert("비밀번호는 최소 하나 이상의 특수문자가 존재해야 합니다.");
				password.focus();
				return false;
			} else if(id.val().length < 5) {
				alert("아이디는 5자 이상으로 해주세요.");
				id.focus();
				return false;
			} else if(countEmail <= 1000) {
				alert("올바른 이메일 형식을 사용해주시기 바랍니다.");
				email.focus();
				return false;
			}
			else {
				alert("성공하셨습니다!");
		    	$(location).attr("href", "userMain.do");				
			}
	    });
	});