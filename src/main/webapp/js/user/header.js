
$(function() {
	var arrStr=[];

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