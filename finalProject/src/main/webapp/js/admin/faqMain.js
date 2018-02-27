/**
 * 
 */

$(function() {
	$(".checkbox_ej").hide();

	$("#list_ej").click(function() {
		$(this).css("display", "none");
		$("#list_ej2").css("display", "inline-block");
		$(".checkbox_ej").show();
		$(".child_ej").attr("colspan", 3);
	});

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

	var consulting = [ "member", "product", "payment", "cancel", "order",
			"delivery", "saving" ];
	// $.each(consulting, function(i, e) {
	// $("." + e).hide();
	// });
	// $(".member").show();

	$("#myTab").children().click(function() {
		var category = $(this).children().attr('id');
		// $("input[name=inputdata]").val(category);
		// var inputdata = $("input[name=inputdata]").val();
		// var category2 = '<c:set var="category" value="${'+inputdata+'}"/>';
		// $("input[name=inputdata]").children().remove();
		// $("input[name=inputdata]").append(category2);
		$("." + category).show();
		$.each(consulting, function(i, e) {
			if (e != category) {
				$("." + e).hide();
			}
		});
		$(".child").hide();
		$(".floatgreen_ej").show();
		$(".floatred_ej").hide();
		$("#list_ej").show();
		// $("#list_ej2").hide();
		// $(".checkbox_ej").hide();
		// $.ajax({
		// url : "adminFaqMain.do",
		// type : "get",
		// datatype : "html",
		// success : function(data) {
		// var renewURL = location.href;
		// //현재 주소 중 page 부분이 있다면 날려버린다.
		// alert(renewURL);
		// renewURL = renewURL.replace(/\pageNumber=([0-9]+)/ig,'');
		// //
		// // //새로 부여될 페이지 번호를 할당한다.
		// // // page는 ajax에서 넘기는 page 번호를 변수로 할당해주거나 할당된 변수로 변경
		// renewURL += 'pageNumber='+1;
		// //
		// // //페이지 갱신 실행!
		// history.pushState(null, null, renewURL);
		// }
		// });
	});
	$(".dataTables_length").hide();
});

function adminFaqUpdate(faq_number, pageNumber) {
	$(location).attr(
			"href",
			"adminFaqUpdate.do?faq_number=" + faq_number + "&pageNumber="
					+ pageNumber);
}

function adminFaqDelete(pageNumber) {
	var checked = [];
	$('input:checkbox[name="table_records"]').each(function() {
		$('input:checkbox[name="table_records"]').is(":checked") == true; // checked
		// 처리
		if (this.checked) {// checked 처리된 항목의 값
			checked.push(this.value);
		}
	});
	$(location).attr(
			"href",
			"adminFaqDeleteOk.do?checked=" + checked + "&pageNumber="
					+ pageNumber);
}

function adminFaqPage(pageNumber) {
	$(location).attr(
			"href",
			"adminFaqMain.do?pageNumber=" + pageNumber + "&category="
					+ category);

	// var param = "pageNumber=" + pageNumber;
	// $.ajax({
	// url : "adminFaqMain.do",
	// type : "get",
	// data : param,
	// datatype : "html",
	// success : function(data) {
	// var renewURL = location.href;
	// //현재 주소 중 page 부분이 있다면 날려버린다.
	// alert(renewURL);
	// renewURL = renewURL.replace(/\pageNumber=([0-9]+)/ig,'');
	// //
	// // //새로 부여될 페이지 번호를 할당한다.
	// // // page는 ajax에서 넘기는 page 번호를 변수로 할당해주거나 할당된 변수로 변경
	// renewURL += 'pageNumber='+pageNumber;
	// //
	// // //페이지 갱신 실행!
	// history.pushState(null, null, renewURL);
	// }
	// });
}

function child(content, id) {
	// $(".parent").each(
	// function(i, e) {
	// $("#plus_ej" + i).click(
	// function() {
	// $(this).hide();
	// $("#minus_ej" + i).show();
	// $(".content_ej" + i).show();
	// var tr = $("<tr class='child content_ej" + i
	// + "'></tr>"); //
	// tr.append("<td class='child_ej' colspan='2'><span>"
	// + content + "</span></td>"); //
	// tr.append("<td colspan='3'><span></span></td>"); // //
	// $(this).closest("tr").after(tr);
	// });
	// });
	//
	// $(".parent").each(function(i, e) {
	// $("#minus_ej" + i).click(function() {
	// $(this).hide();
	// $("#plus_ej" + i).show();
	//
	// $(".content_ej" + i).remove();
	// });
	// });
	var IdBunho = "";
	var splitId = id.split("-");
	IdBunho = splitId[1];
	$("#plus_ej-" + IdBunho).toggle();
	$("#minus_ej-" + IdBunho).toggle();

	$(".content_ej" + IdBunho).toggle();
	$(".faqchild_ej").not($(".content_ej" + IdBunho)).css("display", "none");
	$(".floatred_ej").not($("#minus_ej-" + IdBunho)).css("display", "none");
	$(".floatgreen_ej").not($("#plus_ej-" + IdBunho)).css("display", "inline");

	var tr = $("<tr class='child content_ej" + IdBunho + "'></tr>");
	tr.append("<td></td>").css("display", "none");
	tr.append("<td></td>").css("display", "none");
	tr.append("<td class='child_ej' colspan='2'><span>" + content + "</span></td>");
	tr.append("<td colspan='3'><span></span></td>");
	$("#plus_ej-" + IdBunho).closest("tr").after(tr);
}