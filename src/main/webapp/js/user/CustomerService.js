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
		var div2 = $("<div class='top_content_ej'></div>").append("<p>내용&nbsp;&nbsp;<i class='far fa-hand-point-right'></i>&nbsp;&nbsp;"+con+"</p>");
		div.append(div2);
		
		if(Adate!=null){
			var divap = $("<div class='top_content_ej'></div>").css("overflow","hidden");
			var p = $("<p>&nbsp;&nbsp;<i class='far fa-registered'></i></p>&nbsp;&nbsp;<p>"+Acon+"</p>").after("<p class='date_ej'>답변 날짜:nbsp;"+Adate+"</p>");
			div.append(divap.append(p));
			$(".number"+i+"_ej").parent().after(div);
		}
}

