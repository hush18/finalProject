$(function() {
	$("#add_address").click(function() {
		$(".btn1").toggle();
		$(".btn2").toggle();
		$(".address_list").toggle();
		$(".add_addressList").toggle();
	});
	
	$("#reset_btn").click(function() {
		$(".btn1").toggle();
		$(".btn2").toggle();
		$(".address_list").toggle();
		$(".add_addressList").toggle();
	});
	
	$("#cancel_btn").click(function() {
		$(opener.document).find("input:radio[value='normal']").prop("checked", false);
		window.close();
	});
});