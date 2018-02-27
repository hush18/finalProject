

$(function() {
	var arrStr = [ "치킨", "퇴근", "칼퇴", "산책", "마운틴북", "mountainbook", "apple",
			"callme", "lipstick", "볼펜", "집", "저녁", "식사", "목요일", "주말", "불타는금요일",
			"hungry", "americano" ];
	$("#search_mh").autocomplete({
		source : arrStr,
		autoFocus : true
	});
});