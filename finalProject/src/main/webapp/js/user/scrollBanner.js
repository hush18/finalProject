var scrollHeight = 175;		/*스크롤배너가 따라오기 시작하는 위치*/
$(function(){
	$(window).scroll(function(){
		if($(this).scrollTop()>scrollHeight){
			$("#serviceWrap").addClass("ss_fixed");
		}else{
			$("#serviceWrap").removeClass("ss_fixed");
		}
	});
});
