/**
 * 
 */

$(function(){
	$(".client_mh > .title_mh").trigger('click');
	
	$("#orderSearch").click(function(){
		window.open("CustomerService_order_search.do","","width=700 ,height=500");
	});
	
	$("#questionSearch").click(function(){
		window.open("CustomerService_question_search.do","","width=700 ,height=500");
	});
	
	$(".consulting_sub2_1_ej").hide();
	$(".conpop1_ej").hide();
	$(".conpop2_ej").hide();
	
	var consulting = ["회원","상품","입금/결제","취소/교환/환불","주문","배송","적립"];
	$(".consulting_sub2_ej input").click(function(){
		$(".consulting_sub2_1_ej").hide();
		$(".conpop1_ej").hide();
		$(".conpop2_ej").hide();
		$.each(consulting, function(i,e){
			if($(".consulting_sub2_ej input:radio[name=up_category]:checked").val()==e){
				$("#"+e.substring(0,2)).show();
				if(e=="상품"){
					$(".conpop1_ej").show();
				}else if(e=="입금/결제"||e=="취소/교환/환불"||e=="주문"||e=="배송"){
					$(".conpop2_ej").show();
				}
			}
		});
	});
	
	$("input[name=up_category]").click(function(){
			$("input[name=order_number]").val("");
			$("input[name=counsel_product]").val("");
	});
	
	$(".up_search_ej").click(function(){
    	
    });
});

function cstPopValue(title,popName){
	if(popName=="product"){
		$(opener.document).find("input[name=counsel_product]").val(title);
	}else{
		$(opener.document).find("input[name=order_number]").val(title);
	}
	self.close();
}

function userCst(obj){
	if(obj.up_category.value==""){
		alert("문의유형을 선택해 주세요.");
		return false;
	}
	
	if(obj.down_category.value==""){
		alert("세부유형을 선택해 주세요.");
		return false;
	}
	
	if(obj.title.value == ""){
		alert("제목을 입력해 주세요.");
		obj.title.focus();
		return false;
	}
	
	if(obj.content.value == ""){
		alert("내용을 입력해 주세요.");
		obj.content.focus();
		return false;
	}
	
	if(obj.emailing.value==""){
		alert("답변 여부를 확인해 주세요.");
		return false;
	}
	
	if(obj.emailing.value==1){
		if(obj.email.value==""){
			alert("이메일을 작성해 주세요.");
			return false;
		}
	}
	
	var limit = /["']/;
	title = obj.title.value.match(limit);
	if(title!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.title.focus();
		return false;
	}
	
	content = obj.content.value.match(limit);
	if(content!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.content.focus();
		return false;
	}
}

function up_search(obj){
	if(obj.up_category.value=="default"){
		alert("FAQ분류를 선택해 주세요.");
		return false;
	}
	
	if(obj.search.value==""){
		alert("질문명을 입력해 주세요.");
		return false;
	}
}