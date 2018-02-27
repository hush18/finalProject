/**
 * 
 */

$(function(){
	$(".checkbox_ej").hide();
	
	$("#list_ej").click(function(){
		$(this).css("display","none");
		$("#list_ej2").css("display","inline-block");
		$(".checkbox_ej").show();
		$(".child_ej").attr("colspan",3);
	});
	
	$("#list_ej2").click(function(){
		if($("input[type=checkbox]").is(":checked")==false){
			alert("하나 이상 선택해 주세요.");
			return false;
		}
	});
	
	$("#reset_ej").click(function(){
		$("#textContent_ej").val('');
	});
	
	$(".floatred_ej").hide();
	
	$(".parent").each(function(i,e){
		$("#plus_ej"+i).click(function(){
			$(this).hide();
			$("#minus_ej"+i).show();
			
			var tr = $("<tr class='child content_ej"+i+"'></tr>");
			tr.append("<td class='child_ej' colspan='2'><span>내용</span></td>");
			tr.append("<td colspan='4'><span>문의내용</span></td>");
			
			$(this).closest("tr").after(tr);
			
//			if(답변이 있는 경우~~)
			var tr2 = $("<tr class='child content_ej"+i+"'></tr>");
			tr2.append("<td class='child_ej' colspan='2'><span>답변</span></td>");
			var td = $("<td colspan='4'></td>");
			var span = $("<span></span>").html("답변내용~~~~");
			td.append(span);
			tr2.append(td);
			
			var div = $("<div></div>");
			var span2 = $("<span class='date_ej'></span>").html("답변 날짜: " + "날짜");
			span2.append("<a class='paint-brush_ej' data-toggle='modal' data-target='.bs-example-modal-lg3'><i class='fa fa-paint-brush brush_ej'></i></a>");
			div.append(span2);
			td.append(div);
			
			tr.after(tr2);
			
		});
	});
	
	$(".parent").each(function(i,e){
		$("#minus_ej"+i).click(function(){
			$(this).hide();
			$("#plus_ej"+i).show();
			
			$(".content_ej"+i).remove();
		});
	});
});