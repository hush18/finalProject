$(function() {
	$("#writer_search").click(function() {
		var name = $("#name").val();
		window.open("adminWriterSearch.do?name="+name, "", "width=570, height=600");
		return false;
	})
	
	$("#delete").click(function () {
		var isbn = $("input[name='isbn']").val()+"/";//writer_number
		var writer_number = $("input[name='writer_number']").val();
		var check = confirm("정말 삭제하시겠습니까?");
		
		if(check){
			$(location).attr("href", "adminBookDelete.do?isbn="+isbn+"&writer_number="+writer_number);
		}
		
		return false;
	})
	
	$("#category").change(function () {
		$("input[name='category_number']").val($(this).val());
	})
	
	$("#image_path").change(function () {
		$("#imageView").attr("src",$(this).val());
	})
})