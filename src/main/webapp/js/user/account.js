	$(function() {
		$("#reset").click(function() {
			alert("가입을 취소합니다.");
			location.href="userMain.do";
		});
		
		$("#confirm").click(function() {
			alert("가입을 축하합니다.\n로그인해주세요.");
			this.submit();
		});
		
		$("#confirmUpdate").click(function() {
			alert("정보수정이 완료되었습니다.");
			this.submit();
		});
		
	});
