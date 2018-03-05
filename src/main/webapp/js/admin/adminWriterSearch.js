$(function() {
	var searchList=[];
	
	$.ajax({
        url : "searchWriter.do",
        type: "post",
        dataType : "json",
        success : function(data){
        	var str;
        	for(var i=0; i<data.length; i++){
        		searchList.push(data[i]);
           	}
        	  
        	$("#name").autocomplete({
        		source : function(request, response) {
        			var request = $.ui.autocomplete.filter(searchList, request.term);
        			  
        			response(request.slice(0,10));
        		},
        		autoFocus : true
        	});
        }
  });
})

function searchWriter(data){
	var str;
	for(var i=0; i<data.length; i++){
		searchList.push(data[i]);
   	}
	  
	$("#name").autocomplete({
		source : function(request, response) {
			var request = $.ui.autocomplete.filter(searchList, request.term);
			  
			response(request.slice(0,10));
		},
		autoFocus : true
	});
}