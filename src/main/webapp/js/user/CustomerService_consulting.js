/**
 * 
 */

$(function(){
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
});

function cstPopValue(title){
	$(opener.document).find("input[name=counsel_product]").val(title);
	self.close();
}