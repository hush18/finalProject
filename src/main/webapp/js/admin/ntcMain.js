/**
 * 
 */

$(function() {
	$("#list_ej2").click(function() {
		if ($("input[type=checkbox]").is(":checked") == false) {
			alert("하나 이상 선택해 주세요.");
			return false;
		}
	});

	$("#reset_ej").click(function() {
		$("#textContent_ej").val('');
	});

	$(".floatred_ej").hide();
});

function adminNctDelete() {
	var checked = [];
	$('input:checkbox[name="table_records"]').each(function() {
		$('input:checkbox[name="table_records"]').is(":checked") == true; // checked
		// 처리
		if (this.checked) {// checked 처리된 항목의 값
			checked.push(this.value);
		}
	});
	$(location).attr("href", "adminNctDeleteOk.do?checked=" + checked);
}

function plus(content, id) {
	$("#plus_ej" + id).hide();
	$("#minus_ej" + id).show();

	var tr = $("<tr class='child content_ej" + id + " content_ej'></tr>");
	tr.append("<td style='display:none'></td>");
	tr.append("<td style='display:none'></td>");
	tr.append("<td class='child_ej' colspan='2'><span>내용</span></td>");
	tr.append("<td colspan='3'><span>" + content + "</span></td>");
	$("#plus_ej" + id).closest("tr").after(tr);

	$(".content_ej").not($(".content_ej" + id)).remove();
	$(".floatred_ej").not($("#minus_ej" + id)).css("display", "none");
	$(".floatgreen_ej").not($("#plus_ej" + id)).css("display", "inline");
}

function minus(id) {
	$(".content_ej" + id).remove();
	$("#minus_ej" + id).hide();
	$("#plus_ej" + id).show();
}

function nctEdit(obj){
	if(obj.title.value == ""){
		alert("제목을 입력해 주세요.");
		obj.title.focus();
		return false;
	}
	
	if(obj.content.value == ""){
		alert("내용을 입력해 주세요.");
		obj.content.focus();
		return false;
	}
	
	var limit = /["']/;
	title = obj.title.value.match(limit);
	if(title!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.title.focus();
		return false;
	}
	
	content = obj.content.value.match(limit);
	if(content!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.content.focus();
		return false;
	}
}