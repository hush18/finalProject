var root;
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
	
	$("#delete_btn").click(function() {
		var check=$(".radioInput:checked").val();
		location.href="addressDelete.do?check="+check;
	});
	
	$("#select_btn").click(function() {
		var member_zipcode=$(".radioInput:checked").parent().next().children().text();
		var member_address=$(".radioInput:checked").parent().next().next().children().text();
		var member_detail_address=$(".radioInput:checked").parent().next().next().next().children().text();
		
		$(opener.document).find("input[name='member_zipcode']").val(member_zipcode);
		$(opener.document).find("input[name='member_address']").val(member_address);
		$(opener.document).find("input[name='member_detail_address']").val(member_detail_address);
		
		self.close();
	})
	
});