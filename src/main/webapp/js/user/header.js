
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
	
/*	$("#search_mh").keydown(function() {
		var url="searchHeader.do";
		sendRequest("get", url, fromServer, null);
	});
	
	function fromServer(){
		if(xhr.readyState==4 && xhr.status==200){
			var obj=JSON.parse(xhr.responseText);
			alert(obj.length);
		}
	}*/
	
	  $.ajax({
          url : "searchHeader.do",
          type: "post",
          dataType : "json",
          success : function(data){
        	  setArrStr(data)
          }
      });
	
	  function setArrStr(data){
		  var str;
		  
		  for(var i=0; i<data.length; i++){
			  arrStr.push(data[i]);
    	  }
		  
		 /* alert(arrStr.length);*/
		  
		  $("#search_mh").autocomplete({
			  source : function(request, response) {
				  var request = $.ui.autocomplete.filter(arrStr, request.term);
				  
				  response(request.slice(0,10));
			  },
			  autoFocus : true
		  });
		  
		 // $(".ui-menu-item").eq(10).nextAll("li").hide();
	  }
});