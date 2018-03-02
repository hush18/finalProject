$(function () {
		$( ".star_rating a" ).click(function() {
		    $(this).parent().children("a").removeClass("on");
		    $(this).addClass("on").prevAll("a").addClass("on");
		    
		    var onCount = $(this).parent().find(".on").length;
		    $("input[name='grade']").val(onCount);
		    
		    return false;
		});
		
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
		
		$(".imgBook_jm").mousemove(function() {
			$(this).addClass("hover");
		}).mouseout(function() {
			$(this).removeClass("hover");
		}).click(function() {
			var parent = $(this).parent();
			var isbn = parent.find("input[name='isbn']").val();
			var pageNumber = parent.find("input[name='pageNumber']").val();
			var category_path = parent.find("input[name='category_path']").val();
			
			$(location).attr("href", "bookInfo.do?isbn="+isbn+"&pageNumber="+pageNumber+"&category_path="+category_path);
		})
		
		$("#reviewStandard").next(".info_review_centent_table").attr("style","margin-top:0px;");
		
		var reviewLength = $(".info_review_centent_table").length;
		var pageNumber = reviewLength/10;
		$("#page_ul_jm").find("li").remove();
		var pageText = "";
		for(var i=0; i<pageNumber; i++){
			pageText+="<li onclick='paging("+(i+1)+")'>"+(i+1)+"</li>";
		}
		$("#page_ul_jm").html(pageText);
		
		
		if($(".info_review_centent_table").length>10){
			$(".info_review_centent_table:eq(10)").nextAll(".info_review_centent_table").hide();
		}
		
		$("#reviewInsert").click(function () {
			var parent = $("#reviewValueDiv");
			var isbn = parent.find("input[name='isbn']").val();
			var id = parent.find("input[name='id']").val();
			if(id==null||id==""){
				alert("로그인이 필요한 서비스 입니다.");
				return false;
			}
			var grade = parent.find("input[name='grade']").val();
			var content = parent.find("textarea[name='content']").val().replace("\n","<br>");
			if(content==null||content==""){
				alert("리뷰 내용을 입력해 주십시오");
				return false;
			}
			parent.find("textarea[name='content']").val("");
			//alert(isbn+", "+id+", "+grade+", "+content);
			var params = "isbn="+isbn+"&id="+id+"&grade="+grade+"&content="+content;
			
			$.ajax({
		          url : "reviewInsert.do",
		          type: "post",
		          data: params,
		          dataType : "json",
		          success : function(data) {
		        	  reviewInsert(data);
		    	}
		    });
		})
	})
	
	function reviewInsert(data) {
		var id = data[0].id;
		var grade = data[0].grade;
		var content = data[0].content;
		var writer_date = data[0].writer_date;
		
		//alert(writer_date+", "+id+", "+grade+", "+content);
		
		var reviewList = document.getElementsByClassName("info_review_centent_table")[0];
		
		var gradeText="";
		for(var i=0; i<grade; i++){
			gradeText+="<label class='on'>★</label>";
		}
		for(var i=grade;i<5;i++){
			gradeText+="<label class=''>★</label>";
		}
		
		var newReviewText = "<div class='info_review_centent_table'>"+
								"<div>"+
									"<div class=''>"+id+"</div>"+
									"<div style='word-break: break-all; overflow: hidden; white-space: pre-line;' class='test'>"+content+"</div>"+
									"<div>"+
										"<p class='star_rating'>"+gradeText+"</p>"+
									"</div>"+
									"<div>"+writer_date+"</div>"+
								"</div>"+
							"</div>";
							
		$(reviewList).before(newReviewText);//새로운 리뷰 출력
		
		//새로운 리뷰를 포함한 출력된 리뷰의 출력갯수 조절
		var prev = $("#reviewStandard").prevAll(".info_review_centent_table").length;
		var next = $("#reviewStandard").nextAll(".info_review_centent_table").length;
		var hideNumber = next+prev;
		if(hideNumber>10){
			//alert(hideNumber);
			$(".info_review_centent_table:eq(10)").nextAll(".info_review_centent_table").hide();
		}
		
		$("#page_ul_jm").find("li").remove();
		var pageNumber = hideNumber/10;
		var pageText = "";
		for(var i=0; i<pageNumber; i++){
			pageText+="<li onclick='paging("+(i+1)+")'>"+(i+1)+"</li>";
		}
		$("#page_ul_jm").html(pageText);
		
		paging(1);
	}
	
	
	function cart(isbn,mbId) {//mbId
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "cart.do?isbn="+isbn+"&quantity="+quantity);
	}
	
	function payment(isbn,mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "payment.do?isbn="+isbn+"&quantity="+quantity);
	}
	
	function wishList(isbn,mbId) {
		if(mbId==null||mbId==""){
			alert("로그인이 필요한 서비스입니다.");
			return false;
		}
		var quantity = $("input[id='"+isbn+"']").val();
		
		$(location).attr("href", "wishListInsert.do?isbn="+isbn);
	}