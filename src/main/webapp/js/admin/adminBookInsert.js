$(function() {
	$("#writer_search").click(function() {
		var name = $("#name").val();
		
		window.open("adminWriterSearch.do?name="+name, "", "width=570, height=600");
		return false;
	})
	
	$("#category").change(function () {
		$("input[name='category_number']").val($(this).val());
	})
	
	$("#image_path").change(function () {
		$("#imageView").attr("src",$(this).val());
	})
})