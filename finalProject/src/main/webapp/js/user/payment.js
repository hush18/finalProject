var className = "";
var popupX = (window.screen.width / 2) - (540 / 2);
var popupY= (window.screen.height /2) - (250 / 2);


$(function() {
	$("input:radio[name='payment']").change(function () {
		className = $(this).val();
		//alert(className);
		$(".payment_Detail_input_yk>."+className).css("display", "table");
		$(".payment_Detail_input_yk>:not(."+className+")").css("display", "none");
	});
	
	$("input:radio[name='shipping_address']").change(function () {
		//alert($(this).val());
		if($(this).val()=="normal"){
			$("button[name='find_zipcode']").css("display","none");
//			window.open("addressList.do","","width=540 ,height=450");
			window.open('addressList.do', '',',width=550 ,height=250,left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
		}
		
		if($(this).val()=="enter_new"){
			$("button[name='find_zipcode']").css("display","inline-block");
		}
		
		if($(this).val()=="member_address_same"){
			$("button[name='find_zipcode']").css("display","none");
		}
	});
	$("button[name='add_address']").click(function () {
		
	});
});