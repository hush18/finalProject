/**
 * 
 */

$(function(){
	var aa;
	$(".title_mh").click(function(){
		$(this).siblings(".sub_mh").toggle("fast");
		$(this).find("img").toggle();
		$(".category_mh .sub_mh").not($(this).siblings(".sub_mh")).hide("fast");
		//------------------------------------------------------
		$(".title_mh").not($(this)).find("img[src='images/up.png']").hide();
		$(".title_mh").not($(this)).find("img[src='images/down.png']").show();
		/*$(".category_mh .sub_mh").not($(this)).find("img[src='images/up.png']").hide();
		$(".category_mh .sub_mh").not($(this)).find("img[src='images/down.png']").show();*/
	});
	$(".faq_sc").click(function(){
		location.href="CustomerService_main.do";
	});
	$(".consulting_sc").click(function(){
		location.href="CustomerService_consulting.do";
	});
});
