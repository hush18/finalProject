/**
 * 
 */

$(function(){
	$("#orderSearch").click(function(){
		window.open("CustomerService_order_search.do","","width=500 ,height=450");
	});
	
	$("#questionSearch").click(function(){
		window.open("CustomerService_question_search.do","","width=500 ,height=450");
	});
	
	$(".consulting_sub2_1_ej").hide();
	$(".conpop1_ej").hide();
	$(".conpop2_ej").hide();
	
	var consulting = ["member","product","payment","cancel","order","delivery","saving"];
	$(".consulting_sub2_ej input").click(function(){
		$(".consulting_sub2_1_ej").hide();
		$(".conpop1_ej").hide();
		$(".conpop2_ej").hide();
		$.each(consulting, function(i,e){
			if($(".consulting_sub2_ej input:radio[name=consulting]:checked").val()==e){
				$("#"+e).show();
				if(e=="product"){
					$(".conpop1_ej").show();
				}else if(e=="payment"||e=="cancel"||e=="order"||e=="delivery"){
					$(".conpop2_ej").show();
				}
			}
		});
	});
});