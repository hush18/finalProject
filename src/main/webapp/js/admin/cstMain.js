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

	$(".column-title").click(function() {
		$(".floatgreen_ej").show();
		$(".floatred_ej").hide();
//		$(".checkbox_ej").hide();
		$("#list_ej").show();
		$("#list_ej2").hide();
	});

});

function plus(content, id, adminContent, date, counselNumber) {
	$("#plus_ej" + id).hide();
	$("#minus_ej" + id).show();

	var tr = $("<tr class='child content_ej content_ej" + id + "'></tr>");
	tr.append("<td class='child_ej' colspan='2'><span>내용</span></td>");
	tr.append("<td colspan='6'><span>" + content + "</span></td>");

	$("#plus_ej" + id).closest("tr").after(tr);

	if (adminContent != "") {
		var tr2 = $("<tr class='child content_ej content_ej" + id + "'></tr>");
		tr2.append("<td class='child_ej' colspan='2'><span>답변</span></td>");
		var td = $("<td colspan='6'></td>");
		var span = $("<span></span>").html(adminContent);
		td.append(span);
		tr2.append(td);

		var div = $("<div></div>");
		var span2 = $("<span class='date_ej'></span>").html("답변 날짜: " + date);
		span2.append("<a class='paint-brush_ej' data-toggle='modal' data-target='.bs-example-modal-lg3'><i class='fa fa-paint-brush brush2_ej'></i></a>");
		div.append(span2);
		td.append(div);

		tr.after(tr2);
		
		$(".Awrite").html(adminContent.replace(/<br\/\>/g,"\r\n"));
	}

	$(".content_ej").not($(".content_ej" + id)).remove();
	$(".floatred_ej").not($("#minus_ej" + id)).css("display", "none");
	$(".floatgreen_ej").not($("#plus_ej" + id)).css("display", "inline");
	
	$("input[name=counsel_number]").remove();
	$(".modal-body_ej").after(
			"<input type='hidden' name='counsel_number' value='"+ counselNumber + "'/>");
}

function minus(id) {
	$(".content_ej" + id).remove();
	$("#minus_ej" + id).hide();
	$("#plus_ej" + id).show();
}

function insert(counselNumber, Acon) {
	$("input[name=counsel_number]:eq(0)").remove();
	$("#cstInsert").after("<input type='hidden' name='counsel_number' value='"+ counselNumber + "'/>");
	
	if(Acon!=""){
		alert("이미 등록된 답변이 있습니다.");
		return false;
	}
	
	if(Acon==""){
		$(".cstNumber"+counselNumber).removeAttr("data-toggle");
		$(".cstNumber"+counselNumber).attr("data-toggle","modal");
	}
}

function adminCstDelete() {
	var checked = [];
	$('input:checkbox[name="table_records"]').each(function() {
		$('input:checkbox[name="table_records"]').is(":checked") == true; // checked
		// 처리
		if (this.checked) {// checked 처리된 항목의 값
			checked.push(this.value);
		}
	});
	$(location).attr("href","adminCstDeleteOk.do?checked=" + checked);
}

function cstEdit(obj){
	if(obj.admin_content.value == ""){
		alert("내용을 입력해 주세요.");
		obj.admin_content.focus();
		return false;
	}
	
	var limit = /["']/;
	admin_content = obj.admin_content.value.match(limit);
	if(admin_content!=null){
		alert("특수문자(\",\')는 입력할 수 없습니다.");
		obj.admin_content.focus();
		return false;
	}
}