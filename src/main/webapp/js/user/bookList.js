$(function () {
		if($("#view_jm").val()=="simply"){
			$("#detail *").hide();
			$("#simply_list_jm *").show();
		} else if($("#view_jm").val()=="detail"){
			$("#detail *").show();
			$("#simply_list_jm *").hide();
		}
		
		$("#list_count_jm").change(function () {
			var sortValue = $(".select_list_view_jm").find("input[name='sortValue']").val();
			var category_path = $(".select_list_view_jm").find("input[name='category_path']").val();
			var path = $(".select_list_view_jm").find("input[name='path']").val();
			var view = $(".select_list_view_jm").find("input[name='view']").val();
			var bookListSize = $(this).val();
			
			$(location).attr("href", "bookList.do?sortValue="+sortValue+"&category_path="+category_path+"&path="+path+"&bookListSize="+bookListSize+"&view="+view);
		})
		
		$("#view_jm").change(function () {
			var sortValue = $(".select_list_view_jm").find("input[name='sortValue']").val();
			var category_path = $(".select_list_view_jm").find("input[name='category_path']").val();
			var path = $(".select_list_view_jm").find("input[name='path']").val();
			var view = $(this).val();
			var bookListSize = $(".select_list_view_jm").find("input[name='bookListSize']").val();
			
			$(location).attr("href", "bookList.do?sortValue="+sortValue+"&category_path="+category_path+"&path="+path+"&bookListSize="+bookListSize+"&view="+view);
		})
		
		
		
		$(".info_move_jm > img").mousemove(function() {
			$(this).addClass("hover");
		}).mouseout(function() {
			$(this).removeClass("hover");
		}).click(function() {
			var parent = $(this).parent();
			var isbn = parent.find("input[name='isbn']").val();
			var pageNumber = parent.find("input[name='pageNumber']").val();
			var path = parent.find("input[name='path']").val();
			var category_path = parent.find("input[name='category_path']").val();
			
			$(location).attr("href", "bookInfo.do?isbn="+isbn+"&pageNumber="+pageNumber);
		})
		
		
		$("#checkAll").click(function() {
			var view = $("#view_jm").val();
			if(view=="detail"){//자세히보기
				if($(this).prop("checked")){
					$("#detail").find(".check").each(function() {
						$(this).prop("checked", true);
					})
				}else {
					$("#detail").find(".check").each(function() {
						$(this).prop("checked", false);
					})
				}
			} else if(view=="simply"){//간단히보기
				if($(this).prop("checked")){
					$("#simply_list_jm").find(".check").each(function() {
						$(this).prop("checked", true);
					})
				}else {
					$("#simply_list_jm").find(".check").each(function() {
						$(this).prop("checked", false);
					})
				}
			}
			
		})
		
		$(".quantity_up_jm").click(function() {
			var target = $(this).children("input").val();
			var value = $(this).parent().parent().find("input[id='"+target+"']").val();
			$(this).parent().parent().find("input[id='"+target+"']").val(Number(value)+1);
		})
		$(".quantity_down_jm").click(function() {
			var target = $(this).children("input").val();
			var value = $(this).parent().parent().find("input[id='"+target+"']").val();
			if(value!=1){
				$(this).parent().parent().find("input[id='"+target+"']").val(Number(value)-1);
			}
		})
		
		//검색기능 제민
		
		var category="";
		
		$("#search_title").focus(function () {
			searchList=[];
			category=$("#search_category").val();
			$.ajax({
		          url : "searchTitle.do?category="+category,
		          type: "post",
		          dataType : "json",
		          success : function(data){
		        	  searchTitle(data)
		          }
		    });
		})
		
		$("#search_category").change(function () {
			var value = $(this).val();
			$("#searchFrom").find("input[name='path']").val(value);
			$("#searchFrom").find("input[name='category_path']").val(value);
		})
		
	})
	var searchList=[];
	function searchTitle(data){
		var str;
		for(var i=0; i<data.length; i++){
			searchList.push(data[i]);
	   	}
		  
		$("#search_title").autocomplete({
			source : function(request, response) {
				var request = $.ui.autocomplete.filter(searchList, request.term);
				  
				response(request.slice(0,10));
			},
			autoFocus : true
		});
	}
	
	function cart(isbn, mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "cart.do?isbnList="+isbn+"&quantityList="+quantity);
	}
	
	function payment(isbn, mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "payment.do?isbn="+isbn+"&quantity="+quantity);
	}
	
	function wishList(isbn, mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var quantity = $("input[id='"+isbn+"']").val();
		alert(isbn);
		$(location).attr("href", "wishListInsert.do?isbn="+isbn);
	}
	
	function wishListAll(mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var view = $("#view_jm").val();
		var isbnList="";
		if(view=="detail"){
			$("#detail").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
				}
			})
		} else if(view=="simply"){
			$("#simply_list_jm").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
				}
			})
		}
		if(mbId!=null) $(location).attr("href", "wishListInsert.do?isbnList="+isbnList);
	}
	
	function cartAll(mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var view = $("#view_jm").val();
		var isbnList="";
		var quantityList="";
		if(view=="detail"){
			$("#detail").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
					quantityList += $(this).parent().find("input[id='"+isbn+"']").val()+"/";
				}
			})
		} else if(view=="simply"){
			$("#simply_list_jm").find(".check").each(function() {
				if($(this).prop("checked")==true) {
					var isbn = $(this).val();
					isbnList += isbn;
				}
			})
		}
		if(mbId!=null) $(location).attr("href", "cart.do?isbnList="+isbnList+"&quantityList="+quantityList);
	}