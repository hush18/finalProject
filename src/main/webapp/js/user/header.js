
$(function() {
	var arrStr=[];
	/*var arrStr = [ "치킨", "퇴근", "칼퇴", "산책", "마운틴북", "mountainbook", "apple",
			"callme", "lipstick", "볼펜", "집", "저녁", "식사", "목요일", "주말", "불타는금요일",
			"hungry", "americano" ];*/
	
	/*$.getJSON("export.json", function(data) {
		alert(data.items[1].title);
		for(var i=0; i<data.items.length; i++){
			arrStr.push(data.items[i].title);
		}
		alert(arrStr.length);
	});*/
	
	function toServer(root){
		var url="searchHeader.do";
		
		sendRequest("get", url, fromServer, null);
	}

	$("#search_mh").autocomplete({
		source : arrStr,
		minLength: 2,
		autoFocus : true
	});
});