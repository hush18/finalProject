var className = "";
var popupX = (window.screen.width / 2) - (540 / 2);
var popupY= (window.screen.height /2) - (550 / 2);


$(function() {
	//엔터키 막기
	$("input[type='text']").keydown(function() {
		if (event.keyCode === 13) {
	        event.preventDefault();
	    }
	});
	
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
			window.open('addressList.do', '',',width=550 ,height=550,left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
			$("input[name='member_detail_address']").focus();
		}
		
		if($(this).val()=="enter_new"){
			$("button[name='find_zipcode']").css("display","inline-block");
		}
		
		if($(this).val()=="member_address_same"){
			$("button[name='find_zipcode']").css("display","none");
		}
	});
	
	$("input[name='payment']").on("click",function(){
		var id=$(this).val();
		if(id=="credit_card"){
			$("input[name='credit_card_4']").change(function() {
				var card=$("div[id='"+id+"'] select[name='card']").val();
				var fix_inst=$("div[id='"+id+"'] select[name='fix_inst']").val();
				var credit_card_number=$("input[name='credit_card_1']").val()+"-"+$("input[name='credit_card_2']").val()+"-"+$("input[name='credit_card_3']").val()+"-"+$("input[name='credit_card_4']").val();
				
				var credit_card=card+","+fix_inst+","+credit_card_number;
				$("input[name='credit_card']").attr("value",credit_card);
			})
		}else if (id=="phone_payment") {
			var agency;
			$("input[name='news_agency']").on("click",function(){
				agency=$(this).val();
			});
			
			$("div[id='phone_payment'] input[name='Resident_registration_number_second']").focusout(function() {
				var phone_payment_first=$("select[name='phone_payment_first']").val();
				var phone_payment_second=$("input[name='phone_payment_second']").val();
				var phone_payment_second=$("input[name='phone_payment_third']").val();
				
				var Resident_registration_number_first=$("div[id='phone_payment'] input[name='Resident_registration_number_first']").val();
				var Resident_registration_number_second=$("div[id='phone_payment'] input[name='Resident_registration_number_second']").val();
				
				var phone_payment=agency+","+phone_payment_first+"-"+phone_payment_second+"-"+phone_payment_second+","+Resident_registration_number_first+"-"+Resident_registration_number_second;
				
				$("input[name='phone_payment']").attr("value",phone_payment);
			});
		}else if (id=="realtime_account_transfer") {
			$("div[id='realtime_account_transfer'] input[name='Resident_registration_number_second']").focusout(function() {
				var acount=$("select[name='acountType']").val();
				var account_number=$("input[name='account_number']").val();
				var Resident_registration_number_first=$("div[id='realtime_account_transfer'] input[name='Resident_registration_number_first']").val();
				var Resident_registration_number_second=$("div[id='realtime_account_transfer'] input[name='Resident_registration_number_second']").val();
				var realtime_account_transfer=acount+","+account_number+","+Resident_registration_number_first+"-"+Resident_registration_number_second
				
				$("input[name='realtime_account_transfer']").attr("value",realtime_account_transfer);
			});
		}else if (id=="direct_deposit") {
			$("div[id='direct_deposit'] input[name='depositor']").focusout(function() {
				var acount=$("div[id='direct_deposit'] select[name='acountType']").val();
				var depositor=$("div[id='direct_deposit'] input[name='depositor']").val();
				var direct_deposit=acount+","+depositor;
				
				$("input[name='direct_deposit']").attr("value",direct_deposit);
			});
		}
	});
	
	$("#payment_submit").click(function() {
		
		if($("input[name='member_detail_address']").val()!=""){
			var receive_addr=$("input[name='member_address']").val()+" "+$("input[name='member_detail_address']").val();
			$("input[name='receive_addr']").attr("value",receive_addr)
		}
		
		$("input[name='title']").attr("value",$("div[id='title']").text());
		$("form[name='order']").submit();
	});
});


