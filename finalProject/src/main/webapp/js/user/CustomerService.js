/**
 * 
 */

$(function(){
	$(".jlist_ej").each(function(i,e){
		index = i+1;
		$(".number"+index+"_ej").click(function(){
			$(".top_content_ej").remove();
			var div = $("<div class='top_content_ej'></div>").append("<p>내용</p>");
			$(this).after(div);
		});
	});
	
	$(".list_ej").each(function(i,e){
		index = i+1;
		$(".number"+index+"_ej").click(function(){
			$(".top_content_ej").remove();
			var div = $("<div></div>");
			var div2 = $("<div class='top_content_ej'></div>").append("<p>제목</p><p>내용</p>");
			div.append(div2);
			// if("답변이 있는 경우")
				var divap = $("<div class='top_content_ej'></div>").css("overflow","hidden");
				var p = $("<p>답변</p>").after("<p class='date_ej'>답변 날짜: 2018-02-13</p>");
				div.append(divap.append(p));
			
			$(this).after(div);
		});
	});
});
