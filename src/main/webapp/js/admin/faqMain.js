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
			tr.append("<td colspan='3'><span></span></td>");
			
			$(this).closest("tr").after(tr);
			
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