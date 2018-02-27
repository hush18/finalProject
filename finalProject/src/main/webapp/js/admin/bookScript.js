/**
 * 
 
$(function() {
	$(".quantity_up_jm").click(function() {
		var target = $(this).children("input").val();
		var value = $("#quantity_value_jm_"+target).val();
		$("#quantity_value_jm_"+target).val(Number(value)+1);
	})
	$(".quantity_down_jm").click(function() {
		var target = $(this).children("input").val();
		var value = $("#quantity_value_jm_"+target).val();
		if(value!=1){
			$("#quantity_value_jm_"+target).val(Number(value)-1);
		}
	})
})
*/