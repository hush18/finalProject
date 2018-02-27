/**
 * 
 */

$(function() {
	$(".fa-plus-circle").click(function() {
		$(".create_map_yk").toggle();
		var display=$(".admin_map_yk").css("display");
		if(display=="block"){
			$(".admin_map_yk").css("display","none");
		}
		$(".admin_input_yk").css("display","none");
	});
	
	$(".btn-group_yk>button").click(function () {
		$(".admin_map_yk").toggle();
		var display=$(".create_map_yk").css("display");
		if(display=="block"){
			$(".create_map_yk").css("display","none");
		}
		$(".admin_input_yk").css("display","none");
	});
	
	$("#update").click(function() {
		$(".admin_map_yk input[type=text], .admin_map_yk textarea").removeAttr("disabled");
		$("#updel_btn_group").css("display","none");
		$("#update_btn").css("display","block");
	});
	
	$("#delete").click(function() {
		$(".admin_map_yk").css("display","none");
		$(".admin_input_yk").css("display","block");
	});
	
	$("#calcel_map").click(function () {
		$(".admin_map_yk input[type=text], .admin_map_yk textarea").attr("disabled","disabled");
		$("#update_btn").css("display","none");
		$("#updel_btn_group").css("display","block");
	});
	
	$("#calcel_delete").click(function () {
		$(".admin_input_yk").css("display","none");
		$(".admin_map_yk").css("display","block");
	});
	
	$("#cancel_map").click(function(){
		$(".create_map_yk").css("display","none");
	});
	
});


















