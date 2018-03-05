$(function(){
	$(".client_mh > .title_mh").trigger('click');
});

function faqTTList(i, con) {
	$(function(){
		$(".number"+i+"_ej").each(function(i,e){
			index = i+1;
			$(".top_content_ej").remove();
			var div = $("<div class='top_content_ej'></div>").append("<p>" + con + "</p>");
			$(this).after(div);
		});
	});
}

function faqList(i, con, Acon) {
		$(".number"+i+"_ej").each(function(i,e){
			index = i+1;
			$(".top_content_ej").remove();
			var div = $("<td class='top_content_ej' colspan='3' style='width: 371px;'></td>").append("<p>" + con + "</p>");
			$(this).after(div);
		});
}

function cstReply(i,con, Acon, Adate){
	$(".top_content_ej").remove();
		var div = $("<div></div>");
		var div2 = $("<div class='top_content_ej'></div>").append("<p>"+con+"</p>");
		div.append(div2);
		
		if(Acon!=""){
			var divap = $("<div class='top_content_ej'></div>").css("overflow","hidden");
			var p = $("<p>"+Acon+"</p>").append("<p class='date_ej'>답변 날짜:&nbsp;"+Adate+"</p>");
			div.append(divap.append(p));
		}
		$(".number"+i+"_ej").parent().after(div);
}

function content(i,con){
	$(".jlist_ej").each(function(index,e){
		$(".top_content_ej").remove();
		var div = $("<div class='top_content_ej'></div>").append("<p>"+con+"</p>");
		$(".number"+i+"_ej").parent().after(div);
	});
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

$(function(){
	$(".sub_category_ej").hide();
	var nowAddress = unescape(encodeURIComponent(location.href));
    nowAddress = decodeURIComponent(nowAddress)
	nowAddress = nowAddress.slice(nowAddress.indexOf('=') + 1);
    var param = nowAddress.split("&");
    
    var consulting = ["회원","상품","입금/결제","취소/교환/환불","주문","배송","적립"];
    $.each(consulting, function(i,e){
    	if(param[0]==e){
    		$("#"+e.substring(0,2)).show();
    	}
    });
});