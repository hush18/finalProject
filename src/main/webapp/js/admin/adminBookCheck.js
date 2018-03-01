$(function () {
	$("#updateCheck,#insertCheck").click(function () {
		var isbn = $("input[name='isbn']").val();//도서번호
		if(isbn==null||isbn==""){
			alert("도서번호를 입력하세요.");
			$("input[name='isbn']").focus();
			return false;
		}
		
		var title = $("input[name='title']").val();//도서명
		if(title==null||title==""){
			alert("도서명을 입력하세요.");
			$("input[name='title']").focus();
			return false;
		}
		
		var publisher = $("input[name='publisher']").val();//출판사
		if(publisher==null||publisher==""){
			alert("출판사를 입력하세요.");
			$("input[name='publisher']").focus();
			return false;
		}
		
		var write_date = $("input[name='write_date']").val();//출판일
		if(write_date==null||write_date==""){
			alert("출판일을 선택하세요.");
			$("input[name='write_date']").focus();
			return false;
		}
		
		var price = $("input[name='price']").val();//가격
		if(price==null||price==""){
			alert("가격을 입력하세요.");
			$("input[name='price']").focus();
			return false;
		}
		
		var stock = $("input[name='stock']").val();//재고
		if(stock==null||stock==""){
			alert("재고를 입력하세요.");
			$("input[name='stock']").focus();
			return false;
		}
		
		var category = $("#category").val();//카테고리
		if(category==null||category==""){
			alert("카테고리를 선택하세요.");
			$("input[name='category']").focus();
			return false;
		}
		
		var image_path = $("input[name='image_path']").val();//이미지
		if(image_path==null||image_path==""){
			alert("이미지를 업로드 하세요.");
			$("input[name='image_path']").focus();
			return false;
		}
		
		var contents = $("textarea[name='contents']").val();//목차
		if(contents==null||contents==""){
			alert("목차를 입력하세요.");
			$("textarea[name='contents']").focus();
			return false;
		}
		
		var book_introduction = $("textarea[name='book_introduction']").val();//책소개
		if(book_introduction==null||book_introduction==""){
			alert("책소개를 입력하세요.");
			$("textarea[name='book_introduction']").focus();
			return false;
		}
		
		var writer_number = $("input[name='writer_number']").val();//저자
		if(writer_number==null||writer_number==""){
			alert("검색을 통해 저자를 선택해 주세요.");
			$("#writer_search").click();
			return false;
		}
	})
})